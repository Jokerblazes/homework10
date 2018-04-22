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

   
}