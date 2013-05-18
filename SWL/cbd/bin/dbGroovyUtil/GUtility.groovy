package dbGroovyUtil
import groovy.sql.*;
import java.sql.*;
/**
 * This class is the Utilities class that connects controllerB to the DB. DB calls are made through this class. Functions include:
 * allTracked(), removeAllCourses(), removeAllTracked(), updateNotified(), and bulkLoad(), closeDB().
 * @author Jessica Frierson Capstone project: Student Major Assistant
 *
 */
class GUtility {

	/**
	 * varaiables used to store values for the Sql.newInstance which creates a new connection to the DB specified with the driver specified.
	 */
	def username = "friersoj"
	def password = "Jaden.12"
	def driver = "com.mysql.jdbc.Driver"
	def sql = Sql.newInstance ("jdbc:mysql://zoe.cs.plu.edu:3306/sma_db_499", username, password, driver)
	//def sql = Sql.newInstance("jdbc:mysql://localhost:2000/sma_db_499", username, password, driver)

	
	/**
	 * closeDB() closes the DB connection. 
	 */
	void closeDB(){
		sql.close();
	}

	/**
	 * This function can be used as a backup function to bulkUpload(). loadCourses() can insert course information into the DB course table.
	 * @param subj The course subject usually 4 characters long ex: CSCE.
	 * @param courseNo The course number usually three digits may contain a letter ex: 499B.
	 * @param section The section number of the course.
	 * @param crnNo The unique course identifier
	 * @param capacity The maximum allowed students in a course
	 * @param lname Last name of the professor
	 * @param fname First name of the professor
	 * @return The number of inserted courses into the DB
	 */
	int loadCourses(String subj, String courseNo, String section, String crnNo, int capacity, String lname, String fname){
		def result
		result = sql.executeInsert('INSERT into Course values(?, ?, ?, ?, ?, ?, ?)', [subj, courseNo, section, crnNo, capacity, lname, fname])
		return result
	}

	/**
	 * This functions calls for all of the courses tracked by the DB.
	 * @return Returns an Arraylist of all the courses tracked
	 */
	def allTacked(){
		def tracked = []
		String query = "SELECT T.CRN_Number, C.Capacity, Notified, Phone_number, Carrier, Email, S.User_name, T.Preference, Capacity " +
		"FROM Tracks as T, Student as S, Course as C "+
		"WHERE T.User_name = S.User_name and T.CRN_Number = C.CRN_Number"
		sql.eachRow(query){
			tracked << it.toRowResult().values()}
		return tracked
	}

	/**
	 * This function removes all the courses stored in the DB.  
	 * @return boolean value indicating if the result returns a result set
	 */
	boolean removeAllCourses(){
		boolean result
		result = sql.execute("TRUNCATE table Course")
		return result
	}

	/**
	 * This function removes all the tracked courses stored in the DB.
	 *  @return boolean value indicating if the result returns a result set
	 */
	boolean removeAllTracked(){
		boolean result
		result = sql.execute("TRUNCATE table Tracks")
		return result
	}

	/**
	 * This function updates the student's status from not being notified to being notified
	 * @param fname First name of the student
	 * @param lname Last name of the student
	 * @param uname Student's user name
	 * @param update Value to update the DB with usually 'Y'
	 * @param crn CRN of the course to update the status of
	 * @return boolean value indicating if the result returns a result set
	 */
	boolean updateNotified(String update, String uname, String crn){
		boolean result
		result = sql.executeUpdate("UPDATE Tracks SET notified = ? " +
			"WHERE User_name = ? and CRN_number = ?", [update, uname, crn])
		return result
	}

	/**
	 * Bulk uploads data into the database from a csv file
	 * @param filename file name of the file to upload (may require full path)
	 * @return boolean value indicatind if the result returns a result set
	 */
	boolean bulkLoad(String filename){
		boolean result
		result = sql.executeUpdate("LOAD DATA LOCAL INFILE ? INTO TABLE Course FIELDS TERMINATED By ',' LINES TERMINATED BY '\n'", [filename])
		return result
	}
}
