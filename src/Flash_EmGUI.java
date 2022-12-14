/*
Author: Jonathan Co
Date: 23 Mar/ 4 April 2021
Class: CPSC 1302
Assignment: FINAL PROJECT - GUI Module
Description:
Displays FlashCard objects with Java Frame to user.
Calls methods from other Modules with GUI.
*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class Flash_EmGUI extends JFrame{
	private final int WIDTH = 450;
	private final int HEIGHT = 300;
	private JPanel MainPanel,AnsPanel,DPanel;
	private JButton prev,Flip,check,next;
	private JMenuBar Mb;
	private JMenu  file, Edit;
	private JMenuItem Exit,Load,Rand,Add,Remove,editCard,Help,Export,Sort;
	private JLabel A, D;
	private JTextArea Display;
	private JTextField Ans;
	private ArrayList<FlashCard<String>> cards;
	private String NewQuestion;
	private String NewAnswer;
	private int counter=0;
	private int index = 0;
	public Flash_EmGUI(ArrayList<FlashCard<String>> yeet){
		super("Flash Cards Wooooooo");
		cards = yeet; //:)
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildPanel();
		setJMenuBar(Mb);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void buildPanel(){
		//======================Creates Menu bar===========================
		Mb = new JMenuBar();
		ButtonGroup group3 = new ButtonGroup();
		buildFileM();
		MainPanel = new JPanel();
		MainPanel.setLayout(new GridLayout(2,1));
		//===========creates the answer panel and its components============
		AnsPanel = new JPanel();
		Ans = new JTextField(10);
		AnsPanel.add(Ans);
		A = new JLabel("Your Answer");
		AnsPanel.add(A);
		//-----------adds previous button to answer panel---------------
		prev = new JButton("Previous");
		prev.addActionListener(new ButtonListener());
		group3.add(prev);
		AnsPanel.add(prev);
		//-----------adds flip button to answer panel-------------------
		Flip = new JButton("Flip");
		Flip.addActionListener(new ButtonListener());
		group3.add(Flip);
		AnsPanel.add(Flip);
		//----------adds next button to answer panel--------------------
		next = new JButton("Next");
		next.addActionListener(new ButtonListener());
		group3.add(next);
		AnsPanel.add(next);
		//----------adds check button to answer panel-------------------
		check = new JButton("Check");
		check.addActionListener(new ButtonListener());
		group3.add(check);
		AnsPanel.add(check);
		//==============creates display panel and its components===============
		DPanel = new JPanel();
		Display = new JTextArea(8,25);
		Display.setEditable(false);
		Display.setLineWrap(true);
		Display.setWrapStyleWord(true);
		D = new JLabel("Display");
		JScrollPane scroll = new JScrollPane(Display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		DPanel.add(D);
		DPanel.add(scroll);
		//==========adds sub panels to main panel and adds panel to frame=========
		MainPanel.add(DPanel);
		MainPanel.add(AnsPanel);
		add(MainPanel);
	}

	public void buildFileM(){
		//========Creates file menu and sub menu=========
		file = new JMenu("File");
		ButtonGroup group1 = new ButtonGroup();
		Edit = new JMenu("Edit");
		file.add(Edit);
		//-----add Add button to the edit menu-------
		Add = new JRadioButtonMenuItem("Add");
		Add.addActionListener(new FileListener());
		Edit.add(Add);
		group1.add(Add);
		//-----add remove button to the edit menu------
		Remove = new JRadioButtonMenuItem("Remove");
		Remove.addActionListener(new FileListener());
		Edit.add(Remove);
		group1.add(Remove);
		Edit.add(Add);
		group1.add(Remove);
		//-----add editCard button to the edit menu-----
		editCard = new JRadioButtonMenuItem("EditCard");
		editCard.addActionListener(new FileListener());
		Edit.add(editCard);
		group1.add(editCard);
		//-------adds Load to file menu------------
		Load = new JRadioButtonMenuItem("Load");
		Load.addActionListener(new FileListener());
		file.add(Load);
		group1.add(Load);
		//-------adds export to file menu----------
		Export = new JRadioButtonMenuItem("Export");
		Export.addActionListener(new FileListener());
		file.add(Export);
		group1.add(Export);
		//-------adds Rand to file menu------------
		Rand = new JRadioButtonMenuItem("Randomize");
		Rand.addActionListener(new FileListener());
		file.add(Rand);
		group1.add(Rand);
		//-------adds Sort to file menu------------
		Sort = new JRadioButtonMenuItem("Sort");
		Sort.addActionListener(new FileListener());
		file.add(Sort);
		group1.add(Sort);
		//-------adds help to file menu------------
		Help = new JRadioButtonMenuItem("Help");
		Help.addActionListener(new FileListener());
		file.add(Help);
		group1.add(Help);
		//--------adds exit to file menu------------
		Exit = new JRadioButtonMenuItem("Exit");
		Exit.addActionListener(new FileListener());
		file.add(Exit);
		group1.add(Exit);
		Mb.add(file);
	}

	private class FileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==Exit){
				System.exit(0);
			}
			else if(e.getSource()==Remove){
				UserEdit.removeFlashCard((1+Integer.parseInt(JOptionPane.showInputDialog("Enter Integer of Card to Remove"))), cards);
			}
			else if(e.getSource() == editCard){
				NewQuestion = JOptionPane.showInputDialog("Enter the new Question");
				NewAnswer = JOptionPane.showInputDialog("Enter the new Answer");
				UserEdit.editFlashCardFile((1+Integer.parseInt(JOptionPane.showInputDialog("Enter Integer of Card to Edit"))), NewQuestion, NewAnswer, cards);
			}
			else if(e.getSource() == Add){
				NewQuestion = JOptionPane.showInputDialog("Enter the new Question");
				NewAnswer = JOptionPane.showInputDialog("Enter the new Answer");
				UserEdit.addFlashCard(NewQuestion,NewAnswer,cards);
			}
			else if (e.getSource()==Load){
				if(counter ==0){
					Display.setText((index+1)+"\n"+cards.get(index).getQuestion());
				}
				else{
					Display.setText((index+1)+"\n"+cards.get(index).getAnswer());
				}
			}
			else if(e.getSource()==Export){
				FileInOut.writeCSV(cards);
			}
			else if(e.getSource()==Sort){
				Module3.sort(cards);
			}
			else if(e.getSource()==Rand){
				Module3.shuffle(cards);
			}
			else{
				String helpMessage = "*Important* \nThe Answers input for the questions must be precise for the characters though upper and lower case of letters does not matter\n*Important*\n\nButtons:\nFlip- Click flip to display the answer and questions of each flashcard.\nCheck- Click check to see if your answer matches what is on the flashcards.\n";
				helpMessage = helpMessage + "Next and Previous- Click bext and previous to scroll through each flashcard\n\nFile menu:";
				helpMessage = helpMessage + "\nLoad- Click this menu item in order to load the current flashcard the prgoram is set to\nExport- Click this menu item in order to export the current flashcards with their edits onto a new csv file";
				helpMessage = helpMessage + "\nRand- Click this menu item in order to randomize the order of the current flashcards\nSort- Click on this menu item to sort the current flashcards";
				helpMessage = helpMessage + "\nExit- Exits the program and closes GUI\n\nEdit:\nAdd- Click this menu item in order to add a card to the current flashcards, using input dialog panes";
				helpMessage = helpMessage + "\nRemove- Click this menu item inorder to remove a flashcard from your chosen index, you may find the index to remove from by looking at the top left corner of the display area\nEditCard- Click on this to change the content of a flashcard at a chosen index";
				Display.setText(helpMessage);
			}
		}
	}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==prev) {
				//
				if(index == 0){
					index = cards.size()-1;
				}
				else{
					index = index -1;
				}
				if(counter ==0){
					Display.setText((index+1)+"\n"+cards.get(index).getQuestion());
				}
				else{
					Display.setText((index+1)+"\n"+cards.get(index).getAnswer());
				}
			}
			else if(e.getSource()==Flip){
				if (counter ==0){
					Display.setText((index+1)+"\n"+cards.get(index).getAnswer());
					counter = 1;
				}
				else{
					Display.setText((index+1)+"\n"+cards.get(index).getQuestion());
					counter = 0;
				}
			}
			else if(e.getSource()==check){
				if(cards.get(index).getAnswer().equalsIgnoreCase(Ans.getText())){
					Display.setText("Your answer is correct");
				}
				else{
					Display.setText("Your answer is incorrect. You may click the flip button to see the correct answer.");
				}
			}
			else{
				if(index == cards.size()-1){
					index = 0;
				}
				else{
					index = index +1;
				}
				if(counter ==0){
					Display.setText((index+1)+"\n"+cards.get(index).getQuestion());
				}
				else{
					Display.setText((index+1)+"\n"+cards.get(index).getAnswer());
				}
			}
		}
	}
	public static void main(String [] args){
		
		ArrayList<FlashCard<String>> flashcards = FileInOut.readCSV(args[0]);
		Flash_EmGUI Fl = new Flash_EmGUI(flashcards);
	}
}