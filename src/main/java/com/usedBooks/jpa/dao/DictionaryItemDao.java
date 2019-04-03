package com.usedBooks.jpa.dao;

import com.usedBooks.manager.dictionaryModule.pojo.DictionaryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryItemDao extends JpaRepository<DictionaryItem,Integer> {

    List<DictionaryItem> findAllByDictId(Integer dictId);
}
