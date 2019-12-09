package ErrorApp2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/")
public class ShowAllErrors extends HttpServlet {
    DBHandler dbHandler;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            dbHandler = DBHandler.getInstance();
            ArrayList<ErrErrors> errErrors = new ArrayList<ErrErrors>();
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
