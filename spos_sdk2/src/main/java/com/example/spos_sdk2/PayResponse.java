package com.example.spos_sdk2;

public abstract class PayResponse {

    public abstract void getResponse(PayResult payResult);

    public abstract void onError(ErrorResult result);
}
