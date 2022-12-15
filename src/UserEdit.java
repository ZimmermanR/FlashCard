/*
Author: Ryan Zimmerman
Date: 4/8/2021
Class: CPSC 1302
Assignment: FINAL PROJECT - USER EDIT Module
Description:

*/
import java.util.*;
public class UserEdit extends FileInOut {

	//allows the user to add flashcards to the flashcard array
	public static void addFlashCard(String question, String answer, ArrayList<FlashCard<String>> flashCardArray) {
		String[] newFlashCard = {question, answer};
		FlashCard FC = createFC(newFlashCard);
		try {
			flashCardArray.add(FC);
			writeCSV(flashCardArray);
		}catch(ArrayIndexOutOfBoundsException e) {

		}
	}
	//allows user to remove flashcard
	public static void removeFlashCard(int cardIndex, ArrayList<FlashCard<String>> flashCardArray) {
		for(int i = 0; i < flashCardArray.size() - 1; i++) {
			try {
				if(i == cardIndex - 1) {
					flashCardArray.remove(cardIndex);
				}
			}catch(ArrayIndexOutOfBoundsException e) {

			}
		}
	}
	//allows user to edit questions and answers at the flash card file
	public static void editFlashCardFile(int index, String question, String answer, ArrayList<FlashCard<String>> flashCardArray) {
		String[] newFlashCard = {question, answer};
		FlashCard FC = createFC(newFlashCard);
		for(int i = 0; i < flashCardArray.size() - 1; i++) {
			try{
				if(i == index - 1) {
					flashCardArray.remove(i);
					flashCardArray.add(i, FC);
					writeXML(flashCardArray, "az900.xml");
				}
			}catch(ArrayIndexOutOfBoundsException e) {

			}
		}
	}
}
