package controller;

import services.Parkhaus;
import services.ParkhausIF;
import services.EinnahmenIF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name="controller.AdminServlet", value="/admin")
public class AdminServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, NumberFormatException {
        // benötigte parameter einfügen
        ParkhausIF parkhaus = (ParkhausIF)getServletContext().getAttribute("parkhaus");
        ParkhausServlet.doOnEveryRequest(req, parkhaus);
        addParams(req);

        req.getRequestDispatcher("admin.jsp").forward(req, res);
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // benötigte parameter einfügen
        ParkhausIF parkhaus = (ParkhausIF)getServletContext().getAttribute("parkhaus");
        ParkhausServlet.doOnEveryRequest(req, parkhaus);

        //Monatsticket erstellen
        String aktion = req.getParameter("aktion");
        if (aktion.equals("checkInMonat")) {
            addParams(req); // falls die oeffnungszeiten sich nicht ändern, müssen die alten mitgegeben werden
            req.getRequestDispatcher("/checkInMonatsticket").forward(req, res);
            return;
        }

        // Parkhaus zurücksetzen
        if (aktion.equals("reset")) { // falls reset geklickt wurde
            ParkhausIF ph = new Parkhaus(); // erzeugt neues Parkhaus
            getServletContext().setAttribute("parkhaus", ph); // setzt Parkhaus in der DB
            addParams(req); // setze Einnahmen von null auf 0
            ParkhausServlet.doOnEveryRequest(req, ph); // Endroutine
            req.getRequestDispatcher("admin.jsp").forward(req, res);
            return;
        }


        String ticketNr = req.getParameter("ticket");
        String ticketPreis = req.getParameter("preis");
        String globalPreis = req.getParameter("preisGlobal");
        String oeffnungszeit = req.getParameter("oeffnungszeit");
        String schliesszeit = req.getParameter("schliesszeit");

        // Setzen vom Globalpreis
        if(globalPreis != null) {
            getServletContext().setAttribute("globalPreis", Integer.parseInt(globalPreis));
        }
        // Setzen von einzelnem Ticketpreis
        else if (ticketNr != null && ticketPreis != null) {
            parkhaus.getTicket(Integer.parseInt(ticketNr)).setPreis(Integer.parseInt(ticketPreis));
        }
        // Setzen der Öffnungszeit
        else if (oeffnungszeit != null && schliesszeit != null) {
            getServletContext().setAttribute("oeffnungszeit", LocalTime.parse(oeffnungszeit, DateTimeFormatter.ISO_LOCAL_TIME).minusMinutes(1));
            getServletContext().setAttribute("schliesszeit", LocalTime.parse(schliesszeit, DateTimeFormatter.ISO_LOCAL_TIME).plusMinutes(1));
        }

        addParams(req); // falls die oeffnungszeiten sich ändern, müssen die neuen mitgegeben werden
        req.getRequestDispatcher("admin.jsp").forward(req, res);
    }

    private void addParams(HttpServletRequest req) {
        ParkhausIF parkhaus = (ParkhausIF)getServletContext().getAttribute("parkhaus");
        EinnahmenIF einnahmen = parkhaus.getAutomat().getEinnahmen();

        req.setAttribute("VerkaufteTickets", einnahmen.soldTickets());
        req.setAttribute("DurchschnittlicheEinnahmen", einnahmen.averageIncome());
        req.setAttribute("Gesamteinnahmen", einnahmen.totalIncome());
        req.setAttribute("AnzahlEinfahrten", einnahmen.soldTickets() + parkhaus.getUnbezahlteTickets().length);
        req.setAttribute("AnzahlAusfahrten", einnahmen.soldTickets() - parkhaus.getBezahlteTickets().length);
        int belegtePlaetze = parkhaus.getKapazitaet() - parkhaus.getAnzahlFreiePlaetze();
        req.setAttribute("Auslastung", (int) ( (belegtePlaetze / (float)parkhaus.getKapazitaet()) * 100));

        String oeffnungszeit = ((LocalTime)getServletContext().getAttribute("oeffnungszeit"))
                .plusMinutes(1).toString();
        String schliesszeit = ((LocalTime)getServletContext().getAttribute("schliesszeit"))
                .minusMinutes(1).toString();

        req.setAttribute("oeffnungszeit", oeffnungszeit);
        req.setAttribute("schliesszeit", schliesszeit);
    }
}
