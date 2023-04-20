import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

    @WebServlet(name = "EinfahrtServlet", value = "/inf_se1_ss23_Team_4_sose23_war/test")
    public class ParkhausServlet extends HttpServlet {
        //private String message;
        ServletContext application;
        Ticket ticket;

        boolean checkIn = false;
        Parkhaus parkhaus;

        public void init() {
            application = getApplcation();
        }

        ServletContext getApplcation(){
            return getServletConfig().getServletContext();
        }
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            response.setContentType("text/html");

            this.checkIn = Boolean.parseBoolean(request.getParameter("checkIn"));
            if (checkIn){
                ticket = parkhaus.einfahrt(parkhaus.getEinfahrtSchranken()[0]); //todo Haben wir mehrere Einfahrtschranken?
                System.out.println("Ticket erstellt");
            }
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<p>" + checkIn + "</p>");
            out.println("</body></html>");
        }

        public void destroy() {
        }
    }
