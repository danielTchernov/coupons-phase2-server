package com.daniel.server.logic;


import com.daniel.server.beans.Company;
import com.daniel.server.constants.Constants;
import com.daniel.server.dal.ICategoryDal;
import com.daniel.server.dal.ICompanyDal;
import com.daniel.server.entities.CompanyEntity;
import com.daniel.server.enums.ErrorType;
import com.daniel.server.exceptions.ServerException;
import com.daniel.server.mapper.CompanyMapper;
import com.daniel.server.utils.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyLogic {
    private ICompanyDal companyDal;
    private PurchaseLogic purchaseLogic;
    private CouponsLogic couponsLogic;
    private final ICategoryDal iCategoryDal;
    private CompanyMapper companyMapper;

    @Autowired
    public CompanyLogic(ICompanyDal companyDal , PurchaseLogic purchaseLogic, CouponsLogic couponsLogic,
                        ICategoryDal iCategoryDal , CompanyMapper companyMapper){
        this.companyDal = companyDal;
        this.purchaseLogic = purchaseLogic;
        this.couponsLogic = couponsLogic;
        this.iCategoryDal = iCategoryDal;
        this.companyMapper = companyMapper;
    }


    public void createCompany(CompanyEntity company) throws ServerException {
        companyValidate(company);
        companyDal.save(company);
    }

    public CompanyEntity getCompany(long id) throws ServerException {
        CompanyEntity company = companyDal.findById(id).get();
        return company;
    }

    public void updateCompany(CompanyEntity company) throws ServerException{
        companyValidate(company);
        companyDal.save(company);
    }

    public void removeCompany(long id) throws Exception {
        companyDal.deleteById(id);
    }

    public List<Company> getAllCompanies(int page) throws ServerException{
        int offset = (page - 1) * Constants.recordsPerPage;
        return companyDal.getAllCompanies(offset).stream().map(entity -> companyMapper.toCompany(entity)).collect(Collectors.toList());
    }

    public List<Company> getAllCompaniesByNameOrdered(int page) throws ServerException{
        List<Company> companies;
        int offset = (page - 1) * Constants.recordsPerPage;
        companies = companyDal.getAllCompaniesByNameOrdered(offset);
        return companies;
    }


    private boolean isValidatedPhoneNum(String phoneNumber) throws ServerException {
        if (phoneNumber == null){
            return false;
        }
        if(companyDal.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new ServerException(ErrorType.PHONE_ALREADY_EXIST, "Phone number already exist");
        }
        return true;
    }

    private void companyValidate(CompanyEntity company) throws ServerException {
        if (!isValidatedPhoneNum(company.getPhoneNumber())){
            throw new ServerException(ErrorType.BAD_PHONE , "Bad Phone Number");
        }
        if(!Validators.isAddressValidated(company.getAddress())){
            throw new ServerException(ErrorType.BAD_ADDRESS, "Bad Address");
        }
    }




}
