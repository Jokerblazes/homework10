package tw.core;

import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {
    @Test
    public void testCreateAnswer() {
        Answer answer = Answer.createAnswer("3 4 5 6");
        assertEquals(answer.getIndexOfNum("3"),0);
        assertEquals(answer.getIndexOfNum("4"),1);
        assertEquals(answer.getIndexOfNum("5"),2);
        assertEquals(answer.getIndexOfNum("1"),-1);
    }

    @Test
    public void testValidateSuccess() {
        Answer answer = new Answer();
        answer.setNumList(Arrays.asList("3","4","5","6"));
        try {
            answer.validate();
            assertTrue(true);
        } catch (OutOfRangeAnswerException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testValidateFail() {
        Answer answer = new Answer();
        answer.setNumList(Arrays.asList("3","4","3","6"));
        try {
            answer.validate();
            assertTrue(false);
        } catch (OutOfRangeAnswerException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testCheck() {
        Answer answer = new Answer();
        answer.setNumList(Arrays.asList("1","2","3","4"));

        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("0","3","2","4"));
        Record record = answer.check(inputAnswer);
        int[] result = {1, 2};
        assertArrayEquals(record.getValue(),result);
    }

}