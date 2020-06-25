package projekt;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.Rosliny.*;
import projekt.Wizualizacja.Komentator;
import projekt.Zwierzaki.*;

import java.util.ArrayList;

public class Swiat {
    private int height; // height = 20
    private int width; // width = 20;
    private int tura;
    private boolean koniec_gry;
    private ArrayList<Organizm> organizmy;
    public Komentator komentator;

    private void aktualizacja_mapy(){
        for (int i = 0; i < (int)organizmy.size(); i++) {
            if (!organizmy.get(i).getterZycie()) {
                organizmy.remove(i);
                i -= 1;
            }
        }
    }

    private void wlacz_mozliwosc_rozmnazania(){
        for (int i = 0; i < (int)organizmy.size(); i++)
            organizmy.get(i).setterRozmnazanie(true);
    }

    private void sortowanie_organizmow(){ // wg inicjatywy i wieku
        for (int i = 0; i < (int)organizmy.size(); i++) {
            for (int j = 0; j < (int)(organizmy.size() - 1); j++) {
                if (organizmy.get(j).getterInicjatywa() < organizmy.get(j + 1).getterInicjatywa()) {
                    Organizm temp = organizmy.get(j);
                    organizmy.set(j, organizmy.get(j + 1));
                    organizmy.set(j + 1, temp);
                }
                if (organizmy.get(j).getterInicjatywa() == organizmy.get(j + 1).getterInicjatywa()) {
                    if (organizmy.get(j).getterWiek() < organizmy.get(j + 1).getterWiek()) {
                        Organizm temp = organizmy.get(j);
                        organizmy.set(j, organizmy.get(j + 1));
                        organizmy.set(j + 1, temp);
                    }
                }
            }
        }
    }

    private void czy_koniec_gry(){
        int ile_wilczych_jagod = 0; // pierwsza mozliwosc zakonczenia gry
        for (int i = 0; i < (int)organizmy.size(); i++) {
            if (organizmy.get(i).getterZnak() == '&')
                ile_wilczych_jagod += 1;
        }
        if (ile_wilczych_jagod == 0) {
            komentator.komentuj("WYGRALES");
            this.setterKoniecGry(true);
        }

        if (this.getterTura() == 100 && ile_wilczych_jagod != 0) { // druga mozliwosc zakonczenia gry
            komentator.komentuj("PRZEGRALES");
            this.setterKoniecGry(true);
        }

        int gracz = 0; // trzecia mozliwosc zakonczenia gry
        for (int i = 0; i < (int)organizmy.size(); i++) {
            if (organizmy.get(i).getterZnak() == '+')
                gracz += 1;
        }
        if (gracz == 0) {
            komentator.komentuj("PRZEGRALES");
            this.setterKoniecGry(true);
        }
    }
    
    private void setterKoniecGry(boolean koniec_gry){
        this.koniec_gry = koniec_gry;
    }

    private Organizm losuj_organizm(int x, int y){
        int organizm = (int)(Math.random() * 21) + 1;
        switch (organizm)
        {
            case 1:
            case 11:
            case 16:
                return new Wilk(this, x, y);
            case 2:
            case 12:
                return new Owca(this, x, y);
            case 17:
                return new CyberOwca(this, x, y);
            case 3:
            case 13:
            case 18:
                return new Lis(this, x, y);
            case 4:
            case 14:
            case 19:
                return new Zolw(this, x, y);
            case 5:
            case 15:
            case 20:
                return new Antylopa(this, x, y);
            case 6:
                return new Trawa(this, x, y);
            case 7:
                return new Mlecz(this, x, y);
            case 8:
            case 21:
                return new Guarana(this, x, y);
            case 9:
                return new WilczeJagody(this, x, y);
            case 10:
                return new BarszczSosnowskiego(this, x, y);
        }
        return new Czlowiek(this, x, y); // bo inaczej jest error
    }

    public Swiat(Komentator komentator){
        this.height = 20;
        this.width = 20;
        this.tura = 0;
        this.koniec_gry = false;
        organizmy = new ArrayList<Organizm>();
        this.komentator = komentator;
    }

    public void wypelnij_mape(){
        float wynik = (float)this.width / (float)4;
        int ile_organizmow = Math.round(wynik);
        int[] nr_kolumn = new int[ile_organizmow];
        for (int i = 0; i < ile_organizmow; i++)
            nr_kolumn[i] = 0;
        for (int i = 1; i <= this.height; i++) {
            for (int j = 0; j < ile_organizmow; j++) {
                int kolumna = (int)(Math.random() * this.width) + 1;
                boolean powtorzenie = false;
                for (int k = 0; k < ile_organizmow; k++) {
                    if (nr_kolumn[k] == kolumna)
                        powtorzenie = true;
                }
                if (powtorzenie) {
                    while (powtorzenie) {
                        powtorzenie = false;
                        kolumna = (int)(Math.random() * this.width) + 1;
                        for (int k = 0; k < ile_organizmow; k++) {
                            if (nr_kolumn[k] == kolumna)
                                powtorzenie = true;
                        }
                    }
                }
                nr_kolumn[j] = kolumna;
                Organizm nowy_organizm = this.losuj_organizm(i, nr_kolumn[j]);
                organizmy.add(nowy_organizm);
            }
            if (i == 1) {
                boolean pozycja_dla_czlowieka = false;
                int y = 1;
                while (!pozycja_dla_czlowieka) {
                    pozycja_dla_czlowieka = true;
                    for (int k = 0; k < ile_organizmow; k++) {
                        if (y == nr_kolumn[k]) {
                            pozycja_dla_czlowieka = false;
                            y += 1;
                        }
                    }
                }
                Organizm gracz = new Czlowiek(this, i, y);
                organizmy.add(gracz);
            }
            if (i == this.height) {
                boolean pozycja_dla_bosa = false;
                int y = 1;
                while (!pozycja_dla_bosa) {
                    pozycja_dla_bosa = true;
                    for (int k = 0; k < ile_organizmow; k++) {
                        if (y == nr_kolumn[k]) {
                            pozycja_dla_bosa = false;
                            y += 1;
                        }
                    }
                }
                Organizm bos = new WilczeJagody(this, i, y);
                organizmy.add(bos);
            }
            for (int j = 0; j < ile_organizmow; j++)
                nr_kolumn[j] = 0;
        }
    }

    public boolean czy_puste(int x, int y){
        for (int i = 0; i < (int)organizmy.size(); i++) {
            if (organizmy.get(i).getterX() == x && organizmy.get(i).getterY() == y && organizmy.get(i).getterZycie())
                return false;
        }
        return true;
    }

    public void ruch_zwierzakow(){
        komentator.czyszczenie(); // wyczyszczenie notatnika
        this.wlacz_mozliwosc_rozmnazania();
        this.sortowanie_organizmow();
        this.setterTura(this.getterTura() + 1);
        komentator.komentuj("TURA: " + this.tura);
        for (int i = 0; i < (int)organizmy.size(); i++) {
            if (organizmy.get(i).getterZycie())
                organizmy.get(i).akcja();
        }
        this.aktualizacja_mapy();
        for (int i = 0; i < (int)organizmy.size(); i++)
            organizmy.get(i).setterWiek(organizmy.get(i).getterWiek() + 1);
        this.czy_koniec_gry();
        if (!this.koniec_gry){
            this.komentator.komentuj("TWOJ RUCH!");
        }
    }

    public void dodaj_organizm(Organizm potomek){
        organizmy.add(potomek);
    }

    public void wylacz_mozliwosc_rozmnazania(char gatunek){
        for (int i = 0; i < (int)organizmy.size(); i++) {
            if (organizmy.get(i).getterZnak() == gatunek)
                organizmy.get(i).setterRozmnazanie(false);
        }    
    }

    public Organizm szukaj_gracza(){
        for (int i = 0; i < (int)organizmy.size(); i++){
            if (organizmy.get(i).getterZnak() == '+')
                return organizmy.get(i);
        }
        return null;
    }

    public int ile_barszczy(){
        int suma = 0;
        for (int i = 0; i < (int)organizmy.size(); i++){
            if (organizmy.get(i).getterZnak() == 'B')
                suma += 1;
        }
        return suma;
    }

    public Organizm najblizszy_barszcz(int cyber_owca_x, int cyber_owca_y){
        int odleglosc = Integer.MAX_VALUE;
        Organizm barszcz = null;
        for (int i = 0; i < (int)organizmy.size(); i++){
            if (organizmy.get(i).getterZnak() == 'B'){
                int x = Math.abs(cyber_owca_x - organizmy.get(i).getterX());
                int y = Math.abs(cyber_owca_y - organizmy.get(i).getterY());
                int droga = x + y;
                if (droga < odleglosc){
                    odleglosc = droga;
                    barszcz = organizmy.get(i);
                }
            }
        }
        return barszcz;
    }

    public Organizm szukaj_organizmu(int x, int y){
        for (int i = 0; i < (int)organizmy.size(); i++) {
            if (organizmy.get(i).getterX() == x && organizmy.get(i).getterY() == y && organizmy.get(i).getterZycie())
                return organizmy.get(i);
        }
        return null;
    }

    public void tworzenie_organizmu(char znak, int x, int y){
        switch (znak)
        {
            case '+':
                Organizm gracz = new Czlowiek(this, x, y);
                organizmy.add(gracz);
                break;
            case '&':
                Organizm boss = new WilczeJagody(this, x, y);
                organizmy.add(boss);
                break;
            case 'T':
                Organizm trawa = new Trawa(this, x, y);
                organizmy.add(trawa);
                break;
            case 'M':
                Organizm mlecz = new Mlecz(this, x, y);
                organizmy.add(mlecz);
                break;
            case 'G':
                Organizm guarana = new Guarana(this, x, y);
                organizmy.add(guarana);
                break;
            case 'B':
                Organizm barszcz = new BarszczSosnowskiego(this, x, y);
                organizmy.add(barszcz);
                break;
            case 'W':
                Organizm wilk = new Wilk(this, x, y);
                organizmy.add(wilk);
                break;
            case 'C':
                Organizm cyber = new CyberOwca(this, x, y);
                organizmy.add(cyber);
                break;
            case 'O':
                Organizm owca = new Owca(this, x, y);
                organizmy.add(owca);
                break;
            case 'L':
                Organizm lis = new Lis(this, x, y);
                organizmy.add(lis);
                break;
            case 'Z':
                Organizm zolw = new Zolw(this, x, y);
                organizmy.add(zolw);
                break;
            case 'A':
                Organizm antylopa = new Antylopa(this, x, y);
                organizmy.add(antylopa);
                break;
        }
    }

    public void czyszczenie_swiata(){
        organizmy.clear();
    }

    public char pobierz_znak(int i){
        return organizmy.get(i).getterZnak();
    }

    public int pobierz_x(int i){
        return organizmy.get(i).getterX();
    }

    public int pobierz_y(int i){
        return organizmy.get(i).getterY();
    }

    public int ile_organizmow(){
        return organizmy.size();
    }

    public int getterH() {
        return height;
    }

    public int getterW() {
        return width;
    }

    public int getterTura() {
        return tura;
    }

    public void setterTura(int tura){
        this.tura = tura;
    }

    public boolean getterKoniecGry() {
        return koniec_gry;
    }
}
