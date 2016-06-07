package com.example.paul.client.dto;

public class UserItem implements DataPayload{

	private int userId;
    private String name;
    private String pass;
    
    public UserItem(){}

    public UserItem(int userId,String name, String pass){
    	this.userId=userId;
    	this.name=name;
        this.pass=pass;
    }

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return pass;
	}

    @Override
    public int getUserId() {
        return userId;
    }

    public String toString(){
    	return "User name is "+ getUsername()+", password: "+ getPassword()+" "+ getUserId();
    }

	@Override
	public String getReport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReportId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
