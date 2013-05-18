package dbGroovyUtil

import sun.misc.Signal
import sun.misc.SignalHandler

// Jessica Frierson and Mark Peever, 2013-04-30

class CBDaemon {
//creates varibles
    def name = "CB Daemon"
    static stop = 0
    def file ="output.txt"
   
 static main(args) {

	//create a child process
        def child = new CBDaemon()
        println "My name is ${child.name}"

        // Register our signal handlers
        Signal.handle(new Signal("TERM"), new SignalHandler() {
                
                public void handle(Signal sig) {                    
                    child.log("Received a TERM signal")
                    child.stop()                    
                }
                
            })   
        Signal.handle(new Signal("HUP"), new SignalHandler() {
                
                public void handle(Signal sig){
                    child.log("Received a HUP signal")
                }
                
            })
 	Signal.handle(new Signal("INT"), new SignalHandler() {
                
                public void handle(Signal sig){
                    child.log("Received an INT signal")
                    child.stop()
                }
                
            })        
        
        child.run()
        System.exit(0)
    }

/**
*Method allows for the time and message to be appended onto the log document
*message: The string message passed in to append to the log
*/
    def log(String message){
        def when = new Date().toString()
	def output = new File(file)
	output.append( "${when} ${message}\n")
    }

/**
*defines run to execute CBMain and log when CBMain begins and finishes executing
*/
    def run() {

        while (! stop) {
            log("${name} says \"Running Main!\"")
            CBMain.main(new String[0])
	
            log("${name} says \"Done running Main!\"")
            Thread.sleep(2000L)
        }
    }
/**
*Changes the value of stop from 0 to 1
*/
    def stop() {
        stop = 1
    }

}
