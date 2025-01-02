package arithmatic_operations;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Addition extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the numbers from the request
        String num1Str = request.getParameter("number1");
        String num2Str = request.getParameter("number2");

        // Convert the numbers to integers
        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);

        // Perform the addition
        int sum = num1 + num2;

        // Set the response type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Display the result
        out.print("<h1>The sum of " + num1 + " and " + num2 + " is: " + sum + "</h1>");
        out.print("<a href='addition.html'>Go Back</a>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect POST requests to doGet
        doGet(request, response);
    }
}