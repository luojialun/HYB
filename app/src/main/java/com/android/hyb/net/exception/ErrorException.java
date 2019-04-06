package com.android.hyb.net.exception;


public class ErrorException extends Exception {

    public int type;
    public String msg;
    public int code;


    public ErrorException(Throwable throwable, int type) {
        super(throwable);
        this.type = type;
    }

}
