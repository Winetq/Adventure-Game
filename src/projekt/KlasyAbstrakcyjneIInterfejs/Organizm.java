package projekt.KlasyAbstrakcyjneIInterfejs;

import projekt.Swiat;

public abstract class Organizm {
    protected Swiat swiat;
    protected int sila;
    protected int inicjatywa;
    protected int x;
    protected int y;
    protected int wiek;
    protected char znak;
    protected boolean zycie;
    protected boolean czy_rozmnazanie;

    public Organizm(Swiat swiat, int sila, int inicjatywa, int x, int y, int wiek, char znak, boolean zycie, boolean czy_rozmnazanie){
        this.swiat = swiat;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.x = x;
        this.y = y;
        this.wiek = wiek;
        this.znak = znak;
        this.zycie = zycie;
        this.czy_rozmnazanie = czy_rozmnazanie;
    }

    public abstract void akcja(); // metoda abstrakcyjna
    public abstract Organizm potomstwo(int x, int y); // metoda abstrakcyjna
    public abstract boolean obrona(Organizm atakujacy); // metoda abstrakcyjna

    public void setterSila(int sila){
        this.sila = sila;
    }
    public int getterSila() {
        return sila;
    }

    public int getterInicjatywa() {
        return inicjatywa;
    }

    public void setterX(int x){
        this.x = x;
    }
    public void setterY(int y){
        this.y = y;
    }
    public int getterX() {
        return x;
    }
    public int getterY() {
        return y;
    }

    public void setterWiek(int wiek){
        this.wiek = wiek;
    }
    public int getterWiek() {
        return wiek;
    }

    public void setterZnak(char znak){
        this.znak = znak;
    }
    public char getterZnak() {
        return znak;
    }

    public void setterZycie(boolean zycie){
        this.zycie = zycie;
    }
    public boolean getterZycie() {
        return zycie;
    }

    public void setterRozmnazanie(boolean czy_rozmnazanie){
        this.czy_rozmnazanie = czy_rozmnazanie;
    }
}
