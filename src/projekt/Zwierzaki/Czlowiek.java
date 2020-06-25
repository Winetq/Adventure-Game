package projekt.Zwierzaki;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.KlasyAbstrakcyjneIInterfejs.Zwierze;
import projekt.Swiat;

public class Czlowiek extends Zwierze {
    private boolean znak_specjalny;
    private int czas_trwania;
    private int dezaktywacja;
    private boolean gora;
    private boolean dol;
    private boolean prawo;
    private boolean lewo;

    public Czlowiek(Swiat swiat, int x, int y){
        super(swiat, 0, 10, x, y, 0, '+', true, false);
        this.znak_specjalny = false;
        this.czas_trwania = 0;
        this.dezaktywacja = 0;
        this.gora = false;
        this.dol = false;
        this.prawo = false;
        this.lewo = false;
    }

    @Override
    public void akcja() {
        this.setterDezaktywacja(this.getterDezaktywacja() + 1);
        if (!this.znak_specjalny) {
            if (this.gora) {
                if (!swiat.czy_puste(this.x - 1, this.y)) {
                    Organizm organizm = swiat.szukaj_organizmu(this.x - 1, this.y);
                    this.kolizja(organizm.getterZnak(), this.x - 1, this.y);
                } else {
                    if (this.x - 1 != 0)
                        this.setterX(this.x - 1);
                }
            }
            if (this.dol) {
                if (!swiat.czy_puste(this.x + 1, this.y)) {
                    Organizm organizm = swiat.szukaj_organizmu(this.x + 1, this.y);
                    this.kolizja(organizm.getterZnak(), this.x + 1, this.y);
                } else {
                    if (this.x + 1 != swiat.getterH() + 1)
                        this.setterX(this.x + 1);
                }
            }
            if (this.prawo) {
                if (!swiat.czy_puste(this.x, this.y + 1)) {
                    Organizm organizm = swiat.szukaj_organizmu(this.x, this.y + 1);
                    this.kolizja(organizm.getterZnak(), this.x, this.y + 1);
                } else {
                    if (this.y + 1 != swiat.getterW() + 1)
                        this.setterY(this.y + 1);
                }
            }
            if (this.lewo) {
                if (!swiat.czy_puste(this.x, this.y - 1)) {
                    Organizm organizm = swiat.szukaj_organizmu(this.x, this.y - 1);
                    this.kolizja(organizm.getterZnak(), this.x, this.y - 1);
                } else {
                    if (this.y - 1 != 0)
                        this.setterY(this.y - 1);
                }
            }
        }
        else
            this.umiejetnosc_specjalna();
        this.gora = false;
        this.dol = false;
        this.prawo = false;
        this.lewo = false;
    }

    public void umiejetnosc_specjalna(){
        this.setterCzas(this.czas_trwania + 1);
        boolean szansa = true;
        if (this.czas_trwania == 4 || this.czas_trwania == 5){
            int czy_wykonanie = (int)(Math.random() * 2);
            if (czy_wykonanie == 0)
                szansa = false;
        }
        if (szansa) {
            if (this.gora) {
                if (this.x - 2 >= 1) {
                    if (!swiat.czy_puste(this.x - 2, this.y)) {
                        Organizm organizm = swiat.szukaj_organizmu(this.x - 2, this.y);
                        this.kolizja(organizm.getterZnak(), this.x - 2, this.y);
                    }
                    else
                        this.setterX(this.x - 2);
                }
            }
            if (this.dol) {
                if (this.x + 2 <= swiat.getterH()) {
                    if (!swiat.czy_puste(this.x + 2, this.y)) {
                        Organizm organizm = swiat.szukaj_organizmu(this.x + 2, this.y);
                        this.kolizja(organizm.getterZnak(), this.x + 2, this.y);
                    }
                    else
                        this.setterX(this.x + 2);
                }
            }
            if (this.prawo) {
                if (this.y + 2 <= swiat.getterW()) {
                    if (!swiat.czy_puste(this.x, this.y + 2)) {
                        Organizm organizm = swiat.szukaj_organizmu(this.x, this.y + 2);
                        this.kolizja(organizm.getterZnak(), this.x, this.y + 2);
                    }
                    else
                        this.setterY(this.y + 2);
                }
            }
            if (this.lewo) {
                if (this.y - 2 >= 1) {
                    if (!swiat.czy_puste(this.x, this.y - 2)) {
                        Organizm organizm = swiat.szukaj_organizmu(this.x, this.y - 2);
                        this.kolizja(organizm.getterZnak(), this.x, this.y - 2);
                    }
                    else
                        this.setterY(this.y - 2);
                }
            }
        }
        if (this.czas_trwania == 5){
            this.setterZnakSpecjalny(false);
            this.setterCzas(0);
            this.setterDezaktywacja(0);
        }
    }

    @Override
    public Organizm potomstwo(int x, int y) {
        return new Czlowiek(swiat, x, y);
    }

    @Override
    public boolean obrona(Organizm atakujacy) {
        return false;
    }

    @Override
    public void kolizja(char atakowany, int atakowanyX, int atakowanyY) {
        Organizm przeciwnik = swiat.szukaj_organizmu(atakowanyX, atakowanyY);
        if (przeciwnik != null) {
            if (this.sila >= przeciwnik.getterSila()) {
                if (!przeciwnik.obrona(this)) {
                    this.setterX(atakowanyX);
                    this.setterY(atakowanyY);
                    this.setterSila(this.getterSila() + przeciwnik.getterSila()); // przejmujemy sile ofiary
                    swiat.komentator.komentuj("Twoja sila: " + this.sila);
                    przeciwnik.setterZycie(false);
                    if (przeciwnik instanceof Zwierze)
                        swiat.komentator.komentuj(this.znak + " pokonal " + przeciwnik.getterZnak());
				    else
				        swiat.komentator.komentuj(this.znak + " zjadl " + przeciwnik.getterZnak());
                }
            }
		    else {
                this.setterZycie(false);
                swiat.komentator.komentuj(przeciwnik.getterZnak() + " pokonal " + this.znak);
            }
        }
    }

    public void setterCzas(int czas_trwania){
        this.czas_trwania = czas_trwania;
    }

    public int getterDezaktywacja() {
        return dezaktywacja;
    }

    public void setterDezaktywacja(int dezaktywacja){
        this.dezaktywacja = dezaktywacja;
    }

    public void setterGora(boolean gora) {
        this.gora = gora;
    }

    public void setterDol(boolean dol) {
        this.dol = dol;
    }

    public void setterPrawo(boolean prawo) {
        this.prawo = prawo;
    }

    public void setterLewo(boolean lewo) {
        this.lewo = lewo;
    }

    public boolean getterGora() {
        return gora;
    }

    public boolean getterDol() {
        return dol;
    }

    public boolean getterPrawo() {
        return prawo;
    }

    public boolean getterLewo() {
        return lewo;
    }

    public boolean getterZnakSpecjalny() {
        return znak_specjalny;
    }

    public void setterZnakSpecjalny(boolean znak_specjalny) {
        this.znak_specjalny = znak_specjalny;
    }
}
