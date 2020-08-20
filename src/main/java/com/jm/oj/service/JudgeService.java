package com.jm.oj.service;

public interface JudgeService {
    String fileupload(int id,String code,String language) throws Exception;//学生测试
    String filesubmission(int recordid)throws Exception;//先测试然后生成记录id，通过记录id打包把结果上传上去
    String getfile(int recordid)throws Exception;//教师获取
}
