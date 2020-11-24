package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterestListTest {
    private InterestList in;

    @BeforeEach
    public void runBefore() {
        in = new InterestList();

    }

    @Test
    public void testInterestList() {

        assertEquals(0,in.getSize());
    }

    @Test
    public void testInsertInterest() {
        in.insertInterest("League of Legends");

        assertEquals(1,in.getSize());
        assertTrue(in.containsInterest("League of Legends"));
    }

    @Test
    public void testInsertInterestMany() {
        in.insertInterest("League of Legends");
        in.insertInterest("Playing Guitar");

        assertEquals(2,in.getSize());
        assertTrue(in.containsInterest("League of Legends"));
        assertTrue(in.containsInterest("Playing Guitar"));
    }

    @Test
    public void testRemoveInterest() {
        in.insertInterest("Bowling");
        int count = in.getSize();
        in.removeInterest("Bowling");

        assertEquals(count - 1, in.getSize());


    }

    @Test
    public void testRemoveInterestMany() {
        in.insertInterest("League of Legends");
        in.insertInterest("Bowling");
        int count = in.getSize();
        in.removeInterest("Bowling");

        assertEquals(count - 1, in.getSize());
    }

    @Test
    public void testCommonInterestsNone() {
        InterestList in2 = new InterestList();
        in.insertInterest("Skydiving");
        in2.insertInterest("Scuba Diving");

        InterestList result = in.commonInterests(in2);
        assertEquals(0,result.getSize());
    }

    @Test
    public void testCommonInterestsOne() {
        InterestList in2 = new InterestList();
        in.insertInterest("Skydiving");
        in.insertInterest("Eating");
        in2.insertInterest("Scuba Diving");
        in2.insertInterest("Eating");

        InterestList result = in.commonInterests(in2);
        assertEquals(1,result.getSize());
    }
    @Test
    public void testCommonInterestsMany() {
        InterestList in2 = new InterestList();
        in.insertInterest("Skydiving");
        in.insertInterest("Eating");
        in2.insertInterest("Skydiving");
        in2.insertInterest("Eating");

        InterestList result = in.commonInterests(in2);
        assertEquals(2,result.getSize());
    }

    @Test
    public void testContainsInterest() {
        in.insertInterest("League of Legends");

        assertTrue(in.containsInterest("League of Legends"));
        assertFalse(in.containsInterest("Partying"));
    }

    @Test
    public void testDisplayOne() {
        in.insertInterest("League of Legends");

        assertTrue(in.display().equals("League of Legends"));
    }

    @Test
    public void testDisplayMany() {
        in.insertInterest("League of Legends");
        in.insertInterest("Walking");

        assertTrue(in.display().equals("League of Legends, Walking"));
    }

    @Test
    public void testGetInterests() {
        in.insertInterest("League of Legends");
        in.insertInterest("Walking");

        List<Interest> testInterestList = in.getListOfInterests();
        assertEquals(2,in.getListOfInterests().size());
        assertEquals(testInterestList.size(),in.getSize());

    }

}
