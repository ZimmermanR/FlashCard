import java.util.*;

public class FlashEm
{
	public static void main(String[] args)
	{
		ArrayList<FlashCard<String>> flashcards = FileInOut.readCSV(args[0]);
		Flash_EmGUI Fl = new Flash_EmGUI(flashcards);
	}
}