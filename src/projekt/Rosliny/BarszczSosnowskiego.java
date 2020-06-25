package projekt.Rosliny;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.KlasyAbstrakcyjneIInterfejs.Roslina;
import projekt.KlasyAbstrakcyjneIInterfejs.Zwierze;
import projekt.Swiat;

public class BarszczSosnowskiego extends Roslina {
    public BarszczSosnowskiego(Swiat swiat, int x, int y){
        super(swiat, 10, 0, x, y, 0, 'B', true, false);
    }

    @Override
    public Organizm potomstwo(int x, int y) {
        return new BarszczSosnowskiego(swiat, x, y);
    }

    @Override
    public boolean obrona(Organizm atakujacy) {
        return false;
    }

    @Override
    public void akcja() {
        if (!swiat.czy_puste(this.x - 1, this.y)) {
            Organizm atakowany = swiat.szukaj_organizmu(this.x - 1, this.y);
            if (atakowany instanceof Zwierze && atakowany.getterZnak() != 'C') {
                atakowany.setterZycie(false);
                swiat.komentator.komentuj(this.znak + " pokonal " + atakowany.getterZnak());
            }
        }
        if (!swiat.czy_puste(this.x, this.y + 1)) {
            Organizm atakowany = swiat.szukaj_organizmu(this.x, this.y + 1);
            if (atakowany instanceof Zwierze && atakowany.getterZnak() != 'C') {
                atakowany.setterZycie(false);
                swiat.komentator.komentuj(this.znak + " pokonal " + atakowany.getterZnak());
            }
        }
        if (!swiat.czy_puste(this.x + 1, this.y)) {
            Organizm atakowany = swiat.szukaj_organizmu(this.x + 1, this.y);
            if (atakowany instanceof Zwierze && atakowany.getterZnak() != 'C') {
                atakowany.setterZycie(false);
                swiat.komentator.komentuj(this.znak + " pokonal " + atakowany.getterZnak());
            }
        }
        if (!swiat.czy_puste(this.x, this.y - 1)) {
            Organizm atakowany = swiat.szukaj_organizmu(this.x, this.y - 1);
            if (atakowany instanceof Zwierze && atakowany.getterZnak() != 'C') {
                atakowany.setterZycie(false);
                swiat.komentator.komentuj(this.znak + " pokonal " + atakowany.getterZnak());
            }
        }
    }
}
