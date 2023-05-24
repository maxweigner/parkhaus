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

@WebServlet(name="controller.CheckInMonatsticket", value="/checkInMonatsticket")

public class MonatsticketServlet extends HttpServlet {
    ParkhausIF parkhaus;

    public void init() {
        parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        LocalDateTime time = parkhaus.getAktuelleZeit();

        TicketIF ticket = parkhaus.einfahrt(parkhaus.getEinfahrtSchranken()[0], 50); //EinfahrtSchranke
        ticket.setGesamtpreis(50);
        ticket.setEinfahrtsZeit(time);
        ticket.setMonatsTicket(true);
        System.out.println("Monatsticket erstellt: " + ticket);

        ParkhausServlet.doOnEveryRequest(req);
        req.getRequestDispatcher("/admin.jsp").forward(req, res);
    }
}
