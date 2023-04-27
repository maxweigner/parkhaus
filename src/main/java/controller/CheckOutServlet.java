package controller;

import services.ParkhausIF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, NumberFormatException {
        // dem request die anzahl der schranken mitgeben

        // todo braucht man das?
        ParkhausServlet.addSchrankenParams(req);


        // todo wichtig ist es nach dem setzen von ticket.gueltigkeit = false, den Server die aktuelle Liste zu schicken mit: DANKE :)
        //req.setAttribute("bezahlteTickets", parkhaus.getBezahlteTickets());
        //req.setAttribute("aktiveTickets", parkhaus.getAktiveTickets());
        /*try {
            int id = Integer.parseInt(req.getParameter("idAusfahrt")); // übergebene ID
            TicketIF ticket = parkhaus.getTicket(id - 1); // Ticket wird gesucht
            SchrankeIF schranke = parkhaus.getAusfahrtSchranken()[0];
            // todo: bezahlen fehlt. Aufgabe für den BezahlServlet
            boolean erfolg = parkhaus.ausfahrt(ticket, schranke);
            if (erfolg) { // Ticket bezahlt
                req.getRequestDispatcher("/index.jsp").forward(req, res); // erfolgreiche Ausfahrt
            } else { // Ticket unbezahlt oder Bezahlung zu lange her
                req.setAttribute("error", "Ticket unbezahlt oder Nachzahlung notwendig");
                req.getRequestDispatcher("/index.jsp").forward(req, res);
            }

        } catch (NumberFormatException | IndexOutOfBoundsException error){
            req.setAttribute("error", "ungültige Eingabe");
            req.getRequestDispatcher("/index.jsp").forward(req, res); // Fehlermeldung
        }*/

        String id = req.getParameter("ticket");
        parkhaus.getTicket(Integer.parseInt(id)).setGueltigkeit(false);
        System.out.println(id);

        req.setAttribute("bezahlteTickets", parkhaus.getBezahlteTickets());
        req.setAttribute("aktiveTickets", parkhaus.getUnbezahlteTickets());
        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
}
