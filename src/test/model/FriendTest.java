package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FriendTest {
    private Friend fr;
    @BeforeEach
    public void runBefore() {
        fr = new Friend("Leonardo Dicaprio");

    }

    @Test
    public void testFriend() {
        assertTrue(fr.getName().equals("Leonardo Dicaprio"));
        assertEquals(0,fr.getInterestsSize());

    }

    @Test
    public void testFriendMultipleInputs() {
        InterestList il = new InterestList();
        Friend frMultiple = new Friend("Bob Unicorn", "11/11", il);
        assertTrue(frMultiple.getName().equals("Bob Unicorn"));
        assertEquals(il.getSize(), frMultiple.getInterestsSize());
        assertTrue(frMultiple.getBirthday().equals("11/11"));
    }

    @Test
    public void testSetBirthday() {
        String bday = "12/10";
        fr.setBirthday(bday);

        assertTrue(bday.equals(fr.getBirthday()));

    }

    @Test
    public void testAddInterest() {
        fr.addInterest("Bowling");
        fr.addInterest("Swimming");
        assertEquals(2,fr.getInterestsSize());

    }

    @Test
    public void testRemoveInterest() {
        fr.addInterest("Bowling");
        fr.addInterest("Swimming");
        fr.removeInterest("Bowling");
        assertEquals(1,fr.getInterestsSize());

    }

    @Test
    public void testSummaryNoInterests() {
        fr.setBirthday("12/10");
        assertTrue(fr.summary().equals("Birthday: 12/10, Interests: No known interests yet"));
    }


    @Test
    public void testSummaryHasInterests() {
        fr.setBirthday("12/10");
        fr.addInterest("Biking");
        fr.addInterest("Temple Run");
        assertTrue(fr.summary().equals("Birthday: 12/10, Interests: Biking, Temple Run"));
    }

    @Test
    public void testGetInterests() {
        fr.addInterest("Eating");
        fr.addInterest("Swimming");

        assertEquals(2,fr.getInterests().getSize());
    }



}