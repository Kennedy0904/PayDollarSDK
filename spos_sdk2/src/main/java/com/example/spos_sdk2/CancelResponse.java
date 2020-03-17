package com.example.spos_sdk2;

public abstract class CancelResponse {

    public abstract void getResponse(CancelResult result);

    public abstract void onError(ErrorResult result);
}
