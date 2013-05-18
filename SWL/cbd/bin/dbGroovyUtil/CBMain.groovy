#!/usr/bin/env groovy
package dbGroovyUtil
import webScrape.PullOpenCourses
import webScrape.ParseOpenCourses
import webScrape.PullRestricted
import webScrape.ParseRestricted
import java.sql.ResultSet
import java.sql.SQLException
import sendMessage.SendEmail

class CBMain {
	static GUtility testobj = new GUtility()

	static main(args) throws SQLException{

	//Create instances of Pull and Parse Banner scripts/ classes
		def open = new PullOpenCourses()
		def restricted = new PullRestricted()
		def parseOpen =  new ParseOpenCourses()
		def parseRest = new ParseRestricted()
		def emailer = new SendEmail()
		
	//Execute open and restricted
		open.run()
		restricted.run()

	//Store returned values into variables
		def openCatalog = parseOpen.foundOpen()
		def restrictedCatalog = parseRest.foundRestricted()
		
	//Retrieve informatin from the DB of the students tracking courses
		def results = testobj.allTacked()
	
	//Parse the information from the db results into variables
		for ( int i = 0; i< results.size(); i++){
			String studentinfo = results[i].toString()
			String[] info  = studentinfo.split(", ")
			String temp1 = info[0]
			String CRN = temp1.substring(1)
			String cap = info[1]
			String ntfied = info[2]
			String pno = info[3]
			String carrier = info[4]
			String email = info[5]
			String uname = info[6]
			String temp2 = info[7]
			String pref= temp2.substring(0, 1)
			

			/**
			 * For Open Courses
			 */

		//Check if CRN numbers retrived from the DB are in the openCatalog map
		//and check if the student tracking that CRN number has been notified
			if (openCatalog.containsKey(CRN) && ntfied.equals("N")){
				
			//Retrive information from oprnCatalog and store values in variables
				String[] values = openCatalog[CRN].toString().split(", ")
				String sub = values[0].substring(1)
				String temp3 =values[1]
				String crs =temp3.substring(0, temp3.length()-1)
			
			//Send appropriate message
				if(pref.equals("P")){
					
					emailer.sendMail(pno + "@" + carrier, sub, crs)
				}
				if(pref.equals("E")){
					emailer.sendMail(email, sub, crs)
				}
				if(pref.equals("B")){
					
					emailer.sendMail(pno + "@" + carrier, sub, crs)

					emailer.sendMail(email, sub, crs)
				}
				println "in CBMain uname and CRN: ${uname}, ${CRN}"
	
			//Update the database
				testobj.updateNotified("Y", uname, CRN)
			}

			/**
			 * For Restricted Courses
			 */
		//Check if CRN numbers retrived from the DB are in the openCatalog map
		//and check if the student tracking that CRN number has been notified
			
			if (restrictedCatalog.containsKey(CRN) && ntfied.equals("N")){
				
			//retirieve informatin from restrictedCatalog and store in variables
				String[] values = restrictedCatalog[CRN].toString().split(", ")
				String sub = values[0].substring(1)
				String crs = values[1]
				String temp3 =values[2]
				String act =temp3.substring(0, temp3.length()-1)
				
				//Calculate if less students are registered than the capacity
				def diff = cap - act
				
				if(diff > 0){

					//Send appropriate message
					if(pref.equals("P")){
						
						emailer.sendMail(pno + "@" + carrier, sub, crs)
					}
					if(pref.equals("E")){
						emailer.sendMail(email, sub, crs)
					}
					if(pref.equals("B")){
						
						emailer.sendMail(pno + "@" + carrier, sub, crs)

						emailer.sendMail(email, sub, crs)
					}

				//Update the database
					testobj.updateNotified("Y", uname, CRN)
				}
			}
		}
	}
}
