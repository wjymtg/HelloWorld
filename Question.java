package jiuyangw_CSCI201L_Assignment1;

public class Question {
	// Member Vars
	private String mQuestion;
	private String mAnswer;
	private int mPoint;
	
	// Member functions
	Question(int point, String q, String a){
		mPoint = point;
		mQuestion = q;
		mAnswer = a;
	}
	
	public void setPoint(int point){
		mPoint = point;
	}
	
	public void setAnswer(String a){
		mAnswer = a;
	}
	
	public void setQuestion(String q){
		mQuestion = q;
	}
	
	public String getQuestion(){
		return mQuestion;
	}
	public String getAnswer(){
		return mAnswer;
	}
	public int getPoint(){
		return mPoint;
	}
	public boolean answerIsCorrect(String ans){
		return (ans == mAnswer);
	}
}
