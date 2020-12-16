//EMILY THOMAS PROJECT 4
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.DriverManager; 

import java.io.IOException; 

import java.sql.Connection;  



public class project4 extends HttpServlet {   
   
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String url = null;
	 String username = null;
	 String password = null;
	 
	 ResultSet resultSet;
	 ResultSetMetaData metaData;
	 
	 Connection conn;
	 Statement statement;
	 
	 int numberOfColumns;
	 
	 
protected void doPost(HttpServletRequest request, HttpServletResponse response )
		   throws IOException, ServletException {
		    	doGet(request, response);
		        }


   @Override
protected void doGet( HttpServletRequest request, 
                      HttpServletResponse response ) throws ServletException, IOException  {
	   
	   String out = "<table>";
	   	HttpSession session = request.getSession(true);
	   	
	      url = "jdbc:mysql://localhost:3312/project4";
		  username = "root"; 
		  password = "root"; 
		  boolean connect = false;
		  
		   try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		   try {
			conn = DriverManager.getConnection(url, username , password);

			 connect = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out ="CONNECTION TO SERVER FAILED";
		}
		  
		  
		
		  
	  if(connect == true) {
	   //mySQL cmds
	   String input = request.getParameter("query");
	   try {
		statement = conn.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		out = "n1";
	}
	   if(!input.contains("select")) {
		   try {
			   String suppliers[] = new String[100];
			   int i = 0;
				statement.executeUpdate(input);
				if(input.contains("shipments")) {
				resultSet = statement.executeQuery("select * from shipments");
				String data;
				while(resultSet.next()) {
				data = resultSet.getString("quantity");
				int flag = 0;
				if(Integer.parseInt(data)>=100) {
					for(int h = 0; h<i; h++) {
						if(resultSet.getString("snum").equals(suppliers[h])) {
							flag =1;
							h = i;
						}
						else
							flag = 0;
					}
					if(flag == 0) {
					suppliers[i] = resultSet.getString("snum");
					i++;
					}
				}
				}
				int j;
				for(j=0; j<i; j++) {
					statement.executeUpdate("update suppliers set status = status + 5 where snum = '"+suppliers[j]+"'");
				}
			
				out = "BUSINESS LOGIC DETECTED<br>";
				out+=String.valueOf(j);
				out+=" supplier status marks updated.";
				}
				else {
					out = "UPDATE COMPLETED";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out = "ERROR<br>";
				out += e.toString();
			}
	   	}
		
	   else{
               try {
				resultSet = statement.executeQuery(input);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out = "QUERY COULD NOT BE EXECUTED<br>";
				out += e.toString();
			}
	   
		 
               try {
				metaData = resultSet.getMetaData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out = "n3";
			}
               
			try {
				numberOfColumns = metaData.getColumnCount();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out = "n4";
			}
			out+="<tr>";
               for(int i = 1; i<= numberOfColumns; i++){
                   try {
					out+=("<th>"+metaData.getColumnName(i)+"  </th>");
					
				} 
                   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					out = "n5";
				}
               }
               out+=("</tr>");
               out+=("<tr>");

               try {
				while (resultSet.next()){
				       for (int i = 1; i <= numberOfColumns; i++){
				           out+=("<td>"+(resultSet.getObject(i)).toString()+"</td>");
				         
				       }
				       out+=("</tr>");
				    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out = "n6";
			}
              
           
         
       
	   }
	  }
	  else {
		  out = "nope2";
	  }
       out+="</table>";
	  
	   session.setAttribute("queryResults", out);
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
       
       dispatcher.forward(request,  response);
       out = "";
   }
   

}
