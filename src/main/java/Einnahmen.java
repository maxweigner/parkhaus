public class Einnahmen implements EinnahmenIF{

    private double summe = 0;

    public void addIncome(float ticketpreis) {
       summe += ticketpreis;
    }

    public float totalIncome() {
        return (float)summe;
    }
}
