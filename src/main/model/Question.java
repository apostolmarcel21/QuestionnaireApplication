package model;
import java.util.ArrayList;

public class Question {
	private String text;
	private ArrayList<Option> options = new ArrayList<Option>();
	
	public Question(String question, ArrayList<Option> options) {
		super();
		this.text = question;
		this.options = options;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String question) {
		this.text = question;
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}
	
	@Override
	public String toString() {
		return "\nQuestion [question=" + text + ", options=" + options + "]";
	}
	/**
	 * I have an ArrayList of questions and any question has an ArrayList of answers
	 */
	 
	public static ArrayList<Question> provideQuestions() {
		ArrayList<Question> questions = new ArrayList<Question>();
		
		ArrayList<Option> options1 = new ArrayList<Option>();
		options1.add(new Option("Infinite", true));
		options1.add(new Option("Only one", false));
		options1.add(new Option("System Dependent", false));
		options1.add(new Option("All of the above", false));
		questions.add(new Question("  1. How many arguments can be passed to main()?", options1));

		ArrayList<Option> options2 = new ArrayList<Option>();
		options2.add(new Option(".exe", false));
		options2.add(new Option("Byte Code", true));
		options2.add(new Option(".tar", false));
		options2.add(new Option(".dll", false));
		questions.add(new Question("  2. Java source code is compiled into:", options2));

		ArrayList<Option> options3 = new ArrayList<Option>();
		options3.add(new Option("All the members of the superclass are inherited by the subclass", false));
		options3.add(new Option("A final class can be abstract", false));
		options3.add(new Option("The subclass of a non-abstract class can be declared abstract", true));
		options3.add(new Option("A class in which all the members are declared private, cannot be declared public", false));
		questions.add(new Question("  3. Which statement is true?", options3));

		ArrayList<Option> options4 = new ArrayList<Option>();
		options4.add(new Option("import", false));
		options4.add(new Option("finally", false));
		options4.add(new Option("goto", false));
		options4.add(new Option("friend", true));
		questions.add(new Question("  4. Which of the following is not a reserved word in java?", options4));

		ArrayList<Option> options5 = new ArrayList<Option>();
		options5.add(new Option("protected static int answer = 42", false));
		options5.add(new Option("volatile static int answer = 42", false));
		options5.add(new Option("int answer = 42", true));
		options5.add(new Option("private final static int answer = 42", false));
		questions.add(new Question("<html>  5. Which of the field declaration is legal within the body <br> of an interface?<html>", options5));

		ArrayList<Option> options6 = new ArrayList<Option>();
		options6.add(new Option("Yes-the child classes inherit both", true));
		options6.add(new Option("No-it must have all one or the other", false));
		options6.add(new Option("Yes-but the child classes do not inherit the abstract methods", false));
		options6.add(new Option("No-it must have all abstract methods", false));
		questions.add(new Question("<html>  6. Can an abstract class define both abstract methods and <br> non-abstract methods?<html>", options6));

		ArrayList<Option> options7 = new ArrayList<Option>();
		options7.add(new Option("Interfaces allow multiple implementation inheritance", false));
		options7.add(new Option("Interfaces can extend any number of other interfaces", true));
		options7.add(new Option("Members of an interface are never static", false));
		options7.add(new Option("Members of an interface can always be declared static", false));
		questions.add(new Question("  7. Which statement is true about interfaces?", options7));

		ArrayList<Option> options8 = new ArrayList<Option>();
		options8.add(new Option("notify()", false));
		options8.add(new Option("notifyall()", false));
		options8.add(new Option("wait()", true));
		options8.add(new Option("exits synchronized code", false));
		questions.add(new Question("<html>  8. Which of the following will directly stop the execution <br>of a Thread?<html>", options8));

		ArrayList<Option> options9 = new ArrayList<Option>();
		options9.add(new Option("run()", false));
		options9.add(new Option("construct()", false));
		options9.add(new Option("start()", true));
		options9.add(new Option("register()", false));
		questions.add(new Question("  9. Which method registers a thread in a thread scheduler?", options9));

		ArrayList<Option> options10 = new ArrayList<Option>();
		options10.add(new Option("Synchronization", true));
		options10.add(new Option("Serialization", false));
		options10.add(new Option("Internationalization", false));
		options10.add(new Option("None of the above", false));
		questions.add(new Question("<html>  10. Which is the capability of control the access of <br> multiple threads to any shared resource?<html>", options10));

		return questions;
	}
	
}

