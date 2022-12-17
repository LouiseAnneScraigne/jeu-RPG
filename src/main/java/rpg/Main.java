package rpg;

import util.ConsoleParser;
import util.InputParser;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        InputParser parser = new ConsoleParser();
        Game game = new Game(parser);
        game.start();

    }
}
