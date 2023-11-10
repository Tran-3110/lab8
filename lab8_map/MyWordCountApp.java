package lab8_map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class MyWordCountApp {
	// public static final String fileName = "data/hamlet.txt";
	public static final String fileName = "src/data/fit.txt";
	// <word, its occurences>
	private Map<String, Integer> map = new HashMap<String, Integer>();
	// Load data from fileName into the above map (containing <word, its occurences>)
	// using the guide given in TestReadFile.java
	public void loadData() {
		try {
			Scanner input = new Scanner(new File(fileName));
			while(input.hasNext()) {
				String word = input.next();
				map.put(word, map.getOrDefault(word, 0) +1);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	// Returns the number of unique tokens in the file data/hamlet.txt or fit.txt
	public int countDistinct() {
		return map.size();
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt)
	// In this method, we do not consider the order of tokens.
	public void printWordCounts() throws FileNotFoundException {
		Set<String> set = map.keySet();
		for (String string : set) {
				System.out.println(string+"-"+map.get(string));
		}
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according to ascending order of tokens
	// Example: An - 3, Bug - 10, ...
	public void printWordCountsAlphabet() {
		Map<String, Integer> treeMap = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String k1, String k2) {
				return k1.compareTo(k2);
			}
		});
		treeMap.putAll(map);
		System.out.println(treeMap);
	}
	
	public static void main(String[] args) {
		MyWordCountApp mw = new MyWordCountApp();
		mw.loadData();
		System.out.println(mw.countDistinct());
		try {
			mw.printWordCounts();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mw.printWordCountsAlphabet();
	}
}
