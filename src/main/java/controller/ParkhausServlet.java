package controller;

import javax.servlet.ServletContext;
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
        this.parkhaus = new Parkhaus();
        System.out.println("*** Parkhaus erfolgreich erstellt");
    }

    /**
     * erster get-request landet hier und leitet aufs das Dashboard weiter
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.sendRedirect(req.getContextPath()+"/index.jsp");
    }

    /**
     * Ermoeglicht Zugriff auf weitreichende Applikationselemente
     * @return: laufende Instanz
     */
    private ServletContext getApplication(){
        return getServletConfig().getServletContext();
    }

    /**
     * Zugang für alle Servlets zur Instanz des Parkhauses
     * @return: aktuelles Parkhaus
     */
    public static ParkhausIF getParkhaus(){
        return parkhaus;
    }
}
