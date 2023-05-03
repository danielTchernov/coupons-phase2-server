package com.daniel.server.dal;

import com.daniel.server.beans.User;
import com.daniel.server.dto.SuccessfulLoginData;
import com.daniel.server.entities.CompanyEntity;
import com.daniel.server.entities.UserEntity;
import com.daniel.server.enums.UserType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserDal extends CrudRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE userType= 'ADMIN' AND userName= :userName AND password= :password")
    Optional<UserEntity> getAdminUser(String userName, String password);


    Optional<UserEntity> findByUserNameAndPassword(String userName, String password);

    @Query("SELECT u FROM UserEntity u")
    List<UserEntity> getAllUsers(int offset);

    Optional<UserEntity> findByUserName(String userName);
}
