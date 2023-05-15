package controller;

import services.ParkhausIF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="controller.LadestationServlet", value="/startLaden")
public class StartLadenServlet extends HttpServlet {
    ParkhausIF parkhaus;

    public void init(){
        parkhaus = (ParkhausIF) getServletContext().getAttribute("parkhaus");
    }
    public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String id = req.getParameter("ticket"); // ausgewähltes Ticket
        if (id == null) { // falls keins übergeben wurde
            ParkhausServlet.doOnEveryRequest(req);
            req.getRequestDispatcher("index.jsp").forward(req, res); // soll unverändert zurückgeleitet werden
        }
        parkhaus.startLaden(parkhaus.getTicket(Integer.parseInt(id)));
        ParkhausServlet.doOnEveryRequest(req); // Endroutine
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }
}
