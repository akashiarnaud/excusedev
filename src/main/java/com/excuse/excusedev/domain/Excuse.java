package com.excuse.excusedev.domain;

public class Excuse {
    int http_code;
    String tag;
    String message;

    public Excuse() {
    }

    private Excuse(String tag, String message) {
        this.http_code = 0;
        this.tag = tag;
        this.message = message;
    }
    public static Excuse createExcuse(String tag, String message){
        return new Excuse(tag, message);
    }

    public int getHttp_code() {
        return this.http_code;
    }

    public void setHttp_code(int http_code) {
        this.http_code = http_code;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "{" +
            " http_code='" + getHttp_code() + "'" +
            ", tag='" + getTag() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
