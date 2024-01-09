package com.mmu.product_app.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HttpMessage {
    private int status;
    private String message;

    public HttpMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }
    public HttpMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
