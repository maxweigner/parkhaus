public interface EinnahmenIF {

    /**
     * summiert die eingehenden Beträge auf
     * @param betrag: neue Einnahme
     */
    void addIncome(float betrag);

    /**
     * Gibt die gesamten Einnahmen an
     * @return
     */
    float totalIncome();

}
