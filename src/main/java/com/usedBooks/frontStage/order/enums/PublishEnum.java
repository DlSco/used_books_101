package com.usedBooks.frontStage.order.enums;

public enum PublishEnum {

    PUBLISH_BUY("求购",1),
    PUBLISH_SELL("出售",2),
    PUBLISH_AUCTION("竞拍",3);

    private  String publishName;

    private Integer publishCode;

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    public Integer getPublishCode() {
        return publishCode;
    }

    public void setPublishCode(Integer publishCode) {
        this.publishCode = publishCode;
    }

    PublishEnum(String publishName, Integer publishCode) {
        this.publishName = publishName;
        this.publishCode = publishCode;
    }
}
