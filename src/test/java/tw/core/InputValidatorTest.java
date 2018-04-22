package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    private InputValidator inputValidator;

    @Before
    public void setup() {
        inputValidator = new InputValidator();
    }

    //验证数字超过4个
    @Test
    public void testValidateIfCountMoreThanFour() {
        assertFalse(inputValidator.validate("1 2 3 4 5"));
    }

    //验证有数字重复
    @Test
    public void testValidateIfNotSingle() {
        assertFalse(inputValidator.validate("1 1 2 3"));
    }

    //验证存在数字大于9
    @Test
    public void testValidateIfHasNumberMoreThanNine() {
        assertFalse(inputValidator.validate("1 2 3 4 10"));
    }

    //验证成功
    @Test
    public void testValidateSuccess() {
        assertTrue(inputValidator.validate("1 2 3 4"));
    }

}
