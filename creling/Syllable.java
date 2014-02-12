package creling;

import java.util.ArrayList;

public class Syllable
{
	private ArrayList<Segment> segments;
	private int position;
	
	private String leftBracket = "";
	private String rightBracket = "";

	public Syllable(ArrayList<Segment> segments, int position)
	{
		this.segments = segments;
		this.position = position;
	}
	
	public Syllable(ArrayList<Segment> segments, int position, String leftBracket, String rightBracket)
	{
		this.segments = segments;
		this.position = position;
		this.leftBracket = leftBracket;
		this.rightBracket = rightBracket;
	}

	public String create()
	{
		StringBuilder syllableConstruct = new StringBuilder().append(leftBracket);

		for (Segment segment : segments)
		{
			if (Math.random() > segment.getProbability()) continue;

			syllableConstruct.append(segment.create());
		}
		return syllableConstruct.append(rightBracket).toString();
	}

	public int getPosition()
	{
		return position;
	}
	
	public void setBrackets(String leftBracket, String rightBracket)
	{
		this.leftBracket = leftBracket;
		this.rightBracket = rightBracket;
	}
}