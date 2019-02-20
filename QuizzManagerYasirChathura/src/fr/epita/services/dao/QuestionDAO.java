package fr.epita.services.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.datamodel.Question;

public class QuestionDAO {

	private static final String DB_PASSWORD = "";
	private static final String DB_USERNAME = "sa";
	private static final String DB_URI = "jdbc:h2:C:/Formation/db/h2DS";
									

	
	public void create(Question question) {
		try {
			Connection connection = DriverManager.getConnection(DB_URI, DB_USERNAME, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement("INSERT INTO QUESTION VALUES (?, ?)");
			statement.setString(0, question.getQuestion());
			statement.setInt(1, question.getDifficulty());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}    
		
	}
	
	public List<Question> readByDifficulty(int difficulty) {
		List<Question> foundQuestions = new ArrayList<Question>();
		try {
			Connection connection = DriverManager.getConnection(DB_URI, DB_USERNAME, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement("SELECT QUESTION, DIFFICULTY FROM QUESTION WHERE DIFFICULTY = ?");
			statement.setInt(0, difficulty);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {  // Difficulty Matching For Questions
				String SQLquestion = resultSet.getString(1); // Match Question Names
				int SQLdifficulty = resultSet.getInt(2); //Match Question Difficulty
				Question foundQuestion = new Question(); // Put New Question
				foundQuestion.setQuestion(SQLquestion);
				foundQuestion.setDifficulty(SQLdifficulty);
				foundQuestions.add(foundQuestion); // Add Question to the New list
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return foundQuestions;
	}
	
	public void updateDifficulty(Question question, int newDifficulty) {
		try {
			Connection connection = DriverManager.getConnection(DB_URI, DB_USERNAME, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement("UPDATE QUESTION SET DIFFICULTY = ? WHERE NAME = ?");
			statement.setInt(0, newDifficulty);
			statement.setString(1, question.getQuestion());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Question question) {
		//TODO open connection
		//TODO prepare SQL statement
		//TODO add parameters
		//TODO execute statement
	}
}
