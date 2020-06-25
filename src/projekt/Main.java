package projekt;

import projekt.Wizualizacja.Komentator;
import projekt.Wizualizacja.Mapa;
import projekt.Wizualizacja.Ramka;

public class Main {
    public static void main(String[] args) {
        Komentator komentator = new Komentator();

        Swiat swiat1 = new Swiat(komentator);
        swiat1.wypelnij_mape();

        Mapa mapa = new Mapa(swiat1, komentator);

        Ramka ramka = new Ramka(mapa, swiat1, komentator);
    }
}
