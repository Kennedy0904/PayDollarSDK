package com.example.spos_sdk2.ISO_8583;

public class CardData {

    private String cardNo;
    private String epMonth;
    private String epYear;
    private String cardHolder;
    private String securityCode;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getEpMonth() {
        return epMonth;
    }

    public void setEpMonth(String epMonth) {
        this.epMonth = epMonth;
    }

    public String getEpYear() {
        return epYear;
    }

    public void setEpYear(String epYear) {
        this.epYear = epYear;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
