package com.jm.oj.service.impl;

import com.jm.oj.dataobject.TeacherDO;
import com.jm.oj.dao.TeacherDOMapper;
import com.jm.oj.service.TeacherService;
import com.jm.oj.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServicelmpl implements TeacherService {
    @Autowired
    private TeacherDOMapper teacherDOMapper;

    @Override
    public String login(String email, String pwd) throws Exception {
        // 在数据表中查找用户是否存在
        TeacherDO teacherDO = null;
        try {
            teacherDO = teacherDOMapper.selectByEmail(email);
        } catch (Exception e) {
            throw new Exception("student doesn't exist");
        }


        // 密码是否正确
        if (teacherDO.getPassword().equals(pwd)) {
            // 生成token并返回
            return JWTUtil.create(email, pwd);
        } else {
            throw new Exception("wrong password");
        }
    }
}
