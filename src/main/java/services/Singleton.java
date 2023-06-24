package services;

public class Singleton {
    private static Singleton instance = null;
    private final ParkhausIF parkhaus;

    // privater Konstruktor, damit keine Instanzen einfach erstelt werden
    private Singleton(ParkhausIF parkhaus){
        this.parkhaus = parkhaus;
    }

    /**
     * Garant um immer die gleiche Instanz des Singletons und damit des Parkhauses zu erhalten
     * @return: Singleton-Instanz mit Parkhaus Instanzvariable
     */
    public static synchronized Singleton getInstance(){
        if (instance == null){ // es existiert noch keines
            instance = new Singleton(new Parkhaus()); // Konstruktur aufrufen
        }
        return instance;
    }

    public ParkhausIF getParkhaus(){
        return this.parkhaus;
    }
}
