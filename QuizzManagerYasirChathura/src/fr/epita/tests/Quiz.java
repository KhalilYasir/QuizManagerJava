package fr.epita.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.epita.datamodel.*;
import fr.epita.services.dao.QuestionXMLDAO;

public class Quiz {

	private static String user = "";
	private static int score = 0;
	private static int type = 0;
	private static Scanner scan;
	private static Document doc;
	private static QuestionXMLDAO dao;
	public static void main(String[] args) {
		// Initialization of Quiz
		scan = new Scanner(System.in);
		dao = new QuestionXMLDAO();
		welcomeMenu();

	}

	public static void welcomeMenu() {
		System.out.println("************ Start Your Quiz ****************");
		System.out.print("Enter Your Full Name:");
		user = scan.nextLine();
		System.out.println("Select Desired Quiz Type:"); //Selection of Quiz Type
		System.out.println("Quiz By Topic - Enter 1");
		System.out.println("Complete Quiz - Enter 2");
		try {
			List<Question> questions = new ArrayList<Question>();
			type = scan.nextInt();
			scan.nextLine();
			if (type == 1) {
				questions = buildByTopic();
			} else {
				questions = buildNormal();
			}
			for (int i = 0; i < questions.size(); i++) {
				showQuestion(i, questions.get(i));
				System.out.println("");
			}
			System.out.println("********** Quiz Finished **********"); 
			System.out.println("*********** Your Result *********** ");
			System.out.println(" ");
			System.out.println("Hello "+ user + " You Got " + score + " Marks Out of " + questions.size()); //Showing Result
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void showQuestion(int index, Question question) {
		System.out.println((index + 1) + "-" + question.getQuestion());
		if (!question.getChoices().isEmpty()) {
			System.out.println("Select Your Answer By Entering Option Number: ");
			System.out.println("");
			for (int i = 0; i < question.getChoices().size(); i++) {
				System.out.println((i + 1) + "-" + question.getChoices().get(i).getChoice());
			}
			int choice = scan.nextInt();
			scan.nextLine();
			if (question.getChoices().get(choice - 1).isValid()) {
				score++;
			}
		} else {
			System.out.print("Enter Your Answer: ");
			String reponse = scan.nextLine();
			if (question.getAnswer() != null && question.getAnswer().getText().equalsIgnoreCase(reponse)) {
				score++;
			}
		}
	}

	public static void loadFile() throws SAXException {
		try {
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			// Builder to Parse The XML file
			builder = fact.newDocumentBuilder();
			doc = builder.parse(new File("quiz.xml"));
		} catch (ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Question> buildNormal()
			throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		List<Question> questions = new ArrayList<Question>();
		questions = dao.getAllQuestions();
		return questions;
	}

	public static List<Question> buildByTopic() throws ParserConfigurationException, SAXException, IOException {
		List<Question> questions = new ArrayList<Question>();
		System.out.println("Select Topic:"); //Quiz Topic Selection
		List<String> topics = dao.getAllTopics();
		for (int i = 0; i < topics.size(); i++) {
			System.out.println((i + 1) + "-" + topics.get(i)); //Loop to Select Quiz From Available Types
		}
		int sout = scan.nextInt();
		questions = dao.getAllQuestionsByTopic(topics.get(sout-1));
		return questions;
	}

}
