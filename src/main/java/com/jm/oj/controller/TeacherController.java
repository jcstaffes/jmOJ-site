package com.jm.oj.controller;

import com.jm.oj.service.TeacherService;
import com.jm.oj.util.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/login")
    public BasicResponse login(@RequestParam("email") String email, @RequestParam("pwd") String pwd) {
        Object data = null;

        try {
            data = teacherService.login(email, pwd);
            BasicResponse res = BasicResponse.create(data);

            return res;
        } catch (Exception e) {
            data = e.getMessage();
            BasicResponse res = BasicResponse.create(false, data);

            return res;
        }
    }

    @GetMapping("/password-panel")
    public BasicResponse panel(@RequestParam("email") String email, @RequestParam("pwd") String pwd,@RequestParam("new-pwd")String newpwd) {
        Object data = null;

        try {
            data = teacherService.panel(email,pwd,newpwd);
            BasicResponse res = BasicResponse.create(data);

            return res;
        } catch (Exception e) {
            data = e.getMessage();
            BasicResponse res = BasicResponse.create(false, data);

            return res;
        }
    }
}
