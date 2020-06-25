package projekt.Zwierzaki;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.Swiat;
import projekt.KlasyAbstrakcyjneIInterfejs.Zwierze;

public class Wilk extends Zwierze {
    public Wilk(Swiat swiat, int x, int y){
        super(swiat, 9, 5, x, y, 0, 'W', true, false);
    }

    @Override
    public Organizm potomstwo(int x, int y) {
        return new Wilk(swiat, x, y);
    }

    @Override
    public boolean obrona(Organizm atakujacy) {
        return false;
    }
}
