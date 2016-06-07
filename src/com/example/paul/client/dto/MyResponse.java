package com.example.paul.client.dto;

import java.io.Serializable;

public class MyResponse implements Serializable {
    public String status;
    public DataPayload object;
    
    public MyResponse(String status){
    	this.status=status;
    }
    
    public MyResponse(String status, DataPayload object){
    	this.status=status;
    	this.object=object;
    }
    
    public String toString(){
        return "method: "+status;
    }
}
