import java.io.File;
import java.io.IOException;

public class MapReduce {
	final static String input = "C:\\Users\\butam\\workspace\\MapReduce\\in.txt";
	final static String output = "C:\\Users\\butam\\workspace\\MapReduce\\out.txt";
	final static String locationMap = "C:\\Users\\butam\\OneDrive\\Документы\\Visual Studio 2015\\Projects\\MapScript\\Debug\\MapScript.exe";
	final static String locationDeruce = "F:\\УП c++\\reduce\\Debug\\";
	public static void main(String[] args) throws IOException {
		ProcessBuilder bd = new ProcessBuilder(locationMap);
		bd.redirectInput(new File (input));
		bd.redirectOutput(new File(output));
        Process process = bd.start();
//        InputStream is = process.getInputStream();
//        InputStreamReader isr = new InputStreamReader(is);
//        BufferedReader br = new BufferedReader(isr);
//        String line;
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
    }

}
