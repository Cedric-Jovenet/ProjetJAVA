import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GameEngine implements Engine, KeyListener {
    static DynamicSprite hero;
    private static int currentLevel = 0;
    private static final String[] levels = {"./data/level1.txt", "./data/level2.txt","./data/level3.txt"}; // Liste des niveaux

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void changeLevel(PhysicEngine physicEngine) throws IOException {
        currentLevel = (currentLevel + 1) % levels.length;
        Playground newLevel = new Playground(levels[currentLevel]);

        RenderEngine.clearRenderList();
        RenderEngine.addToRenderList(newLevel.getSpriteList());
        RenderEngine.addToRenderList(hero);

        // Mettre à jour l'environnement en utilisant l'instance de PhysicEngine passée en paramètre
        physicEngine.setEnvironment(newLevel.getSolidSpriteList());
        PhysicEngine.addToMovingSpriteList(hero);

        hero.x = 200;
        hero.y = 300;
    }

}
