package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import services.*;

@WebServlet(name="services.ParkhausServlet", value="")
public class ParkhausServlet extends HttpServlet {
    Parkhaus parkhaus;

    /**
     * ParkhausServlet wird als erstes aufgerufen und erzeugt dabei initial ein Parkhaus
     */
    public void init(){
        this.parkhaus = new Parkhaus();
        System.out.println("*** Parkhaus erfolgreich erstellt");
    }

    /**
     * erster get-request landet hier und leitet auf startpage weiter
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.sendRedirect(req.getContextPath()+"/index.jsp");
        System.out.println(req.getContextPath());
    }

    @Override
    public void destroy() {
    }

    /**
     * Ermoeglicht Zugriff auf weitreichende Applikationselemente
     * @return: laufende Instanz
     */
    private ServletContext getApplication(){
        return getServletConfig().getServletContext();
    }
}
