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

@WebServlet(name="controller.CheckInServlet", value="/checkIn")
public class CheckInServlet extends HttpServlet {
    ParkhausIF parkhaus;

    public void init(){
        parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
    }

    /**
     * Beim Betätigen des CheckIn-Buttons wird die Einfahrt simuliert und ein Ticket erstellt.
     * Die ID des Tickets wird dem Endnutzer angezeigt
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ParkhausServlet.addSchrankenParams(req);
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }

    public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // einfahrtsschranken holen
        SchrankeIF[] schranken = parkhaus.getEinfahrtSchranken();

        // ausgewählte nummer für schranke holen
        int schrankeNr = Integer.parseInt(req.getParameter("schranke"));

        // einfahrt bei ausgewählter Schranke, erstellen von ticket
        TicketIF ticket = parkhaus.einfahrt(schranken[schrankeNr]);

        // dem request die anzahl der schranken mitgeben
        ParkhausServlet.addSchrankenParams(req);
        // dem request die nummer des tickets mitgeben
        req.setAttribute("ticketID", ticket.getID());

        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
}
