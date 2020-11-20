package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Below methods: ToJson, friendsToJson, userInterestsToJson were developed with assistance from
// JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)

// Represents a list of friends which cannot contain any duplicates,
// meaning two friends with identical names cannot be in the same friendsList.
public class FriendsList implements Writable {
    private List<Friend> friends;
    private String username;
    private InterestList userInterests;




    //EFFECTS: creates a new empty friends list
    public FriendsList() {
        friends = new ArrayList<>();
    }


    //EFFECTS: creates a friends list with given name and user interests,
    public FriendsList(String name, InterestList interests) {
        username = name;
        friends = new ArrayList<>();
        userInterests = interests;

    }

    public void addAFriend(Friend f) {
        friends.add(f);

    }

    //REQUIRES: f is not an element in friends list
    //MODIFIES: this
    //EFFECTS: add a friend with name n into friends list

    public void addAFriend(String n) {
        Friend f = new Friend(n);
        friends.add(f);

    }

    //REQUIRES: f is an element of friends list
    //MODIFIES: this
    //EFFECTS: removes the friend from friends list
    public void removeAFriend(String n) {
        friends.remove(getFriendIndex(n));
    }


    //EFFECTS: return the number of friends in friends list
    public int getSize() {
        return friends.size();
    }

    //REQUIRES: this friends list must not be empty
    //EFFECTS: returns the friend associated with the given name n
    public Friend getFriend(int i) {
        return friends.get(i);
    }

    //EFFECTS: returns the position of friend with name n in friends list
    //         if there is no friend in friends list with name n, return -1
    public int getFriendIndex(String n) {
        int friendIndex = -1;
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).getName().equals(n)) {
                friendIndex = i;
            }
        }
        return friendIndex;
    }

    //EFFECTS: returns the username associated with the friends list
    public String getName() {
        return username;
    }

    //EFFECTS: returns the interests list associated with the friends list
    public InterestList getUserInterests() {
        return userInterests;
    }

    //EFFECTS: returns the list of friends associated with the friends list
    public List<Friend> getFriends() {
        return friends;
    }


    //EFFECTS: returns the the list of friends in string form
    public String toString() {
        String result = "";
        for (int i = 0; i < getSize(); i++) {
            result += friends.get(i).getName() + "\n";
        }
        return result;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("friends", friendsToJson());
        json.put("username", username);
        json.put("userinterests", userInterestsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray friendsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Friend f : friends) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns user's interests in this friends list as a JSON array
    private JSONArray userInterestsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Interest i : userInterests.getListOfInterests()) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }



}
