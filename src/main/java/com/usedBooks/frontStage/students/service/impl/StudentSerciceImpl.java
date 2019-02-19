package com.usedBooks.frontStage.students.service.impl;

import com.usedBooks.mapper.StudentsMapper;
import com.usedBooks.pojo.Students;
import com.usedBooks.frontStage.students.service.StuService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSerciceImpl implements StuService {
    @Autowired
    private StudentsMapper studentsMapper;

    private static final Logger logger = LoggerFactory.getLogger(StudentSerciceImpl.class);

    public Students getByPrimaryKey(Integer id) {
        System.out.println(this.studentsMapper.selectByPrimaryKey(id));
        return this.studentsMapper.selectByPrimaryKey(id);
    }

    public long countByStudents(Students students) {
        long count = this.studentsMapper.countByStudents(students);
        logger.debug("count: {}", count);
        return count;
    }

    public List<Students> listWithRowbounds(Students students, RowBounds rowBounds) {
        return this.studentsMapper.selectWithRowbounds(students,rowBounds);
    }

    public int removeByPrimaryKey(Integer id) {
        return this.studentsMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Students students) {
        return this.studentsMapper.updateByPrimaryKeySelective(students);
    }

    public int updateByPrimaryKey(Students students) {
        return this.studentsMapper.updateByPrimaryKey(students);
    }

    public int removeByStudents(Students students) {
        return this.studentsMapper.deleteByStudents(students);
    }

    public int save(Students students) {
        return this.studentsMapper.insert(students);
    }

    public int saveSelective(Students students) {
        return this.studentsMapper.insertSelective(students);
    }
}
