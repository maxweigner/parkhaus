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

    public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ParkhausIF parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
        String ticketStr = req.getParameter("ticket"); // ausgewähltes Ticket

        if (ticketStr != null)
            parkhaus.startLaden(parkhaus.getTicket(Integer.parseInt(ticketStr)));

        ParkhausServlet.doOnEveryRequest(req, parkhaus); // Endroutine
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }
}
