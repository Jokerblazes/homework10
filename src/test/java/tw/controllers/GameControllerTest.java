package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.core.Game;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private GameController gameController;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Game game;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        game = mock(Game.class);
        gameController = new GameController(game, new GameView());
    }

    @Test
    public void testBeginGame() {
        try {
            gameController.beginGame();
            assertEquals(systemOut(),"------Guess Number Game, You have 6 chances to guess!  ------\n");
        } catch (IOException e) {
            assertTrue(false);
        }

    }

    private String systemOut() {
        return outContent.toString();
    }
}