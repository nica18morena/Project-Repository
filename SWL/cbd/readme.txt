Student Major Assistant: ControllerB
Contact: friersjm@plu.edu

Overview
========
Student Major Assistant is a web based application that allows students to track courses that are full and be notified when there are openings. ControllerB is a subsystem for Student Major Assistant that an administrator can use to execute the scripts that check course offering pages and notify users of open courses. 

System Requirements
===================
- Unix environment (other environments not intentionally supported)

Installation
============
No installation is required. Prior to use Groovy must be installed and can be downloaded from the following website:

http://groovy.codehaus.org/Download

How to run
==========
There are two ways to run this:

1.) In a Unix shell type in to view the settings to be configured:
	groovy cbd.groovy 
To use default settings type in:
 	groovy cbd.groovy -s

2.) Using Eclipse download the eclipse plug-in from:
http://dist.springsource.org/release/GRECLIPSE/e4.2/

Once plugin is installed create a new Groovy Project and add the files into the workspace. If using this option to run ControllerB, cbd.groovy and CBDaemon.groovy should not be added to the workspace. Those files may remain elsewhere.

Select CBMain and run the groovy script.

***Initializing the database with course information and removing course information from the database must be executed using option 2.) in How to run.

***Do not run these scripts between the times of 1:00 - 2:00 AM. At this time the course offering pages are down for maintenance.

***When executing option 1.) make sure to Kill the application when no longer "using" option 1 will continually run CBMain.groovy until a kill or stop signal( -p ) is received. 

Uninstallation
==============
NA

Author:
=======
Jessica Frierson <friersjm@plu.edu>

##
