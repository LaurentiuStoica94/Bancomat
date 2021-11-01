ATM Instructions:

Things to know before you start the ATM:
1. In case you introduced an incorrect input value, not the expected format or value not in range of the scope, you will stay in a loop until you introduce the right value.
2. Notification files are cleaned out at the beginning of the program
3. Program is written in Java 11



The flow is the following:

First you start the ATM and you have two options:

1 Default 
* meaning that you will have 50 bills of 100, 50 bills of 50, 100 of 10, 100 of 5 and 100 of 1.

2 Custom 
* meaning that you are the person who decides how many bills of each kind you have. In this case you need to insert the amount for each specific bill.

Afterwards you start to retreat money. You can do as many iteration as you want, the ATM will stop when you introduce the value -1

	* When the ATM still has money but not the right stock for each needed you will receive a message
  
	* You will receive an error message if you want to retreat 0 or negative amount
  
	* You will be informed if the ATM has no more money
  
	* You will also see what bills and amount of each kind you retreated
	
	
	
NOTIFICATIONS:
ATM notifications:

	* Depending on the initial stock of the one hundred and fifty bills a notification it will be sent. Note: if the initial value was 0, in this case no notification it will be sent for that bill.
  
	* If you want to access the history of the notifications please check the file AtmNotifications.txt
	
Client notifications:

	--> For each transaction that exceeds 200 a notification it will be sent
  
	--> If you want to access the history of the notifications please check the file ClientNotifications.txt
