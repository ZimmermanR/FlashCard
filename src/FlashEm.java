import java.util.*;

public class FlashEm
{
	public static void main(String[] args)
	{
		ArrayList<FlashCard<String>> flashcards = FileInOut.readXML(args[0]);
		Flash_EmGUI F1 = new Flash_EmGUI(flashcards);
	}
}