public interface ParkhausIF {

    /**
     * Erstellt ein neues Ticket zum Zeitpunkt der Einfahrt
     * @return Ticket
     */
    TicketIF einfahrt();
    /**
     * Zahlungsvorgang, um mit einem Ticket die Schranke zu öffnen
     * @param ticket: gültiges Ticket
     */

    boolean ausfahrt(TicketIF ticket);

    /**
     * Gibt die Anzahl an noch verfügbaren Plätzen aus
     * @return Anzahl noch freier Plätze
     */
    int getAnzahlFreiePlaetze();

    /**
     * Setzt die Anzahl an verfügbaren Plätzen
     * @param spaces Die neue Anzahl der freien Plätze
     */
    void setAnzahlPlaetze(int spaces);
}
