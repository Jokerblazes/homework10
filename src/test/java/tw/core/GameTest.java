package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.core.model.GuessResult;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {
    private Game game;
    @Before
    public void setup() {
        try {
            AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
            Answer answer = new Answer();
            answer.setNumList(Arrays.asList("1","2","3","4"));
            when(answerGenerator.generate()).thenReturn(answer);
            game = new Game(answerGenerator);
            assertTrue(true);
        } catch (OutOfRangeAnswerException e) {
            assertTrue(false);
        }
    }
    @Test
    //测试guess方法
    public void testGuess() {
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("1", "2", "3", "4"));
        GuessResult guess = game.guess(inputAnswer);
        assertEquals(guess.getInputAnswer(),inputAnswer);

        String pattern = "\\dA\\dB";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(guess.getResult());
        assertTrue(m.matches());
    }
}
