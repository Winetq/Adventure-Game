package projekt.Wizualizacja;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Komentator {
    JTextArea notatnik;
    JScrollPane scrollPane;

    public Komentator(){
        notatnik = new JTextArea();
        scrollPane = new JScrollPane(notatnik);
        scrollPane.setBounds(850, 150, 250, 300);
        notatnik.setText("TWOJ RUCH!\n");
        notatnik.setEditable(false);
        notatnik.setFocusable(false);
        scrollPane.setFocusable(false);
    }
    public void komentuj(String komentarz){
        notatnik.append(komentarz + "\n");
    }
    public void czyszczenie(){
        notatnik.setText(null);
    }
    public JScrollPane getterScrollPane(){
        return scrollPane;
    }
}
