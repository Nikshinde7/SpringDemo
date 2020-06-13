package com.swigoProject.swigobackend.service.impl;

import com.swigoProject.swigobackend.dao.UserProfileDao;
import com.swigoProject.swigobackend.dto.UserMasterDto;
import com.swigoProject.swigobackend.model.UserMaster;
import com.swigoProject.swigobackend.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {


    @Autowired
    private UserProfileDao userProfileDao;


    @Override
    public UserMasterDto updatePasswordService(UserMasterDto userMasterDto) {

                userMasterDto=userProfileDao.updateProfileService(userMasterDto);
                return userMasterDto;
    }

    @Override
    public Boolean registerUserService(UserMasterDto userMasterDto) {

            return true;

    }

    @Override
    public List getDisplayProfileController(Integer userId) {

        List list=userProfileDao.getDisplayProfileService(userId);
        return list;

    }

    @Override
    public List loginDetail(UserMasterDto userMasterDto) {

        System.out.println("In service");
        List list = userProfileDao.loginDetailsDao(userMasterDto);
        return list;
    }

    @Override
    public Boolean updateEmailOtpService(UserMasterDto userMasterDto) {

        Boolean flag = userProfileDao.updateEmailOtpDao(userMasterDto);

        return flag;
    }

    @Override
    public Boolean registerService(String encryptedPassword, UserMasterDto userMasterDto,String salt) {

        UserMaster userMaster = new UserMaster();

        Date d = new Date();

        userMaster.setCreateDate(d);
        userMaster.setUserEmailAddress(userMasterDto.getUserEmailAddress());
        userMaster.setUserName(userMasterDto.getUserName());
        userMaster.setUserPhoneNumber(userMasterDto.getUserPhoneNumber());

        Boolean flag = userProfileDao.registerDao(encryptedPassword,userMaster,salt);

        return flag;

    }


    @Override
    public Boolean updatePhoneOtpService(UserMasterDto userMasterDto) {

        Boolean flag = userProfileDao.updatePhoneOtpDao(userMasterDto);

        return flag;
    }

    @Override
    public Boolean updateEmailService(UserMasterDto userMasterDto) {

        Boolean flag = userProfileDao.updateEmail(userMasterDto);

        return flag;

    }

    @Override
    public Boolean updatePassword(UserMasterDto userMasterDto) {

        Boolean flag = userProfileDao.updatePassword(userMasterDto);

        return flag;

    }

    @Override
    public Boolean updatePhoneService(UserMasterDto userMasterDto) {

        Boolean flag = userProfileDao.updatePhone(userMasterDto);

        return flag;

    }
}
