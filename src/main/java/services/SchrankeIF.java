package services;

public interface SchrankeIF {
    /**
     * Öffnet die services.Schranke
     * @return true wenn die services.Schranke erfolgreich geöffnet wurde
     */
    boolean open();

    /**
     * Schließt eine gegebene services.Schranke
     * @return true wenn die services.Schranke erfolgreich geschlossen wurde
     */
    boolean close();

    /**
     * Weist der services.Schranke die Funktion Einfahrt oder Ausfahrt zu
     * @param einfahrtAusfahrt "einfahrt" oder "ausfahrt", definiert wofür die services.Schranke dient
     */
    void setSchranke(String einfahrtAusfahrt) throws IllegalArgumentException;

    /**
     * Gibt zurück ob die services.Schranke für Einfahrt oder Ausfahrt genutzt werden kann
     * @return String "einfahrt" oder "ausfahrt"
     */
    String getSchranke();

}
