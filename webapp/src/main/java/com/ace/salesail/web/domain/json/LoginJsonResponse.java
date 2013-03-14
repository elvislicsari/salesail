package com.ace.salesail.web.domain.json;

public class LoginJsonResponse {

    private boolean success;
    private String appKey;

    public LoginJsonResponse(boolean success, String appKey) {
        this.success = success;
        this.appKey = appKey;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}