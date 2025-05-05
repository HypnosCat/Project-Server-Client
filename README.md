Project-Server-Client
simple implementation of the server and client using the Java programming language.

(#) To get started: 
  first -> start the server program (Main class). 
  second->To connect to the server, you can use the Telnet protocol (ip 127.0.0.1 port 1050), or start the client interface (Main class).
  
(#) Analysis of the server graphical interface:
the server graphical interface is divided into two parts Client Status (on the right) and Server Status (on the left).

  ?-Server Status serves - as an indicator of all processes that are taking place (server activation, user connection).
  ?-Client Status serves - as an indicator of all processes that the user is performing (monitoring user actions).

(#) Analysis of the client graphical interface:
the client graphical interface is divided into two parts: Table (on the right) and Command Line (on the left).

  ?- Command Line is used to enter commands and create requests to obtain data from the server. Command Line is divided into a text field and a text field (it is used       to obtain hints such as incorrect command, help or shortcuts).

  ?-Table displays data in the form of a table.

(#) Commands (P is a parameter):
                  !(P is a parameter)!
GET_T-(P) -> returns data that have the same type
GET_C-(P) -> returns data that have the same category / classification
GET_M-(P) -> returns data that have the same municipality

FIND-(P) -> returns data that have the same keyword

GET_LT-(P) -> returns a list of all types
GET_LC-(P) -> returns a list of all categories / classifications
GET_LM-(P) -> returns a list of all municipalities

MAN -> returns a list of all commands and their purpose

comands + -H (Help)-> provides information about the command and its purpose.
