package com.daniel.server.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "purchases")
public class PurchaseEntity {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id" , insertable = false , updatable = false)
    private CouponEntity couponId;

    @Column(name = "amount" , nullable = false)
    private int amount;

    @Column(name = "date" , nullable = false)
    private Date timeStamp;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CouponEntity getCouponId() {
        return couponId;
    }

    public void setCouponId(CouponEntity couponId) {
        this.couponId = couponId;
    }

    public PurchaseEntity() {
    }

    public PurchaseEntity(long id, int amount, Date timeStamp) {
        this.id = id;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "PurchaseEntity{" +
                "id=" + id +
                ", amount=" + amount +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
