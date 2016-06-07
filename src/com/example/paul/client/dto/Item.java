package com.example.paul.client.dto;

import java.util.Date;
import java.util.Optional;

public class Item implements DataPayload {

	private static final long serialVersionUID = 7950169519310163575L;
	private String username;
	private String password;
	private int reportId;
	private int userId;
	private String report;
	private double latitude;
	private double longitude;
	private String date_time;
	private String speed;
	private String email;
	private String street;
	private String city;
	private String country;

	public Item() {
	}

	public Item(String userName, String password) {
		this.username = userName;
		this.password = password;
	}

	public Item(int id, String userName, String password, String email) {
		this.userId = id;
		this.username = userName;
		this.password = password;
		this.email = email;
	}

	public Item(String username,int reportId, int userId, String report, double latitude, double longitude,
			String date_time, String speed, String street, String city, String country) {
		this.username=username;
		this.reportId = reportId;
		this.userId = userId;
		this.report = report;
		this.latitude = latitude;
		this.longitude = longitude;
		this.date_time = date_time;
		this.speed = speed;
		this.street = street;
		this.city = city;
		this.country = country;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int hashCode() {
		return reportId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Item [username=" + username + ", reportId=" + reportId + ", userId=" + userId + ", report=" + report
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", date_time=" + date_time + ", speed="
				+ speed + "address= "+ street +", "+city +", "+ country+"]";
	}

}