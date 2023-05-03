package com.daniel.server.logic;

import com.daniel.server.constants.Constants;
import com.daniel.server.dal.ICouponDal;
import com.daniel.server.dto.CouponCompany;
import com.daniel.server.entities.CouponEntity;
import com.daniel.server.enums.ErrorType;
import com.daniel.server.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CouponsLogic {
    private ICouponDal couponDal;
    private PurchaseLogic purchaseLogic;

    @Autowired
    public CouponsLogic(ICouponDal couponDal , PurchaseLogic purchaseLogic) {
        this.couponDal = couponDal;
        this.purchaseLogic = purchaseLogic;
    }

    public void createCoupon(CouponEntity coupon) throws ServerException {
        couponValidate(coupon);
        couponDal.save(coupon);
    }

    public CouponCompany getCoupon(long id) throws ServerException {
        Optional<CouponEntity> CouponEntity = couponDal.findById(id);
        if (!CouponEntity.isPresent()){
            throw new ServerException(ErrorType.ID_NOT_FOUND , "Id not found.");
        }
        return new CouponCompany(CouponEntity.get());
    }

    public void remove(long id) throws ServerException {
        couponDal.deleteById(id);
    }

    public void updateCoupon(CouponEntity coupon) throws ServerException{
        couponValidate(coupon);
        couponDal.save(coupon);
    }


    public void removeOutDated() throws ServerException {
        couponDal.removeOutDated();
    }

    public List<CouponCompany> getAllCoupons (int page) throws ServerException {
        List<CouponCompany> couponsCompanies;
        int offset = (page - 1) * Constants.recordsPerPage;
        couponsCompanies = couponDal.getAllCoupons(offset);
        return couponsCompanies;
    }

    public List<CouponEntity> getAllCouponByNameOrder (int page) throws ServerException{
        List<CouponEntity> couponsCompanies;
        int offset = (page - 1) * Constants.recordsPerPage;
        couponsCompanies = couponDal.getAllCouponByNameOrder(offset);
        return couponsCompanies;
    }

    public List<CouponEntity> getAllCouponByPriceAscending (int page) throws ServerException{
        List<CouponEntity> couponCompanies;
        int offset = (page - 1) * Constants.recordsPerPage;
        couponCompanies = couponDal.getAllCouponByPriceAscending(offset);
        return couponCompanies;
    }

    public List<CouponEntity> getAllCouponByPriceDesc (int page) throws ServerException{
        List<CouponEntity> couponCompanies;
        int offset = (page - 1) * Constants.recordsPerPage;
        couponCompanies = couponDal.getAllCouponByPriceDesc(offset);
        return couponCompanies;
    }

    public List<CouponEntity> getAllCouponByCategoryDesc (int page) throws ServerException {
        List<CouponEntity> couponCompanies;
        int offset = (page - 1) * Constants.recordsPerPage;
        couponCompanies = couponDal.getAllCouponsByCategoryDesc(offset);
        return couponCompanies;
    }

    public List<CouponEntity> getAllCouponByCategoryAscending (int page) throws ServerException{
        List<CouponEntity> couponCompanies;
        int offset= (page - 1) * Constants.recordsPerPage;
        couponCompanies = couponDal.getAllCouponByCategoryAscending(offset);
        return couponCompanies;
    }

    private void couponValidate(CouponEntity coupon) throws ServerException {
        if (!isDateValidate(coupon.getStartDate(), coupon.getEndDate())) {
            throw new ServerException(ErrorType.DATE_ERROR, "Date Error");
        }
        if (!isPriceValidate(coupon.getPrice())) {
            throw new ServerException(ErrorType.PRICE_ERROR, "Price can't be below or equal to 0");
        }
    }

    private boolean isPriceValidate(float price) {
        return price > 0;
    }

    private boolean isDateValidate(Date startDate, Date endDate) {
        return startDate.before(endDate);
    }

}
