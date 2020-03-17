package com.example.spos_sdk2;

public abstract class InquiryResponse {

    public abstract void getResponse(InquiryResult result);

    public abstract void onError(ErrorResult result);
}
