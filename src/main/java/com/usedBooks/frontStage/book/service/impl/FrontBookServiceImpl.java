package com.usedBooks.frontStage.book.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usedBooks.exception.GlobalException;
import com.usedBooks.frontStage.book.mapper.BookFrontMapper;
import com.usedBooks.frontStage.book.mapper.PublishMapper;
import com.usedBooks.frontStage.book.pojo.BrowseRecord;
import com.usedBooks.frontStage.book.service.BrowseService;
import com.usedBooks.frontStage.book.service.FrontBookService;
import com.usedBooks.frontStage.book.vo.BookDetailVo;
import com.usedBooks.frontStage.book.vo.BookSearchVo;
import com.usedBooks.frontStage.book.vo.BookVo;
import com.usedBooks.frontStage.order.enums.PublishEnum;
import com.usedBooks.manager.auctionModule.mapper.AuctionMapper;
import com.usedBooks.pojo.Auction;
import com.usedBooks.pojo.Book;
import com.usedBooks.pojo.Publish;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.util.DateUtils;
import com.usedBooks.util.DicConstants;
import com.usedBooks.util.MyBeanUtils;
import com.usedBooks.util.UploadServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class FrontBookServiceImpl implements FrontBookService {

    @Autowired
    private BookFrontMapper bookFrontMapper;
    @Autowired
    private PublishMapper publishMapper;
    @Autowired
    private BrowseService browseService;
    @Autowired
    private UploadServiceUtil uploadServiceUtil;
    @Autowired
    private DicConstants dicConstants;
    @Autowired
    private AuctionMapper auctionMapper;
    @Override
    public int save(Book book,Publish publish,String beginTime,String endTime) {


        //判断数据库是否有相同的book
        Book tempBook = new Book();
        BeanUtils.copyProperties(book,tempBook,new String[]{ "author","publishHouse", "pictureUrl","originalPrice","classification"});
        log.info("tempBook:{}",tempBook);
       if(bookFrontMapper.selectCount(tempBook)>0) {
           throw new GlobalException(new CodeMsg(500,"存在相同的书籍"));
       }
        log.info("添加前的bookId：{}",book.getId());
        bookFrontMapper.insertUseGeneratedKeys(book);
        Integer bookId = book.getId();
        log.info("添加后的bookId：{}",book.getId());
        if(bookId>0){
            publish.setBookId(bookId);
            publish.setCreateTime(new Date());
            publish.setUpdateTime(new Date());
            publish.setOnshelfTime(new Date());
            publish.setStatus(2);
            int result = publishMapper.insertUseGeneratedKeys(publish);

            if (result>0 && publish.getPublishType() == PublishEnum.PUBLISH_AUCTION.getPublishCode()){
                Auction auction = new Auction();
                auction.setBeginTime(DateUtils.transferDateTime(beginTime));
                auction.setEndTime(DateUtils.transferDateTime(endTime));
                auction.setPublishId(publish.getId());
                return auctionMapper.insert(auction);
            }
        }
        return 0;
    }

    @Override
    public int removeBook(Integer id ) {
        Publish publish = new Publish();
        publish.setBookId(id);
        Integer result = publishMapper.delete(publish);
        if(result>0){
            return bookFrontMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public int modifyBook(Book book, Publish publish) {
        log.info("book:{}",book.toString());
        log.info("publish:{}",publish.toString());
        Integer result = bookFrontMapper.updateByPrimaryKeySelective(book);
        if(result>0){
            publish.setUpdateTime(new Date());
            return publishMapper.updateByPrimaryKeySelective(publish);
        }
        return 0;
    }

    @Override
    public PageInfo<BookVo> toList(Integer page, Integer limit, String keyword, BookSearchVo bookSearchVo
                                    ,String sname, Integer sortRule) {
        Map<String,Object> map = MyBeanUtils.beanToMap(bookSearchVo);
        map.put("sname",sname);
        map.put("sortRule",sortRule==null?"ASC":(sortRule==1?"ASC":"DESC"));
        map.put("keyword",keyword==null?null:"%"+keyword+"%");

        log.info("----:{}",map);
        if(page!=null&&limit!=null){
            PageHelper.startPage(page,limit);
        }
        List<BookVo> list = bookFrontMapper.toList(map);

        for(BookVo bookVo:list){

            bookVo.setClassificationName(dicConstants.getItemName("classification",bookVo.getClassification()+""));
            bookVo.setBookOldStateName(dicConstants.getItemName("bookOldState",bookVo.getBookOldState()+""));
        }
        return new PageInfo(list);
    }

    @Override
    public BookDetailVo toDetail(Integer id,Integer publishType) throws Exception{

        BookDetailVo bookDetailVo = bookFrontMapper.toDetail(id,publishType);

        if(bookDetailVo!=null){
            BrowseRecord browseRecord = new BrowseRecord();
            browseRecord.setBookId(id);
            browseRecord.setPublishType(publishType);
            browseService.addBrowse(1,browseRecord);

            bookDetailVo.setClassificationName(dicConstants.getItemName("classification",bookDetailVo.getClassification()+""));
            bookDetailVo.setBookOldStateName(dicConstants.getItemName("bookOldState",bookDetailVo.getBookOldState()+""));
        }


        return  bookDetailVo;
    }


    /**
     * 热门书籍列表
     * @param page
     * @param limit
     * @return
     */
    public PageInfo<BookVo> toHotBookList(Integer page, Integer limit){

        if(page!=null && limit!=null){
            PageHelper.startPage(page,limit);
        }
        List<BookVo> list = bookFrontMapper.toHotBookList();

        for(BookVo bookVo:list){

            bookVo.setClassificationName(dicConstants.getItemName("classification",bookVo.getClassification()+""));
            bookVo.setBookOldStateName(dicConstants.getItemName("bookOldState",bookVo.getBookOldState()+""));
        }
        return new PageInfo(list);
    }
}
