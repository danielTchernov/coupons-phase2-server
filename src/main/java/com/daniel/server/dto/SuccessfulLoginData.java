package com.daniel.server.dto;

//public String login(SuccessfulLoginData loginDetails) throws Exception {
//        // mocking a successfull login
//       UserData userData = usersDal.login(userLoginDetails);
//       if (userData == null){
//           throw new Exception("Failed to login");
//       }
//
//       // Reaching here means - a successful login
//       ObjectMapper objectMapper = new ObjectMapper();
//
//       // Converting the userData (java object) into a JSON string
//       String jsonLoginDetails = objectMapper.writeValueAsString(userData);
//       String token = JWTUtils.createJWT(jsonLoginDetails);
//       return token;
//    }

import com.daniel.server.entities.UserEntity;
import com.daniel.server.enums.UserType;

import java.util.Date;

public class SuccessfulLoginData {
    private long id;
    private String userName;
    private long companyId;
    private UserType userType;

    public SuccessfulLoginData(UserEntity user) {
        this.id = user.getId();
        this.userType = user.getUserType();
        this.userName = user.getUserName();
    }

    public SuccessfulLoginData() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "SuccessfulLoginData{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", companyId=" + companyId +
                ", userType=" + userType +
                '}';
    }
}
