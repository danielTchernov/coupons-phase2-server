package com.daniel.server.dal;

import com.daniel.server.dto.CouponPurchase;
import com.daniel.server.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IPurchaseDal extends CrudRepository<PurchaseEntity, Long> {

    //SELECT name , amount , phone_number , address , date , category_id , start_date , end_date , description , price FROM coupons JOIN purchases ON purchases.id=coupons.purchase_id

    @Query("SELECT c.name , p.amount , p.timeStamp , c.category , c.startDate , c.endDate , c.description , c.price FROM CouponEntity c JOIN c.purchase p")
    List<CouponPurchase> findCouponPurchase(long id);

    @Query("SELECT p FROM PurchaseEntity p")
    List<CouponPurchase> getAllPurchases(int offset);

    @Query("SELECT p FROM PurchaseEntity p ORDER BY amount ASC")
    List<CouponPurchase> getAllPurchasesByAmountAscending(int offset);

    @Query("SELECT p FROM PurchaseEntity p ORDER BY amount DESC")
    List<CouponPurchase> getAllPurchasesByAmountDesc(int offset);

    @Query("SELECT p FROM PurchaseEntity p ORDER BY timeStamp DESC")
    List<CouponPurchase> getAllPurchasesByTimeStampDescending(int offset);

    @Query("SELECT p FROM PurchaseEntity p ORDER BY timeStamp ASC")
    List<CouponPurchase> getAllPurchasesByTimeStampAscending(int offset);
}
