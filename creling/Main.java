package creling;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main
{
	private int wordsToGenerate = 50003;
	private Stem stem;
	private ConcurrentLinkedQueue<String> stems = new ConcurrentLinkedQueue<String>();
	private int threadAmount;
	private boolean endGeneration = false;
	
	ArrayList<WritingSystem> writingSystems = new ArrayList<WritingSystem>();
	
	public Main()
	{
		ArrayList<Syllable> syllables = new ArrayList<Syllable>();
		
		ArrayList<Segment> segments = new ArrayList<Segment>();
		
		ArrayList<Phoneme> phonemes = new ArrayList<Phoneme>();
		phonemes.add(new Phoneme("k", 5));
		phonemes.add(new Phoneme("s", 4));
		phonemes.add(new Phoneme("t", 4));
		phonemes.add(new Phoneme("n", 4));
		phonemes.add(new Phoneme("h", 3));
		phonemes.add(new Phoneme("m", 1));
		phonemes.add(new Phoneme("y", 1));
		phonemes.add(new Phoneme("r", 1));
		phonemes.add(new Phoneme("w", 1));
		phonemes.add(new Phoneme("g", 2));
		phonemes.add(new Phoneme("z", 2));
		phonemes.add(new Phoneme("d", 1));
		phonemes.add(new Phoneme("b", 2));
		phonemes.add(new Phoneme("p", 2));
		segments.add(new Segment(phonemes, 0.8));
		
		phonemes = new ArrayList<Phoneme>();
		phonemes.add(new Phoneme("Y", 1));
		segments.add(new Segment(phonemes, 0.005));
		
		phonemes = new ArrayList<Phoneme>();
		phonemes.add(new Phoneme("a", 5));
		phonemes.add(new Phoneme("i", 4));
		phonemes.add(new Phoneme("u", 4));
		phonemes.add(new Phoneme("e", 3));
		phonemes.add(new Phoneme("o", 3));
		segments.add(new Segment(phonemes, 1.0));
		
		phonemes = new ArrayList<Phoneme>();
		phonemes.add(new Phoneme("N", 8));
		phonemes.add(new Phoneme("x", 1));
		segments.add(new Segment(phonemes, 0.1));
		
		syllables.add(new Syllable(segments, 0, "<", ">"));

		
		ArrayList<FilterRule> filterRules = new ArrayList<FilterRule>();
		
		filterRules.add(new FilterRule("[yY][Yie]"));
		filterRules.add(new FilterRule("<w[Yiueo]"));
		filterRules.add(new FilterRule("x>$"));
		filterRules.add(new FilterRule("N><.Y", 0.9));
		filterRules.add(new FilterRule("x><.Y"));
		filterRules.add(new FilterRule("<Y"));
		filterRules.add(new FilterRule("x><[^kstpc]"));
		
		filterRules.add(new FilterRule("mYu", 0.95));
		filterRules.add(new FilterRule("di", 0.9));
		filterRules.add(new FilterRule("du", 0.9));
		filterRules.add(new FilterRule("^<d[ui]"));
		ArrayList<ReplaceRule> replaceRules;
		
		replaceRules = new ArrayList<ReplaceRule>();
		writingSystems.add(new WritingSystem(replaceRules));
		
		replaceRules = new ArrayList<ReplaceRule>();
		replaceRules.add(new ReplaceRule("si", "shi"));
		replaceRules.add(new ReplaceRule("sY", "sh"));
		replaceRules.add(new ReplaceRule("ti", "chi"));
		replaceRules.add(new ReplaceRule("hu", "fu"));
		replaceRules.add(new ReplaceRule("tY", "ch"));
		replaceRules.add(new ReplaceRule("zi", "ji"));
		replaceRules.add(new ReplaceRule("zY", "j"));
		replaceRules.add(new ReplaceRule("d([iu])", "j$1"));
		replaceRules.add(new ReplaceRule("N><([aiueo])", "n'><$1"));
		replaceRules.add(new ReplaceRule("x><ch", "t><ch"));
		replaceRules.add(new ReplaceRule("x><(.)(.?)", "$1><$1$2"));
		replaceRules.add(new ReplaceRule("Y", "y"));
		replaceRules.add(new ReplaceRule("N", "n"));
		
		replaceRules.add(new ReplaceRule("a><a", "ā"));
		replaceRules.add(new ReplaceRule("u><u", "ū"));
		replaceRules.add(new ReplaceRule("e><e", "ē"));
		replaceRules.add(new ReplaceRule("o><[ou]", "ō"));
		
		replaceRules.add(new ReplaceRule("[<>]", ""));
		writingSystems.add(new WritingSystem(replaceRules));
		
		replaceRules = new ArrayList<ReplaceRule>();
		replaceRules.add(new ReplaceRule("<ya", "や"));
		replaceRules.add(new ReplaceRule("<yu", "ゆ"));
		replaceRules.add(new ReplaceRule("<yo", "よ"));
		replaceRules.add(new ReplaceRule("Ya", "iゃ"));
		replaceRules.add(new ReplaceRule("Yu", "iゅ"));
		replaceRules.add(new ReplaceRule("Yo", "iょ"));
		
		replaceRules.add(new ReplaceRule("<ka", "か"));
		replaceRules.add(new ReplaceRule("<ki", "き"));
		replaceRules.add(new ReplaceRule("<ku", "く"));
		replaceRules.add(new ReplaceRule("<ke", "け"));
		replaceRules.add(new ReplaceRule("<ko", "こ"));
		
		replaceRules.add(new ReplaceRule("<sa", "さ"));
		replaceRules.add(new ReplaceRule("<si", "し"));
		replaceRules.add(new ReplaceRule("<su", "す"));
		replaceRules.add(new ReplaceRule("<se", "せ"));
		replaceRules.add(new ReplaceRule("<so", "そ"));
		
		replaceRules.add(new ReplaceRule("<ta", "た"));
		replaceRules.add(new ReplaceRule("<ti", "ち"));
		replaceRules.add(new ReplaceRule("<tu", "つ"));
		replaceRules.add(new ReplaceRule("<te", "て"));
		replaceRules.add(new ReplaceRule("<to", "と"));
		
		replaceRules.add(new ReplaceRule("<na", "な"));
		replaceRules.add(new ReplaceRule("<ni", "に"));
		replaceRules.add(new ReplaceRule("<nu", "ぬ"));
		replaceRules.add(new ReplaceRule("<ne", "ね"));
		replaceRules.add(new ReplaceRule("<no", "の"));
		
		replaceRules.add(new ReplaceRule("<ha", "は"));
		replaceRules.add(new ReplaceRule("<hi", "ひ"));
		replaceRules.add(new ReplaceRule("<hu", "ふ"));
		replaceRules.add(new ReplaceRule("<he", "へ"));
		replaceRules.add(new ReplaceRule("<ho", "ほ"));
		
		replaceRules.add(new ReplaceRule("<ma", "ま"));
		replaceRules.add(new ReplaceRule("<mi", "み"));
		replaceRules.add(new ReplaceRule("<mu", "む"));
		replaceRules.add(new ReplaceRule("<me", "め"));
		replaceRules.add(new ReplaceRule("<mo", "も"));
		
		replaceRules.add(new ReplaceRule("<ra", "ら"));
		replaceRules.add(new ReplaceRule("<ri", "り"));
		replaceRules.add(new ReplaceRule("<ru", "る"));
		replaceRules.add(new ReplaceRule("<re", "れ"));
		replaceRules.add(new ReplaceRule("<ro", "ろ"));
		
		replaceRules.add(new ReplaceRule("<wa", "わ"));
		replaceRules.add(new ReplaceRule("<wo", "を"));
		replaceRules.add(new ReplaceRule("N>", "ん"));
		replaceRules.add(new ReplaceRule("x>", "っ"));
		
		replaceRules.add(new ReplaceRule("<ga", "が"));
		replaceRules.add(new ReplaceRule("<gi", "ぎ"));
		replaceRules.add(new ReplaceRule("<gu", "ぐ"));
		replaceRules.add(new ReplaceRule("<ge", "げ"));
		replaceRules.add(new ReplaceRule("<go", "ご"));
		
		replaceRules.add(new ReplaceRule("<za", "ざ"));
		replaceRules.add(new ReplaceRule("<zi", "じ"));
		replaceRules.add(new ReplaceRule("<zu", "ず"));
		replaceRules.add(new ReplaceRule("<ze", "ぜ"));
		replaceRules.add(new ReplaceRule("<zo", "ぞ"));
		
		replaceRules.add(new ReplaceRule("<da", "だ"));
		replaceRules.add(new ReplaceRule("<di", "ぢ"));
		replaceRules.add(new ReplaceRule("<du", "づ"));
		replaceRules.add(new ReplaceRule("<de", "で"));
		replaceRules.add(new ReplaceRule("<do", "ど"));
		
		replaceRules.add(new ReplaceRule("<ba", "ば"));
		replaceRules.add(new ReplaceRule("<bi", "び"));
		replaceRules.add(new ReplaceRule("<bu", "ぶ"));
		replaceRules.add(new ReplaceRule("<be", "べ"));
		replaceRules.add(new ReplaceRule("<bo", "ぼ"));
		
		replaceRules.add(new ReplaceRule("<pa", "ぱ"));
		replaceRules.add(new ReplaceRule("<pi", "ぴ"));
		replaceRules.add(new ReplaceRule("<pu", "ぷ"));
		replaceRules.add(new ReplaceRule("<pe", "ぺ"));
		replaceRules.add(new ReplaceRule("<po", "ぽ"));
		
		replaceRules.add(new ReplaceRule("<a", "あ"));
		replaceRules.add(new ReplaceRule("<i", "い"));
		replaceRules.add(new ReplaceRule("<u", "う"));
		replaceRules.add(new ReplaceRule("<e", "え"));
		replaceRules.add(new ReplaceRule("<o", "お"));
		
		replaceRules.add(new ReplaceRule("[<>]", ""));
		writingSystems.add(new WritingSystem(replaceRules));
		
		replaceRules = new ArrayList<ReplaceRule>();
		replaceRules.add(new ReplaceRule("N><([aiueo])", "n'><$1"));
		replaceRules.add(new ReplaceRule("x><(.)(.?)", "$1><$1$2"));
		replaceRules.add(new ReplaceRule("Y", "y"));
		replaceRules.add(new ReplaceRule("N", "n"));
		
		replaceRules.add(new ReplaceRule("[<>]", ""));
		writingSystems.add(new WritingSystem(replaceRules));
		
		int[] syllableBalance = {2,6,4,2,1};
		stem = new Stem(syllables, syllableBalance, filterRules);
		
		threadAmount = Runtime.getRuntime().availableProcessors() * 2;
		
		Thread[] threads = new Thread[threadAmount];
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < threadAmount; i++)
		{
			threads[i] = new Thread(new Generator(i));
			threads[i].start();
		}
		
		new Thread(new Printer()).start();
		
		try
		{
			for (int i = 0; i < threadAmount; i++)
			{
				threads[i].join();
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		endGeneration = true;
		
		long elapsedTime = System.currentTimeMillis() - start;
		
		System.out.println("\nElapsed Time: " + Double.toString(elapsedTime / 1000D));
		System.out.println("Number of raw stems: " + Integer.toString(stems.size()));
	}
	
	private class Generator extends Thread
	{
		private int id;
		
		public Generator(int id)
		{
			this.id = id;
		}
		
		public void run()
		{
			int loopAmount = wordsToGenerate / threadAmount;
			if (id == 0) loopAmount += wordsToGenerate % threadAmount;
			System.out.println(loopAmount);
			
			for (int i = 0; i < loopAmount; i++)
			{
				stems.add(stem.create());
			}
		}
	}
	
	@SuppressWarnings("unused")
	private class Printer extends Thread
	{
		public void run()
		{
			String stemString;
			int i = 0;
			while (true)
			{
				stemString = stems.poll();
				if (stemString != null)
				{
//					System.out.print(stemString + " ");
					System.out.print(writingSystems.get(3).transliterate(stemString) + " ");
					i++;
					
					if(i % 20 == 0)
					{
						System.out.print("\n");
					}
				}
				else if (endGeneration)
				{
					break;
				}
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		new Main();
	}
}
