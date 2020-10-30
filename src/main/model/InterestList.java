package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a list of String where each string repesents an interest.
//InterestList cannot store duplicates, meaning two interests with the same string cannot be in
//the same InterestList.
public class InterestList {
    private List<Interest> listOfInterests;

    //EFFECTS: creates an empty interest list
    public InterestList() {
        listOfInterests = new ArrayList<>();
    }

    //REQUIRES:The interest must not already be an element in the interest list
    //MODIFIES: this
    //EFFECTS: inserts an interest into the list of interests if it's not already in the list
    public void insertInterest(String s) {
        Interest interest = new Interest(s);
        listOfInterests.add(interest);
    }

    //REQUIRES:The interest must already be an element in the interest list
    //MODIFIES: this
    //EFFECTS: removes an interest from the list of interests if it is contained in the list
    public void removeInterest(String s) {
        listOfInterests.removeIf(i -> i.getInterest().equals(s));
    }


    //EFFECTS: returns a list of interests that are elements in this InterestList and the second interest list
    public InterestList commonInterests(InterestList secondlist) {
        InterestList commonInterests = new InterestList();
        for (int i = 0; i < listOfInterests.size(); i++) {
            String currentInterest = listOfInterests.get(i).getInterest();
            if (secondlist.containsInterest(currentInterest)) {
                commonInterests.insertInterest(currentInterest);
            }
        }
        return commonInterests;
    }


    //EFFECTS: returns true if interest s is in the interest list
    public boolean containsInterest(String s) {
        boolean cond = false;
        for (Interest i: listOfInterests) {
            if (i.getInterest().equals(s)) {
                cond = true;
            }
        }
        return cond;
    }

    //EFFECTS: returns the number of interests in interest list
    public int getSize() {
        return listOfInterests.size();
    }

    //REQUIRES: this interest list must not be empty
    //EFFECTS: returns a string of all interests in the interest list linked together by a ","
    public String toString() {
        String result = "";
        for (int i = 0; i < getSize(); i++) {
            if (result.length() == 0) {
                result = listOfInterests.get(i).getInterest();
            } else {
                result += ", " + listOfInterests.get(i).getInterest();
            }
        }
        return result;
    }

    //EFFECTS: returns the interest list
    public List<Interest> getListOfInterests() {
        return listOfInterests;
    }

}
