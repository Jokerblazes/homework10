package tw.core.generator;

import org.junit.Test;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {
    @Test
    public void testGenerateSuccess() {

        RandomIntGenerator randomIntGenerator = mock(RandomIntGenerator.class);
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 2 3 4");
        AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);
        try {
            answerGenerator.generate();
            assertTrue(true);
        } catch (OutOfRangeAnswerException e) {
            assertTrue(false);
        }
    }
}

