package com.example.paul.client.dto;

import java.io.Serializable;

public class MyRequest implements Serializable {
    public String method;
    public DataPayload dataObject; //this is the serializable interface
    public String street;

    public MyRequest(String m, DataPayload object) {
        this.method = m;
        this.dataObject = object;
    }

    public MyRequest(String m, DataPayload object,String street) {
        this.method = m;
        this.dataObject = object;
        this.street=street;
    }

    public String toString() {
        return "method: " + method;
    }

}