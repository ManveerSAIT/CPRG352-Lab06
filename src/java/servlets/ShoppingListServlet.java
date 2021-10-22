
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    private ArrayList<String> items = new ArrayList<>();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        //If the user clicks the logout link
        if(action != null && action.equals("logout")) {
            //end session
            session.invalidate(); 
        }
        
        //show the register page
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        return;
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //get the value of action
        String action = request.getParameter("action");
        
        //if the user clicks the Register button
        if(action.equals("register")){
            String user_name = request.getParameter("username");
            if(user_name == null || user_name == "") {
                request.setAttribute("noUsername", "Please enter a username.");
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
            session.setAttribute("userName", user_name);
        }
        //if the user clicks Add
        if(action.equals("add")){
            String item = request.getParameter("itemEntered");
            items.add(item);
            session.setAttribute("items", items);
        }
        //if the user clicks Delete
        if(action.equals("delete")){
            String item = request.getParameter("item");
            items.remove(item);
            session.setAttribute("items", items);
        }
        //show the Shopping List page
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        return;
    }
    
}
