import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

class Node {
	private String key;
	private String weight;

	public Node (String key, String weight) {
		this.key = key;
		this.weight = weight;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
}

public class Main {
	final static String input = "C:\\Users\\butam\\workspace\\MapReduce\\in.txt";
	final static String output = "C:\\Users\\butam\\workspace\\MapReduce\\out.txt";
	final static String locationMap = "C:\\Users\\butam\\OneDrive\\Документы\\Visual Studio 2015\\Projects\\MapScript\\Debug\\MapScript.exe";
	final static String locationDeruce = "F:\\УП c++\\reduce\\Debug\\";	
	static void reduce(ArrayList<Node> store) {
		String[] words = null;
		long sum = 0;
		for(int i = 0; i < store.size(); i++) {
			words = store.get(i).getWeight().split(" ");
			for (int j = 0; j < words.length; j++) {
				sum += Integer.parseInt(words[j]);
			}
			store.get(i).setWeight(Long.toString(sum));
			sum = 0;
		}
	}
	public static void main(String[] args) throws IOException{
		ProcessBuilder bd = new ProcessBuilder(locationMap);
		bd.redirectInput(new File (input));
		bd.redirectOutput(new File(output));
        Process process = bd.start();
        try { 
        	process.waitFor(); 
        	} catch (InterruptedException e) { 
        	// TODO Auto-generated catch block 
        	e.printStackTrace(); 
        	}
		ArrayList<Node> store = new ArrayList<>();
		String[] words = null;
		String str;
		File in = new File(output);
		try{
			BufferedReader reader = new BufferedReader(new FileReader(in.getAbsoluteFile()));
			while ((str = reader.readLine()) != null) {
				words = str.split("\t");
				store.add(new Node(words[0], words[1]));
			}
			reader.close();
		} catch(IOException e) {System.out.print("Error"); }
		store.sort(new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		ArrayList<Node> store2 = new ArrayList<>();
		boolean word = true;
		int k = 0;
		for(int i = 0; i < store.size(); i++) {
			if (word == true) {
				store2.add(new Node(store.get(i).getKey(), "1"));
				word = false;
			}
			if (i != store.size() - 1 && store.get(i).getKey().equals(store.get(i + 1).getKey())) {
				store2.get(k).setWeight(store2.get(k).getWeight() + " 1");
			} else {
				k++;
				word = true;
			}
		}
		File out = new File(output);
		try {
			PrintWriter writer = new PrintWriter(out.getAbsoluteFile());
			for (int i = 0; i < store2.size(); i++) {
			    writer.println(store2.get(i).getKey() + " " + store2.get(i).getWeight());
			}
			writer.close();
		} catch (FileNotFoundException e) { }
	}
}