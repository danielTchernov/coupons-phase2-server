package com.daniel.server.logic;

import com.daniel.server.beans.Customer;
import com.daniel.server.constants.Constants;
import com.daniel.server.dal.ICustomerDal;
import com.daniel.server.entities.CustomerEntity;
import com.daniel.server.enums.ErrorType;
import com.daniel.server.exceptions.ServerException;
import com.daniel.server.utils.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerLogic {
    private ICustomerDal customerDal;


    @Autowired
    public CustomerLogic(ICustomerDal customerDal) {
        this.customerDal = customerDal;
    }


    public void createCustomer(CustomerEntity customer) throws Exception {
        customerValidate(customer);
        customerDal.save(customer);
    }

    public CustomerEntity getCustomer(long id) throws Exception {
        Optional<CustomerEntity> customer = customerDal.findById(id);
        if (!customer.isPresent()){
            throw new Exception("Customer not found");
        }
        return customer.get();
    }

    public void remove (long id) throws Exception {
        customerDal.deleteById(id);
    }

    public void updateCustomer(CustomerEntity customer) throws Exception {
        customerValidate(customer);
        customerDal.save(customer);
    }

    public List<Customer> getAllCustomers(int page) throws ServerException{
        List<Customer> customers;
        int offset = (page - 1) * Constants.recordsPerPage;
        customers = customerDal.getAllCustomers(offset);
        return customers;
    }

    public List<Customer> getAllCustomersByAmountOfChildrenAscending(int page) throws ServerException{
        List<Customer> customers;
        int offset = (page - 1) * Constants.recordsPerPage;
        customers= customerDal.getAllCustomersByAmountOfChildrenAscending(offset);
        return customers;
    }

    public List<Customer> getAllCustomersByAmountOfChildrenDesc (int page) throws ServerException{
        List<Customer> customers;
        int offset = (page - 1) * Constants.recordsPerPage;
        customers = customerDal.getAllCustomersByAmountOfChildrenDesc(offset);
        return customers;
    }

    private void customerValidate(CustomerEntity customer) throws ServerException {
        if(!Validators.isAddressValidated(customer.getAddress())){
            throw new ServerException(ErrorType.BAD_ADDRESS , "Bad Address");
        }
        if(!isAmountOfChildValidated(customer.getAmountOfChildren())){
            throw new ServerException(ErrorType.BAD_AMOUNT, "Bad amount of children");
        }
    }

    private boolean isAmountOfChildValidated(Long amountOfChildren) {
        return amountOfChildren != null & amountOfChildren >= 0;
    }


}
