import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadArrayFromFile {

	String fileName;
	
	
	public ReadArrayFromFile(String fileName) {
		this.fileName = fileName;
	}

	public int [] read() {
		String path = "./resources/";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path + fileName));
			String line = reader.readLine();
			int [] tab;
			
			
			int countOfNumbers = 1;
			
			while (line != null) {
				line = reader.readLine();
				if(line == null)break;
				countOfNumbers++;
			}
			reader.close();
			reader = new BufferedReader(new FileReader(path + fileName));
			tab = new int[countOfNumbers];
			int i =0;
			while (line != null) {
				line = reader.readLine();
				tab[i] = Integer.parseInt(reader.readLine());
				if(line == null)break;
				countOfNumbers++;
			}
			reader.close();
			
			return tab;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
