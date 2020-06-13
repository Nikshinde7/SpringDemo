package com.swigoProject.swigobackend.controller;


import com.swigoProject.swigobackend.dto.ResponseVO;
import com.swigoProject.swigobackend.dto.UserMasterDto;

import com.swigoProject.swigobackend.model.UserMaster;
import com.swigoProject.swigobackend.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import com.twilio.type.PhoneNumber;


import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin(value = "*")
public class UserProfileConroller {

    @Autowired
    private UserProfileService userProfileService;


    @RequestMapping(value="/registerUser",method = RequestMethod.POST)
    public ResponseVO registerUserDetails(@RequestBody UserMasterDto userMasterDto){

        ResponseVO responseVO = new ResponseVO();


        Boolean flag = userProfileService.registerUserService(userMasterDto);

        if(flag){

            responseVO.setMessage("Successfull");
            responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
            responseVO.setResult(flag);
        }else{

            responseVO.setResult(flag);
            responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            responseVO.setMessage("Failed ");
        }

        return responseVO;

    }


    @RequestMapping(value="/OtpMobile",method = RequestMethod.POST)
    public ResponseVO OtpMobile(@RequestBody UserMasterDto userMasterDto){

        ResponseVO responseVO = new ResponseVO();



        final String ACCOUNT_SID =
                "AC6ff8289479dabdad854bc5cb599b9156";
        final String AUTH_TOKEN =
                "f9c7e0f1b7cb81b20ccca42d8edb890a";

        Random rand = new Random();

        Integer OTP = rand.nextInt(1000000);

        try {

            System.out.println("to phone Number  "+userMasterDto.getUserPhoneNumber());
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new PhoneNumber(userMasterDto.getUserPhoneNumber()),//to
                    new PhoneNumber("12058325317"),//from
                    "Your Otp For Changing Phone Number is " + OTP)
                    .create();
            System.out.println(message.getSid() + "Done");

            responseVO.setMessage("OTP sent successfully");
            responseVO.setResult(OTP);
            responseVO.setStatusCode(String.valueOf(HttpStatus.OK));


        }catch(Exception e){

            System.out.println(e);
            responseVO.setMessage("Looks Like You havent verified Your number");
        }

        return responseVO;
    }



    @RequestMapping(value = "/updateProfile",method = RequestMethod.POST)
    public ResponseVO<UserMasterDto> updateProfileController(@RequestBody UserMasterDto userMasterDto) {
        ResponseVO responseVO=new ResponseVO();

        userMasterDto = userProfileService.updatePasswordService(userMasterDto);
        if(userMasterDto!=null)
        {
            responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
            responseVO.setMessage("Succesfull");
            responseVO.setResult(userMasterDto);

        }
        else
        {
            responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            responseVO.setMessage("Un-Successfull");
            responseVO.setResult(userMasterDto);
        }
        return responseVO;
    }


    @RequestMapping(value = "/getDisplayProfile/{userId}",method = RequestMethod.GET)
    public ResponseVO getDisplayProfile(@PathVariable Integer userId){
        ResponseVO responseVO=new ResponseVO();
        List list=userProfileService.getDisplayProfileController(userId);
        if(list.size()!=0)
        {
            responseVO.setResult(list);
            responseVO.setMessage("success");
            responseVO.setStatusCode(String.valueOf(org.springframework.http.HttpStatus.OK));
        }
        else
        {

            responseVO.setMessage("failes");
            responseVO.setStatusCode(String.valueOf(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return responseVO;
    }


    @RequestMapping(value="/Login",method=RequestMethod.POST)
    public ResponseVO loginDetails(@RequestBody UserMasterDto userMasterDto){

        ResponseVO responseVO = new ResponseVO();
        System.out.println("In controller");
        List<UserMasterDto> list1 = userProfileService.loginDetail(userMasterDto);

        if(list1.size()!=0){

            responseVO.setResult(list1);
            responseVO.setMessage("Successfull");
            responseVO.setStatusCode(String.valueOf(HttpStatus.OK));

        }else{

            responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            responseVO.setMessage("Failed");
            responseVO.setResult(list1);
        }

        return responseVO;
    }



    @RequestMapping(value="/EmailOtpUpdate",method = RequestMethod.POST)
    public ResponseVO updateEmailOtp(@RequestBody UserMasterDto userMasterDto){

        ResponseVO responseVO = new ResponseVO();

        Boolean flag = userProfileService.updateEmailOtpService(userMasterDto);

        if(flag){

            responseVO.setResult(flag);
            responseVO.setMessage("Done !!");
            responseVO.setStatusCode(String.valueOf(HttpStatus.OK));

        }else{

            responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            responseVO.setMessage("failed");
            responseVO.setResult(flag);
        }

        return responseVO;
    }


    @RequestMapping(value="/PhoneOtpUpdate",method = RequestMethod.POST)
    public ResponseVO updatePhoneOtp(@RequestBody UserMasterDto userMasterDto){

        ResponseVO responseVO = new ResponseVO();

        Boolean flag = userProfileService.updatePhoneOtpService(userMasterDto);

        if(flag){

            responseVO.setResult(flag);
            responseVO.setMessage("Done !!");
            responseVO.setStatusCode(String.valueOf(HttpStatus.OK));

        }else{

            responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            responseVO.setMessage("failed");
            responseVO.setResult(flag);
        }

        return responseVO;
    }


    @RequestMapping(value="/register",method=RequestMethod.POST)
    public ResponseVO register(@RequestBody UserMasterDto userMasterDto){

        ResponseVO responseVO = new ResponseVO();

        final String password = "I AM SHERLOCKED";
        final String salt = KeyGenerators.string().generateKey();

        TextEncryptor encryptor = Encryptors.text(password, salt);

        String ePassword = userMasterDto.getUserPassword();

        String encryptedPassword = encryptor.encrypt(ePassword);


        Boolean flag = userProfileService.registerService(encryptedPassword,userMasterDto,salt);


        if(flag){

            responseVO.setResult(flag);
            responseVO.setMessage("Success");
            responseVO.setStatusCode(String.valueOf(HttpStatus.OK));

        }else{

            responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            responseVO.setMessage("Failed");
            responseVO.setResult(flag);

        }

        return responseVO;
    }


    @RequestMapping(value="/emailUpdate",method = RequestMethod.POST)
    public ResponseVO updateEmail(@RequestBody UserMasterDto userMasterDto){

        ResponseVO responseVO = new ResponseVO();

        Boolean flag = userProfileService.updateEmailService(userMasterDto);

        if(flag){

            responseVO.setResult(flag);
            responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
            responseVO.setMessage("Success");

        }else{

            responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            responseVO.setMessage("Failed");
            responseVO.setResult(flag);
        }
        return responseVO;
    }



    @RequestMapping(value="/passwordUpdate",method = RequestMethod.POST)
    public ResponseVO updatePassword(@RequestBody UserMasterDto userMasterDto){

        ResponseVO responseVO = new ResponseVO();

        Boolean flag = userProfileService.updatePassword(userMasterDto);

        if(flag){

            responseVO.setResult(flag);
            responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
            responseVO.setMessage("Success");

        }else{

            responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            responseVO.setMessage("Failed");
            responseVO.setResult(flag);
        }
        return responseVO;
    }




    @RequestMapping(value="/phoneUpdate",method = RequestMethod.POST)
    public ResponseVO updatePhone(@RequestBody UserMasterDto userMasterDto){

        ResponseVO responseVO = new ResponseVO();

        Boolean flag = userProfileService.updatePhoneService(userMasterDto);

        if(flag){

            responseVO.setResult(flag);
            responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
            responseVO.setMessage("Success");

        }else{

            responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            responseVO.setMessage("Failed");
            responseVO.setResult(flag);
        }
        return responseVO;
    }

}


