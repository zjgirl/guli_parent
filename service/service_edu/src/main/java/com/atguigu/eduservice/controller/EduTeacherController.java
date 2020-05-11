package com.atguigu.eduservice.controller;


import com.atguigu.commonresult.Result;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zijuan
 * @since 2020-05-08
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    //Restful风格
    @ApiOperation("查找所有教师")
    @GetMapping("findAll")
    public Result findAllTeacher() {
        List list = teacherService.list(null);
        return Result.ok().data("items", list);
    }

    @ApiOperation("通过id删除教师")
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}

