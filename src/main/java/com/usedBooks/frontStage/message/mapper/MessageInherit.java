package com.usedBooks.frontStage.message.mapper;

public interface MessageInherit extends MessageMapper {

    int deleteByBatch(Integer ids[]);
}
