package fr.epita.tests;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import fr.epita.datamodel.Question;
import fr.epita.services.dao.QuestionDAO;
import fr.epita.services.dao.QuestionXMLDAO;

public class DAOTest {
	
	
	public static void main(String[] args) throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		Question question = new Question();
		question.setQuestion("");
		question.setDifficulty(8);
		String[] topics = new String[2];
		topics[0] = "";
		topics[1] = "";
		question.setTopics(topics);
		QuestionXMLDAO dao = new QuestionXMLDAO();
		dao.create(question);
	}

}
