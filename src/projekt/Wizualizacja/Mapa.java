package projekt.Wizualizacja;

import projekt.KlasyAbstrakcyjneIInterfejs.Organizm;
import projekt.Swiat;
import projekt.Zwierzaki.Czlowiek;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Mapa extends JPanel implements ActionListener {
    private Swiat swiat;
    private JButton Bwykonaj_ture;
    private JButton Bszybkosc_antylopy;
    private JButton Bzapisz;
    private JButton Bwczytaj;
    private JLabel Lgracz, Lboss, Ltrawa, Lmlecz, Lguarana, Lbarszcz, Lwilk, Lcyber, Lowca, Llis, Lzolw, Lantylopa;
    private Komentator komentator;

    public Mapa(Swiat swiat, Komentator komentator){
        this.swiat = swiat;

        this.komentator = komentator;
        add(komentator.getterScrollPane());

        setLayout(null);

        Bwykonaj_ture = new JButton("Wykonaj ture!");
        Bwykonaj_ture.setBounds(550, 25, 125, 25);
        Bwykonaj_ture.addActionListener(this);
        add(Bwykonaj_ture);
        Bwykonaj_ture.setFocusable(false);

        Bszybkosc_antylopy = new JButton("Szybkosc antylopy!");
        Bszybkosc_antylopy.setBounds(700, 25, 150, 25);
        Bszybkosc_antylopy.addActionListener(this);
        add(Bszybkosc_antylopy);
        Bszybkosc_antylopy.setFocusable(false);

        Bzapisz = new JButton("Zapisz gre!");
        Bzapisz.setBounds(875, 25, 125, 25);
        Bzapisz.addActionListener(this);
        add(Bzapisz);
        Bzapisz.setFocusable(false);

        Bwczytaj = new JButton("Wczytaj gre!");
        Bwczytaj.setBounds(1025, 25, 125, 25);
        Bwczytaj.addActionListener(this);
        add(Bwczytaj);
        Bwczytaj.setFocusable(false);

        Lgracz = new JLabel("- TY");
        Lgracz.setBounds(585, 106, 100, 20);
        add(Lgracz);

        Lboss = new JLabel("- BOSS");
        Lboss.setBounds(585, 141, 100, 20);
        add(Lboss);

        Ltrawa = new JLabel("- trawa");
        Ltrawa.setBounds(585, 176, 100, 20);
        add(Ltrawa);

        Lmlecz = new JLabel("- mlecz");
        Lmlecz.setBounds(585, 211, 100, 20);
        add(Lmlecz);

        Lguarana = new JLabel("- guarana");
        Lguarana.setBounds(585, 246, 100, 20);
        add(Lguarana);

        Lbarszcz = new JLabel("- barszcz sosnowskiego");
        Lbarszcz.setBounds(585, 281, 150, 20);
        add(Lbarszcz);

        Lwilk = new JLabel("- wilk");
        Lwilk.setBounds(585, 316, 100, 20);
        add(Lwilk);

        Lcyber = new JLabel("- cyber owca");
        Lcyber.setBounds(585, 351, 100, 20);
        add(Lcyber);

        Lowca = new JLabel("- owca");
        Lowca.setBounds(585, 386, 100, 20);
        add(Lowca);

        Llis = new JLabel("- lis");
        Llis.setBounds(585, 421, 100, 20);
        add(Llis);

        Lzolw = new JLabel("- zolw");
        Lzolw.setBounds(585, 456, 100, 20);
        add(Lzolw);

        Lantylopa = new JLabel("- antylopa");
        Lantylopa.setBounds(585, 491, 100, 20);
        add(Lantylopa);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.RED);
        for (int i = 1; i <= 20; i++){
            for (int j = 1; j <= 20; j++){
                graphics.drawRect(j * 25, i * 25, 25, 25);
            }
        }
        for (int i = 0; i < swiat.ile_organizmow(); i++){
            Color color = jaki_kolor(swiat.pobierz_znak(i));
            graphics.setColor(color);
            int x = swiat.pobierz_x(i);
            int y = swiat.pobierz_y(i);
            graphics.fillRect(y * 25 + 1, x * 25 + 1, 24, 24);
        }
        char[] znaki = {'+', '&', 'T', 'M', 'G', 'B', 'W', 'C', 'O', 'L', 'Z', 'A'};
        for (int i = 0; i < znaki.length; i++){ // legenda (tyle jest postaci)
            Color color = jaki_kolor(znaki[i]);
            graphics.setColor(color);
            graphics.fillRect(550, (i + 3) * 35,24, 24);
        }
    }

    Color jaki_kolor(char znak_organizmu){
        switch (znak_organizmu)
        {
            case '+':
                return Color.CYAN;
            case '&':
                return Color.BLACK;
            case 'T':
                return Color.GREEN;
            case 'M':
                return Color.YELLOW;
            case 'G':
                return Color.BLUE;
            case 'B':
                return Color.WHITE;
            case 'W':
                return Color.GRAY;
            case 'C':
                return Color.PINK;
            case 'O':
                return Color.LIGHT_GRAY;
            case 'L':
                return Color.ORANGE;
            case 'Z':
                return Color.DARK_GRAY;
            case 'A':
                return Color.MAGENTA;
        }
        return Color.RED; // bo inaczej error
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == Bwykonaj_ture){
            swiat.ruch_zwierzakow();
            repaint();
            if (swiat.getterKoniecGry()){
                JOptionPane.showMessageDialog(this, "Koniec gry!");
                Bwykonaj_ture.setEnabled(false);
                Bszybkosc_antylopy.setEnabled(false);
                Bzapisz.setEnabled(false);
                Bwczytaj.setEnabled(false);
            }
        }
        if (source == Bszybkosc_antylopy){
            Czlowiek gracz = (Czlowiek) swiat.szukaj_gracza();
            if (gracz != null){
                if (!gracz.getterZnakSpecjalny()) {
                    if (gracz.getterDezaktywacja() >= 5) {
                        gracz.setterZnakSpecjalny(true);
                        JOptionPane.showMessageDialog(this, "Szybkosc antylopy aktywowana!");
                    }
                    else
                        komentator.komentuj("Jeszcze nie mozesz uzyc swojej umiejetnosci specjalnej. Zostalo " + (5 - gracz.getterDezaktywacja()) + " tur!");
                }
                else
                    komentator.komentuj("Szybkosc antylopy jest juz aktywowana!");
            }
        }
        if (source == Bzapisz){
            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                File file = fc.getSelectedFile();
                try {
                    PrintWriter pw = new PrintWriter(file);
                    StringBuilder stan_gry = new StringBuilder();
                    for (int i = 1; i <= swiat.getterH(); i++){
                        for (int j = 1; j <= swiat.getterW(); j++){
                            if (!swiat.czy_puste(i, j)){
                                Organizm organizm = swiat.szukaj_organizmu(i, j);
                                String  wiersz = organizm.getterZnak() + "(" + i + ", " + j + ")" + "\n";
                                stan_gry.append(wiersz);
                            }
                        }
                    }
                    Scanner scanner = new Scanner(stan_gry.toString());
                    while (scanner.hasNext())
                        pw.println(scanner.nextLine());
                    pw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (source == Bwczytaj){
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                swiat.czyszczenie_swiata();
                File file = fc.getSelectedFile();
                try {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNext()){
                        String organizm = scanner.nextLine();
                        char[] znaki = organizm.toCharArray();
                        char znak_organizmu = znaki[0];

                        if (znaki.length == 7){
                            int x = znaki[2] - '0';
                            int y = znaki[5] - '0';
                            swiat.tworzenie_organizmu(znak_organizmu, x, y);
                        }
                        if (znaki.length == 8){
                            if (znaki[3] == ','){
                                int x = znaki[2] - '0';
                                String Y = String.valueOf(znaki[5]) + String.valueOf(znaki[6]);
                                int y = Integer.parseInt(Y);
                                swiat.tworzenie_organizmu(znak_organizmu, x, y);
                            }
                            else{
                                String X = String.valueOf(znaki[2]) + String.valueOf(znaki[3]);
                                int x = Integer.parseInt(X);
                                int y = znaki[6] - '0';
                                swiat.tworzenie_organizmu(znak_organizmu, x, y);
                            }
                        }
                        if (znaki.length == 9){
                            String X = String.valueOf(znaki[2]) + String.valueOf(znaki[3]);
                            String Y = String.valueOf(znaki[6]) + String.valueOf(znaki[7]);
                            int x = Integer.parseInt(X);
                            int y = Integer.parseInt(Y);
                            swiat.tworzenie_organizmu(znak_organizmu, x, y);
                        }
                    }
                    repaint();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
