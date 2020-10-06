import javax.swing.*;
import java.awt.*;

//Rectangle class that extends geometry because it has X, Y, Width and Height
//The Rect can be used as a part of the snake body.
public class Rect extends Geometry{
    Color color;
    private String pictureName;

    public Rect() {

        //Set the initiated values to the created rectangle
        this.setX(500);
        this.setY(420);
        this.setWidth(25);
        this.setHeight(25);
        this.color = Color.yellow;
        this.pictureName = "SnakePart.png";
    }


    public void Draw(Graphics g, Gui gui){

        ImageIcon rectImage = new ImageIcon(pictureName);
        rectImage.paintIcon(gui, g, this.getX(), this.getY());


//        g.setColor(this.color);
        //Draw the snake part as Oval (Circle). We will replace that later by an image.
//        g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());

    }


    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
}
