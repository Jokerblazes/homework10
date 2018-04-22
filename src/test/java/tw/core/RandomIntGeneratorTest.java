package tw.core;


import org.junit.Test;
import tw.core.generator.RandomIntGenerator;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {
    @Test
    public void testGenerateNums() {
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        String result = randomIntGenerator.generateNums(9, 4);
        Set<Integer> set = new HashSet<>();
        for (String s : result.split(" ")) {
            set.add(Integer.parseInt(s));
        }
        assertEquals(set.size(),4);
        set.forEach(integer -> assertEquals(integer >= 0 && integer <= 9,true));
    }
}