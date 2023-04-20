package services;

public class Einnahmen implements EinnahmenIF{

    private double summe = 0;

    @Override
    public void addIncome(float betrag) {
       summe += betrag;
    }

    public float totalIncome() {
        return (float)summe;
    }
}
