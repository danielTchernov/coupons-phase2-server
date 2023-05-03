package com.daniel.server.controllers;

import com.daniel.server.dto.CouponCompany;
import com.daniel.server.entities.CouponEntity;
import com.daniel.server.exceptions.ServerException;
import com.daniel.server.logic.CouponsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coupons")
public class CouponsController {

    private CouponsLogic couponsLogic;

    @Autowired
    public CouponsController(CouponsLogic couponsLogic){
        this.couponsLogic = couponsLogic;
    }

    @PostMapping
    public void createCoupons (@RequestBody CouponEntity coupon) throws ServerException {
        couponsLogic.createCoupon(coupon);
    }
    @PutMapping
    public void updateCoupons (@RequestBody CouponEntity coupon) throws ServerException {
        couponsLogic.updateCoupon(coupon);
    }

    @DeleteMapping("{couponId}")
    public void removeCoupon (@PathVariable ("couponId") long id) throws ServerException {
        couponsLogic.remove(id);
        Optional optional = Optional.empty();
    }

    @Scheduled(fixedDelay = 86400000)
    public void removeExpiredCoupons() throws ServerException {
        couponsLogic.removeOutDated();
    }

    @GetMapping("{couponId}")
    public CouponCompany getCoupon(@PathVariable("couponId") long id) throws ServerException {
        CouponCompany CouponEntity = couponsLogic.getCoupon(id);
        return CouponEntity;
    }

    @GetMapping
    public List<CouponCompany> getAllCoupons (@RequestParam("page") int page) throws ServerException {
        List<CouponCompany> couponsCompanies = couponsLogic.getAllCoupons(page);
        return couponsCompanies;
    }

    @GetMapping("/byName")
    public List<CouponEntity> getAllCouponsByNameOrder(@RequestParam("page") int page) throws ServerException {
        List<CouponEntity> couponsCompanies = couponsLogic.getAllCouponByNameOrder(page);
        return couponsCompanies;
    }

    @GetMapping("/byPriceAsc")
    public List<CouponEntity> getAllCouponsByPriceAscending(@RequestParam("page") int page) throws ServerException{
        List<CouponEntity> couponsCompanies = couponsLogic.getAllCouponByPriceAscending(page);
        return couponsCompanies;
    }

    @GetMapping("/byPriceDes")
    public List<CouponEntity> getAllCouponsByPriceDesc(@RequestParam("page") int page) throws ServerException {
        List<CouponEntity> couponsCompanies = couponsLogic.getAllCouponByPriceDesc(page);
        return couponsCompanies;
    }

    @GetMapping("/ByCategoryDes")
    public List<CouponEntity> getAllCouponsByCategoryDesc(@RequestParam("page")int page) throws ServerException {
        List<CouponEntity> couponsCompanies = couponsLogic.getAllCouponByCategoryDesc(page);
        return couponsCompanies;
    }

    @GetMapping("/ByCategoryAsc")
    public List<CouponEntity> getAllCouponsByCategoryAscending(@RequestParam("page") int page) throws ServerException {
        List<CouponEntity> couponsCompanies = couponsLogic.getAllCouponByCategoryAscending(page);
        return couponsCompanies;
    }

}
