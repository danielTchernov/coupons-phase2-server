package com.daniel.server.TimerTask;

import com.daniel.server.dal.ICouponDal;
import com.daniel.server.exceptions.ServerException;
import com.daniel.server.logic.CouponsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class CouponsRemove implements Runnable {

    private final Timer timer;
    @Autowired
    private CouponsLogic couponsLogic;
    private ICouponDal couponsDal;
    private long oncePerDay = 1000*60*60*24;
    private static int timeToStart = 0;
    private static int zeroMinutes = 0;

    public CouponsRemove() {
        this.timer = new Timer();
        this.couponsLogic = couponsLogic;
    }


    @Override
    public void run() {
        try {
            couponsLogic.removeOutDated();
        } catch (ServerException e) {
            throw new RuntimeException(e);
        }
    }
    private static Date getTomorrowMorning0AM(){

        Date date0am = new Date();
        date0am.setHours(timeToStart);
        date0am.setMinutes(zeroMinutes);

        return date0am;
    }
//    public static void startTask(){
//        CouponsRemove task = new CouponsRemove();
//        Timer timer = new Timer();
//        timer.schedule(task, getTomorrowMorning0AM(),1000*10);// for your case u need to give 1000*60*60*24
//    }
//    public static void main(String args[]){
//        startTask();
//
//    }

}