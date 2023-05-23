package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import models.TicketIF;
import services.*;

@WebServlet(name="controller.ParkhausServlet", value="")
public class ParkhausServlet extends HttpServlet {
    private static ParkhausIF parkhaus;

    /**
     * ParkhausServlet wird als erstes aufgerufen und erzeugt dabei initial ein Parkhaus
     */
    public void init(){
        this.parkhaus = new Parkhaus();
        getServletContext().setAttribute("parkhaus", this.parkhaus);
        getServletContext().setAttribute("globalPreis", 2);
        getServletContext().setAttribute("oeffnungszeit", LocalTime.parse("05:59", DateTimeFormatter.ISO_LOCAL_TIME));
        getServletContext().setAttribute("schliesszeit", LocalTime.parse("22:01", DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println("*** Parkhaus erfolgreich erstellt ***");
    }

    /**
     * erster get-request landet hier und leitet aufs das Dashboard weiter
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doOnEveryRequest(req);
        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String aktion = req.getParameter("aktion");
        String[] uhrzeiten = {
                req.getParameter("checkInTime"),
                req.getParameter("checkOutTime"),
                req.getParameter("startChargeTime"),
                req.getParameter("stopChargeTime"),
                req.getParameter("bezahlenTime")
        };
        LocalTime oeffnungszeit = (LocalTime) getServletContext().getAttribute("oeffnungszeit");
        LocalTime schliesszeit = (LocalTime) getServletContext().getAttribute("schliesszeit");

        for(String uhrzeit : uhrzeiten){
           if (uhrzeit == null)
               continue;

           LocalTime zeit = LocalDateTime.parse(uhrzeit, DateTimeFormatter.ISO_LOCAL_DATE_TIME).toLocalTime();
           if (!zeit.isAfter(oeffnungszeit) || !zeit.isBefore(schliesszeit)) {
               System.out.println("*** außerhalb der Öffnungszeiten ***");
               doGet(req, res);
                return;
           }
        }

        switch (aktion) {
            case "checkIn":
                req.getRequestDispatcher("/checkIn").forward(req, res);
                break;

            case "bezahlen":
                req.getRequestDispatcher("/bezahlen").forward(req, res);
                break;

            case "checkOut":
                req.getRequestDispatcher("/checkOut").forward(req, res);
                break;

            case "startLaden":
                req.getRequestDispatcher("/startLaden").forward(req, res);
                break;

            case "stopLaden":
                req.getRequestDispatcher("/stopLaden").forward(req, res);
                break;
        }
    }

    public static void doOnEveryRequest(HttpServletRequest req){
        TicketIF[] ladendeTickets = parkhaus.getLadendeTickets();
        TicketIF[] nichtLadendeTickets = parkhaus.getNichtLadendeTickets();

        TicketIF[] bezahlteTickets = parkhaus.getBezahlteTickets();
        TicketIF[] unbezahlteTickets = parkhaus.getUnbezahlteTickets();

        int freiePlaetze = parkhaus.getAnzahlFreiePlaetze();
        int belegtePlaetze = bezahlteTickets.length + unbezahlteTickets.length;
        int auslastung = (belegtePlaetze / (belegtePlaetze + freiePlaetze))*100;

        req.setAttribute("ladendeTickets", ladendeTickets);
        req.setAttribute("nichtLadendeTickets", nichtLadendeTickets);
        req.setAttribute("bezahlteTickets", bezahlteTickets);
        req.setAttribute("aktiveTickets", unbezahlteTickets);
        req.setAttribute("freiePlaetze", freiePlaetze);
        req.setAttribute("auslastung", auslastung);
        req.setAttribute("anzahl-schranken-einfahrt", parkhaus.getEinfahrtSchranken().length);
        req.setAttribute("anzahl-schranken-ausfahrt", parkhaus.getAusfahrtSchranken().length);
    }

    @Override
    public void destroy() {
    }
}
