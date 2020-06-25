package projekt.Rosliny;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.KlasyAbstrakcyjneIInterfejs.Roslina;
import projekt.Swiat;

public class Guarana extends Roslina {
    public Guarana(Swiat swiat, int x, int y){
        super(swiat, 0, 0, x, y, 0, 'G', true, false);
    }

    @Override
    public Organizm potomstwo(int x, int y) {
        return new Guarana(swiat, x, y);
    }

    @Override
    public boolean obrona(Organizm atakujacy) {
        atakujacy.setterSila(atakujacy.getterSila() + 3);
        swiat.komentator.komentuj("Sila " + atakujacy.getterZnak() + " zwiekszona o 3!");
        return false;
    }
}
