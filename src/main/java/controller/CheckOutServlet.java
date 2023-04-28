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
import java.time.format.DateTimeFormatter;

@WebServlet(name="controller.CheckOutServlet", value="/checkOut")
public class CheckOutServlet extends HttpServlet {
    ParkhausIF parkhaus;

    public void init(){
        parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
    }

    /**
     * Beim Verlassen wird die Ausfahrt simuliert. Dafür muss der Verwender die ID des Tickets übergeben.
     * @throws NumberFormatException: leeres Inputfeld
     * @throws IndexOutOfBoundsException: Ticket konnte unter gegebener ID nicht gefunden werden
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String id = req.getParameter("ticket");
        TicketIF ticket = parkhaus.getTicket(Integer.parseInt(id));
        LocalDateTime time = LocalDateTime.parse(req.getParameter("checkOutTime"), DateTimeFormatter.ISO_DATE_TIME);
        ticket.setAusfahrtsZeit(time);
        if (parkhaus.ausfahrt(ticket, parkhaus.getAusfahrtSchranken()[0])){ // Ausfahrt misslungen

        }
        ParkhausServlet.doOnEveryRequest(req);
        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
}
