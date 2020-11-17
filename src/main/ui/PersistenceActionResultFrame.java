package ui;

import javax.swing.*;
import java.awt.*;

//represents the result of attempting to save/load data by the user
public class PersistenceActionResultFrame extends JFrame {

    JLabel displayedMessage = new JLabel();
    JLabel img = new JLabel(new ImageIcon("./data/WOOOOOOO.jpg"));

    //sets text of displayedMessage to msg, and set up displayMessage and add it to Frame
    //set Frame to be visible and opens a new window.
    public PersistenceActionResultFrame(String msg) {
        this.setSize(1000,650);

        this.setLayout(new FlowLayout());
        displayedMessage.setText(msg);
        displayedMessage.setBounds(50,200,200,30);
        displayedMessage.setFont(new Font("Comic Sans", Font.BOLD, 18));
        this.add(displayedMessage);
        this.add(img);
        this.setVisible(true);

    }
}
