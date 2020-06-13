package com.swigoProject.swigobackend.dao;

import com.swigoProject.swigobackend.dto.UserMasterDto;
import com.swigoProject.swigobackend.model.UserMaster;

import java.util.List;

public interface UserProfileDao {
    UserMasterDto updateProfileService(UserMasterDto userMasterDto);

    List getDisplayProfileService(Integer userId);

    List loginDetailsDao(UserMasterDto userMasterDto);

    Boolean updateEmailOtpDao(UserMasterDto userMasterDto);

    Boolean registerDao(String encryptedPassword, UserMaster userMaster,String salt);

    Boolean updatePhoneOtpDao(UserMasterDto userMasterDto);

    Boolean updateEmail(UserMasterDto userMasterDto);

    Boolean updatePassword(UserMasterDto userMasterDto);

    Boolean updatePhone(UserMasterDto userMasterDto);
}
