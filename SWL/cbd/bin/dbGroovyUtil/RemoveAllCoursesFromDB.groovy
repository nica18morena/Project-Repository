package dbGroovyUtil
import java.sql.SQLException
class RemoveAllCoursesFromDB {

	static main(args) throws SQLException{
		GUtility utilityObj = new GUtility();
		
		boolean numtracksRemoved = utilityObj.removeAllTracked()
		assert numtracksRemoved == false, "Could not delete the rows "
		
		boolean numCoursesRemoved = utilityObj.removeAllCourses()
		assert numCoursesRemoved == false, "Error removing courses"
		
	
	}

}
