import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PhysicEngine implements Engine{
    private static ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList <Sprite> environment;

    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    public void addToEnvironmentList(Sprite sprite){
        if (!environment.contains(sprite)){
            environment.add(sprite);
        }
    }

    public void setEnvironment(ArrayList<Sprite> environment){
        this.environment=environment;
    }

    public static void addToMovingSpriteList(DynamicSprite sprite){
        if (!movingSpriteList.contains(sprite)){
            movingSpriteList.add(sprite);
        }
    }

    @Override
    public void update() throws IOException {
        for(DynamicSprite dynamicSprite : movingSpriteList){
            dynamicSprite.moveIfPossible(environment);

            // Vérification de la position du héros
            Sprite exitPoint = Playground.getExitPoint();
            if (dynamicSprite.isAtPosition(exitPoint)) {
                GameEngine.changeLevel(this);  // Déclenche le changement de niveau
            }
        }
    }





}
