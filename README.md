# My Personal Project

## Friendship Logger

This application enables the user to add a friend to a 
friends list where the user can essentially **create a mini profile about that friend** by **inputting personal information** such as
their *birthdays* and *interests*. These kinds of information **can constantly be added or modified** which would be helpful
in instances where the user learns new information about that friend as they get to know them better or modify a friend's interests if they
 aren't as interested in something they were passionate about before. At the beginning, the user must first input information about themselves for the purposes of comparing information
 about their friends if they wish to.

This application is ideal for users who are often 
forgetful of minor yet important details about others. 
This project interests me because like many 
others, I am an extremely forgetful person when 
it comes to remembering things about people. For example, I might forget about a friend's 
birthday, and am too embarassed to have the awkward chat about when their birthday is after knowing them for five whole years.
Therefore, having an application like this is useful in which I can document the plans I have made with friends and traits about them that I learned through interactions with them.
 As time goes on, I can then use the application to quickly bring up useful summaries such as displaying the common interests between my friend and I, 
or displaying which friend in the friends list has the next birthday. These kinds of summaries would be 
helpful especially in situations where I'm planning for birthday gifts in advance, or
 brainstorming some conversation ideas that I can use when I'm about to interact with them after a long time. 
 
 ## User Stories
 
 As a user:
 - I want to be able to add a friend to my friends list
 - I want to be able to select a friend and add their birthday
 - I want to be able to select a friend and add an interest to their existing interests
 - I want to be able to select a friend and view our common interests
 - I want to be able to select a friend and view a summary showing the important things about them such as their birthdays or
 their interests.
 
 - I want to be able to save my Friends List(friend entries, friends' birthdays, friends' interests, user interests) 
 - I want to have the choice to load my previously saved Friends List and my Interests when I start the application
 
 ## Phase 4: Task 2
 
 For task 2, I have chosen to include a type hierarchy which the interface created is InformationList and the subclasses
 of InformationList are FriendsList, and InterestList. The method display() plays a role in this hierarchy because it's specified in InformationList, and then 
 was overridden in FriendsList and InterestList which each of these classes implement display() differently.
 
 
 ##Phase 4: Task 3
 
- I would refactor AddFriendResultFrame and RemoveFriendResultFrame such that I would create an abstract class called 
EditFriendResultFrame
- In the constructor of EditFriendResultFrame, it would contain the duplicated code in the constructor of 
AddFriendResultFrame and RemoveFriendResultFrame which is essentially everything except the print statements.

- I would refactor more methods in FriendsList and InterestList into their implemented interface, InformationList because 
there are still a lot of methods that the two subclasses share like getSize().
    