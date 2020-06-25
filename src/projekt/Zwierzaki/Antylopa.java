package projekt.Zwierzaki;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.Swiat;
import projekt.KlasyAbstrakcyjneIInterfejs.Zwierze;

public class Antylopa extends Zwierze {
    public Antylopa(Swiat swiat, int x, int y){
        super(swiat, 4, 4, x, y, 0, 'A', true, false);
    }

    @Override
    public Organizm potomstwo(int x, int y) {
        return new Antylopa(swiat, x, y);
    }

    @Override
    public boolean obrona(Organizm atakujacy) {
        int obrona = (int)(Math.random() * 2);
        swiat.komentator.komentuj("Obrona antylopy: " + obrona);
        if (obrona == 0)
            return false;
        else {
            int x_przed_ucieczka = this.x;
            int y_przed_ucieczka = this.y;
            this.akcja();
            if (x_przed_ucieczka == this.x && y_przed_ucieczka == this.y && this.zycie)
                return false;
		    else
                return true;
        }
    }

    @Override
    public void akcja() {
        int kierunek = (int)(Math.random() * 4) + 1;
        switch (kierunek)
        {
            case 1: // N
                if (this.x - 2 >= 1) {
                    if (!swiat.czy_puste(this.x - 2, this.y)) {
                        Organizm organizm = swiat.szukaj_organizmu(this.x - 2, this.y);
                        this.kolizja(organizm.getterZnak(), this.x - 2, this.y);
                    }
			        else
                        this.setterX(this.x - 2);
                }
            break;
            case 2: // E
                if (this.y + 2 <= swiat.getterW()) {
                    if (!swiat.czy_puste(this.x, this.y + 2)) {
                        Organizm organizm = swiat.szukaj_organizmu(this.x, this.y + 2);
                        this.kolizja(organizm.getterZnak(), this.x, this.y + 2);
                    }
			        else
                        this.setterY(this.y + 2);
                }
            break;
            case 3: // S
                if (this.x + 2 <= swiat.getterH()) {
                    if (!swiat.czy_puste(this.x + 2, this.y)) {
                        Organizm organizm = swiat.szukaj_organizmu(this.x + 2, this.y);
                        this.kolizja(organizm.getterZnak(), this.x + 2, this.y);
                    }
			        else
                        this.setterX(this.x + 2);
                }
            break;
            case 4: // W
                if (this.y - 2 >= 1) {
                    if (!swiat.czy_puste(this.x, this.y - 2)) {
                        Organizm organizm = swiat.szukaj_organizmu(this.x, this.y - 2);
                        this.kolizja(organizm.getterZnak(), this.x, this.y - 2);
                    }
			        else
                        this.setterY(this.y - 2);
                }
            break;
        }
    }
}
