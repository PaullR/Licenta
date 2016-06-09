package api;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.example.paul.client.dto.Item;
import com.example.paul.client.dto.Items;
import com.example.paul.client.dto.MyRequest;
import com.example.paul.client.dto.MyResponse;
import com.example.paul.client.dto.UserItem;

import bll.ItemsManager;

public class ClientManagerThread extends Thread {

	private Socket socket;
	private ObjectInputStream inStream;
	private ObjectOutputStream outputStream;
	private MyRequest request;

	ClientManagerThread(Socket socket) throws IOException {
		this.socket = socket;
		inStream = new ObjectInputStream(socket.getInputStream());
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		request=new MyRequest("", null);
	}

	public void run() {
		try {
			while (!request.method.equals("StopConnection")) {
				request = (MyRequest) inStream.readObject();
				System.out.println(request.toString());
				if (!request.method.equals("StopConnection")) { 
					System.out.println("REQUEST: " + request.toString());
					MyResponse response = manageRequest(request);
					outputStream.writeObject(response);
					outputStream.flush();
				} else {
					inStream.close();
					outputStream.close();
					socket.close();
					System.out.println("Server connection stopped");
				}
			} 
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}//end run

	private static MyResponse manageRequest(MyRequest req) {

		MyResponse response = null;
		ItemsManager itemsManager = new ItemsManager();

		if (req.method.equals("GetItemById")) {
			Item item = new Item();
			item = itemsManager.getItemById(req.dataObject.getReportId());
			response = new MyResponse("OK", item);
		}

		if (req.method.equals("GetItems")) {
			Items items;
			if(req.street==null)
				 items = itemsManager.getItems();
			else
				 items = itemsManager.getItemsFromStreet(req.street);
			
			response = new MyResponse("OK", items);
			System.out.println("Size: "+ response.object.toString());
			
		}

		if (req.method.equals("InsertItem")) {
			Item it = new Item();
			it = itemsManager.insertItem((Item) req.dataObject);
			response = new MyResponse("OK");
		}
		
		if(req.method.equals("GetItemByUserName")){
			Item item = new Item();		
			item = itemsManager.getItemByUserName(req.dataObject.getUsername());
			response = new MyResponse("User ok", item);
		}
		
		if(req.method.equals("GetItemsByReport")){
			Items items;
			if(req.street==null)
				 items = itemsManager.getItemsByReport(req.dataObject.getReport());
			else
				 items = itemsManager.getItemsByReportFromStreet(req.dataObject.getReport(),req.street);
			
			
			response = new MyResponse("OK", items);
			System.out.println("Size: "+ response.object.toString());
			//System.out.println("Last item: "+ items.arr.get(items.arr.size()-1).toString());
		}
		
		if(req.method.equals("GetUser")){
			UserItem item = new UserItem();		
			item = itemsManager.getUser(req.dataObject.getUsername(),req.dataObject.getPassword());
			response = new MyResponse("User ok", item);
		}
		
		if(req.method.equals("RegisterNewUser")){
			Item it = new Item();
			it = itemsManager.saveNewUser((Item) req.dataObject);
			response = new MyResponse("OK");
		}
		
		if(req.method.equals("Prediction")){
			Items items;
			System.out.println("Street is: "+ req.street);
			items = itemsManager.getReportsForPrediction(req.street);
			response = new MyResponse(" ok", items);
			System.out.println("Size: "+ response.object.toString());
			
		}
		
		if(req.method.equals("MediumSpeed")){
			Items items;
			items = itemsManager.getMediumSpeed(req.street);
			response = new MyResponse(" ok", items);
			System.out.println("Size: "+ response.object.toString());
		}
		
		System.out.println(response.toString());
		
		return response;
	}
}
