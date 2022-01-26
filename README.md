# SuperChatPalCRM

To run the project:

	*********************************************************************
	Easiest way of getting project working:
	
	a) setup MySQL database: please refer to 1st step below :
	
	b) install JavaFX library: https://gluonhq.com/products/javafx/
	download Windows SDK version, then unzip and open top folder, then open 'lib' folder,
	Copy the directory address as a text.
	
	c) update 'pathOfJavaFXLibrary.txt' file with the directory address.
	
	d) click run.bat to start the project.
	

	*********************************************************************




1) Setup the MySQL database:

	a) download MySQL workbench for Windows
	
	b) setup localhost server username and password.
	
	c) run the "setup_local_MySQL_database.sql" file to setup the database.
	
	d) update "databaseConfig.txt" while the first line is jdbc connection string
	, second line is username and the third line is password.

2) Run the project from eclipse IDE:


	a) external libraries required: 
		1. JavaFX
		2. javax.mail
		3. org.apache.commons.lang3.ObjectUtils
		4. mysql-connector-java-8.0.27.jar
		5. opencsv-5.5.2.jar
	
	b) VM arguments: 
	--module-path "path of javafx library" --add-modules javafx.controls,javafx.fxml
	
	c) keep the file structure, do not delete any file.
	
	
	
	
3) Run the jar file:

	a) make sure place the jar file inside the top level of the project folder.
		(when you download the file, it should automatically be in the correct hirarchy).

	b) open command line app on your windows machine. (ie.  cmd.exe)
		
		1) type `java -version` into the cmd, if your java version is below 17, then please
		update your java runtime to version 17, otherwise it is prone to errors.
		
		2) once you confirm that your java version is at least version 17, then change the current directory
		to the project folder top level (ie. where the .jar file is located).
		
		3) enter `java --module-path "path of javafx library" --add-modules javafx.controls,javafx.fxml -jar crm.jar`
		to run the project. (replace the location to your javafx library with path in the quote above)
		

4) Changing Base Email Address for sending the reminder email from when creating a new task/ modified task:

	a) save the email address and login password into "emailDetails.txt" file.
	
	b) makesure the new email address has to be a gmail address, also, set "Less secure app access" on for your account,
	for more info about how to setup, please go to: https://support.google.com/accounts/answer/6010255?hl=en
	
	
	
	
	
	
	
	
************************************************************************************************************************
hiddden feature:

1) Delete all login user accounts:

	a) when you are at the login panel, enter following:
	
		email:  		@admin
		password: 		root
		
	b) you will then be logged into an admin panel and you can delete all login user accounts there.

