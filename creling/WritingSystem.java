package creling;

import java.util.ArrayList;

public class WritingSystem
{
	private ArrayList<ReplaceRule> replaceRules = new ArrayList<ReplaceRule>();

	public WritingSystem(ArrayList<ReplaceRule> replaceRules)
	{
		super();
		this.replaceRules = replaceRules;
	}
	
	public String transliterate(String stemString)
	{
		for (ReplaceRule replaceRule : replaceRules)
		{
			stemString = replaceRule.replace(stemString);
		}
		
		return stemString;
	}
}
