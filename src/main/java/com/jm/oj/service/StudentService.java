package com.jm.oj.service;

public interface StudentService {
    String login(String email, String pwd) throws Exception;
    String panel(String email,String pwd,String newpwd)throws Exception;
    String registration(String email, String pwd) throws Exception;
    String getquestion(int questionid,String questionname) throws Exception;
}
