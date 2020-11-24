package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//Below methods: ToJson, interestsToJson were developed with assistance from
// JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)

//Represents a friend with information of name, birthday, and interests
public class Friend implements Writable {
    private String name;
    private String birthday = "Unknown";
    private InterestList interests;


    //EFFECTS: creates a friend with the friend's name set to given name and a new list of interests is created
    public Friend(String name) {
        this.name = name;
        interests = new InterestList();
    }

    //EFFECTS: creates a friend with their name is set to given name, birthday set to bday, and interests is set to
    // given interests list
    public Friend(String name, String bday, InterestList il) {
        this.name = name;
        this.birthday = bday;
        interests = il;
    }

    //REQUIRES: string must to be in format "MM/DD" where  01 <= MM <= 12 and 01 <= DD <= 31
    //MODIFIES: this
    //EFFECTS: updates the birthday for friend in format(MONTH<Word> DAY<Integer>);
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    //EFFECTS: returns the size of this interest list
    public int getInterestsSize() {
        return interests.getSize();

    }

    //MODIFIES: this
    //EFFECTS: Adds an interest to interest list, but won't be added if interest is already added.

    public void addInterest(String i) {
        interests.insertInterest(i);
    }

    //REQUIRES: i is an element of the interest list
    //MODIFIES: this
    //EFFECTS: removes interest i in the interest list

    public void removeInterest(String i) {
        interests.removeInterest(i);
    }



    //EFFECTS: returns a string with the friend's birthday and interests
    public String summary() {
        if (interests.getSize() > 0) {
            return "Birthday: " + birthday + ", " + "Interests: " + interests.display();
        } else {
            return "Birthday: " + birthday + ", " + "Interests: No known interests yet";
        }
    }


    //EFFECTS: returns the friend's name
    public String getName() {
        return this.name;
    }


    //EFFECTS: returns the friend's list of interests
    public InterestList getInterests() {
        return interests;
    }

    //EFFECTS: returns the friend's birthday
    public String getBirthday() {
        return birthday;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("birthday", birthday);
        json.put("interests", interestsToJson());
        return json;
    }

    // EFFECTS: returns friend's interests as a JSON array
    private JSONArray interestsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Interest i : interests.getListOfInterests()) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

}
