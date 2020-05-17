package com.jm.oj.service;

public interface TeacherService {
    String login(String email, String pwd) throws Exception;
    String panel(String email,String pwd,String newpwd)throws Exception;
}
