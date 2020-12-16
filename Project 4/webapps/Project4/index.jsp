<!DOCTYPE html>
<!-- index.jsp -->

<% String query = (String) session.getAttribute("query");
  String queryResults = (String) session.getAttribute("queryResults");
  if(queryResults == null) queryResults = " ";
  %>
<head>
   <title>Project 4 by Emily Thomas</title>
   <meta charset="utf-8" />
   <style type="text/css">

	    body{background-color: lightblue; font-size: xx-large; text-align: center;}
		  smalltxt{font-size: x-large; text-align: right;}
      table{font-size: large;border:1px solid black; align-content: center;margin-left:auto;margin-right:auto;}
	</style>
</head>
<body>

   <p>

       <label>  Welcome to the Spring 2020 Project 4 Enterprise System
	             <br>A Remote Database Management System <br>
               <br>


               <smalltxt>
               You are connected to the Project 4 Enterprise System database.<br>
               Please enter and valid SQL query or update statement.<br>
               If no query/update command is initally provided the Execute button will display all suplier information in the database.<br>
               All execution results will appear below.<br>
               </smalltxt>
       </label>
       <br>
       <form action = "/Project4/p4" method = "post">
       <input type = "text" name = "query" value = "select * from suppliers" size =  "50"></input>

      <br>

      <input type = "submit" value = "Execute Command" ></input>
      <input type = "reset" value = "Reset Form" ></input>
      </form>



      <br>
      <smalltxt> Database Results: <br> </smalltxt> <br>
	</p>
  <hr>
  <%=queryResults%>

</body>
</html>
