package projekt.Rosliny;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.KlasyAbstrakcyjneIInterfejs.Roslina;
import projekt.Swiat;

public class WilczeJagody extends Roslina {
    public WilczeJagody(Swiat swiat, int x, int y){
        super(swiat, 99, 0, x, y, 0, '&', true, false);
    }

    @Override
    public Organizm potomstwo(int x, int y) {
        return new WilczeJagody(swiat, x, y);
    }

    @Override
    public boolean obrona(Organizm atakujacy) {
        return false;
    }

    @Override
    public void akcja() {
        int kierunek = (int)(Math.random() * 400) + 1;
        switch (kierunek)
        {
            case 1: // N
                if (swiat.czy_puste(this.x - 1, this.y)) {
                    if (this.x - 1 != 0 && this.czy_rozmnazanie && this.wiek >= 3) {
                        swiat.dodaj_organizm(this.potomstwo(this.x - 1, this.y));
                        swiat.wylacz_mozliwosc_rozmnazania(this.znak);
                        swiat.komentator.komentuj("WILCZA JAGODA SIE ROZMNOZYLA!!!");
                    }
                }
                break;
            case 2: // E
                if (swiat.czy_puste(this.x, this.y + 1)) {
                    if (this.y + 1 != swiat.getterW() + 1 && this.czy_rozmnazanie && this.wiek >= 3) {
                        swiat.dodaj_organizm(this.potomstwo(this.x, this.y + 1));
                        swiat.wylacz_mozliwosc_rozmnazania(this.znak);
                        swiat.komentator.komentuj("WILCZA JAGODA SIE ROZMNOZYLA!!!");
                    }
                }
                break;
            case 3: // S
                if (swiat.czy_puste(this.x + 1, this.y)) {
                    if (this.x + 1 != swiat.getterH() + 1 && this.czy_rozmnazanie && this.wiek >= 3) {
                        swiat.dodaj_organizm(this.potomstwo(this.x + 1, this.y));
                        swiat.wylacz_mozliwosc_rozmnazania(this.znak);
                        swiat.komentator.komentuj("WILCZA JAGODA SIE ROZMNOZYLA!!!");
                    }
                }
                break;
            case 4: // W
                if (swiat.czy_puste(this.x, this.y - 1)) {
                    if (this.y - 1 != 0 && this.czy_rozmnazanie && this.wiek >= 3) {
                        swiat.dodaj_organizm(this.potomstwo(this.x, this.y - 1));
                        swiat.wylacz_mozliwosc_rozmnazania(this.znak);
                        swiat.komentator.komentuj("WILCZA JAGODA SIE ROZMNOZYLA!!!");
                    }
                }
                break;
        }
    }
}
