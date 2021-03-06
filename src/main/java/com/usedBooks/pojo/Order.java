

package com.usedBooks.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * 
 * @table order
 * @author Vakoe
 * @date 2019年02月18日 14:06
 */
@Entity(name="order_form")
@Table(name="order_form")
@Data
public class Order implements Serializable {
    /**
     * Database Column Remarks:
     *   id
     * order.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Database Column Remarks:
     *   买家电话
     * order.buyer_phone
     */
    private String buyerPhone;

    /**
     * Database Column Remarks:
     *   卖家电话
     * order.seller_phone
     */
    private String sellerPhone;

    /**
     * Database Column Remarks:
     *   订单编号
     * order.order_number
     */
    private String orderNumber;

    /**
     * Database Column Remarks:
     *   买家用户名
     * order.buyer
     */
    private String buyer;

    /**
     * Database Column Remarks:
     *   买家id
     * order.buyer_id
     */
    private Integer buyerId;

    /**
     * Database Column Remarks:
     *   卖家用户名
     * order.seller
     */
    private String seller;

    /**
     * Database Column Remarks:
     *   卖家id
     * order.seller_id
     */
    private Integer sellerId;

    /**
     * Database Column Remarks:
     *   下单时间
     * order.time
     */
    private Date time;

    /**
     * Database Column Remarks:
     *   订单总金额
     * order.order_amount
     */
    private Double orderAmount;

    /**
     * Database Column Remarks:
     *   实付总金额
     * order.actual_amount
     */
    private Double actualAmount;

    /**
     * Database Column Remarks:
     *   送货方式
     * order.delivery_method
     */
    private String deliveryMethod;

    /**
     * Database Column Remarks:
     *   订单状态  1:已支付，2:已发货,3：已收货
     * order.state
     */
    private Integer state;

    /**
     * Database Column Remarks:
     *   0:没申请取消订单，1：已申请取消订单
     * order.is_cancel
     */
    private Integer isCancel;

    /**
     * Database Column Remarks:
     *   0：订单无效，1：订单有效,0:订单无效
     * order.is_valid
     */
    private Integer isValid;

    private Integer addressInfoId;   //收货地址表的id

    /**
     * order
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
     * @return  java.lang.String  buyerPhone
     */
    public String getBuyerPhone() {
        return buyerPhone;
    }

    /**
     * @param java.lang.String  buyerPhone
     */
    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    /**
     * @return  java.lang.String  sellerPhone
     */
    public String getSellerPhone() {
        return sellerPhone;
    }

    /**
     * @param java.lang.String  sellerPhone
     */
    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    /**
     * @return  java.lang.String  orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param java.lang.String  orderNumber
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return  java.lang.String  buyer
     */
    public String getBuyer() {
        return buyer;
    }

    /**
     * @param java.lang.String  buyer
     */
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    /**
     * @return  java.lang.Integer  buyerId
     */
    public Integer getBuyerId() {
        return buyerId;
    }

    /**
     * @param java.lang.Integer  buyerId
     */
    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * @return  java.lang.String  seller
     */
    public String getSeller() {
        return seller;
    }

    /**
     * @param java.lang.String  seller
     */
    public void setSeller(String seller) {
        this.seller = seller;
    }

    /**
     * @return  java.lang.Integer  sellerId
     */
    public Integer getSellerId() {
        return sellerId;
    }

    /**
     * @param java.lang.Integer  sellerId
     */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * @return  java.util.Date  time
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getTime() {
        return time;
    }

    /**
     * @param java.util.Date  time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return  java.lang.Double  orderAmount
     */
    public Double getOrderAmount() {
        return orderAmount;
    }

    /**
     * @param java.lang.Double  orderAmount
     */
    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * @return  java.lang.Double  actualAmount
     */
    public Double getActualAmount() {
        return actualAmount;
    }

    /**
     * @param java.lang.Double  actualAmount
     */
    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    /**
     * @return  java.lang.String  deliveryMethod
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * @param java.lang.String  deliveryMethod
     */
    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    /**
     * @return  java.lang.Integer  state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param java.lang.Integer  state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return  java.lang.Integer  isCancel
     */
    public Integer getIsCancel() {
        return isCancel;
    }

    /**
     * @param java.lang.Integer  isCancel
     */
    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    /**
     * @return  java.lang.Integer  isValid
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * @param java.lang.Integer  isValid
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}