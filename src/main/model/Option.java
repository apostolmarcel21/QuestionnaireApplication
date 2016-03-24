package model;
/**
 * this class contains atributes, constructor, getters, setters and toString
 */
public class Option {		
	private String text;
	private boolean isCorrect;
	private boolean isSelected = false;
	
	public Option() {
		super();
	}
	
	public Option(String option, boolean isCorrect) {
		super();
		this.text = option;
		this.isCorrect = isCorrect;
	}

	public boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getText() {
		return text;
	}

	public void setText(String option) {
		this.text = option;
	}

	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	@Override
	public String toString() {
		return "\nOption [text=" + text + ", isCorrect=" + isCorrect + ", isSelected= " + isSelected + "]";
	}
	
}
