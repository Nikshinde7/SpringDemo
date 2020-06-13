package com.swigoProject.swigobackend.service;

import com.swigoProject.swigobackend.dto.UserMasterDto;

import java.util.List;

public interface UserProfileService {


    UserMasterDto updatePasswordService(UserMasterDto userMasterDto);

    Boolean registerUserService(UserMasterDto userMasterDto);

    List getDisplayProfileController(Integer userId);

    List loginDetail(UserMasterDto userMasterDto);

    Boolean updateEmailOtpService(UserMasterDto userMasterDto);

    Boolean registerService(String encryptedPassword, UserMasterDto userMasterDto,String salt);

    Boolean updatePhoneOtpService(UserMasterDto userMasterDto);

    Boolean updateEmailService(UserMasterDto userMasterDto);

    Boolean updatePassword(UserMasterDto userMasterDto);

    Boolean updatePhoneService(UserMasterDto userMasterDto);
}
