package com.swigoProject.swigobackend.dao.impl;

import com.swigoProject.swigobackend.dao.UserProfileDao;
import com.swigoProject.swigobackend.dto.UserMasterDto;
import com.swigoProject.swigobackend.model.UserMaster;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class UserProfileDaoImpl implements UserProfileDao,Runnable {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserMasterDto updateProfileService(UserMasterDto userMasterDto) {

        Session session=null;
        try
        {
            session=sessionFactory.openSession();
            Query query=session.createQuery("UPDATE UserMaster as um SET um.updateDate=:userUpdateDate,um.userAddress=:userAddress,um.userDOB=:userDOB,um.userGender=:userGender,um.userProfileImg=:userProfileImg WHERE um.userId=:userId");
            query.setParameter("userId",userMasterDto.getUserId());
            query.setParameter("userAddress",userMasterDto.getUserAddress());
            query.setParameter("userDOB",userMasterDto.getUserDOB());
            query.setParameter("userGender",userMasterDto.getUserGender());
            query.setParameter("userProfileImg",userMasterDto.getUserProfileImg());

            Date d = new Date();
            query.setParameter("userUpdateDate",d);
            query.executeUpdate();

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally {
            session.close();
        }
        return userMasterDto;
    }

    @Override
    public List getDisplayProfileService(Integer userId) {
        Session session=null;
        List list=null;
        try
        {
            session=sessionFactory.openSession();
            Query query=session.createQuery("select um.userPhoneNumber as userPhoneNumber,um.userName as userName,um.userPhoneNumber as userPhoneNumber,um.userEmailAddress as userEmailAddress,um.userPassword as userPassword,um.userAddress as userAddress,um.userDOB as userDOB,um.userGender as userGender,um.userProfileImg as userProfileImg from UserMaster as um where um.userId=:userId");
            query.setParameter("userId",userId);

            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            list=query.list();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List loginDetailsDao(UserMasterDto userMasterDto) {

        Session session = null;
        List list = null;

        try{

            session = sessionFactory.openSession();

            System.out.println("I came");
            Query query = session.createQuery("select um.userId as userId,um.userPhoneNumber as userPhoneNumber,um.userPhoneNumber as userPhoneNumber,um.userEmailAddress as userEmailAddress,um.userPassword as userPassword,um.userAddress as userAddress,um.userDOB as userDOB,um.userGender as userGender,um.userProfileImg as userProfileImg from UserMaster as um where um.userName=:userName and um.userPassword=:userPassword");
            query.setParameter("userName",userMasterDto.getUserName());
            query.setParameter("userPassword",userMasterDto.getUserPassword());

            query.setResultTransformer(Transformers.aliasToBean(UserMasterDto.class));
            list = query.list();


            return list;

        }catch(Exception e){

            System.out.println(e);
            return list;
        }finally {

            session.close();
        }
    }

    @Override
    public Boolean updateEmailOtpDao(UserMasterDto userMasterDto) {

        Session session = null;

        try{

            session=sessionFactory.openSession();

            Object o = session.load(UserMaster.class,userMasterDto.getUserId());

            UserMaster userMaster1 = (UserMaster) o;

            Transaction tr = session.beginTransaction();

            userMaster1.setMailOtp(userMasterDto.getMailOtp());

            tr.commit();

            UserProfileDaoImpl usr = new UserProfileDaoImpl();
            usr.run(userMasterDto.getUserId(),session);

            return true;

        }catch(Exception e){

            System.out.println(e);
            return false;

        }finally {

            session.close();
        }

    }

    public void run(Integer userId,Session session){


        try {

            Thread.sleep(120000);
            //System.out.println("In thread"+userId);
//
//            session = sessionFactory.openSession();

            System.out.println("In thread 2"+userId);
            Object o = session.load(UserMaster.class,userId);
            UserMaster userMaster1 = (UserMaster) o;

            Transaction tr = session.beginTransaction();
            userMaster1.setMailOtp("- - - ");

            tr.commit();

        }catch(Exception e){
//
//            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void run(){
        System.out.println("HI");
    }

    @Override
    public Boolean registerDao(String encryptedPassword, UserMaster userMaster,String salt) {


        String Password = "I AM SHERLOCKED";

        TextEncryptor decryptor = Encryptors.text(Password,salt);

        String dPassword = decryptor.decrypt(encryptedPassword);

        Session session = null;

        userMaster.setUserPassword(dPassword);

        try{

            session = sessionFactory.openSession();

            Transaction tr = session.beginTransaction();

            session.save(userMaster);

            tr.commit();

            return true;
        }catch(Exception e){

            System.out.println(e);
            return false;
        }finally {

            session.close();
        }



    }


    @Override
    public Boolean updatePhoneOtpDao(UserMasterDto userMasterDto) {

        Session session = null;

        String a = "f";
        try{

            session=sessionFactory.openSession();

            Object o = session.load(UserMaster.class,userMasterDto.getUserId());

            UserMaster userMaster1 = (UserMaster) o;

            Transaction tr = session.beginTransaction();

            userMaster1.setMailOtp(userMasterDto.getMailOtp());

            tr.commit();

            UserProfileDaoImpl usr = new UserProfileDaoImpl();
            usr.run(userMasterDto.getUserId(),session,a);

            return true;

        }catch(Exception e){

            System.out.println(e);
            return false;

        }finally {

            session.close();
        }

    }

    public void run(Integer userId,Session session,String a){


        try {

            Thread.sleep(120000);
            System.out.println("In thread 2"+userId);
            Object o = session.load(UserMaster.class,userId);
            UserMaster userMaster1 = (UserMaster) o;

            Transaction tr = session.beginTransaction();
            userMaster1.setPhoneOtp("- - -");

            tr.commit();

        }catch(Exception e){
//
//            System.out.println(e);
            e.printStackTrace();
        }
    }

    @Override
    public Boolean updateEmail(UserMasterDto userMasterDto) {

        Session session = null;

        try{

            session = sessionFactory.openSession();

            UserMaster userMaster = session.load(UserMaster.class,userMasterDto.getUserId());


            Transaction tr = session.beginTransaction();

            userMaster.setUserEmailAddress(userMasterDto.getUserEmailAddress());

            tr.commit();

            return true;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }finally {

            session.close();
        }


    }

    @Override
    public Boolean updatePassword(UserMasterDto userMasterDto) {

        Session session = null;

        try{

            session = sessionFactory.openSession();

            UserMaster userMaster = session.load(UserMaster.class,userMasterDto.getUserId());


            Transaction tr = session.beginTransaction();

            userMaster.setUserPassword(userMasterDto.getUserPassword());

            tr.commit();

            return true;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }finally {

            session.close();
        }


    }

    @Override
    public Boolean updatePhone(UserMasterDto userMasterDto) {

        Session session = null;

        try{

            session = sessionFactory.openSession();

            UserMaster userMaster = session.load(UserMaster.class,userMasterDto.getUserId());


            Transaction tr = session.beginTransaction();

            userMaster.setUserPhoneNumber(userMasterDto.getUserPhoneNumber());

            tr.commit();

            return true;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }finally {

            session.close();
        }

    }


}
