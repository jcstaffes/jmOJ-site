package com.jm.oj.controller;

import com.jm.oj.service.StudentService;
import com.jm.oj.util.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/registration")
    public BasicResponse registration(@RequestParam("email") String email, @RequestParam("pwd") String pwd) {
        Object data = null;

        try {
            data = studentService.registration(email, pwd);
            BasicResponse res = BasicResponse.create(data);

            return res;
        } catch (Exception e) {
            data = e.getMessage();
            BasicResponse res = BasicResponse.create(false, data);

            return res;
        }
    }

    @GetMapping("/login")
    public BasicResponse login(@RequestParam("email") String email, @RequestParam("pwd") String pwd) {
        Object data = null;

        try {
            data = studentService.login(email, pwd);
            BasicResponse res = BasicResponse.create(data);

            return res;
        } catch (Exception e) {
            data = e.getMessage();
            BasicResponse res = BasicResponse.create(false, data);

            return res;
        }
    }

    @PostMapping("/password-panel")
    public BasicResponse panel(@RequestParam("email") String email, @RequestParam("pwd") String pwd,@RequestParam("new-pwd")String newpwd) {
        Object data = null;

        try {
            data = studentService.panel(email, pwd,newpwd);
            BasicResponse res = BasicResponse.create(data);

            return res;
        } catch (Exception e) {
            data = e.getMessage();
            BasicResponse res = BasicResponse.create(false, data);

            return res;
        }
    }
}
