package persistence;

import model.Friend;
import model.InterestList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Test written based on JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonTest {
    protected void checkFriend(String name, String bday, String interests, int interestSize, Friend f) {
        assertEquals(name, f.getName());
        assertEquals(bday, f.getBirthday());
        assertEquals(interestSize, f.getInterestsSize());
        assertEquals(interests, f.getInterests().toString());
    }
}
