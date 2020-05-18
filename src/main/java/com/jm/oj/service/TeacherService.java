package com.jm.oj.service;

public interface TeacherService {
    String login(String email, String pwd) throws Exception;
    String panel(String email,String pwd,String newpwd)throws Exception;
    String registration(String email, String pwd) throws Exception;
    String uploadproblems(int id,String questionname,String content) throws Exception;
    String updateproblems(int id,String content) throws Exception;
}
