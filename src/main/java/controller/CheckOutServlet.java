package controller;

import services.Parkhaus;
import services.ParkhausIF;
import services.SchrankeIF;
import services.TicketIF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="controller.CheckOutServlet", value="/checkOut")
public class CheckOutServlet extends HttpServlet {
    ParkhausIF parkhaus;

    public void init(){
        parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
    }

    /**
     * Beim Verlassen wird die Ausfahrt simuliert. Dafür muss der Verwender die ID des Tickets übergeben.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, NumberFormatException {
        // dem request die anzahl der schranken mitgeben
        ParkhausServlet.addSchrankenParams(req);

        try {
            int id = Integer.parseInt(req.getParameter("id")); // übergebene ID
            /**
             * todo: LOGIK FEHLT
             */
            res.sendRedirect(req.getContextPath()+"/index.jsp?id=0"); // erfolgreiche Ausfahrt
        } catch (NumberFormatException nfe){ // leeres Inputfeld
            res.sendRedirect(req.getContextPath()+"/index.jsp?id=-1"); // Fehlermeldung
        }
    }

    @Override
    public void destroy() {
    }
}
