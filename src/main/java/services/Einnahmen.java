package services;

public class Einnahmen implements EinnahmenIF{

    private double summe = 0;
    private int tickets = 0;


    @Override
    public void addIncome(float betrag) {
       summe += betrag;
       tickets ++;
    }

    @Override
    public float averageIncome() {
       return (float)(summe/tickets);
    }

    public int soldTickets() {
        return tickets;
    }

    public float totalIncome() {
        return (float)summe;
    }
}
