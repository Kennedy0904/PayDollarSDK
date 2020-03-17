package com.example.spos_sdk2;

import java.util.ArrayList;

public abstract class HistoryResponse {

//    public abstract void getResponse(ArrayList<Record> result);

    public abstract void getResponse(String result);

    public abstract void onError(ErrorResult result);
}
