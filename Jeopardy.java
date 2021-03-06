package jiuyangw_CSCI201L_Assignment1;
import java.io.*;
import java.util.ArrayList;

public class Jeopardy {
	// Member vars
	private ArrayList<Category> mCategories = new ArrayList<Category>();

	public ArrayList<Category> getCategories(){
		return mCategories;
	}
	// Read a word starting from l from word
	private static String readWord(String word, int l){
		int index = l;
		while(index < word.length()){
			if(word.charAt(index) != ':')
				index++;
			else{
				if(index < word.length()-1 && word.charAt(index+1) != ':')
					index++;
				else{
					if(index != word.length()-1){
						break;
					}
				}
			}
		}
		return word.substring(l, index);
	}

	// Read in the file
	public void readFile(String filename){
		try{
			BufferedReader bf = new BufferedReader(new FileReader(filename));
			String line;
			String[] categories = new String[5];
			line = bf.readLine();
			int[] pointValues = new int[5];
			boolean FJ = false;
			
			// Read the first line
			int index = 0;
			int catnum = 0;
			while(catnum < 5){
				categories[catnum] = readWord(line, index);
				index += categories[catnum].length();
				if(index == line.length()-1){
					System.out.println("Error: Too few categories.");
					System.exit(-1);
				}
				for(int i = 0; i < catnum; i++){
					if(categories[i].equals(categories[catnum])){
						System.out.println("Error: Duplicate categories.");
						System.exit(-1);
					}
				}
				if(catnum < 4)
					index += 2;
				catnum++;
			}
			if(index < line.length()-1){
				System.out.println("Error: Too many categories.");
				System.exit(-1);
			}
			
			Category cat1, cat2, cat3, cat4, cat5, cat6;
			cat1 = new Category(categories[0]);
			cat2 = new Category(categories[1]);
			cat3 = new Category(categories[2]);
			cat4 = new Category(categories[3]);
			cat5 = new Category(categories[4]);
			cat6 = new Category("FJ");

			// Read the second line
			line = bf.readLine();
			//System.out.println(line);
			index = 0;
			int pointnum = 0;
			while(pointnum < 5){
				int point = 0;
				try{
					point = Integer.parseInt(readWord(line, index));
					index += (readWord(line, index)).length();
				}
				catch(Exception e){
					System.out.println(e);
					System.out.println("Error: point value not integer.");
					System.exit(-1);
				}
				
				pointValues[pointnum] = point;
				for(int i = 0; i < pointnum; i++){
					if(pointValues[pointnum] == pointValues[i]){
						System.out.println("Error: Duplicate point values");
						System.exit(-1);
					}
				}
				if(pointnum <4)
					index += 2;
				pointnum++;
			}
			if(index < line.length()-1){
				System.out.println("Error: Too many point values.");
				System.exit(-1);
			}
			
			// Read in the questions
			String q="";
			String a="";
			String category="";
			int point=0;
			int colonNumber = 0;
			Question question = new Question(0, "", "");
			
			while((line = bf.readLine())!= null && line.length() != 0){
				index = 0;
				// New question, initialize all vars
				if(line.substring(0,2).equals("::")){
					// Add the previous question
					if(question.getPoint() != 0){
						if(category.equals(cat1.getName())){
							cat1.addQuestion(question);
						}
						else if(category.equals(cat2.getName())){
							cat2.addQuestion(question);
						}
						else if(category.equals(cat3.getName())){
							cat3.addQuestion(question);
						}
						else if(category.equals(cat4.getName())){
							cat4.addQuestion(question);
						}
						else if(category.equals(cat5.getName())){
							cat5.addQuestion(question);
						}
						else if(category.equals("FJ")){
							cat6.addQuestion(question);
						}
						else{
							System.out.println("Error: Wrong category name.");
							System.exit(-1);
						}
					}
					a="";
					q="";
					category="";
					point = 0;
					index += 2;
					colonNumber = 1;
					question = new Question(0, "", "");
				}
				
				// If FJ cont'd
				else{
					if(category.equals("FJ")){
						while(index < line.length()){
							switch(colonNumber){
							case 2:
								q += readWord(line, index);
								index += readWord(line, index).length();
								question.setQuestion(q);
								break;
							case 3:
								a += readWord(line, index);
								index += readWord(line, index).length();
								question.setAnswer(a);
								break;
							default:
								System.out.println("Error: Format error.");
								System.exit(-1);
							}
							if(colonNumber < 3 && index < line.length()-1 && line.substring(index, index+2).equals("::")){
								index+=2;
								colonNumber++;
							}
						}
					}
				}
				// FJ starts
				if(readWord(line, index).equals("FJ")){
					if(FJ){
						System.out.println("Error: Multiple final jeopardy questions.");
						System.exit(-1);
					}
					FJ = true;
					point = -100;
					category = "FJ";
					index += 4;
					colonNumber++;
					if(index < line.length()-1)
						q += readWord(line, index);
					index += q.length();
					index += 2;
					if(index < line.length()-1)
						a += readWord(line, index);
					question.setPoint(point);
					question.setAnswer(a);
					question.setQuestion(q);
				}
				// Other questions
				else{
					while(index < line.length()){
						//System.out.println(colonNumber);
						switch(colonNumber){
						case 1:
							category = category + readWord(line, index);
							boolean right = false;
							for(int i = 0; i < 5; i++){
								if(categories[i].equals(category))
									right = true;
							}
							if(!right){
								System.out.println("Error: Wrong category.");
								System.exit(-1);
							}
							index += category.length();
							break;
						case 2:
							point = Integer.parseInt(readWord(line, index));
							right = false;
							for(int i = 0; i < 5; i++){
								if(pointValues[i] == point)
									right = true;
							}
							if(!right){
								System.out.println("Error: Wrong point value.");
								System.exit(-1);
							}
							index += readWord(line, index).length();
							question.setPoint(point);
							break;
						case 3:
							q = q + readWord(line, index);
							index += readWord(line, index).length();
							question.setQuestion(q);
							break;
						case 4:
							a = a+ readWord(line, index);
							index += readWord(line, index).length();
							question.setAnswer(a);
							if(index < line.length()){
								System.out.println("Error: Format error.");
								System.exit(-1);
							}
							break;
						default:
							System.out.println("Error: Format error.");
							System.exit(-1);
						}
						
						if(colonNumber < 4 && index < line.length()-1 && line.substring(index, index+2).equals("::")){
							index += 2;
							colonNumber++;
						}
					}
				}
			}
			// Add the last question
			if(category.equals(cat1.getName())){
				cat1.addQuestion(question);
			}
			else if(category.equals(cat2.getName())){
				cat2.addQuestion(question);
			}
			else if(category.equals(cat3.getName())){
				cat3.addQuestion(question);
			}
			else if(category.equals(cat4.getName())){
				cat4.addQuestion(question);
			}
			else if(category.equals(cat5.getName())){
				cat5.addQuestion(question);
			}
			else if(category.equals("FJ")){
				cat6.addQuestion(question);
			}
			else{
				System.out.println("Error: Wrong category name.");
				System.exit(-1);
			}
			
			mCategories.add(cat1);
			mCategories.add(cat2);
			mCategories.add(cat3);
			mCategories.add(cat4);
			mCategories.add(cat5);
			mCategories.add(cat6);
			
			bf.close();
		}
		
		catch(IOException ioe){
			System.out.println(ioe);
			System.exit(-1);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		Jeopardy j = new Jeopardy();
		j.readFile(args[0]);
		
	}

}