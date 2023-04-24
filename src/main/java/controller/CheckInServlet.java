package controller;

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
        this.parkhaus = ParkhausServlet.getParkhaus(); // hole aktuelle Instanz
    }

    /**
     * Beim Betätigen des CheckIn-Buttons wird die Einfahrt simuliert und ein Ticket erstellt.
     * Die ID des Tickets wird dem Endnutzer angezeigt
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        SchrankeIF[] schranken = parkhaus.getEinfahrtSchranken(); // verfügbare Schranken
        TicketIF ticket = parkhaus.einfahrt(schranken[0]); // Einfahrt bei ausgewählter Schranke
        int id = ticket.getID(); // ID des soeben erzeugten Tickets
        res.sendRedirect(req.getContextPath()+"/index.jsp?id="+id);
    }

    @Override
    public void destroy() {
    }
}
