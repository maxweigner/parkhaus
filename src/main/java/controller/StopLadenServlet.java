package controller;

import models.TicketIF;
import services.ParkhausIF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name="controller.StopLadenServlet", value="/stopLaden")
public class StopLadenServlet extends HttpServlet {

    public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ParkhausIF parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
        String ticketStr = req.getParameter("ticket"); // ausgewähltes Ticket
        String stundenPreis = req.getParameter("preisGlobal");

        if (stundenPreis == null) { stundenPreis = "2"; }

        // das ticket holen und den ladevorgang stoppen soweit die variablen gesetzt sind
        if (ticketStr != null) {
            TicketIF ticket = parkhaus.getTicket(Integer.parseInt(ticketStr));
            parkhaus.stopLaden(ticket, Integer.parseInt(stundenPreis));
            ticket.setStartLadeZeit(null);
        }

        ParkhausServlet.doOnEveryRequest(req, parkhaus); // Endroutine
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }
}
