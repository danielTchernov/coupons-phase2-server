package com.daniel.server.mapper;

import com.daniel.server.beans.Company;
import com.daniel.server.entities.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toCompany(CompanyEntity entity){
        return new Company(entity.getId() , entity.getName() , entity.getAddress() , entity.getPhoneNumber());
    }
}
