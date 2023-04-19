public interface SchrankeIF {
    /**
     * Öffnet die Schranke
     * @return true wenn die Schranke erfolgreich geöffnet wurde
     */
    boolean open();

    /**
     * Schließt eine gegebene Schranke
     * @return true wenn die Schranke erfolgreich geschlossen wurde
     */
    boolean close();

    /**
     * Weist der Schranke die Funktion Einfahrt oder Ausfahrt zu
     * @param einfahrtAusfahrt "einfahrt" oder "ausfahrt", definiert wofür die Schranke dient
     */
    void setSchranke(String einfahrtAusfahrt) throws IllegalArgumentException;

    /**
     * Gibt zurück ob die Schranke für Einfahrt oder Ausfahrt genutzt werden kann
     * @return
     */
    String getSchranke();

}
