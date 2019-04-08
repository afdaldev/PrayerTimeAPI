package com.example.adzan.data.model;

import com.google.gson.annotations.SerializedName;

public class CurrentDateResponse {
    /**
     * code : 200
     * status : OK
     * data : 08-04-2019
     */

    @SerializedName("code")
    private int code;
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
