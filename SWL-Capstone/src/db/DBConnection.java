package db;

import java.sql.*;

public class DBConnection {

	public Connection conn = null;// Connection object


	public void openDB(){ 
		try { 
			Class.forName("com.mysql.jdbc.Driver"); 
		}catch (ClassNotFoundException e){
			System.out.println("Unalble to load driver."); 
		} 
		//String url ="jdbc:mysql://zoe.cs.plu.edu:3306/sma_db_499"; 
		String url = "jdbc:mysql://localhost:2000/sma_db_499"; 
		String username ="friersoj"; 
		String password = "Jaden.12";

		try{ 
			conn = DriverManager.getConnection(url, username, password);
			//System.out.println("You are connected"); 
		} catch (SQLException e){
			System.out.println("Error connecting to database: " + e.toString());
			System.exit(0); 
		}
	}// end openDB()

	/**
	 * Closed the database
	 */
	public void closeDB() {
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			System.out.println("Failed to close database connection: "
					+ e.toString());
		}
	}// end closeDB();

	/**
	 * This function allows students to add courses to track
	 * @param uname the students user name
	 * @param crnNo the crn number of the course to add
	 * @param pref the preference of notification
	 * @return The number of added courses
	 */
	public int addCourse(String uname, String crnNo, String pref) {
		int result;
		String sql = null;

		try {
			sql = "INSERT into Tracks(User_name, CRN_number, Preference) "+
					"values(?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.clearParameters();
			pstmt.setString(1, uname);
			pstmt.setString(2, crnNo);
			pstmt.setString(3, pref);

			result = pstmt.executeUpdate();

			return result;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return -1;

	}// end addCourse();


	/**
	 * This method allows the student to view the courses that they added
	 * @return The query results
	 */
	public ResultSet viewAddCourse(String uname) {

		ResultSet rset = null;
		String sql = null;

		try {
			sql = "SELECT T.CRN_Number, Subject, Course_Number, Section, Prof_Last "
					+ "FROM Tracks as T, Course as C "
					+ "WHERE T.CRN_Number = C.CRN_Number and T.User_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.clearParameters();
			pstmt.setString(1, uname);

			rset = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rset;
	}// end viewAddCourse()


	/**
	 * This method allows the student to delete a tracked course
	 * @param crnNo The crn number of the tracked course to delete
	 * @param user The user's username
	 * @return the number of deleted courses
	 */
	public int deleteCourse(String crnNo, String user) {

		int result;
		String sql = null;

		try {
			sql = "DELETE from Tracks " + "WHERE CRN_Number = ? and User_name= ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, crnNo);
			pstmt.setString(2, user);

			result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}// end deleteCourse()


	/**
	 * This function initializes the database with the course listings for the semester
	 * @param subj The course subject
	 * @param courseNo The course number
	 * @param section The course section
	 * @param crnNo The crn number for the course
	 * @param capacity The total amount of students allowed in the course
	 * @param lname The last name of the professor
	 * @param fname The first name of the professor
	 * @return
	 */
	public int loadCourses(String subj, String courseNo, String section, String crnNo,
			int capacity, String lname, String fname) {

		int result;
		String sql = null;

		try {
			sql = "INSERT into Course " +
					"values(?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, subj);
			pstmt.setString(2, courseNo);
			pstmt.setString(3, section);
			pstmt.setString(4, crnNo);
			pstmt.setInt(5, capacity);
			pstmt.setString(6, lname);
			pstmt.setString(7, fname);

			result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}// end loadCourses()


	/**
	 * This function adds a user to the database
	 * @param email The email a student registers with
	 * @param fname The student first name
	 * @param lname The student last name
	 * @param uname The students user name
	 * @param pword The students password
	 * @param pnumber The students phone number
	 * @param carrier The students cell phone carrier
	 * @return The number of added students
	 */
	public int adduser(String email, String fname, String lname, String uname,
			String pword, String pnumber, String carrier) {

		int result;
		String sql = null;

		try {
			sql = "INSERT into Student " +
					"values(?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, email);
			pstmt.setString(2, fname);
			pstmt.setString(3, lname);
			pstmt.setString(4, uname);
			pstmt.setString(5, pword);
			pstmt.setString(6, pnumber);
			pstmt.setString(7, carrier);

			result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}// end user

	/**
	 * This function removes a student from the database if a student wishes to delete their account
	 * @param uname The user name of the student
	 * @return the number of deleted students
	 */
	public int removestudent(String uname) {

		int result;
		String sql = null;
		try {
			sql = "DELETE from Student "
					+ "WHERE User_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, uname);

			result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}// end removeStudent()


	/**
	 * This function pulls from the database all students tacking a course
	 * @return The query results
	 */
	public ResultSet trackedCourses() {

		ResultSet rset = null;
		String sql = null;

		try {
			sql = "SELECT T.CRN_Number, C.Capacity, Notified, Phone_number, Carrier, Email, S.User_name, Preference "
					+ "FROM Tracks as T, Student as S "
					+ "WHERE T.User_name = S.User_name";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();
			return rset;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rset;
	}// end trackedCourses


	/**
	 * This function deletes all courses in the database after the semester is no longer accepting registrations
	 * @return the number of removed courses
	 */
	public int removeAllCourses(){
		int result;
		String sql = null;

		try{
			sql = "TRUNCATE table Course";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return -1;
	}// end removeAllCourses()


	/**
	 * This function deletes all courses tracked by students when they are no longer able to register
	 * @return the number of deleted courses
	 */
	public int removeAlltracked(){
		int result;
		String sql = null;

		try{
			sql = "TRUNCATE table Tracks";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return -1;
	}// end removeAlltracked()

	/**
	 * This function updates the Tracks table by updating the notified status
	 * @param uname the user name of the user
	 * @param update the update that needs to change- either Y or N
	 * @return the number of updated notified tables
	 */
	public int updateNotified(String uname, String update, String crn){
		int result;
		String sql = null;

		try{
			sql = "UPDATE Tracks " +
					"SET notified = ? " +
					"WHERE User_name = ? and CRN_number = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, update);
			pstmt.setString(2, uname);
			pstmt.setString(3, crn);

			result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return -1;
	}// end updateNotified()


	/**
	 * This function uploads a file of information for the Course table
	 * @param filename the name of the file with information
	 * @return the number of updated rows
	 */
	public int bulkUpload(String filename){
		int result;
		String sql = null;

		try{
			sql = "LOAD DATA LOCAL INFILE ? "+
					"INTO TABLE Course " +
					"FIELDS TERMINATED BY ',' "+
					"LINES TERMINATED BY '\n'";
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, filename);

			result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return -1;
	}// end bulkUpload

	/**
	 * This function grabs the information from a student based on their username and password
	 * @param uname The students username
	 * @param pword The students password
	 * @return ResultSet
	 */
	public ResultSet inDB(String uname, String pword){
		ResultSet rs = null; 
		String sql = null;
		try{
			sql ="SELECT * "+
					"FROM Student "+
					"WHERE User_name = ? and Password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, uname);
			pstmt.setString(2, pword);

			rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;
	}// end inDB()

	public ResultSet user(String uname){
		ResultSet rs = null;
		String sql = null;
		try{ 
			sql= "SELECT Password "+
					" FROM Student " +
					"WHERE User_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, uname);

			rs= pstmt.executeQuery();
			return rs;
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;
	}// end user

	public int user_update(String email, String fname, String lname, String phno, String carrier, String uname){
		int result;
		String sql = null;

		try{
			sql = "UPDATE Student "+
					"SET Email=?, First_name= ?, Last_name= ?, Phone_number= ?, Carrier= ? "+
					"WHERE User_name = ?";
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, email);
			pstmt.setString(2, fname);
			pstmt.setString(3, lname);
			pstmt.setString(4, phno);
			pstmt.setString(5, carrier);
			pstmt.setString(6, uname);


			result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return -1;
	}//end user_update()

	public int update_pw(String pw, String uname){
		int result;
		String sql = null;

		try{
			sql = "UPDATE Student "+
					"SET Password=? "+
					"WHERE User_name = ?";
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, pw);
			pstmt.setString(2, uname);

			result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return -1;
	}//end update_pw()

	public ResultSet view_profile(String uname){
		ResultSet rs = null;
		String sql = null;
		try{ 
			sql= "SELECT * "+
					" FROM Student " +
					"WHERE User_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, uname);

			rs= pstmt.executeQuery();
			return rs;
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;
	}//end view_profile()

	public ResultSet addCoursesMenu(){
		ResultSet rs = null;
		String sql;

		try{
			sql ="SELECT Subject, Course_Number, Section, CRN_number "+
					"FROM Course";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			rs= pstmt.executeQuery();
			return rs;
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;
	}//end addCourseMenu()

}
