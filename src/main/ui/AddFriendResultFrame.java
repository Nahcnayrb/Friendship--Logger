package ui;

import javax.swing.*;
import java.awt.*;

//Represents the result of attempting to add a friend to user's friends list
public class AddFriendResultFrame extends JFrame {
    JLabel addFriendSuccessfulLabel = new JLabel();
    JLabel addFriendUnsuccessfulLabel = new JLabel();



    //EFFECTS: sets up the labels based on successfullyAdded boolean
    //          if successfullyAdded is true, set up addFriendSuccessfulLabel and add it to frame
    //          Otherwise, set up addFriendUnsuccessfulLabel and add it to frame
    //          set Frame as visible and open a new window
    public AddFriendResultFrame(boolean successfullyAdded) {
        this.setTitle("Alert");
        this.setSize(800,600);
        if (successfullyAdded) {
            addFriendSuccessfulLabel.setText("Friend was successfully added onto Friends List! :))))");
            addFriendSuccessfulLabel.setBounds(25,175,350,35);
            addFriendSuccessfulLabel.setFont(new Font("Comic Sans", Font.BOLD, 14));
            this.add(addFriendSuccessfulLabel);
        } else {
            addFriendUnsuccessfulLabel.setText("Friend could not be added because an existing"
                    + " friend with the same name has already been added :(((((");
            addFriendUnsuccessfulLabel.setBounds(25,150,350,35);
            addFriendUnsuccessfulLabel.setFont(new Font("Comic Sans", Font.BOLD, 14));
            this.add(addFriendUnsuccessfulLabel);
        }
        this.setVisible(true);

    }
}
