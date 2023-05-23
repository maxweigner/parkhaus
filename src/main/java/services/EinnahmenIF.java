package services;

public interface EinnahmenIF {

    /**
     * Fügt eine neue Transaktion hinzu
     * @param betrag die eingenommene Summe
     */
    void addIncome(float betrag);

    /**
     * Gibt den Durchschnittspreis aller bisher bezahlten Tickets an
     */
    float averageIncome();

    /**
     * Gibt die Anzahl der verkauften Tickets an
     */
    int soldTickets();

    /**
     * Gibt die gesamten services.Einnahmen an
     */
    float totalIncome();

}
