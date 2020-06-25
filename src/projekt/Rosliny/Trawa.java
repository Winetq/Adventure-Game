package projekt.Rosliny;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.KlasyAbstrakcyjneIInterfejs.Roslina;
import projekt.Swiat;

public class Trawa extends Roslina {
    public Trawa(Swiat swiat, int x, int y){
        super(swiat, 0, 0, x, y, 0, 'T', true, false);
    }

    @Override
    public Organizm potomstwo(int x, int y) {
        return new Trawa(swiat, x, y);
    }

    @Override
    public boolean obrona(Organizm atakujacy) {
        return false;
    }
}
