package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        if(aktion.equals("checkIn")){
            req.getRequestDispatcher("/checkIn").forward(req, res);
        } else if(aktion.equals("bezahlen")){
            req.getRequestDispatcher("/bezahlen").forward(req, res);
        } else if(aktion.equals("checkOut")){
            req.getRequestDispatcher("/checkOut").forward(req, res);
        }
    }

    public static void doOnEveryRequest(HttpServletRequest req){
        TicketIF[] bezahlteTickets = parkhaus.getBezahlteTickets();
        TicketIF[] unbezahlteTickets = parkhaus.getUnbezahlteTickets();
        int freiePlaetze = parkhaus.getAnzahlFreiePlaetze();
        int belegtePlaetze = bezahlteTickets.length + unbezahlteTickets.length;
        int auslastung = (belegtePlaetze / (belegtePlaetze + freiePlaetze))*100;



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
