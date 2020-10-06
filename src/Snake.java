import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake{

    //Variables (Properties) those must be had by a snake object

    //The snake has body which is many rectangles objects (each for each part)
    ArrayList<Rect> body = new ArrayList<>();

    //The snake has a head which is also a part of the body but it is the last rectangle in the snake (The head will always be the first to move)
    Rect head;

    //The snake has a score will be increased once the snake has eaten an apple
    int score;

    //This boolean determine whether the snake should crash the wall or appear in the other side (We do not need that anymore as we make it in the Conf class by Borders)
//    boolean crashWall;

    //This variable of type (Enum Snake Direction .. see class Conf) will determine the current direction of the snake
    SnakeDirection currentDirection;

    //We add the object apple to the snake for later use (Check collision / apple eaten) but it does not have to be here you can choose your own way.
    Apple apple;

    Timer t;

    public Snake(int length, Apple apple) {
        //Set the current direction to the right as start (The snake will start pointing to the right)
        this.currentDirection = SnakeDirection.Right;
        this.apple = apple;

        //We could use that or just use Conf.Borders or both (We will see later).
//        this.crashWall = false;


        //Add the initial body part (upon to the int length sent as parameter) then set the position for each part
        for(int i = 0; i < length; i++){
            Rect bodyPart = new Rect();
            bodyPart.setX(10 + (25 * i));
            body.add(bodyPart);
        }

        //Set the head as the last part in the body, make it red to identify it easily (We will replace the color by an image later).
        head = body.get(body.size() - 1);
        head.color = Color.RED;
        head.setPictureName("SnakeHeadRight.png");
    }



    //Draw the snake body (including the head)
    public void Draw(Graphics g, Gui gui){

        //Foreach loop that goes throw the snake from index 0 to the end of the body list
        for(Rect part : body){
            //For each index draw the part on that index
            part.Draw(g, gui);

        }
    }

    //Function of moving the head
    public boolean MoveHead(SnakeDirection direction){

        //Check where to move it upon the given direction (We will change that later to be easier)
        switch (direction){
            case Right:
                head.setX(head.getX()+25);
                break;

            case Up:
                head.setY(head.getY() - 25);
                break;

            case Left:
                currentDirection = SnakeDirection.Left;

                head.setX(head.getX()-25);
                break;

            case Down:
                head.setY(head.getY() + 25);
                break;

        }

        CheckCollisionWithApple();

        if(CheckCollisionWithWall()){
            return false;
        }

        if(CheckCollisionWithBody()){
            return false;
        }


        return true;

    }

    public void MoveBody(){

        //Loop throw all body (Except the head).. Set the x and y for each element as the next element by (i + 1)
        for (int i = 0; i < body.size() - 1; i++){

            body.get(i).setX(body.get(i + 1).getX());
            body.get(i).setY(body.get(i + 1).getY());

        }

    }

    public void ChangeDirection(SnakeDirection newDirection){

        //If the direction is allowed to be changed do it. Otherwise, return the function
        switch (newDirection){
            case Up:
                if(currentDirection == SnakeDirection.Down)
                    return;
                this.head.setPictureName("SnakeHeadUp.png");
                break;
            case Down:
                if(currentDirection == SnakeDirection.Up)
                    return;
                this.head.setPictureName("SnakeHeadDown.png");
                break;
            case Left:
                if(currentDirection == SnakeDirection.Right)
                    return;
                this.head.setPictureName("SnakeHeadLeft.png");
                break;
            case Right:
                if(currentDirection == SnakeDirection.Left)
                    return;
                this.head.setPictureName("SnakeHeadRight.png");
                break;
        }

        this.currentDirection = newDirection;

    }

    public void CheckCollisionWithApple(){

        boolean collisionX = head.getX() >= apple.getX() && head.getX() <= apple.getX() + apple.getWidth()-2;
        boolean collisionY = head.getY() >= apple.getY() && head.getY() <= apple.getY() + apple.getHeight()-2;

        if(collisionX && collisionY){
            System.out.println("Got Collision");
            apple.GiveNewPosition();
            this.score++;

            Rect newPart = new Rect();
            newPart.setX(-5000);
            newPart.setY(-5000);
            body.add(0, newPart);
        }

    }

    public boolean CheckCollisionWithWall(){

        boolean collisionRight = head.getX() + head.getWidth() > Conf.GAME_X+ Conf.GAME_WIDTH;
        boolean collisionLeft = head.getX() < Conf.GAME_X;
        boolean collisionUp = head.getY() < Conf.GAME_Y;
        boolean collisionDown = head.getY() + head.getHeight() > Conf.GAME_Y + Conf.GAME_HEIGHT;


        if(Conf.BORDER){
            if(collisionDown || collisionLeft || collisionRight || collisionUp){
                return true;
            }
        }


        if(collisionRight) {
            head.setX(Conf.GAME_X);
        }

        if(collisionLeft){
            head.setX(Conf.GAME_X+ Conf.GAME_WIDTH);
        }

        if (collisionUp) {
            head.setY(Conf.GAME_Y + Conf.GAME_HEIGHT);
        }

        if (collisionDown) {
            head.setY(Conf.GAME_Y);
        }


        return false;

    }

    public boolean CheckCollisionWithBody(){

        for (int i = 0; i < body.size() - 1; i++) {

            if(head.getX() == body.get(i).getX() && head.getY() == body.get(i).getY()){
                return true;
            }

        }

        return false;

    }


}