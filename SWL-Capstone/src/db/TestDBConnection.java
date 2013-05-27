
package db;
import java.io.*;
import java.sql.*;
import java.util.*;

public class TestDBConnection {

	/**
	 * DBConnection object is created to have a utilities object for testing
	 * Scanner object is created to read input from user, reads from std in
	 */
	static DBConnection testobj = new DBConnection();//Utilities object for testing
	static Scanner keyboard = new Scanner(System.in);//standard input
	/**
	 * @param args
	 */
	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		int choice;
		boolean done = false;
		while (!done) {
			System.out.println();
			displaymenu();

			choice = getChoice();
			switch (choice) {
			case 1:{
				open();
				break;
			}
			case 2: {
				try{
					testobj.closeDB();
				} catch (NullPointerException e){
					System.out.println("The DB is already closed\n");
				}
				break;
			}
			case 3: {
				addCourse();
				break;
			}
			case 4:{
				viewAddedCourses();
				break;
			}
			case 5: {
				deleteaCourse();
				break;
			}
			case 6: {
				LoadCourses();
				break;
			}
			case 7: {
				user();
				break;
			}
			case 8: {
				removeStudent();
				break;
			}
			case 9: {
				TrackedCourses();
				break;
			}
			case 10: {
				RemoveAllCourses();
				break;
			}
			case 11:{
				RemoveAlltracked();
				break;
			}
			case 12:{
				updatesNotified();
				break;
			}
			case 13:{
				bulkUpload();
				break;
			}
			case 14:{
				inTheDB();
				break;
			}
			case 15:{
				userp();
				break;
			}
			case 16:{
				user_updates();
				break;
			}
			case 17:{
				viewProfile();
				break;
			}case 18:{
				updatePw();
				break;
			}case 19:{
				add_menu();
				break;
			}// end switch
			}
		}
	}// end main

	/**
	 * Displays the menu for the user
	 */
	static void displaymenu(){
		System.out.println("1) Open the DB");
		System.out.println("2) Close the DB");
		System.out.println("3) Student add a course");
		System.out.println("4) Student view tracked course");
		System.out.println("5) Student remove a course");
		System.out.println("6) Load courses into DB");
		System.out.println("7) Add a user");
		System.out.println("8) Remove a student");
		System.out.println("9) View all tracked courses");
		System.out.println("10) Remove all courses from DB");
		System.out.println("11) Remove all tracked courses from DB");
		System.out.println("12) Update notified status");
		System.out.println("13) Use bulk upload into DB");
		System.out.println("14) User in the DB?");
		System.out.println("15) Get password");
		System.out.println("16) Update profile");
		System.out.println("17) View profile");
		System.out.println("18) Update password");
		System.out.println("19) Menu to add courses");
	}//end displayMenu()

	/**
	 * A method that get the users selected menu choice
	 * @return The menu number they choose
	 */
	static int getChoice(){
		String input;
		int i=0;
		while (i < 1 || i > 19){
			try{
				System.out.print("Please enter an integer between 1 and 19: ");
				input = keyboard.nextLine();
				i = parseI(input);
				System.out.println();
			} catch (NumberFormatException e){
				System.out.println("You need to input an integer");
			}
		}
		return i;

	}// end getChoice

	/**
	 * This method opens the database
	 */
	/*static void openDB(){
		String username, password;
		System.out.print("Enter your username: ");
		username = keyboard.nextLine();
		System.out.print("Enter your password: ");
		password = keyboard.nextLine();
		testobj.openDB(username, password);
	}// end openDb()*/

	static void open() {
		testobj.openDB();
	}//end open()


	/**
	 * This method converts String input into an integer
	 * @param integer The string input that needs conversion
	 * @return The int representation of the String
	 */
	static int parseI(String integer){
		int i;
		try{ 
			i= Integer.parseInt(integer);
			return i;
		} catch(NumberFormatException e){
			i = -1;
			return i;
		}
	}// end parseI

	/**
	 * This method adds a course to be tracked
	 * @throws SQLException
	 */
	static void addCourse() throws SQLException{
		int rs;
		String uname, crnNo, pref;

		System.out.print("What is your user name?: ");
		uname = keyboard.next();
		System.out.print("Enter the course CRN number: ");
		crnNo = keyboard.next();
		System.out.print("What is your preference to recieve messages(E, P, B): E-> email, P-> phone, B-> both");
		pref = keyboard.next();

		rs = testobj.addCourse(uname, crnNo, pref);

		System.out.printf("Added %d courses.", rs);
	}// end addCouse()

	/**
	 * This method allows the user to view the courses they have added
	 * @throws SQLException
	 */
	static void viewAddedCourses() throws SQLException{
		ResultSet rs;
		String uname;
		try{
			System.out.print("What is your user name?: ");
			uname = keyboard.next();

			rs = testobj.viewAddCourse(uname);

			System.out.println("Courses added are: ");
			System.out.println("----------------------");

			while(rs.next()){
				System.out.printf("%s    %s    %s    %s    %s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (NullPointerException e){
			System.out.println("There is an error retrieving the data");
		}
	}// end viewAddCourse()

	/**
	 * This method allows the user to delete a course they are tracking
	 * @throws SQLException
	 */
	static void deleteaCourse() throws SQLException{
		int rs;
		String crnNO, user;
		System.out.print("Enter the CRN number for the course to remove: ");
		crnNO = keyboard.next();
		System.out.print("Enter your user name: ");
		user = keyboard.next();

		rs = testobj.deleteCourse(crnNO, user);
		System.out.printf("Deleted %d course(s)", rs);

	}//end deleteCourse()

	/**
	 * This method allows the application to load all the courses for the semester into the DB
	 */
	static void LoadCourses() throws SQLException{
		int rs, total;
		String subj, lname, fname, courseNo, section, crnNo, t4;
		int capacity;

		System.out.print("What is the name of the file to open ex:[filename.txt] ");
		String file = keyboard.nextLine();

		total = 0;
		try {
			BufferedReader rfile = new BufferedReader(new FileReader(file));

			String line;
			while((line =rfile.readLine())!= null){

				String[] data = line.split(",");
				subj = data[0];
				courseNo= data[1];
				section = data[2];
				crnNo= data[3];	
				t4= data[4];
				capacity = parseI(t4);
				lname = data[5];
				fname= data[6];

				rs = testobj.loadCourses(subj, courseNo, section, crnNo, capacity, lname, fname);
				total = rs + total;
			}

			rfile.close();
			System.out.printf("Inserted %d tuples ", total);
		} catch (IOException e) {
			System.out.println("Could not open specified file");
			//e.printStackTrace();
		}


	}//end loadCourses()

	/**
	 * This method allows the user to register and create an account
	 */
	static void user() throws SQLException{

		int rs;
		String email, fname, lname, uname, pword, pnumber, carrier;
		System.out.println("What is your email: ");
		email = keyboard.next();

		System.out.println("What is your first name: ");
		fname = keyboard.next();

		System.out.println("What is your last name: ");
		lname = keyboard.next();

		System.out.println("What is your user name: ");
		uname = keyboard.next();

		System.out.println("What is your password: ");
		pword = keyboard.next();

		System.out.println("What is your phone number (xxxxxxxxxx): ");
		pnumber = keyboard.next();

		System.out.println("What is your phone carrier: ");
		carrier = keyboard.next();

		rs = testobj.adduser(email, fname, lname, uname, pword, pnumber, carrier);
		System.out.printf("Added %d tuple", rs);

	}//end user()

	/**
	 * This method allows the student to delete their account
	 */
	static void removeStudent() throws SQLException{
		int rs;
		String uname;

		System.out.println("What is your user name: ");
		uname = keyboard.next();

		rs = testobj.removestudent(uname);
		System.out.printf("Deleted %d tuples", rs);
	}//end removeStudent()

	/**
	 * This method allows the application to pull all of the tracked courses from the DB
	 */
	static void TrackedCourses() throws SQLException{
		ResultSet rs = null;

		rs= testobj.trackedCourses();

		while(rs.next()){
			System.out.printf("%s    %s    %s    %s    %s    %s    %s    %s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
		}


	}//end trackedCourses

	/**
	 * This method allows the application to remove all the courses after the registration period ends
	 */
	static void RemoveAllCourses() throws SQLException{

		testobj.removeAllCourses();
		System.out.printf("Removed all entries in the table");

	}//end removeAllCourses()

	/**
	 * This method allows the application to remove all data that has been added to the tracked table
	 */
	static void RemoveAlltracked() throws SQLException{

		testobj.removeAlltracked();
		System.out.printf("Removed all entries in the table");
	}//end removeAlltracked

	static void updatesNotified() throws SQLException{

		//int rs;
		String uname, changeto, crn;

		System.out.println("What is your user name: ");
		uname = keyboard.next();

		System.out.println("For what class do you want to update? ");
		crn = keyboard.next();

		System.out.println("A notification will be sent");
		changeto = "Y";

		testobj.updateNotified(uname, changeto, crn);
	}// end updated Notified

	static void bulkUpload() throws SQLException{

		//String filename;

		System.out.println("What is the file name [ filename.txt]: ");
		//filename = keyboard.next();
		testobj.bulkUpload("C:\\loadDB.txt");

	}//end bulkUpload()

	static void inTheDB() throws SQLException{
		ResultSet rs;
		String uname, pword;

		System.out.print("Enter username: ");

		uname = keyboard.next();
		System.out.print("Enter password: ");
		pword = keyboard.next();

		rs = testobj.inDB(uname, pword);

		/*if(rs.getRow()== 0){
			System.out.println("The username or password you entered is not in the DB");
		}*/
		while(rs.next()){
			System.out.printf("%s    %s    %s    %s    %s    %s    %s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
		}

	}//end inTheDB

	static void userp() throws SQLException{
		ResultSet rs;
		String uname2;
		System.out.print("Enter username: ");
		uname2 = keyboard.next();

		rs= testobj.user(uname2);
		System.out.println(rs);
		while(rs.next()){
			System.out.printf("%s \n", rs.getString(1));
		}

	}// end user()

	static void user_updates() throws SQLException{
		String fname, lname, uname, pno, carrier, email;

		System.out.print("Enter your first name: ");
		fname =keyboard.next();
		System.out.print("Enter your last name: ");
		lname = keyboard.next();
		System.out.print("Enter your user name: ");
		uname= keyboard.next();
		System.out.print("Enter your phone number: ");
		pno=keyboard.next();
		System.out.print("Enter your email: ");
		email = keyboard.next();
		System.out.print("Enter your carrier: ");
		carrier = keyboard.next();

		testobj.user_update(email, fname, lname, pno, carrier, uname);

	}// end user_update()
	
	static void updatePw() throws SQLException{
		String pwn, uname;

		System.out.print("Enter your new password: ");
		pwn = keyboard.next();
		System.out.print("Enter your user name: ");
		uname= keyboard.next();
		

		testobj.update_pw(pwn, uname);

	}// end updatePw()

	static void viewProfile() throws SQLException{
		String uname;
		ResultSet rs= null;
		System.out.print("Enter your user name: ");
		uname= keyboard.next();

		rs = testobj.view_profile(uname);
		while(rs.next()){
			System.out.printf("%s     %s     %s     %s     %s     %s     %s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
		}
	}
	
	static void add_menu() throws SQLException{
		ResultSet rs = null;
		
		rs = testobj.addCoursesMenu();
		while(rs.next()){
			System.out.printf("%s     %s     %s     %s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
		}
	}
}
