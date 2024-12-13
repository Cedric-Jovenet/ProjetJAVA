import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine{
    private static ArrayList<Displayable> renderList;
    private String timerText = "";


    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    public static void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public static void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    public static void clearRenderList() {
        renderList.clear();  // Vide la liste des sprites à afficher
    }

    // Mise à jour du texte du chronomètre
    public void setTimerText(String text) {
        timerText = text;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject:renderList) {
            renderObject.draw(g);
        }

        // Affiche le texte du timer
        g.setColor(Color.BLACK); // Couleur du texte
        g.setFont(new Font("Arial", Font.BOLD, 20)); // Police du texte
        g.drawString(timerText, 10, 30); // Position du texte sur l'écran

    }

    @Override
    public void update(){
        this.repaint();
    }
}
