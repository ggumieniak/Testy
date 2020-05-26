import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadArrayFromFile {

	String fileName;
	
	
	
	public ReadArrayFromFile(String fileName) {
		this.fileName = fileName;
	}


	public int [] readAndLimitMaxSizeBy(int maxArrayCount) {
		String path = "./resources/";
		File pliczek = new File(path + fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path + fileName));
			String line = reader.readLine();
			if (line == null) {
				return new int[0];
			}
			
			int countOfNumbers = 1;
			
			while (line != null) {
				line = reader.readLine();
				if(line == null)break;
				countOfNumbers++;
			}
			reader.close();
			
//			System.out.println("Dlugosc pliku: " + countOfNumbers +"\nDlugosc arrayMax: " + maxArrayCount);
			
			if (countOfNumbers < maxArrayCount) {
				reader = new BufferedReader(new FileReader(path + fileName));
				int tab [] = new int[countOfNumbers];
				line = reader.readLine();
				tab[0]= Integer.parseInt(line);
				int i =1;
				
				while (line != null) {
					line = reader.readLine();
					if(line == null)break;
					tab[i] = Integer.parseInt(line);
					i++;
				}
				reader.close();
//				System.out.println("Dlugosc tablicy zwracanej przy pliku mniejszym niz maks: " + tab.length);
				return tab;
			} else {
				reader = new BufferedReader(new FileReader(path + fileName));
				int tab [] = new int[maxArrayCount];
				line = reader.readLine();
				tab[0]= Integer.parseInt(line);
				int i =1;
				
				while (i < maxArrayCount) {
					line = reader.readLine();
					tab[i] = Integer.parseInt(line);
					i++;
				}
				reader.close();
//				System.out.println("Dlugosc tablicy zwracanej przy pliku wiekszym niz maks: " + tab.length);
				return tab;
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
