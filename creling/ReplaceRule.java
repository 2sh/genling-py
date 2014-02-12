package creling;

import java.util.regex.Pattern;

public class ReplaceRule {

	private String regex;
	private Pattern pattern;
	private String replacement;
	private double probability = 1.0;
	
	public ReplaceRule(String regex, String replacement, double probability) {
		super();
		setRegex(regex);
		this.replacement = replacement;
		this.probability = probability;
	}
	
	public ReplaceRule(String regex, String replacement) {
		super();
		setRegex(regex);
		this.replacement = replacement;
	}
	
	public String replace(String stemString)
	{
		if (Math.random() > probability) return stemString;
		return pattern.matcher(stemString).replaceAll(replacement);
	}

	/**
	 * @return the regex
	 */
	public String getRegex() {
		return regex;
	}

	/**
	 * @param regex the regex to set
	 */
	public void setRegex(String regex) {
		this.regex = regex;
		this.pattern = Pattern.compile(regex);
	}

	/**
	 * @return the replacement
	 */
	public String getReplacement() {
		return replacement;
	}

	/**
	 * @param replacement the replacement to set
	 */
	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

	/**
	 * @return the probability
	 */
	public double getProbability() {
		return probability;
	}

	/**
	 * @param probability the probability to set
	 */
	public void setProbability(double probability) {
		this.probability = probability;
	}
	
	
}
