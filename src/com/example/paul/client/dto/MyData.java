package com.example.paul.client.dto;

public class MyData implements DataPayload {
    Integer id;
    String value;
    String user;
    String pass;
    String report;
    String email;

    public MyData() {

    }

    public MyData(String userName, String pass, String email){
        this.user=userName;
        this.pass=pass;
        this.email=email;
    }


    protected MyData(Integer id, String userName, String pass, String report) {
        this.id = id;
        this.user = userName;
        this.pass = pass;
        this.report = report;
    }

    public static MyData id(Integer id) {
        return new MyData(id, null, null, null);
    }

    public static MyData name(String name) {
        return new MyData(null, name, null, null);
    }

    public static MyData report(String report) {
        return new MyData(null, null, null, report);
    }

    public MyData(String name, String password) {
        this.user = name;
        this.pass = password;
    }

    @Override
    public String getUsername() {
        return this.user;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.pass;
    }

    @Override
    public String getReport() {
        // TODO Auto-generated method stub
        return this.report;
    }

    @Override
    public int getUserId() {
        // TODO Auto-generated method stub
        return this.getUserId();
    }

    @Override
    public int getReportId() {
        // TODO Auto-generated method stub
        return this.getReportId();
    }

}



