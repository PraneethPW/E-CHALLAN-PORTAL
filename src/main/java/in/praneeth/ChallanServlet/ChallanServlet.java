package in.praneeth.ChallanServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.praneeth.DatabaseConnection.ChallanDB;

/**
 * Servlet implementation class ChallanServlet
 */
@WebServlet("/save")
public class ChallanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallanServlet() {
        super();
        System.out.println("SERVLET CREATED");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("SERVLET INITIALIZED");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultset=null;
		String number1=request.getParameter("number");
		try {
		connection=ChallanDB.getConnectionDB();
		String SelectQuery="SELECT * FROM callandetails where number = ?";
		statement=connection.prepareStatement(SelectQuery);
		statement.setString(1, number1);
		resultset=statement.executeQuery();
		PrintWriter out=response.getWriter();
		  while (resultset.next()) {
	            out.println("<table border='1' cellpadding='10'>");
	            out.println("<tr><th>Name</th><td>" + resultset.getString("name") + "</td></tr>");
	            out.println("<tr><th>Vehicle Number</th><td>" + resultset.getString("number") + "</td></tr>");
	            out.println("<tr><th>Vehicle Type</th><td>" + resultset.getString("type") + "</td></tr>");
	            out.println("<tr><th>Challan Type</th><td>" + resultset.getString("challantype") + "</td></tr>");
	            out.println("<tr><th>Amount</th><td>" + resultset.getString("amount") + "</td></tr>");
	            out.println("</table><br>");
	        }
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection=null;
		PreparedStatement statement=null;
	String name=request.getParameter("username");
	String number =request.getParameter("number");
	String Vtype=request.getParameter("vehicleType");
	String Ctype=request.getParameter("challanType");
	String amount=request.getParameter("fineAmount");
	try {
	connection=ChallanDB.getConnectionDB();
	String InsertQuery="INSERT INTO callandetails(name,number,type,challantype,amount) VALUES(? , ? , ? , ?, ?)";
	statement=connection.prepareStatement(InsertQuery);
	statement.setString(1, name);
	statement.setString(2, number);
	statement.setString(3, Vtype);
	statement.setString(4, Ctype);
	statement.setString(5, amount);
	
	int rowsAffected=statement.executeUpdate();
	PrintWriter out=response.getWriter();
	
		if (rowsAffected == 1) {
		    out.println("<!DOCTYPE html>");
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<meta charset='UTF-8'>");
		    out.println("<title>Challan Entry Success</title>");
		    out.println("<style>");
		    out.println("body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }");
		    out.println(".success-icon { width: 100px; height: 100px; margin-bottom: 20px; }");
		    out.println("h2 { color: green; }");
		    out.println("table { margin: 0 auto; border-collapse: collapse; margin-top: 30px; }");
		    out.println("th, td { border: 1px solid #ccc; padding: 10px 20px; }");
		    out.println("th { background-color: #f2f2f2; }");
		    out.println("</style>");
		    out.println("</head>");
		    out.println("<body>");

		    // ✅ Tick Mark SVG Graphic
		    out.println("<svg class='success-icon' viewBox='0 0 24 24' fill='none' xmlns='http://www.w3.org/2000/svg'>");
		    out.println("<circle cx='12' cy='12' r='10' stroke='green' stroke-width='2' fill='lightgreen'/>");
		    out.println("<path d='M7 13l3 3l7-7' stroke='green' stroke-width='2' fill='none' stroke-linecap='round' stroke-linejoin='round'/>");
		    out.println("</svg>");

		    out.println("<h2>Challan Added Successfully!</h2>");

		    out.println("<table>");
		    out.println("<tr><th>Name</th><td>" + name + "</td></tr>");
		    out.println("<tr><th>Number</th><td>" + number + "</td></tr>");
		    out.println("<tr><th>Vehicle Type</th><td>" + Vtype + "</td></tr>");
		    out.println("<tr><th>Challan Type</th><td>" + Ctype + "</td></tr>");
		    out.println("<tr><th>Amount</th><td>" + amount + "</td></tr>");
		    out.println("</table>");

		    out.println("</body>");
		    out.println("</html>");
		}
	
	
	
	
	
	
	
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

}
