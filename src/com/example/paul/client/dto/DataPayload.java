package com.example.paul.client.dto;

import java.io.Serializable;

public interface DataPayload extends Serializable{

    public int getUserId();
    public int getReportId();
    public String getUsername();
    public String getPassword();
    public String getReport();
    public String toString();
}
