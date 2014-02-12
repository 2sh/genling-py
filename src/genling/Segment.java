package genling;

import java.util.ArrayList;

import misc.Random;

public class Segment
{

	private double probability;
	private ArrayList<Phoneme> phonemes = new ArrayList<Phoneme>();
	
	private String leftBracket = "";
	private String rightBracket = "";

	public Segment(ArrayList<Phoneme> phonemes)
	{
		this.phonemes = phonemes;
		this.probability = 1.0;
	}
	
	public Segment(ArrayList<Phoneme> phonemes, String leftBracket, String rightBracket)
	{
		this.phonemes = phonemes;
		this.probability = 1.0;
		this.leftBracket = leftBracket;
		this.rightBracket = rightBracket;
	}
	
	public Segment(ArrayList<Phoneme> phonemes, double probability)
	{
		this.phonemes = phonemes;
		this.probability = probability;
	}
	
	public Segment(ArrayList<Phoneme> phonemes, double probability, String leftBracket, String rightBracket)
	{
		this.phonemes = phonemes;
		this.probability = probability;
		this.leftBracket = leftBracket;
		this.rightBracket = rightBracket;
	}

	public String create()
	{
		int[] weights = new int[phonemes.size()];

		for (int i = 0; i < phonemes.size(); i++)
		{
			weights[i] = phonemes.get(i).getWeight();
		}

		return leftBracket + phonemes.get(Random.weightedChoice(weights)).getGrapheme() + rightBracket;
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
	
	public void setBrackets(String leftBracket, String rightBracket)
	{
		this.leftBracket = leftBracket;
		this.rightBracket = rightBracket;
	}
}
