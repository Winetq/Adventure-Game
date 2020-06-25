package projekt.Wizualizacja;

import projekt.Swiat;
import projekt.Zwierzaki.Czlowiek;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ramka extends JFrame implements KeyListener {
    private JPanel mapa;
    private Swiat swiat;
    private Komentator komentator;

    public Ramka(JPanel mapa, Swiat swiat, Komentator komentator){
        this.mapa = mapa;
        this.swiat = swiat;
        this.komentator = komentator;

        setTitle("GRA");
        setSize(1200, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        this.add(mapa);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                JOptionPane.showMessageDialog(null, "Dziekuje za gre!");
                System.exit(0);
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // niepotrzebne
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int kierunek = keyEvent.getKeyCode();

        Czlowiek gracz = (Czlowiek) swiat.szukaj_gracza();

        if (gracz != null){
            int i = 0;
            if (gracz.getterGora())
                i += 1;
            if (gracz.getterDol())
                i += 1;
            if (gracz.getterPrawo())
                i += 1;
            if (gracz.getterLewo())
                i += 1;
            if (i == 0) {
                if (kierunek == KeyEvent.VK_UP) {
                    komentator.komentuj("GORA");
                    gracz.setterGora(true);
                } else if (kierunek == KeyEvent.VK_DOWN) {
                    komentator.komentuj("DOL");
                    gracz.setterDol(true);
                } else if (kierunek == KeyEvent.VK_RIGHT) {
                    komentator.komentuj("PRAWO");
                    gracz.setterPrawo(true);
                } else if (kierunek == KeyEvent.VK_LEFT) {
                    komentator.komentuj("LEWO");
                    gracz.setterLewo(true);
                } else
                    komentator.komentuj("Jeszcze raz!");
            }
            else
                komentator.komentuj("Juz wykonales swoj ruch w tej turze!");
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // niepotrzebne
    }
}
