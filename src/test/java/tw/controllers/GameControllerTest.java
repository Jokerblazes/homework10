package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.commands.GuessInputCommand;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.GameStatus;
import tw.core.model.GuessResult;
import tw.views.GameView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

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

    //测试Play并且猜对
    @Test
    public void testPlayAndGuessRight() {
        String result = "------Please input your answer as x x x x , x <10 ------\n" +
                "Guess Result: 4A0B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\n" +
                "Game Status: success\n";
        String in = "1 2 3 4";
        System.setIn(new ByteArrayInputStream(in.getBytes()));
        Answer answer = mock(Answer.class);
        when(answer.toString()).thenReturn(in);
        when(game.checkCoutinue()).thenReturn(true).thenReturn(false);
        GuessResult guessResult = new GuessResult("4A0B", answer);
        when(game.guess(any())).thenReturn(guessResult);
        when(game.guessHistory()).thenReturn(Arrays.asList(guessResult));
        when(game.checkStatus()).thenReturn(GameStatus.SUCCESS);

        try {
            gameController.play(new GuessInputCommand());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(systemOut(),result);
    }


    //测试Play并且猜错
    @Test
    public void testPlayAndGuessFail() {
        String result = "------Please input your answer as x x x x , x <10 ------\n" +
                "Guess Result: 3A1B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "------Please input your answer as x x x x , x <10 ------\n" +
                "Guess Result: 3A1B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "------Please input your answer as x x x x , x <10 ------\n" +
                "Guess Result: 3A1B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "------Please input your answer as x x x x , x <10 ------\n" +
                "Guess Result: 3A1B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "------Please input your answer as x x x x , x <10 ------\n" +
                "Guess Result: 3A1B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "[Guess Numbers: 1 2 3 5, Guess Result: 3A1B]\n" +
                "Game Status: fail\n";
        String input = "1 2 3 5\n" +
                "1 2 3 5\n" +
                "1 2 3 5\n" +
                "1 2 3 5\n" +
                "1 2 3 5\n" +
                "1 2 3 5";
        when(game.checkCoutinue()).thenReturn(true).
                thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        Answer answer = mock(Answer.class);
        when(answer.toString()).thenReturn("1 2 3 5");
        GuessResult guessResult = new GuessResult("3A1B", answer);
        when(game.guess(any())).thenReturn(guessResult);
        when(game.checkStatus()).thenReturn(GameStatus.FAIL);
        when(game.guessHistory()).thenReturn(Arrays.asList(guessResult))
        .thenReturn(Arrays.asList(guessResult,guessResult))
        .thenReturn(Arrays.asList(guessResult,guessResult,guessResult))
        .thenReturn(Arrays.asList(guessResult,guessResult,guessResult,guessResult))
        .thenReturn(Arrays.asList(guessResult,guessResult,guessResult,guessResult,guessResult))
        .thenReturn(Arrays.asList(guessResult,guessResult,guessResult,guessResult,guessResult,guessResult));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        try {
            gameController.play(new GuessInputCommand());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(systemOut(),result);
    }

    //测试输入数据格式错误
    @Test
    public void testPlayAndInputError() {
        String result = "------Please input your answer as x x x x , x <10 ------\n" +
                "------Please input your answer as x x x x , x <10 ------\n" +
                "Guess Result: 4A0B\n" +
                "Guess History:\n" +
                "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\n" +
                "Game Status: success\n";
        String input = "1 2\n" +
                "1 2 3 4";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        when(game.checkCoutinue()).thenReturn(true).thenReturn(false);
        Answer answer = mock(Answer.class);
        when(answer.toString()).thenReturn("1 2 3 4");
        GuessResult guessResult = new GuessResult("4A0B", answer);
        when(game.guess(any())).thenReturn(guessResult);
        when(game.guessHistory()).thenReturn(Arrays.asList(guessResult));
        when(game.checkStatus()).thenReturn(GameStatus.SUCCESS);
        try {
            gameController.play(new GuessInputCommand());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(systemOut(),result);
    }

    private String systemOut() {
        return outContent.toString();
    }
}