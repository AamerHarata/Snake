import javax.swing.*;
import java.awt.*;

public class Runner {

    public static void main(String[] args) {


        //Create the main frame
        JFrame frame = new JFrame();

        //Create an instance of the class GUI
        Gui gui = new Gui();

        //Set main dimensions to the main frame
        frame.setBounds(300, 0, Conf.SCREEN_WIDTH, Conf.SCREEN_HEIGHT);
        //Set the main background
        frame.setBackground(Color.DARK_GRAY);
        //Prevent user from resizing the game window
        frame.setResizable(false);
        //Add the GUI instance (JPanel) to the main frame
        frame.add(gui);
        //Make the game window visible
        frame.setVisible(true);

        //Terminate the project on window close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

}