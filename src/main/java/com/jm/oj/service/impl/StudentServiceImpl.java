package com.jm.oj.service.impl;

import com.jm.oj.dao.QuestionDOMapper;
import com.jm.oj.dao.StudentDOMapper;
import com.jm.oj.dataobject.QuestionDO;
import com.jm.oj.dataobject.StudentDO;
import com.jm.oj.service.StudentService;
import com.jm.oj.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDOMapper studentDOMapper;

    @Autowired
    private QuestionDOMapper questionDOMapper;

    @Override
    public String registration(String email, String pwd) throws Exception {
        //在数据表中查找用户是否已经存在
        if (studentDOMapper.selectByEmail(email)!=null)
            return "student already exist";
        else
        {
            //产生随机数作为id
            //2是用户组普通用户（学生）
            Random r=new Random();
            int id=r.nextInt(999);
            studentDOMapper.insertByEmailPwd(email,pwd,id,"2","0","0","0");
            return "insert completed";
        }
    }

    @Override
    public String login(String email, String pwd) throws Exception {
        // 在数据表中查找用户是否存在
        StudentDO studentDO=null;
        try {
            studentDO= studentDOMapper.selectByEmail(email);
        } catch (Exception e) {
            throw new Exception("student doesn't exist");
        }


        // 密码是否正确
        if (studentDO.getPassword().equals(pwd)) {
            // 生成token并返回
            return JWTUtil.create(email, pwd);
        } else {
            throw new Exception("wrong password");
        }
    }

    @Override
    public String panel(String email, String pwd,String newpwd) throws Exception {
        // 在数据表中查找用户是否存在
        StudentDO studentDO=null;
        try {
            studentDO= studentDOMapper.selectByEmail(email);
        } catch (Exception e) {
            throw new Exception("student doesn't exist");
        }


        // 密码是否正确
        if (studentDO.getPassword().equals(pwd)) {
            //密码正确更新密码
            studentDOMapper.updateByPwd(email,newpwd);
            return "update completed";
        } else {
            throw new Exception("wrong password");
        }
    }

    @Override
    public String getquestion(int questionid, String questionname) throws Exception {
        QuestionDO questionDO=null;
        try {
            questionDO=questionDOMapper.selectById(questionid);
        }catch (Exception e) {
            throw new Exception("question doesn't exist");
        }
        String str="id:"+questionDO.getIdquestion()+" name:"+questionDO.getQuestionname()+" content:"+questionDO.getContent()+" group:"+questionDO.getQuestiongroup()+" difficulty:"+questionDO.getDifficulty();
        return str;
    }
}
