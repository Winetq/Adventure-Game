package projekt.Zwierzaki;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.Swiat;
import projekt.KlasyAbstrakcyjneIInterfejs.Zwierze;

public class Owca extends Zwierze {
    public Owca(Swiat swiat, int x, int y){
        super(swiat, 4, 4, x, y, 0, 'O', true, false);
    }

    @Override
    public Organizm potomstwo(int x, int y) {
        return new Owca(swiat, x, y);
    }

    @Override
    public boolean obrona(Organizm atakujacy) {
        return false;
    }
}
