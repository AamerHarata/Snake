import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

//Apple class that extends geometry because it has X, Y, Width and Height
public class Apple extends Geometry{

    //For now the apple has only color as property (will be replace by an image). In addition to the inherited properties (x, y, width and height) from Geometry
    Color color;
    Color newColor;

    ArrayList<Integer> randomX= new ArrayList<>();;
    ArrayList<Integer> randomY = new ArrayList<>();;

    public Apple(){

        //The size of the apple
        this.setWidth(25);
        this.setHeight(25);

        // Random values to give the apple a random position
        Random random = new Random();

        //The min/max x and y to position the apple randomly within the game borders
        int minX = Conf.GAME_X;
        int maxX = Conf.GAME_X + Conf.GAME_WIDTH - this.getWidth();

        int minY = Conf.GAME_Y;
        int maxY = Conf.GAME_Y + Conf.GAME_HEIGHT - this.getHeight();


        for(int i = minX; i< maxX; i+=25)
            randomX.add(i);

        for(int i = minY; i< maxY; i+=25)
            randomY.add(i);





        color = Color.ORANGE;

        this.GiveNewPosition();



        //Set the random values to the apple as position
//        this.setX(random.nextInt(maxX - minX) + 1 + minX);
//        this.setY(random.nextInt(maxY - minY) + 1 + minY);



    }

    //Draw the apple
    public void Draw(Graphics g, Gui gui) {

        ImageIcon apple = new ImageIcon("apple.png");
        apple.paintIcon(gui, g, this.getX(), this.getY());

//        g.setColor(color);
//        g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void GiveNewPosition(){
        Collections.shuffle(randomX);
        Collections.shuffle(randomY);

        this.setX(randomX.get(0));
        this.setY(randomY.get(0));

    }

}
