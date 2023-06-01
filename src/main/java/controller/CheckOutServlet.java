package controller;

import models.TicketIF;
import services.ParkhausIF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="controller.CheckOutServlet", value="/checkOut")
public class CheckOutServlet extends HttpServlet {

    /**
     * Beim Verlassen wird die Ausfahrt simuliert. Dafür muss der Verwender die ID des Tickets übergeben.
     * @throws NumberFormatException: leeres Inputfeld
     * @throws IndexOutOfBoundsException: Ticket konnte unter gegebener ID nicht gefunden werden
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ParkhausIF parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
        String id = req.getParameter("ticket");
        TicketIF ticket = parkhaus.getTicket(Integer.parseInt(id));
        ticket.setAusfahrtsZeit(parkhaus.getAktuelleZeit());

        parkhaus.ausfahrt(ticket, parkhaus.getAusfahrtSchranken()[0]);

        ParkhausServlet.doOnEveryRequest(req, parkhaus);
        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
}
