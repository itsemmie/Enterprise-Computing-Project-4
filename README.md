# Enterprise-Computing-Project-4

![alt text](https://github.com/itsemmie/Enterprise-Computing-Project-4/blob/final/project-preview.PNG?raw=true)
![alt text](https://github.com/itsemmie/Enterprise-Computing-Project-4/blob/final/project-preview2.PNG?raw=true)

In this assignment you will utilize a suppliers/parts/jobs/shipments database
(creation/population script available on the course assignment page) as the back-end database. Frontend access to this database by the client will occur through a single page displayed in the client’s web
browser. The schema of the backend database consists of four tables with the following schemas for
each table:

suppliers (snum, sname, status, city) //information about suppliers

parts (pnum, pname, color, weight, city) //information about parts

jobs (jnum, jname, numworkers, city) //information about jobs

shipments (snum, pnum, jnum, quantity) //suppliers ship parts to jobs in specific quantities
 
 
The first-tier (client-level front-end) of your application will be a JSP page that allows the client to
enter SQL commands into a window (i.e. a form) and submit them to the server application for
processing. The front-end (and only the front-end) will utilize JSP technology. The client front-end
will provide the user a simple form in which they will enter a SQL command (any DML, DDL, or
DCL command could theoretically be entered by the user, however we will restrict to queries, insert,
update, replace, and delete commands). The front-end will provide only two buttons for the user, an
Execute button that will cause the execution of the SQL command they enter, and a Reset button that
simply clears any content in the form input area. The client front-end will run on any web-based
browser that you would like to use. You can elect to have a default query or not, it is entirely your
decision. The application will connect to the backend database as a root user client.

The second-tier servlet, in addition to handling the SQL command interface will also implement the
business/application logic. This logic will increment by 5, the status of a supplier anytime that supplier
is involved in the insertion/update of a shipment record in which the quantity is greater than or equal to
100. Note that any update of quantity >= 100 will affect any supplier involved in a shipment with a
quantity >= 100. The example screen shots illustrate this case. An insert of a shipment tuple (S5, P6,
J7, 400) will cause the status of every supplier who has a shipment with a quantity of 100 or greater to
be increased by 5. In other words, even if a supplier’s shipment is not directly affected by the update,
their status will be affected if they have any shipment with quantity >= 100. (See page 9 for a bonus
problem that implements a modified version of this business rule.) The business logic of the
second tier will reside in the servlet on the Tomcat web-application server (server-side application).
This means that the business logic is not to be implemented in the DBMS via a trigger.

The third-tier (back-end) is the persistent MySQL database described above and is under control of the
MySQL DBMS server. 
