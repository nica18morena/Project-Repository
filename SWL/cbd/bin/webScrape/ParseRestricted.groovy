package webScrape
import java.util.regex.Matcher
class ParseRestricted {

	def foundRestricted(){
		//Variables
		String crn, crs, sub, act
		int numcount
		boolean needed= false
		Map courses = new HashMap()
		List coursesInfo = new ArrayList()
		String regEx1= />[a-zA-Z]{4}</// DEPT
		String regEx2 = />[0-9]{3}<///course#
		String regEx15= />[0-9]{3}[a-zA-Z]{1,2}<///second course#
		String regEx3 = />[0-9]{5}<///CRN #
		String regEx4 = />[0-9]{1,3}<///actual
		Matcher matcher, matcher2, matcher3, matcher4, matcher5

		//Opens and read the Banner Course HTML file
		def f = new File("restricted.html").eachLine {line ->
			if (line =~/^<TR>/){
				needed = true//we need to look into this section of HTML
				numcount = 0//counts the lines
			}
			//If we need to look at this part of the HTML
			if(needed){
				if (line=~/^<TD .+/){
					numcount++;
					//matches pattern for subject
					if(numcount == 3){
						matcher = line =~ regEx1
						if (matcher.find()){
							sub= matcher.group().toString().substring(1, 5)
							
						}
					}
					//matches pattern for course
					else if (numcount == 4){
						matcher2  = line =~ regEx2
						matcher4= line=~ regEx15
						if(matcher2.find()){
							crs= matcher2.group().toString().substring(1, 4)
						}
						else if(matcher4.find()){
							crs= matcher4.group().toString().substring(1,5)
						}
						
					}

					//matches pattern for CRN #
					else if(numcount == 6){
						matcher3  = line =~ regEx3
						if(matcher3.find()){
							crn= matcher3.group().toString().substring(1, 6)
							
						}
					}
					//matches pattern for actual
					else if(numcount == 11 || numcount == 19){
						matcher5  = line =~ regEx4
						if(matcher5.find()){
							act = matcher5.group().toString().substring(1, matcher5.group().toString().length() -1)
							

							//no longer need to look at HTML
							needed = false
						}
					}
				}
			}

			//if there are values for all these variables add them to the classes array
			if (sub != null && crs != null && crn != null && act !=null){
				coursesInfo = [sub, crs, act]
				//add by crn the information into catalog map
				
				courses.put(crn, coursesInfo)
				
				//empty out any values in classes
				sub = null;
				crs = null;
				crn = null;
				act = null;
				coursesInfo =[]
			}
		}
		
		return courses
	}
}
