package com.example.spos_sdk2.FDMS;

import com.example.spos_sdk2.EnvBase;

public class FdmsVariable {

	public String cardNo;
	public String ProcessingCode = "";
	public String amount;
	public String merRef;
	public String POSEntryMode = "";
	public String PANSeqNo = "";
	public String POSCondtionCode = "";
	public String Track2Data = "";
	public String EnryptedPIN = "";
	public String EMVICCRelatedData = "";
	public String InvoiceRef = "";
	public String merId;
	public String merName;
	public String currCode;
	public String merRequestAmt = "";
	public String surcharge;
	public String payType;
	public String pMethod;
	public String cardHolder;
	public String epMonth;
	public String epYear;
	public String CVV2Data = "";
	public String operatorId = "";
	public String channel;
	public String hideSurcharge = "";
	public String appCode;
	public String tc;
	public String tsi;
	public String atc;
	public String tvr;
	public String appName;
	public String aid;
	public String action;
	public String invoiceNo;
	public String RRN;
	public String batchNo;
	public String traceNo;
	public String payMethod;
	public String URL;
	public String requestAction;
	public String result = "";
	public String user;
	public String password;
	public String userID;
	public int responseCode = 0;
	public String payRef;
	public String updateTxnCode;
	public String voidTxnCode;
	public String settleTxnCode;
	public String settle;
	public String payBankId;
	public String status;
	public String errCode;
	public String returnMsg;
	public String txnTime;
	public String bankRef;
	public String payRefArray;
	public String terminalId;
	public String currName;
	public String remark;
	public String errMsg;
	public EnvBase.PayGate payGate;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getPayRefArray() {
		return payRefArray;
	}

	public void setPayRefArray(String payRefArray) {
		this.payRefArray = payRefArray;
	}

	public String getSettle() {
		return settle;
	}

	public void setSettle(String settle) {
		this.settle = settle;
	}

	public String getPayBankId() {
		return payBankId;
	}

	public void setPayBankId(String payBankId) {
		this.payBankId = payBankId;
	}

	public String getSettleTxnCode() {
		return settleTxnCode;
	}

	public void setSettleTxnCode(String settleTxnCode) {
		this.settleTxnCode = settleTxnCode;
	}

	public String getVoidTxnCode() {
		return voidTxnCode;
	}

	public void setVoidTxnCode(String voidTxnCode) {
		this.voidTxnCode =voidTxnCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public String getBankRef() {
		return bankRef;
	}

	public void setBankRef(String bankRef) {
		this.bankRef = bankRef;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateTxnCode() {
		return updateTxnCode;
	}

	public void setUpdateTxnCode(String updateTxnCode) {
		this.updateTxnCode = updateTxnCode;
	}

	public String getPayRef() {
		return payRef;
	}

	public void setPayRef(String payRef) {
		this.payRef = payRef;
	}

	public String getCreateTxnCode() {
		return createTxnCode;
	}

	public void setCreateTxnCode(String createTxnCode) {
		this.createTxnCode = createTxnCode;
	}

	public String createTxnCode;

	public String getCurrName() {
		return currName;
	}

	public void setCurrName(String currName) {
		this.currName = currName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getProcessingCode() {
		return ProcessingCode;
	}

	public void setProcessingCode(String processingCode) {
		ProcessingCode = processingCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMerRef() {
		return merRef;
	}

	public void setMerRef(String merRef) {
		this.merRef = merRef;
	}

	public String getPOSEntryMode() {
		return POSEntryMode;
	}

	public void setPOSEntryMode(String POSEntryMode) {
		this.POSEntryMode = POSEntryMode;
	}

	public String getPANSeqNo() {
		return PANSeqNo;
	}

	public void setPANSeqNo(String PANSeqNo) {
		this.PANSeqNo = PANSeqNo;
	}

	public String getPOSCondtionCode() {
		return POSCondtionCode;
	}

	public void setPOSCondtionCode(String POSCondtionCode) {
		this.POSCondtionCode = POSCondtionCode;
	}

	public String getTrack2Data() {
		return Track2Data;
	}

	public void setTrack2Data(String track2Data) {
		Track2Data = track2Data;
	}

	public String getEnryptedPIN() {
		return EnryptedPIN;
	}

	public void setEnryptedPIN(String enryptedPIN) {
		EnryptedPIN = enryptedPIN;
	}

	public String getEMVICCRelatedData() {
		return EMVICCRelatedData;
	}

	public void setEMVICCRelatedData(String EMVICCRelatedData) {
		this.EMVICCRelatedData = EMVICCRelatedData;
	}

	public String getInvoiceRef() {
		return InvoiceRef;
	}

	public void setInvoiceRef(String invoiceRef) {
		InvoiceRef = invoiceRef;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getMerRequestAmt() {
		return merRequestAmt;
	}

	public void setMerRequestAmt(String merRequestAmt) {
		this.merRequestAmt = merRequestAmt;
	}

	public String getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(String surcharge) {
		this.surcharge = surcharge;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getpMethod() {
		return pMethod;
	}

	public void setpMethod(String pMethod) {
		this.pMethod = pMethod;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
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

	public String getCVV2Data() {
		return CVV2Data;
	}

	public void setCVV2Data(String CVV2Data) {
		this.CVV2Data = CVV2Data;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getHideSurcharge() {
		return hideSurcharge;
	}

	public void setHideSurcharge(String hideSurcharge) {
		this.hideSurcharge = hideSurcharge;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public String getTsi() {
		return tsi;
	}

	public void setTsi(String tsi) {
		this.tsi = tsi;
	}

	public String getAtc() {
		return atc;
	}

	public void setAtc(String atc) {
		this.atc = atc;
	}

	public String getTvr() {
		return tvr;
	}

	public void setTvr(String tvr) {
		this.tvr = tvr;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getRRN() {
		return RRN;
	}

	public void setRRN(String RRN) {
		this.RRN = RRN;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getRequestAction() {
		return requestAction;
	}

	public void setRequestAction(String requestAction) {
		this.requestAction = requestAction;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public  int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public  EnvBase.PayGate getPayGate() {
		return payGate;
	}

	public void setPayGate(EnvBase.PayGate payGate) {
		this.payGate = payGate;
	}
}
