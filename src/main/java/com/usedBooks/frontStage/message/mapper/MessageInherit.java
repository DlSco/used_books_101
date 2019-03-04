package com.usedBooks.frontStage.message.mapper;

import com.usedBooks.mapper.MessageMapper;

public interface MessageInherit extends MessageMapper {

    int deleteByBatch(Integer ids[]);
}
