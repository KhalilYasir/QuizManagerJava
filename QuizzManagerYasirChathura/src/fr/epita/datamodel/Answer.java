package fr.epita.datamodel;

public class Answer {
	
	private Student student;
	
	private Question question;
	
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Answer [student=" + student + ", question=" + question + ", text=" + text + "]";
	}
	
	

}
