public interface EinnahmenIF {

    /**
     * summiert die eingehenden Beträge auf
     * @param betrag
     */
    void addIncome(float betrag);

    /**
     * Gibt die gesamten Einnahmen an
     * @return
     */
    float totalIncome();

}
