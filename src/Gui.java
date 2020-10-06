
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gui extends JPanel implements KeyListener, ActionListener {




    Timer t;

    //Create the needed objects (Apple - Snake)
    Apple apple = new Apple();
    Snake snake = new Snake(4, apple);

    public Gui() {

        //Call the function to set game settings (See inside the function)
        SetGameSettings();

        //Add key listener to the game, and make it focusable to make the listener work correctly
        addKeyListener(this);
        this.setFocusable(true);



        //Set timer that will call Action Listener every 200 milliseconds.
        t = new Timer(Conf.DELAY, this);
        snake.t = t;





    }


    @Override
    //Default pain function that comes by default as we inherit from JPanel (extends JPanel)
    public void paint(Graphics g) {

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, Conf.SCREEN_WIDTH, Conf.SCREEN_HEIGHT);

        //Draw the white banner over the game screen
//        g.setColor(Color.WHITE);
        ImageIcon banner = new ImageIcon("SnakeBanner.png");
        banner.paintIcon(this, g, 10, 10);
//        g.fillRect(10, 10, Conf.SCREEN_WIDTH - 30, 100);

        //Draw the strings on the banner
        Font f = new Font(null, Font.BOLD, 16);
        g.setColor(Color.BLACK);
        g.setFont(f);

        g.drawString("Player Name: " + Conf.PLAYER_NAME, 20, 40);
        g.drawString("Score: " + snake.score, 20, 60);

        //Draw the borders of the game screen
        if(Conf.BORDER)
            g.setColor(Color.RED);
        else
            g.setColor(Color.WHITE);

        g.fillRect(9, 119, Conf.SCREEN_WIDTH - 28, Conf.SCREEN_HEIGHT - 163);

        //Draw the game area
        g.setColor(Color.BLACK);
        g.fillRect(Conf.GAME_X, Conf.GAME_Y, Conf.GAME_WIDTH, Conf.GAME_HEIGHT);



        //Draw game objects
        snake.Draw(g, this);
        apple.Draw(g, this);


    }





    public void SetGameSettings(){
        //Test field where the user will type in the name
        JTextField playerName = new JTextField();

        //List of all possible difficulties in our game (Easy, Medium and Hard) Those are coming as Enum (See Conf class)
        JList<Difficulty> difficulty = new JList<>(Difficulty.values());

        //Set the default difficulty as Medium on start
        difficulty.setSelectedValue(Difficulty.Medium, false);

        //Create the radio option Borders (That will determine whether the snake will crash when hit a wall or appear from the other side).
        JRadioButton rb = new JRadioButton("Borders");

        //Array of objects that take in all the values to show to the user next.
        Object[] messages = {"Enter your name:", playerName, "Difficulty:", difficulty, "Mode", rb};

        //Show message that views all the taken in objects
        JOptionPane.showMessageDialog(null, messages);




        //Check if the user has entered an empty value in the name field. Then determine what the player name is
        if(playerName.getText().isEmpty())
            Conf.PLAYER_NAME = "PLAYER";
        else
            Conf.PLAYER_NAME = playerName.getText();


        //Save tha taken in values to the game configurations (Static variables in Conf class)
        Conf.DIFFICULTY = difficulty.getSelectedValue();
        Conf.BORDER = rb.isSelected();


        switch (Conf.DIFFICULTY){
            case Easy:
                Conf.DELAY = 200;
                break;
            case Medium:
                Conf.DELAY = 100;
                break;
            case Hard:
                Conf.DELAY = 50;
                break;
        }
        
    }

    public void GameOver(){
        t.stop();
        int answer = JOptionPane.showConfirmDialog(null, "Score is: " + snake.score + "\nDo you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);

        if(answer == 0){
            apple = new Apple();
            snake = new Snake(4, apple);
        }else{
            System.exit(0);
        }
    }




    @Override
    //The main action listener that called by Timer (t) in our game.
    public void actionPerformed(ActionEvent e) {

        //When action called (timer) move the snake continuously (That will make the snake move by it self).
        //The current direction is where the snake directed currently (So the timer will just make it keep going not change the direction)
        snake.MoveBody();
        if(!snake.MoveHead(snake.currentDirection)){
            GameOver();
        }

        //Repaint is a default function that call the function Paint() again and draw the objects again
        //This function must be called each time a change in the game (e.g. position) happened
        repaint();

    }




    @Override
    //The main Key Listener that will be called once a key (on the keyboard) pressed
    public void keyPressed(KeyEvent e) {

        if(!t.isRunning()){
            System.out.println("New time here");
            //Start the timer here so the game begins directly (we will change this)
            t.start();
        }

        //Move the snake to a specific direction upon the clicked button
        //We will change that to make the snake only changes its direction here but not to move (Moving must be happened only by Timer t)

        if(e.getKeyCode() == KeyEvent.VK_D){
            snake.ChangeDirection(SnakeDirection.Right);
        }

        if (e.getKeyCode() == KeyEvent.VK_W) {
            snake.ChangeDirection(SnakeDirection.Up);
        }

        if(e.getKeyCode() == KeyEvent.VK_A){
            snake.ChangeDirection(SnakeDirection.Left);
        }

        if(e.getKeyCode() == KeyEvent.VK_S){
            snake.ChangeDirection(SnakeDirection.Down);
        }

//        if(e.getKeyCode() == KeyEvent.VK_Y){
//            snake.score += 1;
//        }

        //ToDo :: Start new game
        if(e.getKeyCode() == KeyEvent.VK_T){
//            apple.GiveNewPosition();
//            System.out.println("You lose the game");

//            snake = new Snake(3);
//            apple = new Apple();
            t.stop();
        }



        //repaint() function must be called here to draw the snake again.
        repaint();
    }

















    //Functions not in use (Default by Key Listener) those can be used when a key released (finish pressed and so on)
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


}
