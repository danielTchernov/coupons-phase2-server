package com.daniel.server;

import com.daniel.server.TimerTask.CouponsRemove;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        CouponsRemove couponsRemove = new CouponsRemove();
        executorService.scheduleAtFixedRate(couponsRemove, 1, 1, TimeUnit.DAYS);
    }
}
