package application;

public class DataBaseConnectionCredentials {
	
	
	//public static String URL = "jdbc:mysql://MYSQL5045.site4now.net:3306/db_a7f545_crm";
	//public static String username = "a7f545_crm";
	//public static String password = "makemoney2022";
	
	//public static String URL = "jdbc:mysql://sql4.freesqldatabase.com:3306/sql4466569";
	//public static String username = "sql4466569";
	//public static String password = "792Zrxau3M";
	
	
	// local database
	public static String URL = FileReaderUtil.fileReader("databaseConfig.txt").get(0);
	public static String username = FileReaderUtil.fileReader("databaseConfig.txt").get(1);
	public static String password = FileReaderUtil.fileReader("databaseConfig.txt").get(2);
	
	
	
	// perhaps save detail into a file for persistance use, and read from file everytime.
}


