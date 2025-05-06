# Project-Server-Client

<div>simple implementation of the server and client using the Java programming language.</div>

## To get started: 
 - <div>first -> To run the server program, go to the Server folder and run the Main class. </div>
 - <div>second-> To connect to the server, you can use the Telnet protocol (ip 127.0.0.1 port 1050) or launch the client interface by going to the Client folder and executing the Main class.</div>
  
## Analysis of the server graphical interface:
the server graphical interface is divided into two parts Client Status (on the right) and Server Status (on the left).

  - ?-Server Status serves - as an indicator of all processes that are taking place (server activation, user connection).
  - ?-Client Status serves - as an indicator of all processes that the user is performing (monitoring user actions).

## Analysis of the client graphical interface:
 the client graphical interface is divided into two parts: Table (on the right) and Command Line (on the left).

 - ?- Command Line is used to enter commands and create requests to obtain data from the server. Command Line is divided into a text field and a text field (it is used       to obtain hints such as incorrect command, help or shortcuts).

 - ?-Table displays data in the form of a table.

# Commands:
<div>commands can be entered regardless of their case</div>
## <div>commands structure</div>
    - GET_C-Unica
<div>The command consists of</div> 
- root (GET_C/M/T)
- separator ( - ) 
- Parameter (STRING)
<div> (!) It is important when entering a command between the root separator and the parameter (in the case of GET_M-P P the second parameter must be separated by a space) that there should be no spaces.</div> 

- <p>!(P is a parameter)!</p>

- <div> GET_T-(P String) -> returns data that have the same type </div>
- <div>GET_C-(P String) -> returns data that have the same category / classification</div>
- <div>GET_M-(P1 String) (P2 String) -> returns data that have the same municipality</div>

- <p>FIND-(P String) -> returns data that have the same keyword</p>

- <div>GET_LT -> returns a list of all types</div>
- <div>GET_LC -> returns a list of all categories / classifications</div>
- <div>GET_LM -> returns a list of all municipalities</div>

- <p>MAN -> returns a list of all commands and their purpose</p>

- <p>comands + -H (Help)-> provides information about the command and its purpose.</p>

