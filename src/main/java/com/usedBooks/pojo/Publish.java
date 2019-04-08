package com.usedBooks.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name="publish")
@Data
public class Publish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                //id

    private Integer bookId;            //书本id

    private Integer userId;            //用户id，代表是谁发布的

    private Integer status;    //1:待审，2：审核通核过

    private Integer isDrop;    //1:已下架 ，0:未下架

    private Date onshelfTime;  //上架时间

    private Date dropshelfTime; //下架时间

    private String description;//描述

    private Date updateTime;//修改时间

    private Date createTime;//创建时间

    private String remark;//发布人说

    private Integer quantity;//数量

    private Integer publishType;//类型，1:求购，2：出售，3：竞拍

    private Double price;//售价或求购价或竞拍

    private Integer bookOldState;//新旧程度

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getOnshelfTime() {
        return onshelfTime;
    }

    public void setOnshelfTime(Date onshelfTime) {
        this.onshelfTime = onshelfTime;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getDropshelfTime() {
        return dropshelfTime;
    }

    public void setDropshelfTime(Date dropshelfTime) {
        this.dropshelfTime = dropshelfTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
