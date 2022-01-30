package com.gemini.api.models.dto;

public class WithdrawFaucetBody {


    private String address;

    public WithdrawFaucetBody(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
