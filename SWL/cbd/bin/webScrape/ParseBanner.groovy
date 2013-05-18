/*
 * This Groovy script pulls information from the HTML file and creates a csv file to upload into the database. 
 */

//Variables
String sub, crs, sec, crn, nm, nm2, cap, res
int numcount, m=0
boolean needed= false
boolean needed2 = false
Map catalog = new HashMap()
List classes = new ArrayList()
String regEx9= />[a-zA-Z]{4}</// DEPT
String regEx8 = />[0-9]{3}<///course#
String regEx15= />[0-9]{3}[a-zA-Z]{1,2}<///second course#
String regEx10 = />[0-9]{2}<///section
String regEx14 = />[a-zA-Z]{1,2}[0-9]{1,2}<///section part two.
String regEx11 = />[0-9]{5}<///CRN #
String regEx12 = />[^,]{1,15},[^<]{1,15}<///prints out last name and first name w initial
String regEx16 = />[a-zA-Z]{5}<///prints out names not with a comma
String regEx13 = />[0-9]{1,3}<///capacity
String regEx17 = /<TD .+>///inititial
String regEx18 = />[a-zA-Z]{10}<///Restricted

//Opens and read the Banner Course HTML file
def f = new File("banner.html").eachLine {line ->
	if (line =~/^<TR>/){
		needed = true//we need to look into this section of HTML
		numcount = 0//counts the lines
	}

	//If we need to look at this part of the HTML
	if(needed){
		//numcount = 0
		matcher = line=~regEx17
		
		if(matcher.find()){
			
			numcount++;
			
			if(numcount == 1){
				matcher1 = line =~ regEx18
				if( matcher1.find()){
					res = matcher1.group().toString().substring(1,(matcher1.group().toString().size()-1))
					
				}
			}
			//matches pattern for subject
			if(numcount == 3){
				matcher2 = line =~ regEx9
				if (matcher2.find()){
					sub= matcher2.group().toString().substring(1, 5)

				}
				
			}
			//matches pattern for course
			if (numcount == 4){
				matcher3  = line =~ regEx8
				matcher4 = line =~ regEx15
				if(matcher3.find()){
					crs= matcher3.group().toString().substring(1, 4)

				}
				else if(matcher4.find()){
					crs = matcher4.group().toString().substring(1, (matcher4.group().toString().size() -1))

				}
				
			}
			//matches pattern for section
			if(numcount == 5){
				matcher5  = line =~ regEx10
				matcher6 = line =~regEx14
				if(matcher5.find()){
					sec= matcher5.group().toString().substring(1, 3)

				}
				else if(matcher6.find()){
					sec = matcher6.group().toString().substring(1, 4)

				}
				
			}
			//matches pattern for CRN #
			if(numcount == 6){
				matcher7  = line =~ regEx11
				if(matcher7.find()){
					crn= matcher7.group().toString().substring(1, 6)

				}
				
			}
			//matches pattern for Capacity
			if(numcount == 14 | numcount == 18){
				matcher8  = line =~ regEx13
				if(matcher8.find()){
					cap= matcher8.group().toString().substring(1,(matcher8.group().toString().size() -1))

				}
				
			}
			//matches pattern for Instructor name
			if(!res.equals(null) & res.equals("RESTRICTED")){
				if(numcount == 13){
					matcher9  = line =~ regEx12
					matcher10 = line =~ regEx16
					if(matcher9.find()){
						nm= matcher9.group().toString().substring(1, (matcher9.group().toString().size() -1))
					
					}
					else if(matcher10.find()){
						nm = matcher10.group().toString().substring(1, (matcher10.group().toString().size() -1))
						nm= nm + ", "
						
					}
					
					res=null
				}
			}
			if(numcount == 21){
				matcher11  = line =~ regEx12
				matcher12 = line =~ regEx16
				if(matcher11.find()){
					nm= matcher11.group().toString().substring(1, (matcher11.group().toString().size() -1))

				}
				else if(matcher12.find()){
					nm = matcher12.group().toString().substring(1, (matcher12.group().toString().size() -1))
					nm= nm + ", "
				}
				
				//no longer need to look at HTML
				needed = false
			}
		}
		
	}//end if needed
	//if there are values for all these variables add them to the classes array
		if(!sub.equals(null) && !crs.equals(null) && !sec.equals(null) && !crn.equals(null) && !cap.equals(null) && !nm.equals(null)){
		classes = [sub, crs, sec, cap, nm]
		
	//add by crn the information into catalog map
		catalog.put(crn, classes)
	//empty out any values in classes
		sub = null;
		crs = null;
		sec = null;
		crn = null;
		cap = null;
		nm = null;
		classes =[]
	}
	
//Write information found into a file
	def bw = new File("loadDB.txt").newOutputStream().withWriter() { writer -> catalog.each{k, v -> writer.write"${v[0]},${v[1]},${v[2]},${k},${v[3]},${v[4]}\n"}}//prints out DB format

}//end for each line

