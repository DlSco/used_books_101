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
import com.usedBooks.pojo.Book;
import com.usedBooks.pojo.Publish;
import com.usedBooks.util.DicConstants;
import com.usedBooks.util.MyBeanUtils;
import com.usedBooks.util.UploadServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
    @Override
    public int save(Book book,Publish publish,HttpServletRequest request) {


//        String dirName = "book"+"/"+publish.getUserId()+"/"+publish.getPublishType();
//        String fileKey = "pictureUrl";
//        List<String> picUrls = uploadServiceUtil.Upload(request,fileKey,dirName);
//        book.setPictureUrl(picUrls.get(0));
        log.info("添加前的bookId：{}",book.getId());
        bookFrontMapper.insertUseGeneratedKeys(book);
        Integer bookId = book.getId();
        log.info("添加后的bookId：{}",book.getId());
        if(bookId>0){
            publish.setBookId(bookId);
            publish.setCreateTime(new Date());
            publish.setUpdateTime(new Date());
            publish.setOnshelfTime(new Date());
            publish.setDropshelfTime(new Date());
            return publishMapper.insert(publish);
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

        bookDetailVo.setClassificationName(dicConstants.getItemName("classification",bookDetailVo.getClassification()+""));
        bookDetailVo.setBookOldStateName(dicConstants.getItemName("bookOldState",bookDetailVo.getBookOldState()+""));

        if(bookDetailVo!=null){
            BrowseRecord browseRecord = new BrowseRecord();
            browseRecord.setBookId(id);
            browseRecord.setPublishType(publishType);
            browseService.addBrowse(1,browseRecord);
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
