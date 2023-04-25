package controller;

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

    public void init(){
        einnahmen = ((ParkhausIF)getServletContext().getAttribute("parkhaus")).getAutomat().getEinnahmen();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, NumberFormatException {
        req.setAttribute("VerkaufteTickets", einnahmen.soldTickets());
        req.setAttribute("DurchschnittlicheEinnahmen", einnahmen.averageIncome());
        req.setAttribute("Gesamteinnahmen", einnahmen.totalIncome());
        req.getRequestDispatcher("admin.jsp").forward(req, res);
    }
}
