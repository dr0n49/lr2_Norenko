package ErrorApp2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/create")
public class CreateErrorsServ extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/createErrors.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBHandler dbHandler = null;

        try {
            dbHandler = new DBHandler();
            String name = request.getParameter("name");
            String date = request.getParameter("date");
            String description = request.getParameter("description");
            int typeID = Integer.parseInt(request.getParameter("typeID"));
            ErrErrors errErrors = new ErrErrors(1,name, date,description,typeID);
            dbHandler.addErrors(errErrors);
            response.sendRedirect(request.getContextPath()+"/index");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
