package com.example.spos_sdk2.Settlement;

import com.example.spos_sdk2.ErrorResult;

public abstract class SettlementResponse {

    public abstract void getResponse(String result);

    public abstract void onError(ErrorResult result);
}
