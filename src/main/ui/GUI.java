package ui;

import model.FriendsList;
import model.InterestList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;


//Friendship Logger GUI Application
public class GUI implements ActionListener {
    private static final String JSON_STORE = "./data/friendslist.json";
    private InterestList userInterests;
    private FriendsList userFriends;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    JFrame mainFrame = new JFrame();

    JPanel panelCont = new JPanel();
    JPanel mainMenuUI = new JPanel();
    JPanel userInterestsUI = new JPanel();
    JPanel editMyFriendsUI = new JPanel();
    JPanel editMyFriendsEditFriendsListUI = new JPanel();
    JPanel editMyFriendsEditAFriendUI = new JPanel();
    JPanel viewMyFriendsUI = new JPanel();
    JPanel viewMyFriendsViewAFriendUI = new JPanel();
    JPanel viewMyFriendsViewFriendsListUI = new JPanel();


    JButton editMyFriendsButton = new JButton("Edit My Friends");
    JButton editFriendsListButton = new JButton("Edit My Friends List");
    JButton editAFriendButton = new JButton("Edit An Existing Friend");
    JButton viewMyFriendsButton = new JButton("View My Friends");
    JButton viewAFriendButton = new JButton("View A Friend");
    JButton viewFriendsListButton = new JButton("View My Friends List");
    JButton saveMyDataButton = new JButton("Save My Data So Far");
    JButton loadMyDataButton = new JButton("Load My Data From Before");
    JButton mainMenuButton = new JButton("View User Interests");
    JButton addFriendButton = new JButton("Add Entered Friend");
    JButton removeFriendButton = new JButton("Remove Entered Friend");


    JButton return0 = new JButton("Return to Main Menu");
    JButton return1 = new JButton("Return to Main Menu");
    JButton return2 = new JButton("Return to Main Menu");
    JButton return3 = new JButton("Return to Main Menu");
    JButton return4 = new JButton("Return to Main Menu");
    JButton return5 = new JButton("Return to Main Menu");
    JButton return6 = new JButton("Return to Main Menu");


    JLabel viewFriendsListLabel = new JLabel();
    JLabel friendNameLabel = new JLabel();

    GridBagConstraints gbc = new GridBagConstraints();
    JTextField enterFriendName = new JTextField(10);




    CardLayout cl = new CardLayout();





    //MODIFIES: this
    //EFFECTS: initializes the GUI
    public GUI() {
        friendShipLoggerApp();
        panelCont.setLayout(cl);



        setHomeButtonBounds();
        setPanelLayouts();
        setBackgroundColours();
        addHomeMenuButtons();
        addButtons();
        addListeners();
        addPanels();

        cl.show(panelCont, "1");
        mainFrame.add(panelCont);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Friendship Logger");
        mainFrame.setSize(690,690);
        mainFrame.setVisible(true);


    }


    //MODIFIES: this
    //EFFECTS: changes the bounds for existing "return to home menu" buttons
    private void setHomeButtonBounds() {
        return0.setBounds(0,0,10,10);
        return1.setBounds(0,0,10,10);
        return2.setBounds(0,0,10,10);
        return3.setBounds(0,0,10,10);
        return4.setBounds(0,0,20,20);
        return5.setBounds(0,0,10,10);
        return6.setBounds(0,0,10,10);
    }

    //MODIFIES: this
    //EFFECTS: change the layouts for panels viewMyFriendsViewFriendsListUI and editMyFriendsEditFriendsListUI
    private void setPanelLayouts() {
        viewMyFriendsViewFriendsListUI.setLayout(new GridBagLayout());
        editMyFriendsEditFriendsListUI.setLayout(null);

    }

    //method written based on JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
    //EFFECTS: constructs userInterests and friends list and runs the Friendship Logger Application
    public void friendShipLoggerApp() {
        userInterests = new InterestList();
        userFriends = new FriendsList("Bryan's friends list", userInterests);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }

    //MODIFIES: this
    //EFFECTS: add the existing buttons to corresponding panels
    private void addButtons() {
        mainMenuUI.add(mainMenuButton);
        mainMenuUI.add(saveMyDataButton);
        mainMenuUI.add(loadMyDataButton);
        editMyFriendsUI.add(editAFriendButton);
        editMyFriendsUI.add(editFriendsListButton);
        viewMyFriendsUI.add(viewAFriendButton);
        viewMyFriendsUI.add(viewFriendsListButton);
        mainMenuUI.add(viewMyFriendsButton);
        mainMenuUI.add(editMyFriendsButton);
    }

    //MODIFIES:this
    //EFFECTS: add actionListener for each button
    private void addListeners() {
        mainMenuButton.addActionListener(this);
        editMyFriendsButton.addActionListener(this);
        viewMyFriendsButton.addActionListener(this);
        return0.addActionListener(this);
        return1.addActionListener(this);
        return2.addActionListener(this);
        return3.addActionListener(this);
        return4.addActionListener(this);
        return5.addActionListener(this);
        return6.addActionListener(this);
        editFriendsListButton.addActionListener(this);
        editAFriendButton.addActionListener(this);
        viewAFriendButton.addActionListener(this);
        viewFriendsListButton.addActionListener(this);
        removeFriendButton.addActionListener(this);
        addFriendButton.addActionListener(this);
        saveMyDataButton.addActionListener(this);
        loadMyDataButton.addActionListener(this);
    }

    //MODIFIES: this
    //adds panels onto main panel control for mainFrame's cardLayout
    private void addPanels() {
        panelCont.add(mainMenuUI, "1");
        panelCont.add(userInterestsUI, "2");
        panelCont.add(editMyFriendsUI, "3");
        panelCont.add(viewMyFriendsUI, "4");
        panelCont.add(viewMyFriendsViewAFriendUI, "5");
        panelCont.add(viewMyFriendsViewFriendsListUI, "6");
        panelCont.add(editMyFriendsEditFriendsListUI, "7");
        panelCont.add(editMyFriendsEditAFriendUI, "8");
    }

    //MODIFIES: this
    //EFFECTS: changes the background colours of each panel
    private void setBackgroundColours() {
        mainMenuUI.setBackground(Color.PINK);
        userInterestsUI.setBackground(Color.YELLOW);
        editMyFriendsUI.setBackground(Color.CYAN);
        editMyFriendsEditFriendsListUI.setBackground(Color.PINK);
        editMyFriendsEditAFriendUI.setBackground(Color.GREEN);
        viewMyFriendsUI.setBackground(Color.red);
        viewMyFriendsViewAFriendUI.setBackground(Color.BLACK);
        viewMyFriendsViewFriendsListUI.setBackground(Color.PINK);
    }

    //MODIFIES: this
    //EFFECTS: adds the corresponding buttons to each panel
    private void addHomeMenuButtons() {
        userInterestsUI.add((return0));
        editMyFriendsUI.add(return1);
        viewMyFriendsUI.add(return2);
        viewMyFriendsViewAFriendUI.add(return3);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;


        gbc.anchor = GridBagConstraints.FIRST_LINE_START;


        viewMyFriendsViewFriendsListUI.add(return4, gbc);

        editMyFriendsEditAFriendUI.add(return6);
    }

    //MODIFIES: this
    //Sets up the viewFriendsListLabel and add it to viewMyFriendsViewFriendsListUI panel
    public void setUpViewFriendsListLabel() {
        viewFriendsListLabel.setText("Friends currently in your friends list: " +  userFriends.toString());


        viewFriendsListLabel.setFont(new Font("Comic Sans", Font.PLAIN,20));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        viewMyFriendsViewFriendsListUI.add(viewFriendsListLabel,gbc);



    }


    //MODIFIES: this
    //EFFECTS: processes user actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() == return0) || (e.getSource() == return1) || (e.getSource() == return2)
                || (e.getSource() == return3) || (e.getSource() == return4)
                || (e.getSource() == return6)) {
            cl.show(panelCont, "1");
        } else if (e.getSource() == return5) {
            enterFriendName.setText("");
            cl.show(panelCont, "1");
        } else if (e.getSource() == saveMyDataButton) {
            saveFriendsList();
        } else if (e.getSource() == loadMyDataButton) {
            loadFriendsList();
        } else {
            actionPerformedHelper(e);
        }
    }



    //MODIFIES: this
    //EFFECTS: helper method for actionPerformed() because it was too long
    private void actionPerformedHelper(ActionEvent e) {
        if (e.getSource() == mainMenuButton) {
            cl.show(panelCont, "2");
        } else if (e.getSource() == editMyFriendsButton) {
            cl.show(panelCont, "3");
        } else if (e.getSource() == viewMyFriendsButton) {
            cl.show(panelCont, "4");
        } else if (e.getSource() == removeFriendButton) {
            implementRemoveFriendListener();
        } else if (e.getSource() == addFriendButton) {
            implementAddFriendListener();
        } else if (e.getSource() == viewAFriendButton) {

            cl.show(panelCont, "5");

        } else if (e.getSource() == viewFriendsListButton) {
            setUpViewFriendsListLabel();
            cl.show(panelCont, "6");

        } else if (e.getSource() == editFriendsListButton) {

            setupEditFriendsListUI();

            cl.show(panelCont, "7");

        } else if (e.getSource() == editAFriendButton) {

            cl.show(panelCont, "8");
        }

    }

    //Method written based on JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
    //EFFECT: save friends list to file
    private void saveFriendsList() {
        try {
            jsonWriter.open();
            jsonWriter.write(userFriends);
            jsonWriter.close();
            new PersistenceActionResultFrame("Successfully saved " + userFriends.getName() + " to " + JSON_STORE + ".");
        } catch (FileNotFoundException e) {
            new PersistenceActionResultFrame("Unable to write to file: " + JSON_STORE + ".");
        }
    }

    //Method written based on JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
    //MODIFIES: this
    //EFFECT: loads friends list from file
    private void loadFriendsList() {
        try {
            userFriends = jsonReader.read();
            new PersistenceActionResultFrame("Successfully loaded " + userFriends.getName()
                    + " from " + JSON_STORE + ".");
        } catch (IOException e) {
            new PersistenceActionResultFrame("Unable to read from file: " + JSON_STORE + ".");
        }

    }

    //MODIFIES: this
    //EFFECTS: sets up the components associated with the editFriendsListUI panel
    private void setupEditFriendsListUI() {
        return5.setBounds(0,0,150,100);
        friendNameLabel.setText("Friend's Name: ");
        friendNameLabel.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        friendNameLabel.setBounds(175,200,130,50);

        enterFriendName.setBounds(325,215,200,30);

        addFriendButton.setBounds(325,280, 200,35);
        removeFriendButton.setBounds(325,325, 200, 35);
        editFriendsListAddComponents();



    }

    //MODIFIES: this
    //EFFECTS: adds the components associated with the editMyFriendsEditFriendsListUI panel
    private void editFriendsListAddComponents() {
        editMyFriendsEditFriendsListUI.add(return5);
        editMyFriendsEditFriendsListUI.add(enterFriendName);
        editMyFriendsEditFriendsListUI.add(friendNameLabel);
        editMyFriendsEditFriendsListUI.add(addFriendButton);
        editMyFriendsEditFriendsListUI.add(removeFriendButton);

    }

    //MODIFIES: this
    //EFFECTS: gets string entered by user in the text field and removes the friend with name as given string
    private void implementRemoveFriendListener() {
        String friendString = enterFriendName.getText().trim().toLowerCase();
        removeExistingFriend(friendString);

    }

    //MODIFIES: this
    //EFFECTS: gets string entered by user in the text field and adds a friend with name as given string
    private void implementAddFriendListener() {
        String friendString = enterFriendName.getText().trim().toLowerCase();
        addNewFriend(friendString);

    }

    //MODIFIES: this
    //EFFECTS: remove an existing friend entry from user's Friends List
    private void removeExistingFriend(String friendName) {
        if ((userFriends.getFriendIndex(friendName)) == -1) {
            new RemoveFriendResultFrame(false);
        } else {
            userFriends.removeAFriend(friendName);
            new RemoveFriendResultFrame(true);
        }

    }

    //MODIFIES: this
    //EFFECTS: adds a new friend entry to user's Friends List if it's not already added
    private void addNewFriend(String friendName) {
        if ((userFriends.getFriendIndex(friendName)) == -1) {
            userFriends.addAFriend(friendName);
            new AddFriendResultFrame(true);
        } else {
            new AddFriendResultFrame(false);
        }
    }
}
