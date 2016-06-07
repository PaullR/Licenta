package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.example.paul.client.dto.Item;
import com.example.paul.client.dto.UserItem;

public class ItemsCommand{

	MysqlDatabase mysqlDatabase = new MysqlDatabase();

	public ArrayList<Item> getItems() {
		System.out.println("Connect to the database and read all the items");
		
		ArrayList<Item> items = new ArrayList<Item>();

		mysqlDatabase.openConnection();
		Connection connection = mysqlDatabase.getConnection();

		String mySQLQuery = "SELECT users.username, reports.* FROM users join reports on users.userId=reports.userId WHERE `reports`.date_time > DATE_SUB(NOW(), INTERVAL 24 HOUR)";

		Statement stat = mysqlDatabase.getStatement();
		ResultSet result;
		try {
			result = stat.executeQuery(mySQLQuery);

			while (result.next()) {
				Item item = new Item(result.getString(1),result.getInt(2), result.getInt(3), result.getString(4), result.getDouble(5),result.getDouble(6),result.getString(7),result.getString(8),result.getString(9),result.getString(10),result.getString(11));			
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Close DB Connection
		mysqlDatabase.closeConnection();
		return items;
	}
	
	public ArrayList<Item> getItemsFromStreet(String street) {
		System.out.println("Connect to the database and read all the items from the street searched");
		
		ArrayList<Item> items = new ArrayList<Item>();

		mysqlDatabase.openConnection();
		Connection connection = mysqlDatabase.getConnection();

		String mySQLQuery = "SELECT users.username, reports.* FROM users join reports on users.userId=reports.userId WHERE `reports`.date_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) and street='"+street+"'";

		Statement stat = mysqlDatabase.getStatement();
		ResultSet result;
		try {
			result = stat.executeQuery(mySQLQuery);

			while (result.next()) {
				Item item = new Item(result.getString(1),result.getInt(2), result.getInt(3), result.getString(4), result.getDouble(5),result.getDouble(6),result.getString(7),result.getString(8),result.getString(9),result.getString(10),result.getString(11));			
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Close DB Connection
		mysqlDatabase.closeConnection();
		return items;
	}

	public Item getItemById(int id) {
		System.out.println("Connect to the database and read the item id="+id);
		
		Item item=new Item();

		mysqlDatabase.openConnection();
		Connection connection = mysqlDatabase.getConnection();

		String mySQLQuery = "SELECT users.username, reports.* FROM users join reports on users.userId=reports.userId  WHERE id="+id;
		Statement stat = mysqlDatabase.getStatement();
		ResultSet result;
		try {
			result = stat.executeQuery(mySQLQuery);	
			while (result.next()) 					
				item = new Item(result.getString(1), result.getInt(2), result.getInt(2), result.getString(4),result.getDouble(5),result.getDouble(6),result.getString(7),result.getString(8),result.getString(9),result.getString(10),result.getString(11));			

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Close DB Con
		mysqlDatabase.closeConnection();
		System.out.println(item.toString());
		return item;
	}

	public Item insertItem(Item item) {
		System.out.println("Connect to the database and insert a new item");	
		
		Date date = new Date();
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		item.setDate_time(df.format(date));
	    
		Item newItem=new Item();
		mysqlDatabase.openConnection();
		Connection connection = mysqlDatabase.getConnection();

		String mySQLQuery = "INSERT INTO reports ( userID, report, latitude, longitude, date_time, speed, street, city, country) VALUES ('"+item.getUserId()+"','"+item.getReport()+"','"+item.getLatitude()+"','"+item.getLongitude()+"','"+item.getDate_time()+"','"+item.getSpeed()+"','"+item.getStreet()+"','"+item.getCity()+"','"+item.getCountry()+"')";
		Statement stat = mysqlDatabase.getStatement();

		try {
			int result = stat.executeUpdate(mySQLQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mySQLQuery = "select users.username, reports.* from users join reports on users.userId=reports.userId ORDER BY reportId DESC LIMIT 1" ;
		System.out.println("Username is: " + item.getUsername());
		try {
			ResultSet result = stat.executeQuery(mySQLQuery);

			while (result.next()) {
				newItem = new Item(result.getString(1), result.getInt(2), result.getInt(3), result.getString(4),result.getDouble(5),result.getDouble(6),result.getString(7),result.getString(8),result.getString(9),result.getString(10),result.getString(11));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Close DB Con
		mysqlDatabase.closeConnection();	
		System.out.println("NIT: "+newItem.toString());
		return newItem;
	}
	
	public void delete(String name){
		mysqlDatabase.openConnection();
		Connection connection = mysqlDatabase.getConnection();
		
		String mySQLQuery = "delete from api-db.users join reports on users.userId=reports.userId where Name='"+name+"'";
		Statement stat = (Statement) mysqlDatabase.getStatement();
		ResultSet result;
		try {
			 stat.executeUpdate(mySQLQuery);
		} catch (Exception ex) {
            ex.printStackTrace();
        }
		
			 mysqlDatabase.closeConnection();	
	}
	
	public Item getItemByUserName(String name) {
		System.out.println("Connect to the database and find user with user = "+name);
		
		Item item = new Item();

		mysqlDatabase.openConnection();
		Connection connection = mysqlDatabase.getConnection();

		String mySQLQuery = "SELECT users.username, reports.* FROM users join reports on users.userId=reports.userId WHERE User = '"+ name+"'";
		Statement stat = mysqlDatabase.getStatement();
		ResultSet result;
		try {
			result = stat.executeQuery(mySQLQuery);
			while (result.next()) {
				item = new Item(result.getString(1), result.getInt(2), result.getInt(2), result.getString(4),result.getDouble(5),result.getDouble(6),result.getString(7),result.getString(8),result.getString(9),result.getString(10),result.getString(11));			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Close DB Con
		mysqlDatabase.closeConnection();
		return item;
	}
	
	public ArrayList<Item> getItemsByReport(String report) {
	System.out.println("Connect to the database and get all items with report= " + report);
		
		ArrayList<Item> items = new ArrayList<Item>();

		mysqlDatabase.openConnection();
		Connection connection = mysqlDatabase.getConnection();

		String mySQLQuery = "SELECT users.username, reports.* FROM users join reports on users.userId=reports.userId where report='"+report+"' and date_time > DATE_SUB(NOW(), INTERVAL 24 HOUR)";
		Statement stat = mysqlDatabase.getStatement();
		ResultSet result;
		try {
			result = stat.executeQuery(mySQLQuery);

			while (result.next()) {
				Item item = new Item(result.getString(1), result.getInt(2), result.getInt(3), result.getString(4),result.getDouble(5),result.getDouble(6),result.getString(7),result.getString(8),result.getString(9),result.getString(10),result.getString(11));			
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Close DB Connection
		mysqlDatabase.closeConnection();
		return items;
	}
	
	public ArrayList<Item> getItemsByReportFromStreet(String report, String street) {
		System.out.println("Connect to the database and get all items with report= " + report);
			
			ArrayList<Item> items = new ArrayList<Item>();

			mysqlDatabase.openConnection();
			Connection connection = mysqlDatabase.getConnection();

			String mySQLQuery = "SELECT users.username, reports.* FROM users join reports on users.userId=reports.userId where report='"+report+"' and date_time > DATE_SUB(NOW(), INTERVAL 24 HOUR) and street='"+street+"'";
			Statement stat = mysqlDatabase.getStatement();
			ResultSet result;
			try {
				result = stat.executeQuery(mySQLQuery);

				while (result.next()) {
					Item item = new Item(result.getString(1), result.getInt(2), result.getInt(3), result.getString(4),result.getDouble(5),result.getDouble(6),result.getString(7),result.getString(8),result.getString(9),result.getString(10),result.getString(11));			
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// Close DB Connection
			mysqlDatabase.closeConnection();
			return items;
		}
	
	public UserItem getUser(String name, String password) {
		System.out.println("Connect to the database and find user with Username ="+ name+" and Password = "+password);
		
		UserItem item = new UserItem();

		mysqlDatabase.openConnection();
		Connection connection = mysqlDatabase.getConnection();

		String mySQLQuery = "SELECT * FROM users WHERE username ='"+ name+"' and password = '"+password+"'";
		Statement stat = mysqlDatabase.getStatement();
		ResultSet result;
		try {
			result = stat.executeQuery(mySQLQuery);

			while (result.next()) {
				item = new UserItem(result.getInt(1),result.getString(2), result.getString(3));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mysqlDatabase.closeConnection();
		return item;
	}
	
	public Item saveNewUser(Item item) {
		System.out.println("Connect to the database and insert a new user");		
		Item newItem=new Item();
		mysqlDatabase.openConnection();
		Connection connection = mysqlDatabase.getConnection();

		String mySQLQuery = "INSERT INTO users (username, password, email)VALUES ('"+item.getUsername()+"','"+item.getPassword()+"','"+item.getEmail()+"')";
		Statement stat = mysqlDatabase.getStatement();

		try {
			int result = stat.executeUpdate(mySQLQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mySQLQuery = "select * from users  ORDER BY userId DESC LIMIT 1" ;
		System.out.println("Username is: " + item.getUsername());
		try {
			ResultSet result = stat.executeQuery(mySQLQuery);

			while (result.next()) {
				newItem = new Item(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Close DB Con
		mysqlDatabase.closeConnection();	
		System.out.println("NIT: "+newItem.toString());
		return newItem;
	}
	
		public ArrayList<Item> getReportsForPrediction(String street) {
			System.out.println("Connect to the database and get items for prediction");
			
			ArrayList<Item> items = new ArrayList<Item>();

			mysqlDatabase.openConnection();
			Connection connection = mysqlDatabase.getConnection();

			String mySQLQuery = "SELECT users.username, reports.* FROM users join reports on users.userId=reports.userId WHERE `reports`.date_time > DATE_SUB(NOW(), INTERVAL 7 DAY) and street='"+street+"' and (report='Traffic Congestion' or report='Accident')";

			Statement stat = mysqlDatabase.getStatement();
			ResultSet result;
			try {
				result = stat.executeQuery(mySQLQuery);

				while (result.next()) {
					Item item = new Item(result.getString(1),result.getInt(2), result.getInt(3), result.getString(4), result.getDouble(5),result.getDouble(6),result.getString(7),result.getString(8),result.getString(9),result.getString(10),result.getString(11));			
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// Close DB Connection
			mysqlDatabase.closeConnection();
			return items;
		}
	}


