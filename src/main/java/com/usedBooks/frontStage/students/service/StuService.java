package com.usedBooks.frontStage.students.service;

import com.usedBooks.pojo.Students;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface StuService {
    long countByStudents(Students students);

    Students getByPrimaryKey(Integer id);

    List<Students> listWithRowbounds(Students students, RowBounds rowBounds);

    int removeByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Students students);

    int updateByPrimaryKey(Students students);

    int removeByStudents(Students students);

    int save(Students students);

    int saveSelective(Students students);
}

