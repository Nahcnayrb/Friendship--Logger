package persistence;

import model.Friend;
import model.FriendsList;
import model.InterestList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


//Tests written based on JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            InterestList il = new InterestList();
            FriendsList fl = new FriendsList("Bryannn's friends list", il);
            JsonWriter writer = new JsonWriter("./data/my\0randomfilenamehehexd.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            InterestList il = new InterestList();
            FriendsList fl = new FriendsList("Bryannn's friends list", il);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFriendsList.json");
            writer.open();
            writer.write(fl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFriendsList.json");
            fl = reader.read();
            assertEquals("Bryannn's friends list", fl.getName());
            assertEquals(0, fl.getFriends().size());
            assertEquals(il.getSize(), fl.getUserInterests().getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            InterestList il = new InterestList();
            il.insertInterest("minecraft");
            il.insertInterest("walking");
            FriendsList fl = new FriendsList("Johnny's friends list", il);
            InterestList friend1InterestList = new InterestList();
            friend1InterestList.insertInterest("flying");
            friend1InterestList.insertInterest("league of legends");
            Friend friend1 = new Friend("ethan", "10/10", friend1InterestList);
            InterestList friend2InterestList = new InterestList();
            friend2InterestList.insertInterest("dreaming");
            friend2InterestList.insertInterest("leaving the house");
            Friend friend2 = new Friend("cody", "01/10", friend2InterestList);
            fl.addAFriend(friend1);
            fl.addAFriend(friend2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFriendsList.json");
            writer.open();
            writer.write(fl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFriendsList.json");
            fl = reader.read();
            assertEquals("Johnny's friends list", fl.getName());
            List<Friend> friends = fl.getFriends();
            assertEquals(2, friends.size());
            checkFriend("ethan", "10/10", friend1InterestList.toString(),2, fl.getFriends().get(0));
            checkFriend("cody", "01/10", friend2InterestList.toString(),2, fl.getFriends().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
