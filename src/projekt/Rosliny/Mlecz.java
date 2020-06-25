package projekt.Rosliny;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.KlasyAbstrakcyjneIInterfejs.Roslina;
import projekt.Swiat;

public class Mlecz extends Roslina {
    public Mlecz(Swiat swiat, int x, int y){
        super(swiat, 0, 0, x, y, 0, 'M', true, false);
    }

    @Override
    public Organizm potomstwo(int x, int y) {
        return new Mlecz(swiat, x, y);
    }

    @Override
    public boolean obrona(Organizm atakujacy) {
        return false;
    }

    @Override
    public void akcja() {
        super.akcja();
        super.akcja();
        super.akcja();
    }
}
