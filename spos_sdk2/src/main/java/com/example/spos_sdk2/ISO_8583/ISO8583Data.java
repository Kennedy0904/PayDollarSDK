package com.example.spos_sdk2.ISO_8583;

public class ISO8583Data {

    /** For Card ISO */
    private String AID;
    private String EMVData;
    private String POSConditionCode;
    private String POSEntryMode;
    private String TC;
    private String appname;
    private String cardAcceptorId;
    private String enryptedPIN;
    private String invoiceRef;
    private String processingCode;
    private String rrn;
    private String terminalId;
    private String traceNo;
    private String track2Data;

    public String getAID() {
        return AID;
    }

    public void setAID(String AID) {
        this.AID = AID;
    }

    public String getEMVData() {
        return EMVData;
    }

    public void setEMVData(String EMVData) {
        this.EMVData = EMVData;
    }

    public String getPOSConditionCode() {
        return POSConditionCode;
    }

    public void setPOSConditionCode(String POSConditionCode) {
        this.POSConditionCode = POSConditionCode;
    }

    public String getPOSEntryMode() {
        return POSEntryMode;
    }

    public void setPOSEntryMode(String POSEntryMode) {
        this.POSEntryMode = POSEntryMode;
    }

    public String getTC() {
        return TC;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getCardAcceptorId() {
        return cardAcceptorId;
    }

    public void setCardAcceptorId(String cardAcceptorId) {
        this.cardAcceptorId = cardAcceptorId;
    }

    public String getEnryptedPIN() {
        return enryptedPIN;
    }

    public void setEnryptedPIN(String enryptedPIN) {
        this.enryptedPIN = enryptedPIN;
    }

    public String getInvoiceRef() {
        return invoiceRef;
    }

    public void setInvoiceRef(String invoiceRef) {
        this.invoiceRef = invoiceRef;
    }

    public String getProcessingCode() {
        return processingCode;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public String getTrack2Data() {
        return track2Data;
    }

    public void setTrack2Data(String track2Data) {
        this.track2Data = track2Data;
    }
}
