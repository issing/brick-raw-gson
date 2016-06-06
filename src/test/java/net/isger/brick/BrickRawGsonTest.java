package net.isger.brick;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.isger.raw.Depository;

public class BrickRawGsonTest extends TestCase {

    public BrickRawGsonTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BrickRawGsonTest.class);
    }

    public void testRawGson() {
        List<?> result = Depository.getArtifact("brick-modules.json")
                .transform(List.class);
        System.out.println(result);
        assertTrue(result != null);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0), "test");
    }
}
