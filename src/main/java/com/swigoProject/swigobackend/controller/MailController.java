package com.swigoProject.swigobackend.controller;


import com.swigoProject.swigobackend.dto.MailDto;
import com.swigoProject.swigobackend.dto.ResponseVO;
import com.swigoProject.swigobackend.model.MailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@RestController
@CrossOrigin(value = "*")
public class MailController {


    @Autowired
    JavaMailSender javaMailSender;


    @RequestMapping(value = "sendMail", method = RequestMethod.POST)
    public ResponseVO sendMail(@RequestBody MailDto mailDto) {





        
        ResponseVO responseVO = new ResponseVO();

        Random random=new Random();
        Integer OTP=random.nextInt(1000000);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("shindenikhil336@gmail.com");

        System.out.println("This  is to email "+mailDto.getTo());
        message.setSubject(mailDto.getSubject());
        message.setText("Your Otp Is"+OTP);
        message.setSentDate(new Date());
        System.out.println("this is from email>>>" + mailDto.getFrom());
        message.setFrom(mailDto.getFrom());



        javaMailSender.send(message);

        responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
        responseVO.setMessage("Otp Send Successfully");
        responseVO.setResult(OTP);
        System.out.println(OTP+"at back");
        return responseVO;


    }
}
