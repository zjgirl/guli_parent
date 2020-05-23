package com.atguigu.eduservice.controller;


import com.atguigu.commonresult.Result;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("分页查询")
    @GetMapping("pageTeacher/{current}/{size}")
    public Result pageTeacher(@PathVariable Integer current, @PathVariable Integer size) {
        Page<EduTeacher> page = new Page<>(current, size);
        //分页数据将封装到page对象中
        teacherService.page(page, null);
        return Result.ok().data("total", page.getTotal()).data("records", page.getRecords());
    }

    @ApiOperation("条件查询带分页")
    @PostMapping("pageTeacherCondition/{current}/{size}")
    public Result pageTeacherCondition(@PathVariable Integer current, @PathVariable Integer size,
                                       @RequestBody(required = false) TeacherQuery vo) {
        Page<EduTeacher> page = new Page<>(current, size);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(vo.getName())) {
            wrapper.like("name", vo.getName());
        }
        if (!StringUtils.isEmpty(vo.getLevel())) {
            wrapper.eq("level", vo.getLevel());
        }
        if (!StringUtils.isEmpty(vo.getBegin())) {
            wrapper.ge("gmt_create", vo.getBegin());
        }
        if (!StringUtils.isEmpty(vo.getEnd())) {
            wrapper.le("gmt_create", vo.getEnd());
        }
        teacherService.page(page, wrapper);
        return Result.ok().data("total", page.getTotal()).data("records", page.getRecords());
    }

    @ApiOperation("添加教师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody EduTeacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation(("根据id查询教师"))
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return Result.ok().data("item", eduTeacher);
    }

    @ApiOperation("修改讲师")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}

