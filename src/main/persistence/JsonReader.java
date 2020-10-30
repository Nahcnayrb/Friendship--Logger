package persistence;



import model.Friend;
import model.FriendsList;
import model.InterestList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Copied from JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads friendslist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FriendsList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFriendsList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses friendslist from JSON object and returns it
    private FriendsList parseFriendsList(JSONObject jsonObject) {
        String name = jsonObject.getString("username");
        InterestList userInterestsList = new InterestList();
        JSONArray jsonArray = jsonObject.getJSONArray("userinterests");
        addInterests(userInterestsList, jsonArray);
        FriendsList fl = new FriendsList(name, userInterestsList);
        addFriends(fl, jsonObject);
        return fl;
    }

    // MODIFIES: wr
    // EFFECTS: parses friends from JSON object and adds them to friendslist
    private void addFriends(FriendsList fl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("friends");
        for (Object json : jsonArray) {
            JSONObject nextFriend = (JSONObject) json;
            addFriend(fl, nextFriend);
        }
    }

    //DONT KNOW NAME FOR THIS METHOD FIX LATER
    // MODIFIES: fl
    // EFFECTS: parses friend from JSON object and adds it to friendslist
    private void addFriend(FriendsList fl, JSONObject jsonObject) {
        String friendname = jsonObject.getString("name");
        String birthday = jsonObject.getString("birthday");
        JSONArray jsonArray = jsonObject.getJSONArray("interests");
        InterestList friendinterests = new InterestList();
        addInterests(friendinterests, jsonArray);
        Friend friend = new Friend(friendname, birthday, friendinterests);
        fl.addAFriend(friend);
    }

//    private Friend parseFriend(JSONObject jsonObject) {
//        InterestList friendInterests = new InterestList();
//        JSONArray interests = jsonObject.getJSONArray("interests");
//        addInterest(f,interests );
//        Friend f = new Friend();
//        return f;
//    }

    // MODIFIES: InterestsList
    // EFFECTS: parses interest from JSON object and adds it to interests
    private void addInterests(InterestList interests, JSONArray jsonArray) {

        for (Object json: jsonArray) {
            String interest = ((JSONObject) json).getString("interest");
            interests.insertInterest(interest);
        }
    }

}
