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
import java.time.LocalDateTime;

@WebServlet(name="controller.BezahlServlet", value="/bezahlen")
public class BezahlServlet extends HttpServlet {
    ParkhausIF parkhaus;

    public void init(){
        parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect("/");
    }

    public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // ticket id holen
        int ticketNr = Integer.parseInt(req.getParameter("ticketIdBezahlen"));
        LocalDateTime now = LocalDateTime.parse(req.getParameter("bezahlenTime"));

        // ticket bezahlen
        parkhaus.getAutomat().bezahlen(parkhaus.getTicket(ticketNr), now);
        req.setAttribute("bezahlt", true);

        req.getRequestDispatcher("index.jsp").forward(req, res);
    }
}
