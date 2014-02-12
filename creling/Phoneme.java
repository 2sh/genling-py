package creling;

public class Phoneme
{

	private String grapheme;
	private int weight;

	/**
	 * 
	 * @param grapheme
	 *            the grapheme of the phoneme.
	 * @param weight
	 *            Probability of phoneme being chosen over other phonemes. A
	 *            larger value equals a higher probability.
	 */
	public Phoneme(String grapheme, int weight)
	{
		super();
		this.grapheme = grapheme;
		this.weight = weight;
	}

	/**
	 * 
	 * @param grapheme
	 *            the grapheme of the phoneme.
	 */
	public Phoneme(String grapheme)
	{
		super();
		this.grapheme = grapheme;
		this.weight = 1;
	}

	/**
	 * @return the weight
	 */
	public int getWeight()
	{
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	/**
	 * @return the grapheme
	 */
	public String getGrapheme()
	{
		return grapheme;
	}

	/**
	 * @param grapheme
	 *            the grapheme to set
	 */
	public void setGrapheme(String grapheme)
	{
		this.grapheme = grapheme;
	}

	@Override
	public String toString()
	{
		return grapheme;
	}

}
