Flash 'Em! Application
CPSC 1302 Final Project
Created by: Jonathan Co, Vincent Miller, Michael Soskin, Ryan Zimmerman

Overview:
Our application is designed to offer a digital solution to using flashcards. The user may have an array of flashcards created from a .csv (comma-separated values) file that they have organized via the format: question, answer. Additionally, Flash ‘Em! allows the student to create, remove, or edit a given flashcard from the GUI for quick maintenance. For a more dynamic experience, the cards can be shuffled and sorted to promote a more challenging study session and avoid learning by static flashcard patterns.

How-To:
FlashEm.java is our driver/main program that runs the application.
FlashEm_GUI.java, FileInOut.java, Module3.java, and UserEdit.java are the support files.

flashcards.csv is the import file for flashcard objects.
It comes preloaded with 6 example/test flashcards.

flashcards2.csv only exists upon export/writing to CSV file. It write to a separate file to preserve the first file in case of errors. You must rename flashcards2.csv to flashcards.csv if you wish to import the updated flashcards.

*Important* 
The Answers input for the questions must be precise for the characters though upper and lower case of letters does not matter
*Important*

Buttons:
Flip- Click flip to display the answer and questions of each flashcard.
Check- Click check to see if your answer matches what is on the flashcards.
Next and Previous- Click bext and previous to scroll through each flashcard

File menu:
Load- Click this menu item in order to load the current flashcard the prgoram is set to
Export- Click this menu item in order to export the current flashcards with their edits onto a new csv file
Rand- Click this menu item in order to randomize the order of the current flashcards
Sort- Click on this menu item to sort the current flashcards
Exit- Exits the program and closes GUI

Edit:
Add- Click this menu item in order to add a card to the current flashcards, using input dialog panes
Remove- Click this menu item inorder to remove a flashcard from your chosen index, you may find the index to remove from by looking at the top left corner of the display area
EditCard- Click on this to change the content of a flashcard at a chosen index