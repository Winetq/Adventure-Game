package projekt.KlasyAbstrakcyjneIInterfejs;

import projekt.Swiat;

public abstract class Zwierze extends Organizm implements Ikolizja {
    public Zwierze(Swiat swiat, int sila, int inicjatywa, int x, int y, int wiek, char znak, boolean zycie, boolean czy_rozmnazanie){
        super(swiat, sila, inicjatywa, x, y, wiek, znak, zycie, czy_rozmnazanie);
    }

    public abstract Organizm potomstwo(int x, int y); // metoda abstrakcyjna (nwm czy potrzebna)
    public abstract boolean obrona(Organizm atakujacy); // metoda abstrakcyjna (nwm czy potrzebna)

    @Override
    public void akcja() {
        int kierunek = (int)(Math.random() * 4) + 1;
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

    @Override
    public void kolizja(char atakowany, int atakowanyX, int atakowanyY) {
        if (this.znak == atakowany) {
            Organizm partner = swiat.szukaj_organizmu(atakowanyX, atakowanyY);
            if (this.x - 1 != 0 && swiat.czy_puste(this.x - 1, this.y) && this.czy_rozmnazanie && this.wiek >= 3 && partner.getterWiek() >= 3) {
                swiat.dodaj_organizm(this.potomstwo(this.x - 1, this.y));
                swiat.wylacz_mozliwosc_rozmnazania(this.znak);
            }
		    else if (this.y + 1 != swiat.getterW() + 1 && swiat.czy_puste(this.x, this.y + 1) && this.czy_rozmnazanie && this.wiek >= 3 && partner.getterWiek() >= 3) {
                swiat.dodaj_organizm(this.potomstwo(this.x, this.y + 1));
                swiat.wylacz_mozliwosc_rozmnazania(this.znak);
            }
		    else if (this.y - 1 != 0 && swiat.czy_puste(this.x, this.y - 1) && this.czy_rozmnazanie && this.wiek >= 3 && partner.getterWiek() >= 3) {
                swiat.dodaj_organizm(this.potomstwo(this.x, this.y - 1));
                swiat.wylacz_mozliwosc_rozmnazania(this.znak);
            }
		    else if (this.x + 1 != swiat.getterH() + 1 && swiat.czy_puste(this.x + 1, this.y) && this.czy_rozmnazanie && this.wiek >= 3 && partner.getterWiek() >= 3) {
                swiat.dodaj_organizm(this.potomstwo(this.x + 1, this.y));
                swiat.wylacz_mozliwosc_rozmnazania(this.znak);
            }
		    else {
		        swiat.komentator.komentuj("Nie ma miejsca na nowego potomka lub gatunek w tej turze juz sie rozmnozyl lub osobnik jest za mlody!");
            }
        }
	    else {
            Organizm przeciwnik = swiat.szukaj_organizmu(atakowanyX, atakowanyY);
            if (przeciwnik != null) {
                if (this.sila >= przeciwnik.getterSila()) {
                    if (!przeciwnik.obrona(this)) {
                        this.setterX(atakowanyX);
                        this.setterY(atakowanyY);
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
    }
}
