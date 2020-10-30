package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FriendsListTest {
    private FriendsList f;

    @BeforeEach
    public void runBefore() {
        f = new FriendsList();
    }

    @Test
    public void testFriendsList() {

        assertEquals(0,f.getSize());
    }

    @Test
    public void testFriendsListMultipleInputs() {

        InterestList il = new InterestList();
        il.insertInterest("lol");
        FriendsList fr = new FriendsList("Bob's friends list", il);
        assertEquals(il.getSize(), fr.getUserInterests().getSize());
        assertEquals(0,f.getSize());
        assertTrue(fr.getName().equals("Bob's friends list"));
    }



    @Test
    public void testAddAFriend() {
        f.addAFriend("Bob Vance");

        assertEquals(1,f.getSize());
        assertEquals(1,f.getFriends().size());
        assertTrue(f.getFriend(0).getName().equals("Bob Vance"));


    }

    @Test
    public void testAddAFriendInputFriend() {
        Friend fri = new Friend("Bob Vance");
        f.addAFriend(fri);

        assertEquals(1,f.getSize());
        assertTrue(f.getFriend(0).getName().equals("Bob Vance"));

    }



    @Test
    public void testAddAFriendMany() {
        f.addAFriend("Bob Vance");
        f.addAFriend("Roger Rabbit");

        assertEquals(2,f.getSize());
        assertTrue(f.getFriend(0).getName().equals("Bob Vance"));
        assertTrue(f.getFriend(1).getName().equals("Roger Rabbit"));

    }

    @Test
    public void testAddAFriendManyInputFriend() {
        Friend fri1 = new Friend("Bob Vance");
        Friend fri2 = new Friend("Roger Rabbit");
        f.addAFriend(fri1);
        f.addAFriend(fri2);

        assertEquals(2,f.getSize());
        assertTrue(f.getFriend(0).getName().equals("Bob Vance"));
        assertTrue(f.getFriend(1).getName().equals("Roger Rabbit"));

    }

    @Test
    public void testRemoveAFriend() {
        Friend fr = new Friend("Steve Rogers");

        f.addAFriend("Steve Rogers");
        int count = f.getSize();
        f.removeAFriend("Steve Rogers");

        assertEquals(count - 1, f.getSize());


    }

    @Test
    public void testRemoveAFriendMany() {
        Friend fr = new Friend("Steve Rogers");
        Friend fr2 = new Friend("Elon Musk");

        f.addAFriend("Steve Rogers");
        f.addAFriend("Elon Musk");
        int count = f.getSize();
        f.removeAFriend("Elon Musk");

        assertEquals(count - 1, f.getSize());
        assertTrue(f.getFriend(0).getName().equals("Steve Rogers"));


    }

    @Test
    public void testGetFriendIndex() {
        f.addAFriend("Leo Dicaprio");
        f.addAFriend("Bertha Thompson");

        assertEquals(1,f.getFriendIndex("Bertha Thompson"));
        assertEquals(-1,f.getFriendIndex("Tom Cruise"));

    }
}
