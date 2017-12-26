package com.xingrongjinfu.utils;

/**
 * Created by duanlei on 16/7/11.
 */
@SuppressWarnings("serial")
public class DecryptExcption extends Exception{
    private String message;

    public DecryptExcption(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
