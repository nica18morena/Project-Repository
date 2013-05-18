package webScrape

//This class pulls HTML from Banner and stores it in a file.

/**
 * html and html2 are URL objects. We print out the text giving us the HTML for each URL.
 * These 2 URL's pull all Banner courses offered for the current term
 */
String term1 ="201344";
String term2 ="201342";
String filename = "restricted.html";
def html1 = new URL("https://banweb.plu.edu/pls/pap/hxskschd.P_ListSchClassSimple?sel_subj=dummy&sel_day=dummy&sel_status=dummy&sel_status=R&sel_subj=ANTH&sel_subj=ARTD&sel_subj=BIOL&sel_subj=BUSA&sel_subj=BMSF&sel_subj=CHEM&sel_subj=CHLC&sel_subj=CHIN&sel_subj=CHSP&sel_subj=CLAS&sel_subj=COMA&sel_subj=CSCE&sel_subj=DANC&sel_subj=ECON&sel_subj=EDUC&sel_subj=EPSY&sel_subj=ENGL&sel_subj=ENVT&sel_subj=FREN&sel_subj=GEOS&sel_subj=GERM&sel_subj=GLST&sel_subj=GREK&sel_subj=HISP&sel_subj=HIST&sel_subj=IHON&sel_subj=KINS&sel_subj=LANG&sel_subj=LATN&sel_subj=MFTH&sel_subj=MATH&sel_subj=MILS&sel_subj=MUSI&sel_subj=NORW&sel_subj=NURS&sel_subj=PHIL&sel_subj=PHED&sel_subj=PHYS&sel_subj=POLS&sel_subj=PSYC&sel_subj=RELI&sel_subj=SCAN&sel_subj=SIGN&sel_subj=SOCW&sel_subj=SOCI&sel_subj=SPED&sel_subj=STAT&sel_subj=THEA&sel_subj=WMGS&sel_subj=WRIT&sel_crse=&sel_attr=%25&begin_hh=00&begin_mi=00&end_hh=00&end_mi=00&term="+ term1).text
def html2 = new URL("https://banweb.plu.edu/pls/pap/hxskschd.P_ListSchClassSimple?sel_subj=dummy&sel_day=dummy&sel_status=dummy&sel_status=R&sel_subj=ANTH&sel_subj=ARTD&sel_subj=BIOL&sel_subj=BUSA&sel_subj=BMSF&sel_subj=CHEM&sel_subj=CHLC&sel_subj=CHIN&sel_subj=CHSP&sel_subj=CLAS&sel_subj=COMA&sel_subj=CSCE&sel_subj=DANC&sel_subj=ECON&sel_subj=EDUC&sel_subj=EPSY&sel_subj=ENGL&sel_subj=ENVT&sel_subj=FREN&sel_subj=GEOS&sel_subj=GERM&sel_subj=GLST&sel_subj=GREK&sel_subj=HISP&sel_subj=HIST&sel_subj=IHON&sel_subj=KINS&sel_subj=LANG&sel_subj=LATN&sel_subj=MFTH&sel_subj=MATH&sel_subj=MILS&sel_subj=MUSI&sel_subj=NORW&sel_subj=NURS&sel_subj=PHIL&sel_subj=PHED&sel_subj=PHYS&sel_subj=POLS&sel_subj=PSYC&sel_subj=RELI&sel_subj=SCAN&sel_subj=SIGN&sel_subj=SOCW&sel_subj=SOCI&sel_subj=SPED&sel_subj=STAT&sel_subj=THEA&sel_subj=WMGS&sel_subj=WRIT&sel_crse=&sel_attr=%25&begin_hh=00&begin_mi=00&end_hh=00&end_mi=00&term="+ term2).text

/**
 * This creates a file that deletes existing "open.html" files and replaces it with a new one. The HTML from the above URLs
 * are written to the file.
 */
def file = new File(filename);
if(file.exists()){
	file.delete();
	file.createNewFile();
}
file.write(html1);
file.append(html2);


