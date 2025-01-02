package arithmatic_operations;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Calculator extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the numbers and operation from the request
        String num1Str = request.getParameter("number1");
        String num2Str = request.getParameter("number2");
        String operation = request.getParameter("operation");

        // Set the response type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Convert the numbers to integers
            int num1 = Integer.parseInt(num1Str);
            int num2 = Integer.parseInt(num2Str);
            int result = 0;
            String operationName = "";

            // Perform the selected operation
            switch (operation) {
                case "add":
                    result = num1 + num2;
                    operationName = "Addition";
                    break;
                case "subtract":
                    result = num1 - num2;
                    operationName = "Subtraction";
                    break;
                case "multiply":
                    result = num1 * num2;
                    operationName = "Multiplication";
                    break;
                case "divide":
                    if (num2 != 0) {
                        result = num1 / num2;
                        operationName = "Division";
                    } else {
                        out.print("<h1>Error: Division by zero is not allowed.</h1>");
                        out.print("<a href='addition.html'>Go Back</a>");
                        return;
                    }
                    break;
                default:
                    out.print("<h1>Invalid operation selected.</h1>");
                    out.print("<a href='addition.html'>Go Back</a>");
                    return;
            }

            // Display the result
            out.print("<h1>The result of " + operationName + " of " + num1 + " and " + num2 + " is: " + result + "</h1>");
        } catch (NumberFormatException e) {
            out.print("<h1>Invalid input. Please enter valid numbers.</h1>");
        }
        out.print("<a href='addition.html'>Go Back</a>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect POST requests to doGet
        doGet(request, response);
    }
}