package com.daniel.server.dal;

import com.daniel.server.beans.Company;
import com.daniel.server.entities.CompanyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// Data layer -- Database, API - layer 1
// DAOs
// Service layer -- CategoryService, ...
// API layer, presentation -- Controllers, View

// React application --> Server (spring boot application)
// Client / Server

// findBy

//JPARepo, CrudRep, PagingRepo

public interface ICompanyDal extends CrudRepository<CompanyEntity, Long> {
    @Query("SELECT c FROM CompanyEntity c")
    List<CompanyEntity> getAllCompanies(int offset);

    Optional<Company> findByName(String name);

    @Query("SELECT c FROM CompanyEntity c ORDER BY name")
    List<Company> getAllCompaniesByNameOrdered(int offset);

    Optional<CompanyEntity> findByPhoneNumber(String phoneNumber);
}
