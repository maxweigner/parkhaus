package services;

public interface EinnahmenIF {

    /**
     * summiert die eingehenden Beträge auf
     * @param betrag: neue Einnahme
     */
    void addIncome(float betrag);

    /**
     * gibt den Durchschnittspreis der bezahlten Tickets an
     */
    float averageIncome();

    /**
     * gibt die Anzahl der verkauften Tickets an
     */
    int soldTickets();

    /**
     * Gibt die gesamten services.Einnahmen an
     * @return
     */
    float totalIncome();

}
