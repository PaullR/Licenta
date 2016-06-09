package bll;

import com.example.paul.client.dto.Item;
import com.example.paul.client.dto.Items;
import com.example.paul.client.dto.UserItem;

import dal.ItemsCommand;

public class ItemsManager {
	private ItemsCommand itemsCommand;
	
	public ItemsManager(){
		itemsCommand = new ItemsCommand();
	}
	
	public Item insertItem(Item item) {		
		Item newItem = itemsCommand.insertItem(item);
		System.out.println("Save item: "+item.getUsername());
		return newItem;
	}

	public Item getItemById(int id) {
		Item item = itemsCommand.getItemById(id);
		return item;
	}

	public Items getItems() {
		System.out.println("GET REQ");
		Items items= new Items();
		items.arr = itemsCommand.getItems();
		return items;
	}
	
	public Items getItemsFromStreet(String street) {
		System.out.println("GET REQ");
		Items items= new Items();
		items.arr = itemsCommand.getItemsFromStreet(street);
		return items;
	}
	
	public Item getItemByUserName(String name){
		Item item = itemsCommand.getItemByUserName(name);
		return item;
	}
	
	public Items getItemsByReport(String report){
		System.out.println("GET REQ");
		Items items= new Items();
		items.arr = itemsCommand.getItemsByReport(report);
		return items;
	}
	
	public Items getItemsByReportFromStreet(String report,String street) {
		System.out.println("GET REQ");
		Items items= new Items();
		items.arr = itemsCommand.getItemsByReportFromStreet(report,street);
		return items;
	}
	
	public UserItem getUser(String name,String password){
		UserItem item = itemsCommand.getUser(name,password);
		return item;
	}
	
	public Item saveNewUser(Item item){
		Item newItem = itemsCommand.saveNewUser(item);
		System.out.println("Save item: "+item.getUsername());
		return newItem;
	}

	public Items getReportsForPrediction(String street){
		System.out.println("GET REQ");
		Items items= new Items();
		items.arr = itemsCommand.getReportsForPrediction(street);
		System.out.println("Predict: "+ items.arr);
		return items;
	}
	
	public Items getMediumSpeed(String street){
		System.out.println("GET REQ");
		Items items= new Items();
		items.arr = itemsCommand.getMediumSpeed(street);
		System.out.println("MediumSpeed: "+ items.arr);
		return items;
	}
}
