package ui;

import javax.swing.*;
import java.awt.*;

//Represents the result of attempting to remove a friend from user's friends list
public class RemoveFriendResultFrame extends JFrame {

    JLabel removeFriendSuccessfulLabel = new JLabel();
    JLabel removeFriendUnsuccessfulLabel = new JLabel();


    //EFFECTS: sets up the labels based on successfullyRemoved boolean
    //          if successfullyRemoved is true, set up removeFriendSuccessfulLabel and add it to frame
    //          Otherwise, set up removeFriendUnsuccessfulLabel and add it to frame
    //          set Frame as visible and open a new window
    public RemoveFriendResultFrame(boolean successfullyRemoved) {
        this.setTitle("Alert");
        this.setSize(800,600);
        if (successfullyRemoved) {
            removeFriendSuccessfulLabel.setText("Friend was successfully removed from Friends List!   :))");
            removeFriendSuccessfulLabel.setBounds(25,175,350,35);
            removeFriendSuccessfulLabel.setFont(new Font("Comic Sans", Font.BOLD, 14));
            this.add(removeFriendSuccessfulLabel);
        } else {
            removeFriendUnsuccessfulLabel.setText("Remove unsuccessful because no friend with "
                    + "the given name was found.   :((");
            removeFriendUnsuccessfulLabel.setBounds(25,150,350,35);
            removeFriendUnsuccessfulLabel.setFont(new Font("Comic Sans", Font.BOLD, 14));
            this.add(removeFriendUnsuccessfulLabel);
        }
        this.setVisible(true);

    }
}
