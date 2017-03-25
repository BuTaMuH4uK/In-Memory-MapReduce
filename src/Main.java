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
	final static String answer = "C:\\Users\\butam\\workspace\\MapReduce\\ans.txt";
	final static String locationMap = "C:\\Users\\butam\\OneDrive\\Документы\\Visual Studio 2015\\Projects\\MapScript\\Debug\\MapScript.exe";
	final static String locationReduce = "C:\\Users\\butam\\OneDrive\\Документы\\Visual Studio 2015\\Projects\\ReduceScript\\Debug\\ReduceScript.exe";
	public static void main(String[] args) throws IOException{
		ProcessBuilder pb_map = new ProcessBuilder(locationMap);
		pb_map.redirectInput(new File (input));
		pb_map.redirectOutput(new File(output));
        Process proc_map = pb_map.start();
        try { 
        	proc_map.waitFor(); 
        } catch (InterruptedException e) {e.printStackTrace();}
		ArrayList<Node> store = new ArrayList<>();
		String[] words;
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
		File out = new File(output);
		try {
			PrintWriter writer = new PrintWriter(out.getAbsoluteFile());
			for (int i = 0; i < store.size(); i++) {
			    writer.println(store.get(i).getKey() + "\t" + store.get(i).getWeight());
			}
			writer.close();
		} catch (FileNotFoundException e) { }
		ProcessBuilder pb_reduce = new ProcessBuilder(locationReduce);
		pb_reduce.redirectInput(new File (output));
		pb_reduce.redirectOutput(new File(answer));
        Process proc_reduce = pb_reduce.start();
        try { 
        	proc_reduce.waitFor(); 
        } catch (InterruptedException e) {e.printStackTrace();}
	}
}