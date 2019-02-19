

package com.usedBooks.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * 
 * @table auction
 * @author Vakoe
 * @date 2019年02月18日 14:06
 */
public class Auction implements Serializable {
    /**
     * Database Column Remarks:
     *   id
     * auction.id
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   书籍id
     * auction.book_id
     */
    private Integer bookId;

    /**
     * Database Column Remarks:
     *   发布者id
     * auction.announcer_id
     */
    private Integer announcerId;

    /**
     * Database Column Remarks:
     *   开始时间
     * auction.begin_time
     */
    private Date beginTime;

    /**
     * Database Column Remarks:
     *   结束时间
     * auction.end_time
     */
    private Date endTime;

    /**
     * auction
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return  java.lang.Integer  id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param java.lang.Integer  id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return  java.lang.Integer  bookId
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * @param java.lang.Integer  bookId
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * @return  java.lang.Integer  announcerId
     */
    public Integer getAnnouncerId() {
        return announcerId;
    }

    /**
     * @param java.lang.Integer  announcerId
     */
    public void setAnnouncerId(Integer announcerId) {
        this.announcerId = announcerId;
    }

    /**
     * @return  java.util.Date  beginTime
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * @param java.util.Date  beginTime
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return  java.util.Date  endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param java.util.Date  endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}