package genling;

import java.util.regex.Pattern;
import java.lang.Math;

public class FilterRule
{

	private String regex;
	private Pattern pattern;
	private double probability = 1.0;
	private boolean deny = true;

	public FilterRule(String regex, double probability, boolean deny)
	{
		setRegex(regex);
		this.probability = probability;
		this.deny = deny;
	}

	public FilterRule(String regex, double probability)
	{
		setRegex(regex);
		this.probability = probability;
	}

	public FilterRule(String regex)
	{
		setRegex(regex);
	}

	public boolean check(String stemString)
	{
		if (Math.random() > probability) return false;

		if (pattern.matcher(stemString).find()) return deny;
		return !deny;
	}

	/**
	 * @return the regex
	 */
	public String getRegex()
	{
		return regex;
	}

	/**
	 * @param regex
	 *            the regex to set
	 */
	public void setRegex(String regex)
	{
		this.regex = regex;
		this.pattern = Pattern.compile(regex);
	}

	/**
	 * @return the probability
	 */
	public double getProbability()
	{
		return probability;
	}

	/**
	 * @param probability
	 *            the probability to set
	 */
	public void setProbability(double probability)
	{
		this.probability = probability;
	}

}
