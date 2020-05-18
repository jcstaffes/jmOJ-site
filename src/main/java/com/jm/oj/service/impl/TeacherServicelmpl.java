package com.jm.oj.service.impl;

import com.jm.oj.dao.QuestionDOMapper;
import com.jm.oj.dataobject.QuestionDO;
import com.jm.oj.dataobject.StudentDO;
import com.jm.oj.dataobject.TeacherDO;
import com.jm.oj.dao.TeacherDOMapper;
import com.jm.oj.service.TeacherService;
import com.jm.oj.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TeacherServicelmpl implements TeacherService {
    @Autowired
    private TeacherDOMapper teacherDOMapper;
    private QuestionDOMapper questionDOMapper;

    @Override
    public String registration(String email, String pwd) throws Exception {
        //在数据表中查找用户是否已经存在
        if (teacherDOMapper.selectByEmail(email)!=null)
            return "student already exist";
        else
        {
            //产生随机数作为id
            //1是用户组超级管理员（教师）
            Random r=new Random();
            int id=r.nextInt(999);
            teacherDOMapper.insertByEmailPwd(email,pwd,id,"0","0","1");
            return "insert completed";
        }
    }
    @Override
    public String login(String email, String pwd) throws Exception {
        // 在数据表中查找用户是否存在
        TeacherDO teacherDO = null;
        try {
            teacherDO = teacherDOMapper.selectByEmail(email);
        } catch (Exception e) {
            throw new Exception("teacher doesn't exist");
        }


        // 密码是否正确
        if (teacherDO.getPassword().equals(pwd)) {
            // 生成token并返回
            return JWTUtil.create(email, pwd);
        } else {
            throw new Exception("wrong password");
        }
    }
    @Override
    public String panel(String email, String pwd,String newpwd) throws Exception {
        // 在数据表中查找用户是否存在
        TeacherDO teacherDO=null;
        try {
            teacherDO= teacherDOMapper.selectByEmail(email);
        } catch (Exception e) {
            throw new Exception("teacher doesn't exist");
        }


        // 密码是否正确
        if (teacherDO.getPassword().equals(pwd)) {
            //密码正确更新密码
            teacherDOMapper.updateByPwd(email,newpwd);
            return "update completed";
        } else {
            throw new Exception("wrong password");
        }
    }

    @Override
    public String uploadproblems(int id,String questionname, String content) throws Exception {
        //在数据表中查找题目是否已经存在
        questionDOMapper.insertQuestion(id,questionname,content);
        return "insert completed";

    }

    @Override
    public String updateproblems(int id, String content) throws Exception {
        QuestionDO questionDO=null;
        try {
            questionDO = questionDOMapper.selectById(id);
        } catch (Exception e) {
            throw new Exception("question doesn't exist");
        }

        try{
            questionDOMapper.updateQuestion(id,content);
            return "insert completed";
        }catch (Exception e){
            throw new Exception("error");
        }

    }
}
