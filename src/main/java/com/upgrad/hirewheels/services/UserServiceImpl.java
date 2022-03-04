package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.UserDao;
import com.upgrad.hirewheels.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUser(User user) {
        User checkedUser = userDao.findByEmailIgnoreCase(user.getEmail());
        if(checkedUser != null){
            System.out.println("User not registered");
        }
        User retrieveUser = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(retrieveUser != null){
            System.out.println("Invalid Credentials");
        }
        return retrieveUser;
    }

    @Override
    public User createUser(User user) {
        User returnedUser = userDao.findByEmailIgnoreCase(user.getEmail());
        if(returnedUser != null){
            System.out.println("Email already exists");
        }
        User returnedUser1 = userDao.findByMobileNoIgnoreCase(user.getMobileNo());
        if(returnedUser1 != null){
            System.out.println("Mobile number already exists");
        }
        User savedUser = userDao.save(user);
        return savedUser;
    }

    @Override
    public User getUserDetailsByEmail(String email) {
        User retrievedUser = userDao.findByEmailIgnoreCase(email);
        if(retrievedUser == null){
            System.out.println("User not registered");
        }
        return retrievedUser;
    }
}
