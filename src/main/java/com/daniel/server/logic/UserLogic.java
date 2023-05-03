package com.daniel.server.logic;
import com.daniel.server.beans.User;
import com.daniel.server.constants.Constants;
import com.daniel.server.dal.IUserDal;
import com.daniel.server.dto.SuccessfulLoginData;
import com.daniel.server.dto.UserLoginData;
import com.daniel.server.entities.UserEntity;
import com.daniel.server.enums.ErrorType;
import com.daniel.server.exceptions.ServerException;
import com.daniel.server.utils.JWTUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserLogic {

    private static IUserDal userDal;

    @Autowired
    public UserLogic(IUserDal userDal) {
        this.userDal = userDal;
    }


    public boolean isAdminLogin(String userName, String password) throws ServerException {
        Optional<UserEntity> userData = userDal.getAdminUser(userName, password);
        if (!userData.isPresent()) {
            throw new ServerException(ErrorType.ACCESS_FORBIDEN, "Only admins can access that");
        }
        return true;
    }


    public String login(UserLoginData userLoginData) throws ServerException, JsonProcessingException {
        Optional<UserEntity> userData = userDal.findByUserNameAndPassword(userLoginData.getUserName(), userLoginData.getPassword());
        if (!userData.isPresent()) { //are you encode passwords ? using jwt, I mean how it store in db ? it doesn't show them crypted
            throw new ServerException(ErrorType.BAD_USER_OR_PASSWORD , "Bad username or password" );
        }
        SuccessfulLoginData successfulLoginData = new SuccessfulLoginData(userData.get());
        String token = JWTUtils.createJWT(successfulLoginData);
        return token;
    }


    public void createUser(UserEntity user) throws ServerException {
        userValidate(user);
        userDal.save(user);
    }

    public void removeUser(long id) throws ServerException {
        userDal.deleteById(id);
    }

    public void updateUser(UserEntity user) throws ServerException {
        userValidate(user);
        userDal.save(user);
    }

    public UserEntity getUser(long id) throws ServerException {
        UserEntity user = userDal.findById(id).get();
        return user;
    }

    public static boolean isUserNameValid(UserEntity user) throws ServerException {
        if (user.getUserName().length() < 4 && user.getUserName().length() > 12) {
            throw new ServerException(ErrorType.WRONG_LENGTH, "Wrong length, the username should be between 4-12 charecters.");
        }

        String regex = "^[_A-Za-z0-9-//+]+(\\.[_A-Za-z0-9-]+)"
                + "[_A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getUserName());
        if (matcher.find() && matcher.group().equals(user.getUserName())) {
            return false;
        }
        return true;
    }

    public List<UserEntity> getAllUsers(int page) throws ServerException {
        List<UserEntity> users;
        int offset = (page - 1) * Constants.recordsPerPage;
        users = userDal.getAllUsers(offset);
        return users;
    }

    private static boolean isPasswordValid(UserEntity user) throws ServerException {
        if (user.getPassword().length() < 5 || user.getPassword().length() > 15) {
            throw new ServerException(ErrorType.WRONG_LENGTH, "Check your password length");
        }
        return true;
    }

    private static void userValidate(UserEntity user) throws ServerException {
        if (!isUserNameValid(user)) {
            throw new ServerException(ErrorType.BAD_USERNAME , "Bad userName");
        }
        if (!isUserNameAvailable(user)) {
            throw new ServerException(ErrorType.USERNAME_OCCUPIED , "Username Occupied.");
        }
        if (!isPasswordValid(user)) {
            throw new ServerException(ErrorType.SIZE_BAD_PASSWORD , "Bad password.");
        }
    }

    private static boolean isUserNameAvailable(UserEntity user) throws ServerException {
        if (userDal.findByUserName(user.getUserName()).isPresent()) {
            throw new ServerException(ErrorType.USERNAME_OCCUPIED , "Username Occupied");
        }
        return true;
    }
}

