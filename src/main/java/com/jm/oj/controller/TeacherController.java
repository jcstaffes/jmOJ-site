package com.jm.oj.controller;

import com.jm.oj.service.TeacherService;
import com.jm.oj.util.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping("/registration")
    public BasicResponse registration(@RequestParam("email") String email, @RequestParam("pwd") String pwd) {
        Object data = null;

        try {
            data = teacherService.registration(email, pwd);
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
            data = teacherService.login(email, pwd);
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
            data = teacherService.panel(email,pwd,newpwd);
            BasicResponse res = BasicResponse.create(data);

            return res;
        } catch (Exception e) {
            data = e.getMessage();
            BasicResponse res = BasicResponse.create(false, data);

            return res;
        }
    }
    @PostMapping("/uploadquestions")
    public BasicResponse uploadquestion(@RequestParam("id")int id,@RequestParam("name") String questionname,@RequestParam("content")String content){
        Object data=null;

        try{
            data=teacherService.uploadproblems(id,questionname,content);
            BasicResponse res = BasicResponse.create(data);

            return res;
        }catch (Exception e) {
            data = e.getMessage();
            BasicResponse res = BasicResponse.create(false, data);

            return res;
        }
    }

    @PostMapping("/updatequestions")
    public BasicResponse uploadquestion(@RequestParam("id")int id,@RequestParam("content")String content){
        Object data=null;

        try{
            data=teacherService.updateproblems(id,content);
            BasicResponse res = BasicResponse.create(data);

            return res;
        }catch (Exception e) {
            data = e.getMessage();
            BasicResponse res = BasicResponse.create(false, data);

            return res;
        }
    }
}
