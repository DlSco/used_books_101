

package com.usedBooks.pojo;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * 
 * @table book
 * @author Vakoe
 * @date 2019年02月18日 14:06
 */
public class Book implements Serializable {
    /**
     * Database Column Remarks:
     *   书籍id
     * book.id
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   ISBN
     * book.isbn
     */
    private String isbn;

    /**
     * Database Column Remarks:
     *   书籍名称
     * book.name
     */
    private String name;

    /**
     * Database Column Remarks:
     *   作者
     * book.author
     */
    private String author;

    /**
     * Database Column Remarks:
     *   出版社
     * book.publish_house
     */
    private String publishHouse;

    /**
     * Database Column Remarks:
     *   书籍封面图片url
     * book.picture_url
     */
    private String pictureUrl;

    /**
     * Database Column Remarks:
     *   原价
     * book.original_price
     */
    private Double originalPrice;

    /**
     * Database Column Remarks:
     *   售价或者竞拍价或者求购价
     * book.price
     */
    private Double price;

    /**
     * Database Column Remarks:
     *   数量
     * book.quantity
     */
    private Integer quantity;

    /**
     * Database Column Remarks:
     *   新旧程度，1:九成新,2:八成新,3:七成新,4：六成新及以下
     * book.book_old_state
     */
    private Integer bookOldState;

    /**
     * Database Column Remarks:
     *   分类id
     * book.classification_id
     */
    private Integer classificationId;

    /**
     * Database Column Remarks:
     *   类型 1：求购 2：出售  3:出售
     * book.type
     */
    private Integer type;

    /**
     * Database Column Remarks:
     *   发布人说
     * book.remark
     */
    private String remark;

    /**
     * Database Column status:
     *   1:待审核，2：审核通过
     * book.status
     */
    private Integer status;
    /**
     * Database Column is_drop:
     *   1:已下架 ，0:未下架
     * book.is_drop
     */
    private Integer isDrop;
    /**
     * book
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
     * @return  java.lang.String  isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param java.lang.String  isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return  java.lang.String  name
     */
    public String getName() {
        return name;
    }

    /**
     * @param java.lang.String  name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  java.lang.String  author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param java.lang.String  author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return  java.lang.String  publishHouse
     */
    public String getPublishHouse() {
        return publishHouse;
    }

    /**
     * @param java.lang.String  publishHouse
     */
    public void setPublishHouse(String publishHouse) {
        this.publishHouse = publishHouse;
    }

    /**
     * @return  java.lang.String  pictureUrl
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * @param java.lang.String  pictureUrl
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * @return  java.lang.Double  originalPrice
     */
    public Double getOriginalPrice() {
        return originalPrice;
    }

    /**
     * @param java.lang.Double  originalPrice
     */
    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * @return  java.lang.Double  price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param java.lang.Double  price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return  java.lang.Integer  quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param java.lang.Integer  quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return  java.lang.Integer  bookOldState
     */
    public Integer getBookOldState() {
        return bookOldState;
    }

    /**
     * @param java.lang.Integer  bookOldState
     */
    public void setBookOldState(Integer bookOldState) {
        this.bookOldState = bookOldState;
    }

    /**
     * @return  java.lang.Integer  classificationId
     */
    public Integer getClassificationId() {
        return classificationId;
    }

    /**
     * @param java.lang.Integer  classificationId
     */
    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }

    /**
     * @return  java.lang.Integer  type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param java.lang.Integer  type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return  java.lang.String  remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param java.lang.String  remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDrop() {
        return isDrop;
    }

    public void setIsDrop(Integer isDrop) {
        this.isDrop = isDrop;
    }
}