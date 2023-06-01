package controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

import models.TicketIF;
import services.ParkhausIF;
import services.Parkhaus;

@WebServlet(name="controller.ParkhausServlet", value="")
public class ParkhausServlet extends HttpServlet {

    /**
     * ParkhausServlet wird als erstes aufgerufen und erzeugt dabei initial ein Parkhaus
     */
    public void init(){
        ParkhausIF parkhaus = new Parkhaus();
        getServletContext().setAttribute("parkhaus", parkhaus);
        getServletContext().setAttribute("globalPreis", 2);
        getServletContext().setAttribute("oeffnungszeit", LocalTime.parse("05:59", ISO_LOCAL_TIME));
        getServletContext().setAttribute("schliesszeit", LocalTime.parse("22:01", ISO_LOCAL_TIME));
        System.out.println("*** Parkhaus erfolgreich erstellt ***");
    }

    /**
     * erster get-request landet hier und leitet auf das Dashboard weiter
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ParkhausIF parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
        doOnEveryRequest(req, parkhaus);

        // das kann nicht in die doOnEveryRequest
        String oeffnungszeit = ((LocalTime)getServletContext().getAttribute("oeffnungszeit"))
                .plusMinutes(1).toString();
        String schliesszeit = ((LocalTime)getServletContext().getAttribute("schliesszeit"))
                .minusMinutes(1).toString();
        req.setAttribute("oeffnungszeit", oeffnungszeit);
        req.setAttribute("schliesszeit", schliesszeit);

        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ParkhausIF parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
        LocalTime oeffnungszeit = (LocalTime) getServletContext().getAttribute("oeffnungszeit");
        LocalTime schliesszeit = (LocalTime) getServletContext().getAttribute("schliesszeit");

        String changeTimeTo = req.getParameter("changeTimeTo");
        if (changeTimeTo != null) {
            LocalTime newTime = LocalDateTime.parse(changeTimeTo, ISO_LOCAL_DATE_TIME).toLocalTime();
            if (newTime.isBefore(oeffnungszeit) || newTime.isAfter(schliesszeit)) {
                System.out.println("*** Außerhalb der Öffnungszeiten ***");
                doGet(req, res);
                return;
            }
        }

        String aktion = req.getParameter("aktion");
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

            case "changeTime":
                parkhaus.setAktuelleZeit(
                        LocalDateTime.parse(
                                req.getParameter("changeTimeTo"), ISO_LOCAL_DATE_TIME)
                                .truncatedTo(ChronoUnit.MINUTES));
                doGet(req, res);
                break;
        }

        // das kann nicht in die doOnEveryRequest
        String open = oeffnungszeit.plusMinutes(1).toString();
        String close = schliesszeit.minusMinutes(1).toString();
        req.setAttribute("oeffnungszeit", open);
        req.setAttribute("schliesszeit", close);
    }

    public static void doOnEveryRequest(HttpServletRequest req, ParkhausIF parkhaus){
        TicketIF[] ladendeTickets = parkhaus.getLadendeTickets();
        TicketIF[] nichtLadendeTickets = parkhaus.getNichtLadendeTickets();

        TicketIF[] bezahlteTickets = parkhaus.getBezahlteTickets();
        TicketIF[] unbezahlteTickets = parkhaus.getUnbezahlteTickets();

        int freiePlaetze = parkhaus.getAnzahlFreiePlaetze();
        int belegtePlaetze = parkhaus.getKapazitaet() - parkhaus.getAnzahlFreiePlaetze();
        int auslastung = (int) ( (belegtePlaetze / (float)parkhaus.getKapazitaet()) * 100);

        String aktuelleZeit = parkhaus.getAktuelleZeit().toString();

        req.setAttribute("currentTime", aktuelleZeit);
        req.setAttribute("ladendeTickets", ladendeTickets);
        req.setAttribute("nichtLadendeTickets", nichtLadendeTickets);
        req.setAttribute("bezahlteTickets", bezahlteTickets);
        req.setAttribute("aktiveTickets", unbezahlteTickets);
        req.setAttribute("freiePlaetze", freiePlaetze);
        req.setAttribute("auslastung", auslastung);
        req.setAttribute("anzahl-schranken-einfahrt", parkhaus.getEinfahrtSchranken().length);
        req.setAttribute("anzahl-schranken-ausfahrt", parkhaus.getAusfahrtSchranken().length);
    }
}
