package controller;

import services.ParkhausIF;
import models.TicketIF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name="controller.CheckInServlet", value="/checkIn")
public class CheckInServlet extends HttpServlet {

    public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ParkhausIF parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
        LocalDateTime time = parkhaus.getAktuelleZeit();
        int preis = (Integer) getServletContext().getAttribute("globalPreis");

        String id = req.getParameter("mTicket");
        if (!id.equals("")){ // Monatsticket wurde ausgewählt
            TicketIF ticket = parkhaus.getTicket(Integer.parseInt(id));
            parkhaus.einfahrt(parkhaus.getAusfahrtSchranken()[0], ticket);
        } else {
            TicketIF ticket = parkhaus.einfahrt(parkhaus.getEinfahrtSchranken()[0], preis); //EinfahrtSchranke
            ticket.setEinfahrtsZeit(time);
            System.out.println("Ticket erstellt: " + ticket);
        }


        ParkhausServlet.doOnEveryRequest(req, parkhaus);
        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
}
