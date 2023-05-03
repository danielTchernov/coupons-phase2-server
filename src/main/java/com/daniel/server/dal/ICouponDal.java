package com.daniel.server.dal;

import com.daniel.server.dto.CouponCompany;
import com.daniel.server.entities.CouponEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ICouponDal extends CrudRepository<CouponEntity, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM CouponEntity c WHERE endDate < Current_date()")
    void removeOutDated();



    @Query("SELECT NEW com.daniel.server.dto.CouponCompany(cou.id, cou.name, cou.price, cou.description, cou.startDate, cou.endDate, cou.category.name, cou.company.name) FROM CouponEntity cou JOIN CompanyEntity com ON cou.company.id = com.id")
    List<CouponCompany> getAllCoupons(int offset);

    @Query("SELECT c FROM CouponEntity c ORDER BY name")
    List<CouponEntity> getAllCouponByNameOrder(int offset);

    List<CouponEntity> findById(int offset);

    @Query("SELECT c FROM CouponEntity c ORDER BY price ASC")
    List<CouponEntity> getAllCouponByPriceAscending(int offset);

    @Query("SELECT c FROM CouponEntity c ORDER BY price DESC")
    List<CouponEntity> getAllCouponByPriceDesc(int offset);

    @Query("SELECT c FROM CouponEntity c ORDER BY category DESC")
    List<CouponEntity> getAllCouponsByCategoryDesc(int offset);

    @Query("SELECT c FROM CouponEntity c ORDER BY category ASC")
    List<CouponEntity> getAllCouponByCategoryAscending(int offset);
}
