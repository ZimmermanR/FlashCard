import java.util.*;
import java.io.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FileInOut
{
	public static ArrayList<FlashCard<String>> readCSV(String filename)
	{
		ArrayList<FlashCard<String>> FC = new ArrayList<>();
		try
		{
			File file = new File(filename);	
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String current = br.readLine();
			String[] temp;
			while(current != null)
			{
				temp = current.split(",");
				FlashCard flashcard = createFC(temp);
				FC.add(flashcard);
				current = br.readLine();
			}
			br.close();
		}
		catch(IOException ioe)
		{ioe.printStackTrace();}
		return FC;
	}

	public static void writeCSV(ArrayList<FlashCard<String>> fc)
	{
		try
		{
			File file = new File("flashcards2.csv");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			try
			{
				for(int i=0;i<fc.size();i++)
				{
					bw.write(fc.get(i)+"");
				}
			}
			catch(IndexOutOfBoundsException e) {}
			bw.close();
		} 
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
	         NodeList nList = doc.getElementsByTagName("FlashCard");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;
	                FlashCard<String> flashCard = new FlashCard<String>(eElement.getElementsByTagName("QUESTION").item(0).getTextContent(), eElement.getElementsByTagName("ANSWER").item(0).getTextContent());
	                FC.add(flashCard);
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		return FC;
	}
	
	static void writeXML(ArrayList<FlashCard<String>> FC, String filename) 
	{
		ArrayList<FlashCard<String>> currentCards = readXML(filename);
    	
		try {
			File inputFile = new File(filename);
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse(inputFile);
	        Element root = doc.getDocumentElement();
	        
	        
	        for(int i = 0; i < FC.size(); i++)
	        {
	        	boolean match = false;
	        	for(int j=0; j < currentCards.size();j++) {
	        		if(FC.get(i).getQuestion().equals(currentCards.get(j).getQuestion())) {
	        			match = true;
	        		}
	        	}
	        	if(match == false) {
		        	Element flashCard = doc.createElement("FlashCard");
			        root.appendChild(flashCard);
		        	String elementNumber = Integer.toString(i);
		        	
		        	Element id = doc.createElement("ID");
		        	Element question = doc.createElement("QUESTION");
		        	Element answer = doc.createElement("ANSWER");

		        	id.appendChild(doc.createTextNode(elementNumber + 1));
		        	question.appendChild(doc.createTextNode(FC.get(i).getQuestion()));
		        	answer.appendChild(doc.createTextNode(FC.get(i).getAnswer()));
		        	
		        	flashCard.appendChild(id);
		        	flashCard.appendChild(question);
		        	flashCard.appendChild(answer);
        		}
	        }
	        
	        TransformerFactory factory = TransformerFactory.newInstance();
	        Transformer transformer = factory.newTransformer();
	        DOMSource domSource = new DOMSource(doc);
	        StreamResult streamResult = new StreamResult(new File(filename));
	        transformer.transform(domSource, streamResult);

	        DOMSource source = new DOMSource(doc);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
	public static FlashCard<String> createFC(String[] temp)
	{
		String question = temp[0];
		String answer = temp[1];
		return new FlashCard<String>(question,answer);
	}
}