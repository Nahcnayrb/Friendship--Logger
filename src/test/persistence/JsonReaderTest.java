package persistence;

import model.Friend;
import model.FriendsList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/randomFileLolXd.json");
        try {
            FriendsList fl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFriendsList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFriendsList.json");
        try {
            FriendsList fl = reader.read();
            assertEquals("Bryan's friends list", fl.getName());
            assertEquals(0,fl.getSize());
            assertEquals(0,fl.getUserInterests().getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFriendsList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFriendsList.json");
        try {
            FriendsList fl = reader.read();
            assertEquals("Bryan's friends list", fl.getName());
            List<Friend> friends = fl.getFriends();
            assertEquals(2, friends.size());
            checkFriend("Tyler One", "12/10", "flying, league of legends",
                    2, friends.get(0));
            checkFriend("Me MyselfandI", "01/01", "breathing air, staying in my room",
                    2, friends.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
