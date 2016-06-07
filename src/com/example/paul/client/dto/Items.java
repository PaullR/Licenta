package com.example.paul.client.dto;

import java.util.ArrayList;

public class Items implements DataPayload{
    public ArrayList<Item> arr;

    public Items(){}
    
    public Items(ArrayList<Item> array) {
        this.arr=arr;
    }

    @Override
    public int getReportId() {
        return 1;
    }

   

    public String toString() {
        return "Array size: "+arr.size();
    }

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getUserId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
