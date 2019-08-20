# sensor-data-simulation-webapp

The simulator is available as a web application and is based on Java version 1.8.


prerequisites:
  - Java version 8 or higher must be installed.
  - An Apache Tomcat server version 9 or higher must be installed.
  
Installation
  - Download the software from the repository.
  - Compiling the Software by running the "Build Tasks" in the "build.gradle"-File.
  - After the software has been compiled, the directory "build/libs" contains a ".war"-File with the name "machine-simulator.war".
  - This file must be placed in the directory "/webapps"-Folder of the Tomcat server.
  
Start
  - The server can now be started. The startup process can take a few seconds.

The simulator can be reached as follows:
  1. hostname:port/machine-simulator/
  2. hostname:port/machine-simulator/home
  
The setup and installation are now complete. The simulator is now ready for use.
