package com.heresy.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;
import com.heresy.annotations.AuthCheck;
import com.heresy.domain.user.FirebaseUser;
import com.heresy.domain.user.User;
import com.heresy.domain.user.UserWithCount;
import com.heresy.exceptions.UserServiceException;
import com.heresy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * @user park
 * @date 2018. 9. 7.
 **/

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    private PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value="/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    @ResponseBody
    public boolean signup(HttpServletResponse response, @RequestBody @Valid User user,
                         BindingResult bindingResult) {
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");

        String passwordForFireBase = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAuthSnsId("1");
        user.setExperience(1);
        user.setIntroduction("1");
        user.setTendency(1);

        if(bindingResult.hasErrors()){
            throw new UserServiceException(
                    "User Data Binding Error",
                    "binding error",
                    "user data invalid");
        }

        boolean signUpResult = false;

        if(userService.selectOneByUserId(user.getUserId()) == null){
            if(userService.selectOneByNickName(user.getUserNickName()) == null){
                if(userService.insert(user) == 1){
                    CreateRequest request = new CreateRequest()
                            .setEmail(user.getUserId())
                            .setEmailVerified(false)
                            .setPassword(passwordForFireBase)
                            //.setPhoneNumber("+11234567890")
                            .setDisplayName(user.getUserNickName())
                            //.setPhotoUrl("http://www.example.com/12345678/photo.png")
                            .setDisabled(false);

                    UserRecord userRecord;
                    try {
                        userRecord = FirebaseAuth.getInstance().createUser(request);
                        System.out.println("Successfully created new user: " + userRecord.getUid());
                    } catch (FirebaseAuthException e) {
                        e.printStackTrace();
                        if(userService.delete(
                                userService.selectOneByUserId(user.getUserId()).getUserIdx()
                        ) == 1){
                            throw new UserServiceException(
                                    "Firebase Auth Error",
                                    "Firebase Auth",
                                    e.getMessage());
                        }

                    }

                }
            }
        }

        return signUpResult;
    }

    @RequestMapping(value="/modifyUserNicName", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    @ResponseBody
    @AuthCheck
    public boolean modifyUserNicName(HttpServletResponse response, @RequestBody HashMap upComingUser,
                                     FirebaseUser firebaseUser, User user) {
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");

        boolean result = false;
        if(upComingUser.get("userId").equals(user.getUserId())){
            UpdateRequest request = new UpdateRequest(firebaseUser.getUid())
                    .setDisplayName(upComingUser.get("userNickName").toString());
            try {
                UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
                if(userRecord != null){
                    upComingUser.put("userIdx", user.getUserIdx());
                    if(userService.updateUserNickName(upComingUser) > 0){
                        result = true;
                    }
                }
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
                throw new UserServiceException("Firebase Auth Error",
                        "Firebase Auth",
                        e.getMessage());
            }
        }else{
            throw new UserServiceException("User Not Matching","Auth","User Not Matching");
        }
        return result;
    }

    @RequestMapping(value="/modifyUserPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    @ResponseBody
    @AuthCheck
    public boolean modifyUserInfo(HttpServletResponse response, @RequestBody HashMap upComingUser,
                                  FirebaseUser firebaseUser, User user) {
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");

        boolean result = false;
        if(upComingUser.get("userId").equals(user.getUserId())){
            String password = upComingUser.get("password").toString();
            UpdateRequest request = new UpdateRequest(firebaseUser.getUid())
                    .setPassword(password);
            try {
                UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
                if(userRecord != null){
                    String newEncodedPassword = bCryptPasswordEncoder.encode(password);
                    upComingUser.put("encodedPassword", newEncodedPassword);
                    upComingUser.put("userIdx", user.getUserIdx());
                    System.out.println(result);
                    if(userService.updateUserPassword(upComingUser) > 0){
                        result = true;
                    }
                }
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
                throw new UserServiceException("Firebase Auth Error",
                        "Firebase Auth",
                        e.getMessage());
            }
        }else{
            throw new UserServiceException("User Not Matching","Auth","User Not Matching");
        }
        return result;
    }

    @RequestMapping(value = "/verifyPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    @ResponseBody
    @AuthCheck
    public boolean verifyPassword(HttpServletResponse response,
                               @RequestBody HashMap payload, User user){
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");

        String incomingPassword = payload.get("password").toString();
        int authUserIdx = user.getUserIdx();

        boolean isMatch = userService.isPasswordMatch(incomingPassword, authUserIdx);

        return isMatch;
    }

    @RequestMapping(value = "/getUserProfile", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    @ResponseBody
    @AuthCheck
    public User getUserProfile(HttpServletResponse response, User user){
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        //String decodedUserId = URLDecoder.decode(userId, "UTF-8");
        User selectedUser = userService.selectOneByUserId(user.getUserId());

        return selectedUser;
    }

    @RequestMapping(value = "/getUserProfileWithCount", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    @ResponseBody
    @AuthCheck
    public User getUserProfileWithCount(HttpServletResponse response, User user){
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        //String decodedUserId = URLDecoder.decode(userId, "UTF-8");
        UserWithCount selectedUser = userService.selectOneWithCount(user.getUserIdx());

        return selectedUser;
    }

    @RequestMapping(value = "/checkUserByUserId", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    @ResponseBody
    public boolean checkUserByUserId(HttpServletResponse response,
                           @RequestParam(value = "userId") String userId){
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        User user = userService.selectOneByUserId(userId);
        boolean reusable = true;
        if(user != null){
            reusable = false;
        }
        return reusable;
    }

    @RequestMapping(value = "/checkUserByUserNickName", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    @ResponseBody
    public boolean checkUserExistence(HttpServletResponse response,
                                   @RequestParam(value = "userNickName") String userNickName){
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        User user = userService.selectOneByNickName(userNickName);
        boolean reusable = true;
        if(user != null){
            reusable = false;
        }
        return reusable;

    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    @ResponseBody
    @AuthCheck
    public User deleteUser(HttpServletResponse response,
                         @RequestParam(value = "userId") String userId)
    {
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        User user = userService.selectOneByUserId(userId);

        return user;
    }
}

