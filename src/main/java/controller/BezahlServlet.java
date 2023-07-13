package controller;

import services.ParkhausIF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name="controller.BezahlServlet", value="/bezahlen")
public class BezahlServlet extends HttpServlet {

    public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ParkhausIF parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
        // ticket id holen
        String ticketNr = req.getParameter("ticket");
        if (ticketNr == null) {
            req.setAttribute("bezahlt", false);
            ParkhausServlet.doOnEveryRequest(req, parkhaus);
            req.getRequestDispatcher("index.jsp").forward(req, res);
            return;
        }

        // die aktuelle (gePOSTete Zeit) parsen
        LocalDateTime time = parkhaus.getAktuelleZeit();

        // ticket bezahlen
        parkhaus.getAutomat().bezahlen(parkhaus.getTicket(Integer.parseInt(ticketNr)), time);
        req.setAttribute("bezahlt", true);

        ParkhausServlet.doOnEveryRequest(req, parkhaus);
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }
}
