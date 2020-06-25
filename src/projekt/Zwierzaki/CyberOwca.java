package projekt.Zwierzaki;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.KlasyAbstrakcyjneIInterfejs.Zwierze;
import projekt.Swiat;

public class CyberOwca extends Zwierze {
    public CyberOwca(Swiat swiat, int x, int y){
        super(swiat, 11, 4, x, y, 0, 'C', true, false);
    }

    @Override
    public Organizm potomstwo(int x, int y) {
        return new CyberOwca(swiat, x, y);
    }

    @Override
    public boolean obrona(Organizm atakujacy) {
        return false;
    }

    @Override
    public void akcja() {
        if (swiat.ile_barszczy() != 0){
            Organizm barszcz = swiat.najblizszy_barszcz(this.x, this.y);
            int odleglosc_x = this.x - barszcz.getterX();
            int odleglosc_y = this.y - barszcz.getterY();
            if (odleglosc_x != 0){
                if (odleglosc_x > 0){
                    if (!swiat.czy_puste(this.x - 1, this.y)) {
                        Organizm organizm = swiat.szukaj_organizmu(this.x - 1, this.y);
                        this.kolizja(organizm.getterZnak(), this.x - 1, this.y);
                    }
                    else
                        this.setterX(this.x - 1);
                }
                else{
                    if (!swiat.czy_puste(this.x + 1, this.y)) {
                        Organizm organizm = swiat.szukaj_organizmu(this.x + 1, this.y);
                        this.kolizja(organizm.getterZnak(), this.x + 1, this.y);
                    }
                    else
                        this.setterX(this.x + 1);
                }
            }
            else {
                if (odleglosc_y != 0) {
                    if (odleglosc_y > 0) {
                        if (!swiat.czy_puste(this.x, this.y - 1)) {
                            Organizm organizm = swiat.szukaj_organizmu(this.x, this.y - 1);
                            this.kolizja(organizm.getterZnak(), this.x, this.y - 1);
                        } else
                            this.setterY(this.y - 1);
                    }
                    else {
                        if (!swiat.czy_puste(this.x, this.y + 1)) {
                            Organizm organizm = swiat.szukaj_organizmu(this.x, this.y + 1);
                            this.kolizja(organizm.getterZnak(), this.x, this.y + 1);
                        } else
                            this.setterY(this.y + 1);
                    }
                }
            }
        }
        else
            super.akcja();
    }
}
