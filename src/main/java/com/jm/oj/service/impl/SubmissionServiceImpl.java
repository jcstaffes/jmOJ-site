package com.jm.oj.service.impl;

import com.jm.oj.dao.SubmissionDOMapper;
import com.jm.oj.dataobject.SubmissionDO;
import com.jm.oj.service.SubmissionService;
import com.jm.oj.util.ExcuUtli;
import com.jm.oj.util.SaveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    @Autowired
    private SubmissionDOMapper submissionDOMapper;

    @Override
    public String fileupload(String email,int pid, MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String FileID="Q"+pid+"_"+email+"_"+1;//文件编号
        String time="";
        String memory="";
        String compile="";
        String pass="";
        if (!SaveUtil.isFileAllowed(extension)){
            return "The file extension is error";
        }//判断是否是java文件
        try{
            Files.copy(file.getInputStream(),new File(SaveUtil.saveDir+FileID+".java").toPath(),
                    StandardCopyOption.REPLACE_EXISTING);//存储原始文件
            Files.copy(file.getInputStream(),new File(SaveUtil.saveDir+"Main.java").toPath(),
                    StandardCopyOption.REPLACE_EXISTING);//复制一份并且改名Main文件作为编译文件
            System.out.println(FileID);
            submissionDOMapper.insertSubid(FileID,pid,0,0,0,0,email);//先记录submission id
            String[] ans= ExcuUtli.Exec(FileID,"2 7 11 15 9");
            submissionDOMapper.updateifcompile(1,FileID);
            compile="Compile success.";
            System.out.println("compile success");
            submissionDOMapper.updatetime(Integer.parseInt(ans[1]),FileID);
            submissionDOMapper.updatememory(Integer.parseInt(ans[2]),FileID);
            System.out.println("output:"+ans[0]);
            time="Time:"+ans[1]+"ms ";
            memory="Memory:"+ans[2]+"kb";
            if (ans[0].equals("1 0")){
                submissionDOMapper.updateifpass(1,FileID);
                pass="Pass.";
                System.out.println("pass");
            }else {
                submissionDOMapper.updateifpass(0,FileID);
                pass="Not pass.";
                System.out.println("not pass");
            }
        }catch (IOException e){
        }
        String output="Q"+pid+" has been submitted!Submission id is "+FileID+"."+compile+pass+time+memory;
        return output;
    }

    @Override
    public String checklist(String sid) throws Exception {
        SubmissionDO submissionDO=null;
        try {
            submissionDO=submissionDOMapper.selectbySID(sid);
        }catch (Exception e){
            throw new Exception("submission doesn't exist");
        }
        String str="id:"+submissionDO.getIdsub()+" problemid:"+submissionDO.getProblemid()+" useremail:"+submissionDO.getEmail()+
                " time_used:"+submissionDO.getTimeUsed()+"ms"+" memory_used:"+submissionDO.getMemoryUsed()+"kb";
        return str;
    }
}
