package com.example.spos_sdk2.FDMS;

import com.example.spos_sdk2.ErrorResult;
import com.pax.fdms.opensdk.SaleMsg;

public abstract class FdmsResponse {

    public abstract void getResponse(FdmsVariable response);

    public abstract void onError(ErrorResult result);
}
