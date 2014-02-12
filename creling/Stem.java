package creling;

import java.util.ArrayList;

import misc.Random;

public class Stem
{
	private ArrayList<Syllable> syllables;
	private int[] syllableBalance;
	private ArrayList<FilterRule> filterRules;
	
	private String leftBracket = "";
	private String rightBracket = "";

	public Stem(ArrayList<Syllable> syllables, int[] syllableBalance, ArrayList<FilterRule> filterRules)
	{
		this.syllables = syllables;
		this.syllableBalance = syllableBalance;
		this.filterRules = filterRules;
	}

	public Stem(ArrayList<Syllable> syllables, int[] syllableBalance)
	{
		this.syllables = syllables;
		this.syllableBalance = syllableBalance;
		filterRules = new ArrayList<FilterRule>();
	}

	public String create()
	{
		StringBuilder stemConstruct;
		int syllableAmount = Random.weightedChoice(syllableBalance) + 1;
		boolean isAllowed;

		String stemString;

		int breakCount = 0;
		do
		{
			isAllowed = true;
			stemConstruct = new StringBuilder().append(leftBracket);

			for (int i = 0; i < syllableAmount; i++)
			{
				for (Syllable syllable : syllables)
				{
					if (syllable.getPosition() == 0 ||
						syllable.getPosition() == i + 1 ||
						syllable.getPosition() == i - syllableAmount)
					{
						stemConstruct.append(syllable.create());
						break;
					}
				}
			}

			stemString = stemConstruct.append(rightBracket).toString();

			for (FilterRule filterRule : filterRules)
			{
				if (filterRule.check(stemString))
				{
					isAllowed = false;
					break;
				}
			}
			breakCount++;
		}
		while (!isAllowed || breakCount >= 100);

		return stemString;
	}

	public void setFilterRules(ArrayList<FilterRule> filterRules)
	{
		this.filterRules = filterRules;
	}
	
	public void setBrackets(String leftBracket, String rightBracket)
	{
		this.leftBracket = leftBracket;
		this.rightBracket = rightBracket;
	}
}
