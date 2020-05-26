import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ArrayExamplesTest extends ArrayExamples {
	
	private static int maxArrayCount;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 // metoda zostala uzyta tylko raz, aby sprawdzic limity		
		System.out.println("Konfigurowanie testu");
		int i = 1;
		try {
			while(true) {
				int [] array = new int[i];
				i+=1;
				array = null;
			}
		} catch (OutOfMemoryError e ) {
			i-=1;
			maxArrayCount = i;
			
			System.out.println("Maksymalna dlugosc to " + (i));
		}
	
	}


	
	private int [] read(String fileName) {
		String path = "./resources/";
		File pliczek = new File(path + fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path + fileName));
			String line = reader.readLine();
			
			int countOfNumbers = 1;
			
			while (line != null) {
				line = reader.readLine();
				if(line == null)break;
				countOfNumbers++;
			}
			reader.close();
			
			System.out.println("Dlugosc pliku: " + countOfNumbers +"\nDlugosc arrayMax: " + maxArrayCount);
			
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
				System.out.println("Dlugosc tablicy zwracanej przy pliku mniejszym niz maks: " + tab.length);
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
				System.out.println("Dlugosc tablicy zwracanej przy pliku wiekszym niz maks: " + tab.length);
				return tab;
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private int oczekiwanyWynikInt(String file) {
		switch (file) {
		case "Marcin.txt":
		case "PosortowanyMarcin.txt":
		case "PosortowanyMalejacoMarcin.txt":
			return -2147476739;
		case "Wspolne.txt":
		case "PosortowanyWspolne.txt":
		case "PosortowanyMalejacoWspolne.txt":
			return -2147010079;
		case "JedenElement.txt":
			return -2147383980;
		case "Grzegorz.txt":
		case "PosortowanyGrzegorz.txt":
		case "PosortowanyMalejacoGrzegorz.txt":
			return -2147480970;
		default:
			return 0;
		}
	}
	
	private String oczekiwanyWynikString(String file) {
		switch (file) {
		case "Marcin.txt":
		case "PosortowanyMarcin.txt":
		case "PosortowanyMalejacoMarcin.txt":
			return "PosortowanyMarcin.txt";
		case "Wspolne.txt":
		case "PosortowanyWspolne.txt":
		case "PosortowanyMalejacoWspolne.txt":
			return "PosortowanyWspolne.txt";
		case "JedenElement.txt":
			return "JedenElement.txt";
		case "Grzegorz.txt":
		case "PosortowanyGrzegorz.txt":
		case "PosortowanyMalejacoGrzegorz.txt":
			return "PosortowanyGrzegorz.txt";
		default:
			return "brakpliku.txt";
		}
	}
	
	private Boolean oczekiwanyWynikBoolean(String file) {
		switch (file) {
		case "PosortowanyWspolne.txt":
		case "PosortowanyMarcin.txt":
		case "PosortowanyGrzegorz.txt":
		case "JedenElement.txt":
			return true;
		default:
			return false;
		}
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Grzegorz.txt","Marcin.txt","Wspolne.txt","PosortowanyGrzegorz.txt","PosortowanyMarcin.txt"
			,"PosortowanyWspolne.txt","PosortowanyMalejacoGrzegorz.txt","PosortowanyMalejacoMarcin.txt", "PosortowanyMalejacoWspolne.txt",
			"ZeroElement.txt","JedenElement.txt"})
	void testFindMin(String plik) {
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = read(plik);	
		System.out.print("Dlugosc tablicy testowanej: " + testowaTablica.length);
		int wynikIndex = test.findMin(testowaTablica);
		int oczekiwana = oczekiwanyWynikInt(plik); 
		int wynik = testowaTablica[wynikIndex];
		assertEquals(oczekiwana, wynik);
		
	}

	@Test
	void testBadResize() {
		fail("Not yet implemented");
	}

	@Test
	void testGoodResize() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAndPrintPairs() {
		fail("Not yet implemented");
	}

	@ParameterizedTest
	@ValueSource(strings = {"Grzegorz.txt","Marcin.txt","Wspolne.txt","PosortowanyGrzegorz.txt","PosortowanyMarcin.txt"
			,"PosortowanyWspolne.txt","PosortowanyMalejacoGrzegorz.txt","PosortowanyMalejacoMarcin.txt", "PosortowanyMalejacoWspolne.txt",
			"ZeroElement.txt","JedenElement.txt"})
	void testBubblesort(String file) {
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = read(file);	
		String PosortowanaGotowa = oczekiwanyWynikString(file);
		int [] testowaTablicaPosortowana = read(PosortowanaGotowa);	
		test.bubblesort(testowaTablica);
		assertEquals(testowaTablica, testowaTablicaPosortowana);
	}

	@ParameterizedTest
	@ValueSource(strings = {"Grzegorz.txt","Marcin.txt","Wspolne.txt","PosortowanyGrzegorz.txt","PosortowanyMarcin.txt"
			,"PosortowanyWspolne.txt","PosortowanyMalejacoGrzegorz.txt","PosortowanyMalejacoMarcin.txt", "PosortowanyMalejacoWspolne.txt",
			"ZeroElement.txt","JedenElement.txt"})
	void testShowList(String file) {
		ArrayExamples test = new ArrayExamples();
//		int [] testowaTablica = read(file);	
		int [] testowaTablica = new ReadArrayFromFile(file).read(maxArrayCount);	
		test.showList(testowaTablica);
	}

	@ParameterizedTest
	@ValueSource(strings = {"Grzegorz.txt","Marcin.txt","Wspolne.txt","PosortowanyGrzegorz.txt","PosortowanyMarcin.txt"
			,"PosortowanyWspolne.txt","PosortowanyMalejacoGrzegorz.txt","PosortowanyMalejacoMarcin.txt", "PosortowanyMalejacoWspolne.txt",
			"ZeroElement.txt","JedenElement.txt"})
	void testIsAscending(String file) {
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).read(maxArrayCount);
		Boolean actual = test.isAscending(testowaTablica);
		Boolean expected = oczekiwanyWynikBoolean(file);
		assertEquals(expected, actual);
		
	}

}
