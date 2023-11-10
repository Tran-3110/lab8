package lab8_map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Map.Entry;

public class TextAnalyzer {
	// <word, its positions>
	private Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

	// load words in the text file given by fileName and store into map by using add
	// method in Task 2.1.
	// Using BufferedReader reffered in file TextFileUtils.java
	public void load(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = null;
		int count = 1;
		while (true) {
			line = reader.readLine();

			if (line == null)
				break;
			StringTokenizer tokens = new StringTokenizer(line, " ");

			while (tokens.hasMoreTokens()) {
				String word = tokens.nextToken();
				if (!tokens.hasMoreTokens()) {
					add(word, -count);
				} else {
					add(word, count);
				}
				count++;
			}
		}
		reader.close();
	}

	// In the following method, if the word is not in the map, then adding that word
	// to the map containing the position of the word in the file. If the word is
	// already in the map, then its word position is added to the list of word
	// positions for this word.
	// Remember to negate the word position if the word is at the end of a line in
	// the text file

	public void add(String word, int position) {
		ArrayList<Integer> arrList;
		if (!map.containsKey(word)) {
			arrList = new ArrayList<>();
			arrList.add(position);
			map.put(word, arrList);
		} else {
			map.get(word).add(position);
		}
	}

	// This method should display the words of the text file along with the
	// positions of each word, one word per line, in alphabetical order
	public void displayWords() {
		Map<String, ArrayList<Integer>> treeMap = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		treeMap.putAll(map);
		System.out.println(treeMap);
	}

	// This method will display the content of the text file stored in the map
	public void displayText() {
		int length = 0;
		Set<Map.Entry<String, ArrayList<Integer>>> entry = map.entrySet();
		ArrayList<Integer> indexEnd = new ArrayList<>();
		for (Entry<String, ArrayList<Integer>> entry2 : entry) {
			length += entry2.getValue().size();
		}
		String[] arrWord = new String[length];
		for (Entry<String, ArrayList<Integer>> entry2 : entry) {
			for (Integer integer : entry2.getValue()) {
				if(integer < 0) indexEnd.add(Math.abs(integer)-1);
				arrWord[Math.abs(integer)-1] = entry2.getKey();
			}
		}
		for (int i = 0; i < arrWord.length; i++) {
			if(indexEnd.contains(i)) {
				System.out.print(arrWord[i] + " ");
				System.out.println();
			}
			else {
				System.out.print(arrWord[i] + " ");
			}
		}
	}

	// This method will return the word that occurs most frequently in the text file
	public String mostFrequentWord() {
		int max = 0;
		String result = "";
		Set<Map.Entry<String, ArrayList<Integer>>> entry = map.entrySet();
		for (Entry<String, ArrayList<Integer>> entry2 : entry) {
			if (entry2.getValue().size() > max) {
				max = entry2.getValue().size();
				result = entry2.getKey();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		TextAnalyzer text = new TextAnalyzer();
		try {
			text.load("src/data/short.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(text.map);
		text.displayWords();
		System.out.println(text.mostFrequentWord());
		System.out.println();
		text.displayText();
	}

}
