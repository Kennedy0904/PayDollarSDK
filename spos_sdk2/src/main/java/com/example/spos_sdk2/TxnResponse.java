package com.example.spos_sdk2;

public abstract class TxnResponse {

    public abstract void getResponse(TxnResult payResult);

    public abstract void onError(ErrorResult result);
}
