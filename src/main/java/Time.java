public class Time implements TimeIF {
    private int hours = 0;
    private int minutes = 0;
    public Time(int hour, int minutes) {
        this.hours = hour % 24;
        this.minutes = minutes % 60;
    }

    /**
     * Addiert auf diese Instanz einen Zeitstempel und gibt die Summe als neues Objekt zurück
     * @param time: Implementierung von TimeIF
     * @return: Summe zweier Stempel
     */
    @Override
    public TimeIF addieren(TimeIF time) {
        int h1 = time.getHours();
        int m1 = time.getMinutes();
        int h2 = this.hours;
        int m2 = this.minutes;

        int h3 = (h1 + h2) % 24; // Addition mit Berücksichtigung eines Überlaufs
        int m3 = (m1 + m2) % 60;

        if (m3 != (m1+m2)){  // falls Minuten überlaufen, müssen die Stunden erhöht werden
            h3 = (h3+1)%24; // Stunde erhöhen und Überlauf berücksichtigen
        }

        Time result = new Time(h3, m3);
        return result;
    }

    /**
     * Get-Methode, um die Minutenangabe zu erfahren
     * @return: Minutenzahl als int
     */
    @Override
    public int getMinutes() {
        return this.minutes;
    }

    /**
     * Get-Methode, um die Stundeangabe zu erfahren
     * @return: Stundenzahl als int
     */
    @Override
    public int getHours() {
        return this.hours;
    }

    /**
     * Gibt die Zeitangabe als String heraus. Zum Beispiel: "10:34", "11:49", ...
     * @return String im Format HH:MM
     */
    @Override
    public String toString(){
        String h = Integer.toString(hours); // hours als String
        String m = Integer.toString(minutes); // minutes als String
        if (hours < 10){ // Format HH einhalten (z.B "9" -> "09")
            h = "0" + hours;
        }
        if (minutes < 10){ // Format MM einhalten (z.B "4" -> "04")
            m = "0" + minutes;
        }
        return m + ":" + h; // "HH:MM"
    }
}
