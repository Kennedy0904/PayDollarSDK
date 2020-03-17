package com.example.spos_sdk2;

public class PayMethod {

    private String name;
    private String bankId;
    private String bankKey;
    private String bankTerminalId;

    public PayMethod(String name, String bankId, String bankKey, String bankTerminalId) {
        setName(name);
        setBankId(bankId);
        setBankKey(bankKey);
        setBankTerminalId(bankTerminalId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankKey() {
        return bankKey;
    }

    public void setBankKey(String bankKey) {
        this.bankKey = bankKey;
    }

    public String getBankTerminalId() {
        return bankTerminalId;
    }

    public void setBankTerminalId(String bankTerminalId) {
        this.bankTerminalId = bankTerminalId;
    }
}
