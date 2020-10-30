package model;

import org.json.JSONObject;
import persistence.Writable;

//Below method: ToJson, were developed with assistance from
// JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)

//represents an interest description represented by a string
public class Interest implements Writable {
    private String interest;

    //creates an interest with given string as the description of interest
    public Interest(String interest) {
        this.interest = interest;
    }

    //returns the interest
    public String getInterest() {
        return this.interest;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("interest", interest);
        return json;
    }
}
