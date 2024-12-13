import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Playground {
    private ArrayList<Sprite> environment = new ArrayList<>();
    private static Sprite exitSprite = null; // Sprite représentant le point de sortie

    public Playground(String pathName) throws IOException {

            final Image imageTree = ImageIO.read(new File("./img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("./img/grass.png"));
            final Image imageRock = ImageIO.read(new File("./img/rock.png"));
            final Image imageTrap = ImageIO.read(new File("./img/trap.png"));
            final Image imageExit = ImageIO.read(new File("./img/tileSetTest.png"));
            final Image imageTileset = ImageIO.read(new File("./img/tileSet.png"));

            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);

            final int tileWidth = 64;
            final int tileHeight = 64;

            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);

            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line = bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;

            while (line != null) {
                for (byte element : line.getBytes(StandardCharsets.UTF_8)) {
                    switch (element) {
                        case 'T':
                            environment.add(new SolidSprite(columnNumber * imageTreeWidth,
                                    lineNumber * imageTreeHeight, imageTree, imageTreeWidth, imageTreeHeight));
                            break;
                        case ' ':
                            environment.add(new Sprite(columnNumber * imageGrassWidth,
                                    lineNumber * imageGrassHeight, imageGrass, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R':
                            environment.add(new SolidSprite(columnNumber * imageRockWidth,
                                    lineNumber * imageRockHeight, imageRock, imageRockWidth, imageRockHeight));
                            break;
                        case '.': // Point de sortie
                            exitSprite = new Sprite(columnNumber * imageTreeWidth,
                                    lineNumber * imageTreeHeight, imageExit, imageTreeWidth, imageTreeHeight);
                            environment.add(exitSprite);
                            break;
                        case 'H': // Utilisation de la tuile de l'image du tileset à la 10ème ligne et 2ème colonne
                            int tileX = 1 * tileWidth;  // 2ème colonne (indexée à partir de 0)
                            int tileY = 9 * tileHeight; // 10ème ligne (indexée à partir de 0)
                            // Extraire la tuile spécifique de l'image du tileset
                            Image grassTile = ((java.awt.image.BufferedImage) imageTileset).getSubimage(tileX, tileY, tileWidth, tileHeight);
                            environment.add(new Sprite(columnNumber * tileWidth,
                                    lineNumber * tileHeight, grassTile, tileWidth, tileHeight));
                            break;
                    }
                    columnNumber++;
                }
                columnNumber = 0;
                lineNumber++;
                line = bufferedReader.readLine();


        }
    }

    public ArrayList<Sprite> getSolidSpriteList() {
        ArrayList<Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }

    public ArrayList<Displayable> getSpriteList() {
        ArrayList<Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment) {
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }

    public static Sprite getExitPoint() {
        return exitSprite;
    }
}
