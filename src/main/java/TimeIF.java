public interface TimeIF {

    /**
     * Addiert auf einen Zeitstempel einen weiteren Zeitstempel auf
     * @param time: Implementierung von TimeIF
     * @return: neu erzeugter Zeitstempel als Summe zwei anderer
     */
    public TimeIF addieren(TimeIF time);
    /**
     * Get-Methode, um die Stundeangabe zu erfahren
     * @return: Stundenzahl als int
     */
    public int getMinutes();
    /**
     * Get-Methode, um die Minutenangabe zu erfahren
     * @return: Minutenzahl als int
     */
    public int getHours();
}
