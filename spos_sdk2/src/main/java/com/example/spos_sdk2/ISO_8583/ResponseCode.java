package com.example.spos_sdk2.ISO_8583;

public enum ResponseCode {
    APPROVED("00" ,"Approved" ),
    REFERTOISSUER("01","Refer to Issuer"),
    REFERRAL("02","Referral"),
    INVALIDMERCHANT("03","Invalid Merchant");

    private final String name;
    private final String make;

    private ResponseCode(String name , String make) {
        this.name = name;
        this.make = make;
    }

}
