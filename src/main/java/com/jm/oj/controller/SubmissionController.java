package com.jm.oj.controller;

import com.jm.oj.service.SubmissionService;
import com.jm.oj.util.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
    @Autowired
    SubmissionService submissionService;

    @PostMapping("/uploadanswer")
    public BasicResponse uploadanswer(@RequestParam("email") String email,@RequestParam("problemid") int pid, @RequestParam("file") MultipartFile file) {
        Object data = null;

        try {
            data = submissionService.fileupload(email,pid,file);
            BasicResponse res = BasicResponse.create(data);

            return res;
        } catch (Exception e) {
            data = e.getMessage();
            BasicResponse res = BasicResponse.create(false, data);
            return res;
        }
    }

    @GetMapping("/checkrecords")
    public BasicResponse checkrecords(@RequestParam("idsub") String idsub) {
        Object data = null;

        try {
            data = submissionService.checklist(idsub);
            BasicResponse res = BasicResponse.create(data);

            return res;
        } catch (Exception e) {
            data = e.getMessage();
            BasicResponse res = BasicResponse.create(false, data);

            return res;
        }
    }
}
