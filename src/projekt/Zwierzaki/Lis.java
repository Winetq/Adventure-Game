package projekt.Zwierzaki;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.Swiat;
import projekt.KlasyAbstrakcyjneIInterfejs.Zwierze;

public class Lis extends Zwierze {
    public Lis(Swiat swiat, int x, int y){
        super(swiat, 3, 7, x, y, 0, 'L', true, false);
    }

    @Override
    public Organizm potomstwo(int x, int y) {
        return new Lis(swiat, x, y);
    }

    @Override
    public boolean obrona(Organizm atakujacy) {
        return false;
    }

    @Override
    public void akcja() {
        int kierunek = (int)(Math.random() * 4) + 1;
        switch (kierunek)
        {
            case 1: // N
                if (!swiat.czy_puste(this.x - 1, this.y)) {
                Organizm atakowany = swiat.szukaj_organizmu(this.x - 1, this.y);
                    if (atakowany != null) {
                        if (this.sila >= atakowany.getterSila())
                            this.kolizja(atakowany.getterZnak(), this.x - 1, this.y);
                    }
                }
		        else {
                    if (this.x - 1 != 0)
                        this.setterX(this.x - 1);
                }
            break;
            case 2: // E
                if (!swiat.czy_puste(this.x, this.y + 1)) {
                    Organizm atakowany = swiat.szukaj_organizmu(this.x, this.y + 1);
                    if (atakowany != null) {
                        if (this.sila >= atakowany.getterSila())
                            this.kolizja(atakowany.getterZnak(), this.x, this.y + 1);
                    }
                }
		        else {
                    if (this.y + 1 != swiat.getterW() + 1)
                        this.setterY(this.y + 1);
                }
            break;
            case 3: // S
                if (!swiat.czy_puste(this.x + 1, this.y)) {
                    Organizm atakowany = swiat.szukaj_organizmu(this.x + 1, this.y);
                    if (atakowany != null) {
                        if (this.sila >= atakowany.getterSila())
                            this.kolizja(atakowany.getterZnak(), this.x + 1, this.y);
                    }
                }
		        else {
                    if (this.x + 1 != swiat.getterH() + 1)
                        this.setterX(this.x + 1);
                }
            break;
            case 4: // W
                if (!swiat.czy_puste(this.x, this.y - 1)) {
                    Organizm atakowany = swiat.szukaj_organizmu(this.x, this.y - 1);
                    if (atakowany != null) {
                        if (this.sila >= atakowany.getterSila())
                            this.kolizja(atakowany.getterZnak(), this.x, this.y - 1);
                    }
                }
		        else {
                    if (this.y - 1 != 0)
                        this.setterY(this.y - 1);
                }
            break;
        }
    }
}
