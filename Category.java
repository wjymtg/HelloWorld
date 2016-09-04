package jiuyangw_CSCI201L_Assignment1;

import java.util.ArrayList;

public class Category {
	// Member vars
	private ArrayList<Question> mQuestions;
	private String mName;
	
	// Member functions
	Category(String name){
		mName = name;
		mQuestions = new ArrayList<Question>();
	}
	
	public String getName(){
		return mName;
	}
	
	public Question getQuestion(int point){
		for(Question q : mQuestions){
			if(q.getPoint() == point){
				return q;
			}
				
		}
		return (new Question(-100, "", ""));
	}
	
	public ArrayList<Question> getQuestions(){
		return mQuestions;
	}
	
	public void addQuestion(Question q){
		mQuestions.add(q);
	}
	
	public int getQuestionNumber(){
		return mQuestions.size();
	}
	
	public boolean pointIncluded(int point){
		boolean included = false;
		for(Question q : mQuestions){
			if(q.getPoint() == point){
				included = true;
				break;
			}
		}
		return included;
	}
}