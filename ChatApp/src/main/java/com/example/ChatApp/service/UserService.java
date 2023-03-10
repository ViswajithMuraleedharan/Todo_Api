package com.example.ChatApp.service;

import com.example.ChatApp.dao.IUserRepository;
import com.example.ChatApp.model.User;
import com.example.ChatApp.utils.CommonUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public int saveUser(User user) {

        User userObj = userRepository.save(user);
        return userObj.getUserId();
    }

    public JSONArray getUsers(String userId) {
        JSONArray response = new JSONArray();
        if(null != userId) {
            List<User> usersList = userRepository.getUserByUserId(Integer.valueOf(userId));
            for (User user:usersList) {
                JSONObject userObj = createResponse(user);
                response.put(userObj);
            }
        } else {
            List<User> usersList = userRepository.getAllUsers();
            for (User user:usersList) {
                JSONObject userObj = createResponse(user);
                response.put(userObj);
            }
        }
        return response;
    }

    public JSONObject login (String username, String password) {
        JSONObject response = new JSONObject();
        List<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            response.put("errorMessage", "username doesn't exist");
        } else {
            User userObj = user.get(0);
            if(password.equals(userObj.getPassword())) {
                response = createResponse(userObj);
            }else {
                response.put("errorMessage" , "password is not valid");
            }
        }
        return response;
    }

    private JSONObject createResponse(User user) {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("userId", user.getUserId());
        jsonObj.put("username", user.getUserName());
        jsonObj.put("password", user.getPassword());
        jsonObj.put("firstName", user.getFirstName());
        jsonObj.put("lastName", user.getLastName());
        jsonObj.put("age", user.getAge());
        jsonObj.put("email", user.getEmail());
        jsonObj.put("phoneNumber", user.getPhoneNumber());
        jsonObj.put("gender", user.getGender());
        jsonObj.put("createdDate", user.getCreatedDate());

        return jsonObj;
    }

    public void deleteUserByUserId(int userId) {
        userRepository.deleteById(userId);
    }

    public JSONObject updateUser(User newUser, String userId) {
        List<User> usersList = userRepository.getUserByUserId(Integer.valueOf(userId));
        JSONObject obj = new JSONObject();
        if(!usersList.isEmpty()) {
            User oldUser = usersList.get(0);
            newUser.setUserId(oldUser.getUserId());
            newUser.setCreatedDate(oldUser.getCreatedDate());
            newUser.setPassword(oldUser.getPassword());
            Timestamp updatedTime = new Timestamp(System.currentTimeMillis());
            newUser.setUpdatedDate(updatedTime);
            userRepository.save(newUser);
        } else {
            obj.put("errorMessage" , "User doesn't exist");
        }
        return obj;
    }
}
