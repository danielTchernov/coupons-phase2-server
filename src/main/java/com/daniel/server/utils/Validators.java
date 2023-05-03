package com.daniel.server.utils;

public class Validators {
    private Validators(){

    }
    public static boolean isAddressValidated(String address) {
        return address == null || address.length() < 40;
    }
}
