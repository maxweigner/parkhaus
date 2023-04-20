public class Schranke implements SchrankeIF{

    private boolean zustand = false; // true -> open, false -> closed
    private String schrankenTyp;

    public boolean open() {
        if (!zustand) {
            zustand = true;
            return true;
        }

        return false;
    }

    public boolean close() {
        if (zustand) {
            zustand = false;
            return true;
        }

        return false;
    }

    public void setSchranke(String einfahrtAusfahrt) throws IllegalArgumentException {
        if (einfahrtAusfahrt.equals("einfahrt") || einfahrtAusfahrt.equals("ausfahrt")) {
            this.schrankenTyp = einfahrtAusfahrt;
            return;
        }

        throw new IllegalArgumentException();
    }

    public String getSchranke() {
        return schrankenTyp;
    }
}
