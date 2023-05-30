package controller;

import models.TicketIF;
import services.ParkhausIF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="controller.LadestationServlet", value="/startLaden")
public class StartLadenServlet extends HttpServlet {
    ParkhausIF parkhaus;

    public void init(){
        parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
    }

    public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String ticketStr = req.getParameter("ticket"); // ausgewähltes Ticket


        if (ticketStr != null) {
            TicketIF ticket = parkhaus.getTicket(Integer.parseInt(ticketStr));
            if(ticket.getStartLadeZeit() == null)
                parkhaus.startLaden(ticket);
        }

        ParkhausServlet.doOnEveryRequest(req); // Endroutine
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }
}
