package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import model.Option;
import model.Question;

public class GUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int currentQuestionIndex = 0;
	private double percentage = 0;
	private ArrayList<JRadioButton> currentRadioButtons = new ArrayList<JRadioButton>();
	private JToggleButton btBack = new JToggleButton("Back");
	private JToggleButton btNext = new JToggleButton("Next");
	private JToggleButton btFinish = new JToggleButton("Finish");
	private ButtonGroup bg = new ButtonGroup();
	private ArrayList<Question> questions = Question.provideQuestions();
	private Container cont = getContentPane();
	
	/**
	 * Initializes the container with its inner panel
	 */
	private void setContainerProperties() {
		setTitle("Testing Java");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 350);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
		initializePanel();
		setMinimumSize(new Dimension(500, 300));
		pack();
	}
	
	
	public GUI() {
		setContainerProperties();
	}

	private void initaliseButtons() {
		btNext.setForeground(Color.BLACK);
		btNext.addActionListener(this);
		btBack.setForeground(Color.BLACK);
		btBack.addActionListener(this);
		btFinish.setForeground(Color.BLACK);
		btFinish.addActionListener(this);
	}

	/**
	 * Initializes the panel and adds the first question to be displayed in the first view
	 */
	private void initializePanel() {
		displayCurrentQuestion();
		initaliseButtons();
	}

	/**
	 * Calculates the score percentage based on the correct vs incorrect answers rate
	 */
	private void calculateScore() {
		int contor = 0;
		
		for (int i = 0; i < questions.size(); i++) {
			
			Question question = questions.get(i);
			int optionsSize = question.getOptions().size();
			
			for (int j = 0; j < optionsSize; j++) {
				
				Option option = question.getOptions().get(j);

				if (option.getIsCorrect() == true && option.getIsSelected() == true) {
					contor++;
				}
			}
		}
		percentage = ((double)contor / questions.size()) * 100.0;
	}

	private void cleanUpButtons() {
		for (JRadioButton button : currentRadioButtons) {
			cont.remove(button);
		}
		currentRadioButtons.removeAll(currentRadioButtons);
		cont.removeAll();
	}

	/**
	 * Initializes the question with corresponding answers and buttons
	 */
	private void displayCurrentQuestion() {
		cleanUpButtons();
		
		Question currentQuestion = questions.get(currentQuestionIndex);
		ArrayList<Option> currentOptionList = currentQuestion.getOptions();
		
		JLabel questionLabel = new JLabel();
		questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
		questionLabel.setText(currentQuestion.getText());
		cont.add(questionLabel, BorderLayout.PAGE_START);
		
		
		JPanel optionsPanel = new JPanel();
		int optionsSize = currentOptionList.size();
		optionsPanel.setLayout(new GridLayout(optionsSize, 1));

		for (int i = 0; i < optionsSize; i++) {
			Option currentOption = currentOptionList.get(i);
			Boolean selectedOption = currentOption.getIsSelected();
			String optionText = currentOption.getText();
			JRadioButton button = new JRadioButton();
			button.setSelected(selectedOption);
			button.setText(optionText);
			bg.add(button);
			currentRadioButtons.add(button);
			optionsPanel.add(button);
		}
		cont.add(optionsPanel, BorderLayout.CENTER);
		JPanel buttonsPane = new JPanel();
		buttonsPane.add(btBack, BorderLayout.LINE_START);
		buttonsPane.add(btNext, BorderLayout.LINE_END);		
		cont.add(buttonsPane, BorderLayout.PAGE_END);
		cont.validate();
	}

	/**
	 *on the last page, print the score and a message
	 */
	private void printScore() {
		cont.removeAll();

		JLabel scoreLabel = new JLabel("", SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 22));

		if (percentage > 50) {
			scoreLabel.setText("<html>Congratulation !<br>"
					+ "Your score is " + String.format("%.1f", percentage) + ".<html>");
			scoreLabel.setForeground(Color.GREEN);
		} else {
			scoreLabel.setText("<html>Sorry, your score is " + String.format("%.1f", percentage) + ".<br>"
					+ "Please try again!<html>");
			scoreLabel.setForeground(Color.RED);
		}

		cont.add(scoreLabel, BorderLayout.CENTER);
		cont.validate();
	}
	
	private void clearSelection() {
		btNext.setSelected(false);
		btBack.setSelected(false);
	}
	
	/**
	 * Initializes last page and display the choices
	 */
	private void intialiseLastPage() {
		String finishText = "<html>There are no more questions.<br><br>"
				+ "To finish and see your score, press <b>Finish</b>.<br><br>"
				+ "To review your answears, press <b>Back</b>.<br>";
		JLabel finalMessage = new JLabel(finishText, SwingConstants.CENTER);
		finalMessage.setFont(new Font("Arial", Font.PLAIN , 18));
					
		cont.add(finalMessage, BorderLayout.CENTER);
		JPanel buttonsPane = new JPanel();
		buttonsPane.add(btBack, BorderLayout.LINE_START);
		buttonsPane.add(btFinish, BorderLayout.LINE_END);		
		cont.add(buttonsPane, BorderLayout.PAGE_END);
		
		cont.validate();
	}
	
	
	private void persistSelectedOption() {
		for (JRadioButton jRadioButton : currentRadioButtons) {
			if (jRadioButton.isSelected()) {
				
				String textSelectedButton = jRadioButton.getText();
				Question currentQuestion = questions.get(currentQuestionIndex);
				
				for (Option option : currentQuestion.getOptions()) {
					option.setIsSelected(false);
					if (textSelectedButton.equals(option.getText())) {
						option.setIsSelected(true);					
					}
				}
			}
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (btFinish.isSelected()) {
			//The page where is displayed the score 
			calculateScore();
			printScore();
		} 
		else {
			persistSelectedOption();
			
			if (btNext.isSelected()) {
				currentQuestionIndex++;
				
				if (currentQuestionIndex == questions.size()) {
					//Last page 
					cont.removeAll();
					cont.repaint();
					intialiseLastPage();
				} else {
					//Not last page 
					displayCurrentQuestion();
				}
			}
			
			if (btBack.isSelected()) {
				currentQuestionIndex--;
				//Not first page 
				if (currentQuestionIndex >= 1) {
					displayCurrentQuestion();
				 }
			}
			
			clearSelection();
		}
	}
}
