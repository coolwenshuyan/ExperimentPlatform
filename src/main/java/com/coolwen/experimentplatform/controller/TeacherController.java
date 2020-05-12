package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.TeacherRepository;
import com.coolwen.experimentplatform.model.Teacher;
import com.coolwen.experimentplatform.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/teachers")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    TeacherService teacherService;

    /**
     * 列表查询
     * @param model
     * @param pageNum
     * @return
     */

    @GetMapping(value = "/list")
    public String TeacherList(Model model, @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum,3);
        Page<Teacher> page = teacherRepository.findAll(pageable);
        model.addAttribute("teacherPageInfo",page);
        return "teacher/list";
    }
    /**
     * 删除
     */
    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id){
        teacherService.delete(id);
        return "redirect:/teachers/list";
    }
    /**
     * 添加
     */
    //跳转到add页面
    @GetMapping(value = "/add")
    public String TeacherAdd(){
        return "/teacher/add";
    }
    @PostMapping(value = "/add")
    public String add(Teacher teacher){
        teacherService.add(teacher);
        return "redirect:/teachers/list";

    }
    //进入修改界面
    @GetMapping(value = "/{id}/update")
    public String update(@PathVariable int id, Model model){
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher",teacher);
        return "teacher/update";
    }

    //完成修改操作
    @PostMapping(value = "/{id}/update")
    public String update(@PathVariable int id,Teacher teacher){
        teacher.setId(id);
        teacherService.add(teacher);


        System.out.println("修改成功");
        return "redirect:/teachers/list";
    }









}