package com.springmvcproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvcproject.core.service.HibernateService;
import com.springmvcproject.core.model.Student;
/**
 * Created by yektan on 14.12.2016.
 */
@Controller
public class StudentController {

    private HibernateService<Student, Integer> studentService;

    @Autowired(required=true)
    @Qualifier(value="studentService")
    public void setStudentService(HibernateService<Student, Integer> ss){
        this.studentService = ss;
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String listStudents(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("listStudents", this.studentService.list());
        return "student";
    }

    @RequestMapping(value= "/student/add", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("student") Student s){


        this.studentService.add(s);


        return "redirect:/students";

    }

    @RequestMapping("/remove/{id}")
    public String removeStudent(@PathVariable("id") int id){

        this.studentService.remove(id);
        return "redirect:/students";
    }

    @RequestMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") int id, Model model){
        model.addAttribute("student", this.studentService.getById(id));
        model.addAttribute("listStudents", this.studentService.list());
        return "student";
    }
}
