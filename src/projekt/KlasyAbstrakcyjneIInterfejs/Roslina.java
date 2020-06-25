package projekt.KlasyAbstrakcyjneIInterfejs;

import projekt.Swiat;

public abstract class Roslina extends Organizm {
    public Roslina(Swiat swiat, int sila, int inicjatywa, int x, int y, int wiek, char znak, boolean zycie, boolean czy_rozmnazanie){
        super(swiat, sila, inicjatywa, x, y, wiek, znak, zycie, czy_rozmnazanie);
    }

    public abstract Organizm potomstwo(int x, int y); // metoda abstrakcyjna
    public abstract boolean obrona(Organizm atakujacy); // metoda abstrakcyjna

    @Override
    public void akcja() {
        int kierunek = (int)(Math.random() * 40) + 1;
        switch (kierunek)
        {
            case 1: // N
                if (swiat.czy_puste(this.x - 1, this.y)) {
                    if (this.x - 1 != 0 && this.czy_rozmnazanie && this.wiek >= 3) {
                        swiat.dodaj_organizm(this.potomstwo(this.x - 1, this.y));
                        swiat.wylacz_mozliwosc_rozmnazania(this.znak);
                        swiat.komentator.komentuj(this.znak + " rozprzestrzenil sie");
                    }
                }
            break;
            case 2: // E
                if (swiat.czy_puste(this.x, this.y + 1)) {
                    if (this.y + 1 != swiat.getterW() + 1 && this.czy_rozmnazanie && this.wiek >= 3) {
                        swiat.dodaj_organizm(this.potomstwo(this.x, this.y + 1));
                        swiat.wylacz_mozliwosc_rozmnazania(this.znak);
                        swiat.komentator.komentuj(this.znak + " rozprzestrzenil sie");
                    }
                }
            break;
            case 3: // S
                if (swiat.czy_puste(this.x + 1, this.y)) {
                    if (this.x + 1 != swiat.getterH() + 1 && this.czy_rozmnazanie && this.wiek >= 3) {
                        swiat.dodaj_organizm(this.potomstwo(this.x + 1, this.y));
                        swiat.wylacz_mozliwosc_rozmnazania(this.znak);
                        swiat.komentator.komentuj(this.znak + " rozprzestrzenil sie");
                    }
                }
            break;
            case 4: // W
                if (swiat.czy_puste(this.x, this.y - 1)) {
                    if (this.y - 1 != 0 && this.czy_rozmnazanie && this.wiek >= 3) {
                        swiat.dodaj_organizm(this.potomstwo(this.x, this.y - 1));
                        swiat.wylacz_mozliwosc_rozmnazania(this.znak);
                        swiat.komentator.komentuj(this.znak + " rozprzestrzenil sie");
                    }
                }
            break;
        }
    }
}
