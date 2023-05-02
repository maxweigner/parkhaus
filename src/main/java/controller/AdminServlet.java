package controller;

import models.TicketIF;
import services.ParkhausIF;
import services.EinnahmenIF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="controller.AdminServlet", value="/admin")
public class AdminServlet extends HttpServlet {

    EinnahmenIF einnahmen;
    ParkhausIF parkhaus;

    public void init(){
        parkhaus = (ParkhausIF)getServletContext().getAttribute("parkhaus");
        einnahmen = parkhaus.getAutomat().getEinnahmen();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, NumberFormatException {
        addParams(req);
        ParkhausServlet.doOnEveryRequest(req);
        req.getRequestDispatcher("admin.jsp").forward(req, res);
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // ticket id holen
        String ticketNr = req.getParameter("ticket");
        String ticketPreis = req.getParameter("preis");
        if (ticketNr == null || ticketPreis == null) {
            ParkhausServlet.doOnEveryRequest(req);
            req.getRequestDispatcher("admin.jsp").forward(req, res);
        }

        parkhaus.getTicket(Integer.parseInt(ticketNr)).setPreis(Integer.parseInt(ticketPreis));

        addParams(req);
        ParkhausServlet.doOnEveryRequest(req);
        req.getRequestDispatcher("admin.jsp").forward(req, res);
    }

    private void addParams(HttpServletRequest req) {
        req.setAttribute("VerkaufteTickets", einnahmen.soldTickets());
        req.setAttribute("DurchschnittlicheEinnahmen", einnahmen.averageIncome());
        req.setAttribute("Gesamteinnahmen", einnahmen.totalIncome());
        req.setAttribute("AnzahlEinfahrten", einnahmen.soldTickets() + parkhaus.getUnbezahlteTickets().length);
        req.setAttribute("AnzahlAusfahrten", einnahmen.soldTickets() - parkhaus.getBezahltNichtAusgefahren().length);
        req.setAttribute("Auslastung", (parkhaus.getUnbezahlteTickets().length + parkhaus.getBezahltNichtAusgefahren().length) / (float)parkhaus.getKapazitaet() * 100);
    }
}
