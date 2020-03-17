package com.example.spos_sdk2;

public abstract class LoginResponse {

    public abstract void getResponse(LoginResult result);

    public abstract void onError(ErrorResult result);
}
