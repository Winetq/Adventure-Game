package projekt.Zwierzaki;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.Swiat;
import projekt.KlasyAbstrakcyjneIInterfejs.Zwierze;

public class Zolw extends Zwierze {
    public Zolw(Swiat swiat, int x, int y){
        super(swiat, 2, 1, x, y, 0, 'Z', true, false);
    }

    @Override
    public Organizm potomstwo(int x, int y) {
        return new Zolw(swiat, x, y);
    }

    @Override
    public boolean obrona(Organizm atakujacy) {
        if (atakujacy.getterSila() >= 5)
            return false;
        else {
            swiat.komentator.komentuj("Obrona zolwia!");
            return true;
        }
    }

    @Override
    public void akcja() {
        int kierunek = (int)(Math.random() * 16) + 1;
        switch (kierunek)
        {
            case 1: // N
                if (!swiat.czy_puste(this.x - 1, this.y)) {
                    Organizm organizm = swiat.szukaj_organizmu(this.x - 1, this.y);
                    this.kolizja(organizm.getterZnak(), this.x - 1, this.y);
                }
                else {
                    if (this.x - 1 != 0)
                        this.setterX(this.x - 1);
                }
                break;
            case 2: // E
                if (!swiat.czy_puste(this.x, this.y + 1)) {
                    Organizm organizm = swiat.szukaj_organizmu(this.x, this.y + 1);
                    this.kolizja(organizm.getterZnak(), this.x, this.y + 1);
                }
                else {
                    if (this.y + 1 != swiat.getterW() + 1)
                        this.setterY(this.y + 1);
                }
                break;
            case 3: // S
                if (!swiat.czy_puste(this.x + 1, this.y)) {
                    Organizm organizm = swiat.szukaj_organizmu(this.x + 1, this.y);
                    this.kolizja(organizm.getterZnak(), this.x + 1, this.y);
                }
                else {
                    if (this.x + 1 != swiat.getterH() + 1)
                        this.setterX(this.x + 1);
                }
                break;
            case 4: // W
                if (!swiat.czy_puste(this.x, this.y - 1)) {
                    Organizm organizm = swiat.szukaj_organizmu(this.x, this.y - 1);
                    this.kolizja(organizm.getterZnak(), this.x, this.y - 1);
                }
                else {
                    if (this.y - 1 != 0)
                        this.setterY(this.y - 1);
                }
                break;
        }
    }
}
