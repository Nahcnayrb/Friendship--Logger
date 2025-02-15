package ui;

import model.Friend;
import model.FriendsList;
import model.InterestList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Friendship Logger Application
public class FriendshipLoggerApp {
    private static final String JSON_STORE = "./data/friendslist.json";
    private InterestList userInterests;
    private FriendsList userFriends;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //method written based on JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
    //EFFECTS: constructs userInterests and friends list and runs the Friendship Logger Application
    public FriendshipLoggerApp() {
        userInterests = new InterestList();
        userFriends = new FriendsList("Bryan's friends list", userInterests);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runLogger();
    }


    //MODIFIES: this
    //EFFECTS: inputs from the user are processed.
    private void runLogger() {
        input = new Scanner(System.in);

        greet();
        mainMenuLoop();

        System.out.println("Thanks for using Friendship Logger!");
    }

    //EFFECTS: displays a message to user and greeting them
    private void greet() {
        System.out.println("Welcome to Friendship Logger!");
    }


    //MODIFIES: this
    //EFFECTS: removes a user's interest if it is in the user's interest list
    private void removeUserInterestUI() {
        System.out.println("Please enter the interest you would like to remove");
        System.out.print("Interest: ");
        input.nextLine();
        String userInput = input.nextLine().toLowerCase();
        if (userFriends.getUserInterests().containsInterest(userInput)) {
            userFriends.getUserInterests().removeInterest(userInput);
            System.out.println("Interest successfully removed");
        } else {
            System.out.println("Interest was unsuccessfully removed because it is not in the list of interests.");
            System.out.println("Please try again");
        }
    }

    //MODIFIES: this
    //EFFECTS: adds an interest to user's interest list if its not already in the list
    private void addUserInterestUI() {
        String userInput = "";
        System.out.println("What are your hobbies and interests? Enter one thing you enjoy and press enter!");
        System.out.print("Interest: ");
        input.nextLine();
        userInput = input.nextLine().toLowerCase();
        if (userInput.trim().length() == 0) {
            System.out.println("Interest was unsuccessfully added because an interest should not be empty.");
        } else if ((!(userFriends.getUserInterests().containsInterest(userInput)))) {
            userFriends.getUserInterests().insertInterest(userInput);
            System.out.println("Interest sucessfully added");
        } else {
            System.out.println("Interest was unsuccessfully added because it's already documented.");
        }

    }

    //EFFECTS: displays the user's inputted interests so far
    private void viewMyInterestsUI() {
        if (userFriends.getUserInterests().getSize() == 0) {
            System.out.println("You currently do not have any interests documented");
        } else {
            System.out.println("Your Interests: " + userFriends.getUserInterests().display());
        }
    }


    //MODIFIES: this
    //EFFECTS: keeps the application running by returning to main menu after each action
    private void mainMenuLoop() {
        String userinput = null;
        boolean exitLoop = false;

        while (!exitLoop) {
            System.out.println("What would you like to do? Enter the number left of the description.");
            mainMenuSelectionDisplay();
            userinput = input.next();
            exitLoop = mainMenuInterpretation(userinput);
        }
    }

    //helper method for mainMenuLoop()
    //EFFECTS: Displays the main menu options to User
    private void mainMenuSelectionDisplay() {
        System.out.println("1 > Edit my interests");
        System.out.println("2 > View my interests");
        System.out.println("3 > Edit my Friends List");
        System.out.println("4 > View my Friends List");
        System.out.println("5 > Quit Application");
        System.out.println("6 > Save my data so far");
        System.out.println("7 > Load my data from before");
    }

    //helper function of mainMenuLoop()
    //MODIFIES:this
    //EFFECTS: processes inputs by the user and
    // returns true when user inputs 5, meaning they wish to exit the application.
    // Otherwise, return false.
    private boolean mainMenuInterpretation(String userinput) {
        boolean exitLoop = false;
        if (userinput.equals("1")) {
            editMyInterestsUI();
        } else if (userinput.equals("2")) {
            viewMyInterestsUI();
        } else if (userinput.equals("3")) {
            editMyFriendsListUI();

        } else if (userinput.equals("4")) {
            viewMyFriendsUI();

        } else if (userinput.equals("5")) {
            exitLoop = true;
        } else if (userinput.equals("6")) {
            saveFriendsList();
        } else if (userinput.equals("7")) {
            loadFriendsList();
        } else {
            invalidInputWarning();
        }

        return exitLoop;
    }

    //Method written based on JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
    //EFFECT: save friends list to file
    private void saveFriendsList() {
        try {
            jsonWriter.open();
            jsonWriter.write(userFriends);
            jsonWriter.close();
            System.out.println("Successfully saved " + userFriends.getName() + " to " + JSON_STORE + ".");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE + ".");
        }

    }


    //Method written based on JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
    //MODIFIES: this
    //EFFECT: loads friends list from file
    private void loadFriendsList() {
        try {
            userFriends = jsonReader.read();
            System.out.println("Successfully loaded " + userFriends.getName() + " from " + JSON_STORE + ".");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE + ".");
        }

    }

    //EFFECTS: views different parts of the friends list based on user input
    private void viewMyFriendsUI() {
        System.out.println("What would you like to view about your friends list?");
        String userinput = null;
        viewFriendsMenuSelectionDisplay();
        userinput = input.next();
        if (userinput.equals("1")) {
            System.out.println("Friends currently in your friends list: " + userFriends.display());
        } else if (userinput.equals("2")) {
            viewAFriend();
        } else {
            invalidInputWarning();
        }
    }




    //helper function of viewMyFriendsUI()
    //EFFECTS: displays the options for viewing information about a friend
    private void viewFriendsMenuSelectionDisplay() {
        System.out.println("1 < View Friends List");
        System.out.println("2 < View A Current Friend");
    }

    //helper function of viewMyFriendsUI(
    //EFFECTS: selects a friend in the friends list by inputting their name then viewing information about them.
    private void viewAFriend() {
        String userInput = null;
        System.out.println("Enter the name of the friend you would like to view.");
        input.nextLine();
        userInput = input.nextLine().toLowerCase();

        if (userFriends.getFriendIndex(userInput) == -1) {
            System.out.println("Friend was not found. Please try again.");
        } else {
            int index = userFriends.getFriendIndex(userInput);
            viewFriendUI(userFriends.getFriend(index));
        }

    }

    //helper function of viewAFriend()
    //EFFECTS: views different information about a specific friend which the information displayed
    //is different based on the user's input
    private void viewFriendUI(Friend f) {
        String userinput = null;
        System.out.println("What would you like to view about " + f.getName() + "?");
        friendMenuSelectionDisplay();
        userinput = input.next();
        if (userinput.equals("1")) {
            if ((userFriends.getUserInterests().commonInterests(f.getInterests())).getSize() == 0) {
                System.out.println("There are no common interests between you and " + f.getName() + ".");
            } else {
                System.out.println("Common Interests between you and " + f.getName() + ": "
                        + f.getInterests().commonInterests(userFriends.getUserInterests()).display());
            }
        } else if (userinput.equals("2")) {
            System.out.println(f.summary());
        }
    }

    //helper function of viewFriendUI(Friend f)
    //EFFECTS: displays the options for viewing information about a friend
    private void friendMenuSelectionDisplay() {
        System.out.println("1 < View Common Interests");
        System.out.println("2 < View Summary");
    }

    //EFFECTS: displays a warning of invalid input
    private void invalidInputWarning() {
        System.out.println("Invalid Input. Please try again.");
    }

    //MODIFIES: this
    //EFFECTS: edits the user's interest list differently based on user's input
    private void editMyInterestsUI() {
        String userinput = null;
        boolean validOption = false;
        while (!validOption) {
            editMyInterestsSelectionDisplay();
            userinput = input.next();
            if (userinput.equals("1")) {
                addUserInterestUI();
                validOption = true;
            } else if (userinput.equals("2")) {
                removeUserInterestUI();
                validOption = true;
            } else {
                invalidInputWarning();
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: edits the user's friends list differently based on inputted value by user
    private void editMyFriendsListUI() {
        String userinput = null;
        System.out.println("What would you like to edit in your Friends List?");
        editMyFriendsListDisplay();
        boolean validInput = false;
        userinput = input.next();
        while (!validInput) {
            if (userinput.equals("1")) {
                addNewFriendUI();
                validInput = true;
            } else if (userinput.equals("2")) {
                removeFriendUI();
                validInput = true;
            } else if (userinput.equals("3")) {
                editFriendUI();
                validInput = true;
            } else {
                invalidInputWarning();
                break;
            }
        }
    }

    //EFFECTS: displays the menu of functions that can be used by the user
    // for when called by editMyFriendsList
    private void editMyFriendsListDisplay() {
        System.out.println("1 < Add a new Friend");
        System.out.println("2 < Remove a Friend");
        System.out.println("3 < Edit an existing Friend");
    }

    //MODIFIES: this
    //EFFECTS: allows the user to specify which friend in the friends list they want to modify information about.
    private void editFriendUI() {
        String userinput;
        System.out.println("What is the name of the friend you wish to edit?");
        System.out.print("Name: ");
        input.nextLine();
        userinput = input.nextLine().toLowerCase();
        if ((userFriends.getFriendIndex(userinput)) == -1) {
            System.out.println("Unable to edit because there is currently no friend in the"
                    + " Friends List with the given name");
        } else {
            int index = userFriends.getFriendIndex(userinput);
            editValidFriend(userFriends.getFriend(index));

        }
    }

    //MODIFIES:this, f
    //changes the birthday or interest list of a friend in the user's friends list based on the input they provide.
    private void editValidFriend(Friend f) {
        String userInput = null;
        boolean validOption = false;
        while (!validOption) {
            System.out.println("What would you like to edit about " + f.getName() + "?");
            editFriendOptionsDisplay();
            userInput = input.next();
            if (userInput.equals("1")) {
                System.out.println("Enter the new birthday in MM/DD form.");
                System.out.print("Birthday: ");
                input.nextLine();
                userInput = input.nextLine();
                f.setBirthday(userInput);
                System.out.println("Birthday set to " + userInput + " for " + f.getName());
                validOption = true;
            } else if (userInput.equals("2")) {
                editFriendInterests(f);
                validOption = true;
            }
        }
    }

    //helper method of editValidFriend(Friend f)
    //EFFECTS: displays the menu options for editing a friend's information
    private void editFriendOptionsDisplay() {
        System.out.println("1 < Edit Birthday");
        System.out.println("2 < Edit Interests");
    }

    //MODIFIES: this, f
    //EFFECTS: edits a friend's interests differently based on user input
    private void editFriendInterests(Friend f) {
        String userinput;

        editMyInterestsSelectionDisplay();
        input.nextLine();
        userinput = input.nextLine();
        if (userinput.equals("1")) {
            addFriendInterestUI(f);
        } else if (userinput.equals("2")) {
            removeFriendInterestUI(f);

        } else {
            invalidInputWarning();
        }


    }

    //helper method of EditFriendInterests()
    //MODIFIES: this, f
    //EFFECTS: adds an interest specified by the user to a friend's interests
    private void addFriendInterestUI(Friend f) {
        String userInput = "";
        boolean validInterest = false;
        System.out.println("What are " + f.getName() + "'s" + " hobbies and interests? "
                + "Enter one thing they enjoy and press enter!");
        System.out.print("Interest: ");
        userInput = input.nextLine().toLowerCase();
        if (userInput.trim().length() == 0) {
            System.out.println("Interest was unsuccessfully added because an interest should not be empty");
        } else if ((!(f.getInterests().containsInterest(userInput)))) {
            f.addInterest(userInput);
            System.out.println("Interest sucessfully added for " + f.getName() + ".");
        } else {
            System.out.println("Interest was unsuccessfully added because it's already documented.");
        }
    }

    //helper method of editFriendsInterests(Friend f)
    //MODIFIES:this, f
    //EFFECTS: removes a friend's interest specified by user from a friend's interests.
    private void removeFriendInterestUI(Friend f) {
        String userInput = "";
        boolean validRequest = false;

        System.out.println("Please enter the interest you would like to remove for "
                + f.getName() + ".");
        System.out.print("Interest: ");
        userInput = input.nextLine().toLowerCase();
        if (f.getInterests().containsInterest(userInput)) {
            f.removeInterest(userInput);
            System.out.println("Interest sucessfully removed for " + f.getName());
            validRequest = true;
        } else {
            System.out.println("Interest was unsuccessfully removed for " + f.getName()
                    + " because it is not in the list of intersts.");
        }

    }


    //MODIFIES: this
    //EFFECTS: adds a new friend entry to user's Friends List
    private void addNewFriendUI() {
        String userinput = "";
        System.out.println("What's the full name of your new friend?");
        System.out.print("Name: ");
        input.nextLine();
        userinput = input.nextLine().toLowerCase();
        if (userinput.trim().length() == 0) {
            System.out.println("Friend was added unsuccessfully because a friend's name should not be empty");
        } else if ((userFriends.getFriendIndex(userinput)) == -1) {
            userFriends.addAFriend(userinput);
            System.out.println(userinput + " was added successfully to your Friends List");
        } else {
            System.out.println(userinput + "was added unsuccessfully"
                    + " because an existing friend with the same name has already been added.");
        }
    }


    //MODIFIES: this
    //EFFECTS: removes a friend entry from user's Friend List
    private void removeFriendUI() {
        String userinput = "";
        System.out.println("What's the name of the friend you wish to remove?");
        System.out.print("Name: ");
        input.nextLine();
        userinput = input.nextLine().toLowerCase();
        if ((userFriends.getFriendIndex(userinput)) == -1) {
            System.out.println(userinput + " was removed unsuccessfully"
                    + " because there is currently no friend in your Friends List that has the inputted name.");
        } else {
            userFriends.removeAFriend(userinput);
            System.out.println(userinput + " was removed successfully from your friends list");
        }
    }


    //EFFECTS: displays the options when in the menu of editMyInterests
    private void editMyInterestsSelectionDisplay() {
        System.out.println("1 > Add an interest: ");
        System.out.println("2 > Remove an interest: ");
    }

}
