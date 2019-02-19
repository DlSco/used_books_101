package com.usedBooks.frontStage.students.controller;


import com.usedBooks.pojo.Students;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.usedBooks.frontStage.students.service.StuService;
@RestController
@RequestMapping(value = "/Students")
public class StudentsController {

    @Autowired
    private StuService stuService;
    @RequestMapping("/getStudent")
    public Result getStudent(Integer id){
        return Result.success(stuService.getByPrimaryKey(id));
    }
    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    public Result addStudent(Students students){
        if(stuService.save(students)>0){
            return Result.success(null);
        }else{
            return Result.error(new CodeMsg(0,"添加学生失败！"));
        }
    }
}
