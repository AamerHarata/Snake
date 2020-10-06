//Enum to describe the snake direction
enum SnakeDirection{
    Up, Down, Left, Right
}

//Enum of all difficulties we can have in our game
enum Difficulty{
    Easy, Medium, Hard
}

//Static variables those are shared in the whole project. We use them as game settings or configurations.
public class Conf {

    //Main screen
    public static int SCREEN_WIDTH = 1380;
    public static int SCREEN_HEIGHT = 900;

    //Playing area (The black area)
    public static int GAME_X = 10;
    public static int GAME_WIDTH = SCREEN_WIDTH - 30;
    public static int GAME_Y = 120;
    public static int GAME_HEIGHT = SCREEN_HEIGHT - 165;

    //Other settings
    public static String PLAYER_NAME;
    public static Difficulty DIFFICULTY;
    public static boolean BORDER;
    public static int DELAY = 100;

}
