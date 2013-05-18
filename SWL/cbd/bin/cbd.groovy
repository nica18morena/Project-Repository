#!/usr/bin/env groovy

// Jessica Frierson and Mark Peever, 2013-04-30
//

// System Adminstrator, please set these options:

def groovy = "which groovy".execute().text;

// End SysAdmin options

// We need a CLI parser so we can read our command line options
// See: http://www.comitservices.com/wp/?p=19

def cl = new CliBuilder(usage: 'cbd [OPTIONS]');
cl.h(longOpt:'help', 'Show usage information and quit');
cl.s(longOpt: 'start', 'Start the CBDaemon');
cl.p(longOpt: 'stop', 'Stop the CBDaemon');
def cbdhome = "/home/student/friersoj/SWL/cbd"
cl.h(longOpt: 'cbdHome', args: 1, required: false, "Specify the CBD_HOME [${cbdhome}]");
def pidfilename = "cbd.pid";
cl.f(longOpt: 'pidfile', args: 1, required: false,
     "Specify the PID file [\${CBD_HOME}/${pidfilename}]");
def libDir = "lib";
cl.l(longOpt: 'libDir', args: 1, required: false,
     "Specify a lib dir with JAR files [\${CBD_HOME}/${libDir}]");
def cbdaemon = 'bin/dbGroovyUtil/CBDaemon.groovy'
cl.d(longOpt: 'CBDaemon', args: 1, required: false,
     "Specify path of the CBDaemon [\${CBD_HOME}/${cbdaemon}]");

def opt = cl.parse(args);

// print a help message
if (opt.h){
    cl.usage();
    System.exit(1);
}

// change the pidfile
if(opt.f) {
    pidfilename = opt.f;
    println "Using pid file: ${pidfilename}";
} else {
    pidfilename = "${cbdhome}/${pidfilename}";
}
// change the lib dir
if(opt.l) {
  println "Using new libDir ${opt.l}";
  libDir = opt.l;
} else {
  libDir = "${cbdhome}/${libDir}"
}
//Use the specified path for CBDaemon
if(opt.d){
  println "Using CBDaemon ${opt.d}"
  cbdaemon = opt.d
} else {
  cbdaemon = "${cbdhome}/${cbdaemon}"
}
//Start CBDaemon
if (opt.s) {
    println "Starting the CBDaemon";
	
    def classpathFiles = ["${cbdhome}", "${cbdhome}/bin"];
    def dir = new File(libDir);
    dir.traverse(nameFilter:~/.*\.jar$/){ classpathFiles.add(it)};

    def classpath = classpathFiles.join(":");
    def command = "${groovy} -cp ${classpath} ${cbdaemon}";

    println "Command: ${command}";
    def proc = "${command}".execute();

    def pid = proc.pid;

    println "Child PID: ${pid}";
    def pidfile = new File(pidfilename);
    pidfile.write("${pid}");
    System.exit(0);
}
//define stop CBDaemon
if(opt.p){
    println "Stopping the CBDaemon";
    def pidfile = new File(pidfilename);
    def pid = -1;
    pidfile.each{line ->
        if(line) {
            pid = Integer.valueOf(line);
        }
    }
    
	//TERM 15
    def proc = "kill -15 ${pid}".execute();
    proc.waitFor();
    System.exit(proc.exitValue());
}

println "Invalid arguments!";
cl.usage();
System.exit(1);
