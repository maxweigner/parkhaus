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

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@WebServlet(name="controller.CheckInServlet", value="/checkIn")
public class CheckInServlet extends HttpServlet {
    ParkhausIF parkhaus;

    public void init(){
        parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
    }

    public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        LocalDateTime time = LocalDateTime.parse(req.getParameter("checkInTime"), ISO_LOCAL_DATE_TIME);
        int preis = (Integer) getServletContext().getAttribute("globalPreis");

        TicketIF ticket = parkhaus.einfahrt(parkhaus.getEinfahrtSchranken()[0], preis); //EinfahrtSchranke
        ticket.setEinfahrtsZeit(time);

        System.out.println("Ticket erstellt: " + ticket);

        ParkhausServlet.doOnEveryRequest(req);
        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
}
