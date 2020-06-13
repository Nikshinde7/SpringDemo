package com.swigoProject.swigobackend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_details_tbl")
public class UserMaster {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email_address")
    private String userEmailAddress;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_phone_number")
    private String userPhoneNumber;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_dob")
    @Temporal(TemporalType.DATE)
    private Date userDOB;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_profileImg")
    private String userProfileImg;

    @Column(name = "phone_otp")
    private String phoneOtp;

    @Column(name = "mail_otp")
    private String mailOtp;

    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;


    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    private Date updateDate;


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Date getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(Date userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserProfileImg() {
        return userProfileImg;
    }

    public void setUserProfileImg(String userProfileImg) {
        this.userProfileImg = userProfileImg;
    }

    public String getPhoneOtp() {
        return phoneOtp;
    }

    public void setPhoneOtp(String phoneOtp) {
        this.phoneOtp = phoneOtp;
    }

    public String getMailOtp() {
        return mailOtp;
    }

    public void setMailOtp(String mailOtp) {
        this.mailOtp = mailOtp;
    }

}
