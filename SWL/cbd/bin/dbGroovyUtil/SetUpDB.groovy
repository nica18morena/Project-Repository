package dbGroovyUtil

import java.sql.SQLException;

class SetUpDB {
	static main(args) throws SQLException{

		GUtility utilityObj = new GUtility();
		String filename = "C:\\loadDB.txt"
		boolean bulk = utilityObj.bulkLoad(filename)
		assert bulk == true, "Error uploading the file"

	}
}