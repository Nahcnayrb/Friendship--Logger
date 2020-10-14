package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of friends which cannot contain any duplicates,
// meaning two friends with identical names cannot be in the same friendsList.
public class FriendsList {
    public List<Friend> friends;



    //EFFECTS: creates a new empty friends list
    public FriendsList() {
        friends = new ArrayList<>();

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
}
