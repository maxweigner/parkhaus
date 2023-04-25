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

@WebServlet(name="controller.CheckOutServlet", value="/checkOut")
public class CheckOutServlet extends HttpServlet {
    ParkhausIF parkhaus; // hole aktuelle Instanz

    public void init(){
        this.parkhaus = ParkhausServlet.getParkhaus();
    }

    /**
     * Beim Verlassen wird die Ausfahrt simuliert. Dafür muss der Verwender die ID des Tickets übergeben.
     * @throws NumberFormatException: leeres Inputfeld
     * @throws IndexOutOfBoundsException: Ticket konnte unter gegebener ID nicht gefunden werden
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, NumberFormatException {
        try {
            int id = Integer.parseInt(req.getParameter("idAusfahrt")); // übergebene ID
            TicketIF ticket = parkhaus.getTicketListe().get(id - 1);

            /**
             * todo: LOGIK FEHLT
             */
            res.sendRedirect(req.getContextPath()+"/index.jsp?id=0"); // erfolgreiche Ausfahrt
        } catch (NumberFormatException | IndexOutOfBoundsException error){
            res.sendRedirect(req.getContextPath()+"/index.jsp?id=-1"); // Fehlermeldung
        }
    }
}
