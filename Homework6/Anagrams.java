package Homework6;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	final Integer[] primes;
	Map<Character, Integer> letterTable;
	
	Map<Long, ArrayList<String>> anagramTable = new HashMap<Long, ArrayList<String>>();

	// Methods Below
	private void processFile(String s) throws IOException { 
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}

	public Anagrams() {
		primes = new Integer[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
				89, 97, 101 };
		buildLetterTable();
	}

	
	private void buildLetterTable() {
		Character[] alphabets = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		letterTable = new HashMap<Character, Integer>();
		for (int i = 0; i < primes.length; i++) {
			letterTable.put(alphabets[i], primes[i]);
		}
	}

	private long myHashCode(String s) {
		long product = 1;
		for (int i = 0; i < s.length(); i++) {
			long p = letterTable.get(s.charAt(i));
			product = product * p;
		}
		return product;
	}

	private void addWord(String s) {

		long newhash = myHashCode(s);
		ArrayList<String> temp;
		if (anagramTable.containsKey(newhash))
			temp = anagramTable.get(newhash);
		else
			temp = new ArrayList<String>();
		temp.add(s);
		anagramTable.put(newhash, temp);
	}

	
	@SuppressWarnings("unchecked")
	private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {

		int max = 0;

		@SuppressWarnings("rawtypes")
		ArrayList<Map.Entry<Long, ArrayList<String>>> res = new ArrayList();
		for (Map.Entry<Long, ArrayList<String>> e : anagramTable.entrySet()) {

			int size = e.getValue().size();
			if (size > max) {
				max = size;

			}
		}

		for (Map.Entry<Long, ArrayList<String>> e : anagramTable.entrySet()) {
			int size = e.getValue().size();
			if (size == max) {
				res.add(e);

			}
		}
		return res;
	}

	public static void main(String[] args) { 
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();
		try {
			
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime / 1000000000);
		System.out.println(" Elapsed Time : " + seconds);
		System.out.println(" Key of max anagrams : " + maxEntries.get(0).getKey());
		System.out.println(" List of max anagrams : " + maxEntries.get(0).getValue());
		System.out.println(" Length of List of max anagrams : " + maxEntries.get(0).getValue().size());
	}

}
