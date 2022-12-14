/*
Author: Vincent Miller
Date: 23 Mar/04 April 2021
Class: CPSC 1302
Assignment: FINAL PROJECT - FILE I/O Module
Description:
Import from csv file to flash card objects.
Export flash card objects to csv.
MAIN CURRENTLY READS FROM "flashcards.csv" STORING FLASHCARDS OBJECTS INTO
ARRAYLIST THEN EXPORTS TO "flashcards2.csv"
*/
import java.util.*;
import java.io.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class FileInOut
{
	//read CSV into an ArrayList and returns ArrayList containing flash card objects
  public static ArrayList<FlashCard<String>> readCSV(String filename)
  {
	//creates arraylist called FC
	ArrayList<FlashCard<String>> FC = new ArrayList<>();
	//error catch
	try
	{
		//
		File file = new File(filename);
		//creates reader from file
		FileReader fr = new FileReader(file);
		//creates buffered reader from file reader
		BufferedReader br = new BufferedReader(fr);
		//create string to hold current line being read
		//initialize to first line being read (it would skip without)
		String current = br.readLine();
		//creates temporary array
		String[] temp;
		//loop to read every line in file
		while(current != null)
		{
			//puts current line into temp array split by commas
			temp = current.split(",");
			//creates new flash card to hold string from temp array
			FlashCard flashcard = createFC(temp);
			//adds flash card object to FC ArrayList
			FC.add(flashcard);
			//sets new line to current
			current = br.readLine();
		}
		br.close();
		}
		//error catch
		catch(IOException ioe)
		{ioe.printStackTrace();}
		//returns the ArrayList containing flash card objects
		return FC;
	}

	//writes flashcards to csv file
	public static void writeCSV(ArrayList<FlashCard<String>> fc)
	{
		//error catch
		try
		{
			//creates file to write to
			File file = new File("flashcards2.csv");
			//creates file writer
			FileWriter fw = new FileWriter(file);
			//creates buffered writer
			BufferedWriter bw = new BufferedWriter(fw);
			//error catch
			try
			{
				//loop to write flashcards
				for(int i=0;i<fc.size();i++)
				{
					//writes flashcard to object using toString method
					bw.write(fc.get(i)+"");
				}
			}
			//error catch
			catch(IndexOutOfBoundsException e) {}
			bw.close();
		} 
		//error catch
		catch(IOException ioe)
		{ioe.printStackTrace();}
	}
	
	public static ArrayList<FlashCard<String>> readXML(String filename)
	{
		ArrayList<FlashCard<String>> FC = new ArrayList<FlashCard<String>>();
		
		try {
	         File inputFile = new File(filename);
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         NodeList nList = doc.getElementsByTagName("flashCard");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;
	                FlashCard<String> flashCard = new FlashCard<String>(eElement.getElementsByTagName("question").item(0).getTextContent(), eElement.getElementsByTagName("answer").item(0).getTextContent());
	                FC.add(flashCard);
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		return FC;
	}
	//creates flash card objects from temp array
	public static FlashCard<String> createFC(String[] temp)
	{
		//assign 1st position to question
		String question = temp[0];
		//assign 2nd position to answer
		String answer = temp[1];
		//returns new flash card holding question and answer
		return new FlashCard<String>(question,answer);
	}

}
