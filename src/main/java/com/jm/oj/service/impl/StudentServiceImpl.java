package com.jm.oj.service.impl;

import com.jm.oj.dao.StudentDOMapper;
import com.jm.oj.dataobject.StudentDO;
import com.jm.oj.dataobject.StudentDOKey;
import com.jm.oj.service.StudentService;
import com.jm.oj.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDOMapper studentDOMapper;

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

}
