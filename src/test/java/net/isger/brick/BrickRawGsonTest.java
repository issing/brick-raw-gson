package net.isger.brick;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.isger.raw.Depository;
import net.isger.raw.TextRaw;

public class BrickRawGsonTest extends TestCase {

    public BrickRawGsonTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BrickRawGsonTest.class);
    }

    public void testRawGson() {
        List<?> result = Depository.getArtifact("brick-modules.json").transform(List.class);
        System.out.println(result);
        Hello hello = Depository.getArtifact(new TextRaw("{'input_man' : 'test'}")).transform(Hello.class);
        System.out.println(hello.getInput_man());
        assertTrue(result != null);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0), "test");
    }

    public static class Hello {

        private String input_man;

        public String getInput_man() {
            return input_man;
        }

        public void setInput_man(String input_man) {
            this.input_man = input_man;
        }

    }
}
