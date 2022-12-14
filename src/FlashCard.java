public class FlashCard<T> implements Comparable<FlashCard<T>>
{
	private String question, answer;
	
	public FlashCard(String question, String answer)
	{
		this.question = question;
		this.answer = answer;
	}
	public String toString()	{ return this.question + "," + this.answer + "\n"; }
	
	public void setQuestion(String inputQuestion) { this.question = inputQuestion; }
	
	public String getQuestion() { return this.question; }
	
	public void setAnswer (String inputAnswer) { this.answer = inputAnswer; }
	
	public String getAnswer() { return this.answer; }
	@Override
	public int compareTo(FlashCard<T> o) {
		return 0;
	}
}