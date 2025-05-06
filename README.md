# Project-Server-Client

<div>simple implementation of the server and client using the Java programming language.</div>

## To get started: 
 - <div>first -> start the server program in the Server folder run Main class. </div>
 - <div>second->To connect to the server, you can use the Telnet protocol (ip 127.0.0.1 port 1050), or start the client interface int the Client folder run Main class.</div>
  
## Analysis of the server graphical interface:
the server graphical interface is divided into two parts Client Status (on the right) and Server Status (on the left).

  ?-Server Status serves - as an indicator of all processes that are taking place (server activation, user connection).
  ?-Client Status serves - as an indicator of all processes that the user is performing (monitoring user actions).

## Analysis of the client graphical interface:
the client graphical interface is divided into two parts: Table (on the right) and Command Line (on the left).

  ?- Command Line is used to enter commands and create requests to obtain data from the server. Command Line is divided into a text field and a text field (it is used       to obtain hints such as incorrect command, help or shortcuts).

  ?-Table displays data in the form of a table.

## Commands (P is a parameter):
<div>!(P is a parameter)!</div>
<div> GET_T-(P) -> returns data that have the same type </div>
<div>GET_C-(P) -> returns data that have the same category / classification</div>
<div>GET_M-(P1) (P2) -> returns data that have the same municipality</div>

<p>FIND-(P) -> returns data that have the same keyword</p>

<div>GET_LT -> returns a list of all types</div>
<div>GET_LC -> returns a list of all categories / classifications</div>
<div>GET_LM -> returns a list of all municipalities</div>

<div>MAN -> returns a list of all commands and their purpose</div>

<div>comands + -H (Help)-> provides information about the command and its purpose.</div>
