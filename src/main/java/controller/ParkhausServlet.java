package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import services.*;

@WebServlet(name="controller.ParkhausServlet", value="")
public class ParkhausServlet extends HttpServlet {
    private static ParkhausIF parkhaus;

    /**
     * ParkhausServlet wird als erstes aufgerufen und erzeugt dabei initial ein Parkhaus
     */
    public void init(){
        if (parkhaus == null) {
            this.parkhaus = new Parkhaus();
            getServletContext().setAttribute("parkhaus", this.parkhaus);
            System.out.println("*** Parkhaus erfolgreich erstellt ***");
        } else {
            this.parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
        }

    }

    /**
     * erster get-request landet hier und leitet aufs das Dashboard weiter
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        addSchrankenParams(req);
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.sendRedirect("/checkIn");
    }

    @Override
    public void destroy() {
    }

    public static void addSchrankenParams(HttpServletRequest req) {
        int ase = parkhaus.getEinfahrtSchranken().length;
        int asa = parkhaus.getAusfahrtSchranken().length;

        req.setAttribute("anzahl-schranken-einfahrt", ase);
        req.setAttribute("anzahl-schranken-ausfahrt", asa);
    }
}
