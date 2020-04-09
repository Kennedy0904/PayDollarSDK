package com.example.spos_sdk2.FDMS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.spos_sdk2.EnvBase;
import com.pax.fdms.opensdk.AdjustMsg;
import com.pax.fdms.opensdk.GetTotalMsg;
import com.pax.fdms.opensdk.GetTransMsg;
import com.pax.fdms.opensdk.ITransAPI;
import com.pax.fdms.opensdk.InstalmentMsg;
import com.pax.fdms.opensdk.OfflineMsg;
import com.pax.fdms.opensdk.PreAuthCompMsg;
import com.pax.fdms.opensdk.PreAuthCompVoidMsg;
import com.pax.fdms.opensdk.PreAuthMsg;
import com.pax.fdms.opensdk.PreAuthVoidMsg;
import com.pax.fdms.opensdk.RefundMsg;
import com.pax.fdms.opensdk.ReprintTotalMsg;
import com.pax.fdms.opensdk.ReprintTransMsg;
import com.pax.fdms.opensdk.SaleMsg;
import com.pax.fdms.opensdk.SettleMsg;
import com.pax.fdms.opensdk.TransAPIFactory;
import com.pax.fdms.opensdk.TransReviewMsg;
import com.pax.fdms.opensdk.VoidMsg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class FdmsRequest extends AppCompatActivity {

	protected static final String FDMS_TAG = "Fiserv";

	ITransAPI transAPI;
	Context context;

	public String merRef;
	public String amount;
	public String currency;
	public String userID;
	public String surcharge;
	public String currCode;
	public String payMethod;
	public String hideSurcharge;
	public String merRequestAmt;
	public String payType;
	public String payRef;
	public String traceNo;
	public String batchNo;
	public String remark;
	public String payBankId;
	public String cardNo;
	public String date;
	public String settle;


	// Params return from PayDollar after Accepted

	// Update & Void Trx return from PayDollar
	public String status;
	public String returnMsg;
	public String cardHolder;
	public String prc;
	public String src;
	//
	int saleResCodeFDMS = -1;
	int voidResCodeFDMS = -1;
	int settleResCodeFDMS = -1;

	// FDMS Sale response params
	String acquirerName ="";
	String aid = "";
	String appName = "";
	String appCode = "";
	String appid = "";
	String atc = "";
	String cardholderCode = "";
	byte[] cardholderSignature;
	int cardType;
	String dccTransAmt = "";
	String emiPerMonth = "";
	String enterMode = "";
	String fxRate = "";
	String interestAmt = "";
	public String invoiceNo = "";
	String localCurrCode = "";
	String merchantId = "";
	String merchantName = "";
	String productCode = "";
	String RRN = "";

	String rspMsg = "";
	String tc = "";
	String tenure = "";
	String tsi = "";
	String terminalId = "";
	String tipAmount = "";
	String transTime = "";
	String tvr = "";

	private HashMap<String, String> map = null;

	FdmsVariable fdmsVariable;
	FdmsResponse Fdresponse;

	public FdmsRequest(Context context) {
		this.context = context;
	}

	public void setFdmsVariables(FdmsVariable fdmsVariable){
		this.fdmsVariable = fdmsVariable;
	}

	public void process() {

		if(fdmsVariable.getRequestAction().equals(EnvBase.FDRequest.SALE)){

			// Paydollar create Txn
			// 'requestAction' used for send request in 'FdmsHttpRequest'

			Calendar c = Calendar.getInstance();
			String epMonth = new SimpleDateFormat("MM").format(c.getTime());
			String year = new SimpleDateFormat("yyyy").format(c.getTime());
			String epYear = String.valueOf(parseInt(year) + 3);

			fdmsVariable.setCardNo("4518354303137777");
			fdmsVariable.setSurcharge("0");
			fdmsVariable.setpMethod("VISA");
			fdmsVariable.setCardHolder("");
			fdmsVariable.setEpMonth(epMonth);
			fdmsVariable.setEpYear(epYear);

			FdmsHttpReq request = new FdmsHttpReq(new FdmsAsyncResponse() {
				@Override
				public void processFinish(FdmsVariable output) {
					Fdresponse.getResponse(output);
				}
			}, fdmsVariable, FdmsRequest.this, context);
			request.execute();

		} else if(fdmsVariable.getRequestAction().equals(EnvBase.FDRequest.VOID)) {
			voidRequest();
		} else if(fdmsVariable.getRequestAction().equals(EnvBase.FDRequest.SETTLEMENT)) {
			settleRequest();
		} else if(fdmsVariable.getRequestAction().equals(EnvBase.FDRequest.REPRINT)) {
			reprintRequest();
		}



//		FdmsHttpReq request = new FdmsHttpReq(FdmsRequest.this, context);
//		request.execute();
	}

	//**************************************
	//*   FDMS API REQUEST FUNCTION LIST   *
	//**************************************

	/** Sale Transaction */
	protected void saleRequest() {
		SaleMsg.Request request = new SaleMsg.Request();
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		request.setAmount((int)(parseFloat(fdmsVariable.getAmount()) * 100));

		transAPI = TransAPIFactory.createTransAPI((Activity)context);
		boolean ret = transAPI.doTrans(request);
		// response will go back to EnterAmount.java
	}

	/** Void Transaction */
	protected void voidRequest() {
		VoidMsg.Request request = new VoidMsg.Request();
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		request.setShowDetail(true);
		request.setInvoiceNo(parseInt(fdmsVariable.getInvoiceNo()));
		ITransAPI transAPI = TransAPIFactory.createTransAPI((Activity) context);
		boolean ret = transAPI.doTrans(request);
		// response will go back to DialogActivity.java
	}

	/** Refund Transaction */
	protected boolean refundRequest(Activity activity, int amount) {
		RefundMsg.Request request = new RefundMsg.Request();
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		request.setAmount(amount);
		ITransAPI transAPI = TransAPIFactory.createTransAPI(activity);
		boolean ret = transAPI.doTrans(request);
		return ret;
	}

	// Offline Transaction
	protected boolean offlineRequest(Activity activity, int amount) {
		OfflineMsg.Request request = new OfflineMsg.Request();
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		request.setAmount(amount);
		ITransAPI transAPI = TransAPIFactory.createTransAPI(activity);
		boolean ret = transAPI.doTrans(request);
		return ret;
	}

	// Adjust Transaction
	protected boolean adjustRequest(Activity activity, int invoiceNo) {
		AdjustMsg.Request request = new AdjustMsg.Request();
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		request.setInvoiceNo(invoiceNo);
		ITransAPI transAPI = TransAPIFactory.createTransAPI(activity);
		boolean ret = transAPI.doTrans(request);
		return ret;
	}

	// PreAuth Transaction
	protected boolean preAuthRequest(Activity activity, int amount) {
		PreAuthMsg.Request request = new PreAuthMsg.Request();
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		request.setAmount(amount);
		ITransAPI transAPI = TransAPIFactory.createTransAPI(activity);
		boolean ret = transAPI.doTrans(request);
		return ret;
	}

	// PreAuthVoid Transaction
	protected boolean preAuthVoidRequest(Activity activity) {
		PreAuthVoidMsg.Request request = new PreAuthVoidMsg.Request();
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		ITransAPI transAPI = TransAPIFactory.createTransAPI(activity);
		boolean ret = transAPI.doTrans(request);
		return ret;
	}

	// PreAuthComp Transaction
	protected boolean preAuthCompRequest(Activity activity) {
		PreAuthCompMsg.Request request = new PreAuthCompMsg.Request();
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		ITransAPI transAPI = TransAPIFactory.createTransAPI(activity);
		boolean ret = transAPI.doTrans(request);
		return ret;
	}

	// PreAuthCompVoid Transaction
	protected boolean preAuthCompVoidRequest(Activity activity) {
		PreAuthCompVoidMsg.Request request = new PreAuthCompVoidMsg.Request();
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		ITransAPI transAPI = TransAPIFactory.createTransAPI(activity);
		boolean ret = transAPI.doTrans(request);
		return ret;
	}

	// Settle Transaction
	protected void settleRequest() {
		SettleMsg.Request request = new SettleMsg.Request();
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		request.setPrint(true);
		ITransAPI transAPI = TransAPIFactory.createTransAPI((Activity) context);
		boolean ret = transAPI.doTrans(request);
	}

	// Reprint Transaction
	protected void reprintRequest() {
		ReprintTransMsg.Request request = new ReprintTransMsg.Request();
		request.setTraceNo(parseInt(fdmsVariable.getTraceNo()));
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		ITransAPI transAPI = TransAPIFactory.createTransAPI((Activity) context);
		boolean ret = transAPI.doTrans(request);
	}

	// Reprint Total
	protected void reprintTotalRequest() {
		ReprintTotalMsg.Request request = new ReprintTotalMsg.Request();
		//request.setAcquireName("FDMS-CUP");
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		ITransAPI transAPI = TransAPIFactory.createTransAPI((Activity) context);
		boolean ret = transAPI.doTrans(request);
	}

	// Get Transaction
	protected void getTransactionRequest(Activity activity, int traceNo) {
		GetTransMsg.Request request = new GetTransMsg.Request();
		request.setTraceNo(traceNo);
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		ITransAPI transAPI = TransAPIFactory.createTransAPI(activity);
		boolean ret = transAPI.doTrans(request);
	}

	// Get Total
	protected void getTotalRequest() {
		GetTotalMsg.Request request = new GetTotalMsg.Request();
		request.setAcquireName("FDMS-V/M");
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		ITransAPI transAPI = TransAPIFactory.createTransAPI((Activity) context);
		boolean ret = transAPI.doTrans(request);

	}

	// View History
	protected void viewHistoryRequest() {
		TransReviewMsg.Request request = new TransReviewMsg.Request();
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		ITransAPI transAPI = TransAPIFactory.createTransAPI((Activity) context);
		boolean ret = transAPI.doTrans(request);
	}

	// Installment Transaction
	protected boolean installmentRequest(Activity activity, int amount) {
		InstalmentMsg.Request request = new InstalmentMsg.Request();
		request.setAppId("com.pax.fdms.base24");
		request.setPackageName("com.pax.fdms.base24");
		request.setAmount(amount);
		ITransAPI transAPI = TransAPIFactory.createTransAPI(activity);
		boolean ret = transAPI.doTrans(request);
		return ret;
	}

	public void responseHandler(FdmsResponse response) {
		setFDMSResponse(response);
	}

	protected void setFDMSResponse(FdmsResponse response) {
		this.Fdresponse = response;
	}


	//*********************************************
	//*   START FDMS API RESPONSE FUNCTION LIST   *
	//*********************************************

	// Sale Response
	public void saleResponse(int requestCode, int resultCode, Intent data) {
		SaleMsg.Response response = (SaleMsg.Response) transAPI.onResult(requestCode, resultCode, data);

		if (data != null) {
			Bundle bundle = data.getExtras();
			if (bundle != null) {
				Set<String> keys = bundle.keySet();
				Iterator<String> it = keys.iterator();
				Log.d(FDMS_TAG, "Intent value start");
				while (it.hasNext()) {
					String key = it.next();
					Log.d(FDMS_TAG, "[" + key + "=" + bundle.get(key) + "]");
				}
				Log.d(FDMS_TAG, "Intent value end");
			}
		}
		Log.d(FDMS_TAG, "Intent value end");

		if (requestCode == 100 && response != null) {

			acquirerName = (response.getAcquirerName() == null) ? "": response.getAcquirerName();
			aid = (response.getAid() == null) ? "": response.getAid();
			appName = (response.getApp() == null) ? "": response.getApp();
			appCode = (response.getAppCode() == null) ? "": response.getAppCode();
			appid = response.getAppId();
			atc = (response.getAtc() == null) ? "": response.getAtc();
			batchNo = String.valueOf(response.getBatchNo());
			cardholderCode = response.getCardholderCode();
			cardholderSignature = response.getCardholderSignature();
			cardNo = response.getCardNo();
			//cardType = response.getCardType();
			dccTransAmt = response.getDccTransAmt();
			emiPerMonth = response.getEmiPerMonth();
			enterMode = response.getEnterMode();
			fxRate = response.getFxRate();
			interestAmt = response.getInterestAmt();
			invoiceNo = String.valueOf(response.getInvoiceNo());
			payMethod = response.getIssuerName();
			localCurrCode = response.getLocalCurrCode();
			merchantId = response.getMerchantId();
			merchantName = response.getMerchantName();
			productCode = response.getProductCode();
			RRN = response.getRefNo();
			saleResCodeFDMS = response.getRspCode();
			rspMsg = response.getRspMsg();
			tc = (response.getTc() == null) ? "": response.getTc();
			tenure = (response.getTenure() == null) ? "": response.getTenure();
			tsi = (response.getTsi() == null) ? "": response.getTsi();
			terminalId = response.getTerminalId();
			tipAmount = response.getTipAmount();
			traceNo = String.valueOf(response.getTraceNo());
			transTime = response.getTransTime();
			tvr = (response.getTvr() == null) ? "": response.getTvr();

			Log.d(FDMS_TAG, "---acquirerName: " + acquirerName);
			Log.d(FDMS_TAG, "---aid: " + aid);
			Log.d(FDMS_TAG, "---amount: " + response.getAmount());
			Log.d(FDMS_TAG, "---app: " + appName);
			Log.d(FDMS_TAG, "---appcode: " + appCode);
			Log.d(FDMS_TAG, "---appid: " + appid);
			Log.d(FDMS_TAG, "---atc: " + atc);
			Log.d(FDMS_TAG, "---batchno: " + batchNo);
			Log.d(FDMS_TAG, "---cardholderCode: " + cardholderCode);
			Log.d(FDMS_TAG, "---cardholderSignature: " + cardholderSignature);
			Log.d(FDMS_TAG, "--- cardNo : " + cardNo);
			Log.d(FDMS_TAG, "--- cardType: " + cardType);
			Log.d(FDMS_TAG, "--- dccTransAmt: " + dccTransAmt);
			Log.d(FDMS_TAG, "--- emiPerMonth: " + emiPerMonth);
			Log.d(FDMS_TAG, "--- enterMode: " + enterMode);
			Log.d(FDMS_TAG, "--- fxRate: " + fxRate);
			Log.d(FDMS_TAG, "--- interestAmt: " + interestAmt);
			Log.d(FDMS_TAG, "--- invoiceNo: " + invoiceNo);
			Log.d(FDMS_TAG, "--- issuerName: " + payMethod);
			Log.d(FDMS_TAG, "--- localCurrCode: " + localCurrCode);
			Log.d(FDMS_TAG, "--- merchantId: " + merchantId);
			Log.d(FDMS_TAG, "--- merchantName: " + merchantName);
			Log.d(FDMS_TAG, "--- productCode: " + productCode);
			Log.d(FDMS_TAG, "--- RRN: " + RRN);
			Log.d(FDMS_TAG, "--- rspCode: " + saleResCodeFDMS);
			Log.d(FDMS_TAG, "--- rspMsg: " + rspMsg);
			Log.d(FDMS_TAG, "--- tc: " + tc);
			Log.d(FDMS_TAG, "--- tenure: " + tenure);
			Log.d(FDMS_TAG, "--- tsi: " + tsi);
			Log.d(FDMS_TAG, "--- terminalId: " + terminalId);
			Log.d(FDMS_TAG, "--- tipAmount: " + tipAmount);
			Log.d(FDMS_TAG, "--- traceNo: " + traceNo);
			Log.d(FDMS_TAG, "---transTime: " + transTime);
			Log.d(FDMS_TAG, "--- tvr: " + tvr);

			fdmsVariable.setResponseCode(saleResCodeFDMS);
			fdmsVariable.setReturnMsg(rspMsg);

			if (saleResCodeFDMS == 0) {
				// Success Transaction

				while (batchNo.length() < 6) {
					batchNo = "0" + batchNo;
				}

				while (invoiceNo.length() < 6) {
					invoiceNo = "0" + invoiceNo;
				}

				while (traceNo.length() < 6) {
					traceNo = "0" + traceNo;
				}

				// Remove cardNo space
				cardNo = cardNo.replaceAll(" ", "");

				if (payMethod.equalsIgnoreCase("MASTER")) {
					payMethod = "Master";
				}

				// 'requestAction' used for send request in 'FdmsHttpRequest'
				fdmsVariable.setRequestAction(EnvBase.FDRequest.UPDATE_TXN_ACCEPTED);
				fdmsVariable.setAction("accepted");
				fdmsVariable.setInvoiceNo(invoiceNo);
				fdmsVariable.setCardNo(cardNo);
				fdmsVariable.setRRN(RRN);
				fdmsVariable.setBatchNo(batchNo);
				fdmsVariable.setTraceNo(traceNo);
				fdmsVariable.setPayMethod(payMethod);
				fdmsVariable.setAppCode(appCode);
				fdmsVariable.setTc(tc);
				fdmsVariable.setTsi(tsi);
				fdmsVariable.setAtc(atc);
				fdmsVariable.setTvr(tvr);
				fdmsVariable.setAppName(appName);
				fdmsVariable.setAid(aid);
				fdmsVariable.setPayGate(EnvBase.PayGate.PAYDOLLAR);

				FdmsHttpReq updateTxn = new FdmsHttpReq(new FdmsAsyncResponse() {
					@Override
					public void processFinish(FdmsVariable output) {
						Fdresponse.getResponse(output);
					}
				}, fdmsVariable, FdmsRequest.this, context);
				updateTxn.execute();
				// Continue to FdmsHttpReq.java

			} else {
				fdmsVariable.setPayMethod("Card");
				//FdmsVariable.setCardNo("");

				if (saleResCodeFDMS == -22) {
					fdmsVariable.setRequestAction(EnvBase.FDRequest.UPDATE_TXN_CANCELLED);
					fdmsVariable.setAction("cancelled");

				} else {
					fdmsVariable.setRequestAction(EnvBase.FDRequest.UPDATE_TXN_REJECTED);
					fdmsVariable.setAction("rejected");
				}

				FdmsHttpReq updateTxn = new FdmsHttpReq(new FdmsAsyncResponse() {
					@Override
					public void processFinish(FdmsVariable output) {
						Fdresponse.getResponse(output);
					}
				}, fdmsVariable, FdmsRequest.this, context);
				updateTxn.execute();
				// Continue to FdmsHttpRequest.java
			}
		} else {
			Log.d(FDMS_TAG, "FDMS Sales Failed");
			Log.d(FDMS_TAG, "Payment Ref: " + fdmsVariable.getPayRef());

			fdmsVariable.setErrMsg("Sales Request Failed");
			Fdresponse.getResponse(fdmsVariable);
		}
	}

	public void voidResponse(int requestCode, int resultCode, Intent data) {
		Log.d(FDMS_TAG, "requestCode: " + requestCode);
		Log.d(FDMS_TAG, "resultCode: " + resultCode);
		Log.d(FDMS_TAG, "data: " + data);

		//VoidMsg.Response response = (VoidMsg.Response) transAPI.onResult(requestCode, resultCode, data);
		// Printout Intent value
		if (data != null) {
			Bundle bundle = data.getExtras();
			if (bundle != null) {
				Set<String> keys = bundle.keySet();
				Iterator<String> it = keys.iterator();
				Log.d(FDMS_TAG, "Intent value start");
				while (it.hasNext()) {
					String key = it.next();
					Log.d(FDMS_TAG, "[" + key + "=" + bundle.get(key) + "]");
				}
				Log.d(FDMS_TAG, "Intent value end");
			}
		}

		// Printout Intent value
		if (data != null) {
			Bundle bundle = data.getExtras();
			if (bundle != null) {
				Set<String> keys = bundle.keySet();
				Iterator<String> it = keys.iterator();
				while (it.hasNext()) {
					String key = it.next();
					if (key.equalsIgnoreCase("_fdms_response_code")) {
						voidResCodeFDMS = parseInt(bundle.get(key).toString());
					} else if (key.equalsIgnoreCase("_fdms_response_invoice_no")) {
						invoiceNo = bundle.get(key).toString();
					} else if (key.equalsIgnoreCase("_fdms_response_trace_no")) {
						traceNo = bundle.get(key).toString();
					}
				}
			}
		}

		if (voidResCodeFDMS == 0) {
			while (invoiceNo.length() < 6) {
				invoiceNo = "0" + invoiceNo;
			}

			while (traceNo.length() < 6) {
				traceNo = "0" + traceNo;
			}

			Log.d(FDMS_TAG, "FDMS Void Success");
			Log.d(FDMS_TAG, "Payment Ref: " + fdmsVariable.getPayRef());

			fdmsVariable.setRequestAction(EnvBase.FDRequest.VOID);
			fdmsVariable.setAction("Void");
			fdmsVariable.setInvoiceNo(invoiceNo);
			fdmsVariable.setTraceNo(traceNo);

			FdmsHttpReq voidTxn = new FdmsHttpReq(new FdmsAsyncResponse() {
				@Override
				public void processFinish(FdmsVariable output) {
					Fdresponse.getResponse(output);
				}
			}, fdmsVariable, FdmsRequest.this, context);
			voidTxn.execute();
			// Continue to FdmsHttpRequest.java
		} else {
			Log.d(FDMS_TAG, "FDMS Void Failed");
			Log.d(FDMS_TAG, "Payment Ref: " + fdmsVariable.getPayRef());

			fdmsVariable.setErrMsg("Void Request Failed");
			Fdresponse.getResponse(fdmsVariable);
		}
	}

	public void settlementResponse(int requestCode, int resultCode, Intent data, ArrayList<String> payRefArray) {
		Log.d(FDMS_TAG, "requestCode: " + requestCode);
		Log.d(FDMS_TAG, "resultCode: " + resultCode);
		Log.d(FDMS_TAG, "data: " + data);

		System.out.print("payRefArray payRefArray: " + payRefArray);

		// Printout Intent value
		if (data != null) {
			Bundle bundle = data.getExtras();
			if (bundle != null) {
				Set<String> keys = bundle.keySet();
				Iterator<String> it = keys.iterator();
				Log.d(FDMS_TAG, "Intent value start");
				while (it.hasNext()) {
					String key = it.next();
					Log.d(FDMS_TAG, "[" + key + "=" + bundle.get(key) + "]");
				}
				Log.d(FDMS_TAG, "Intent value end");
			}
		}

		// Printout Intent value
		if (data != null) {
			Bundle bundle = data.getExtras();
			if (bundle != null) {
				Set<String> keys = bundle.keySet();
				Iterator<String> it = keys.iterator();
				while (it.hasNext()) {
					String key = it.next();
					if (key.equalsIgnoreCase("_fdms_response_code")) {
						settleResCodeFDMS = parseInt(bundle.get(key).toString());
						break;
					}
				}
			}
		}

		if (settleResCodeFDMS == 0) {
			Log.d(FDMS_TAG, "FDMS Settlement Success");
			Log.d(FDMS_TAG, "Payment Ref Array: " + payRefArray);

			fdmsVariable.setRequestAction(EnvBase.FDRequest.SETTLEMENT);
			fdmsVariable.setAction("settlement");
			fdmsVariable.setPayRefArray(payRefArray.toString());

			FdmsHttpReq settlementTxn = new FdmsHttpReq(new FdmsAsyncResponse() {
				@Override
				public void processFinish(FdmsVariable output) {
					Fdresponse.getResponse(output);
				}
			}, fdmsVariable, FdmsRequest.this, context);
			settlementTxn.execute();
			// Continue to FdmsHttpRequest.java

		} else {
			Log.d(FDMS_TAG, "FDMS Settlement Failed");
			Log.d(FDMS_TAG, "Payment Ref Array: " + payRefArray);

			fdmsVariable.setErrMsg("Settlement Request Failed");
			Fdresponse.getResponse(fdmsVariable);
//			fdmsVariable.setRequestAction("settlementTxnFailed");
//			GlobalFunction.disablePaxNavigationButton(context);
		}
	}
}
