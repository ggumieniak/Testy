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
		case "Jeden.txt":
			return 1;
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
		case "Jeden.txt":
			return "Jeden.txt";
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
		case "Jeden.txt":
			return true;
		default:
			return false;
		}
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Grzegorz.txt","Marcin.txt","Wspolne.txt","PosortowanyGrzegorz.txt","PosortowanyMarcin.txt"
			,"PosortowanyWspolne.txt","PosortowanyMalejacoGrzegorz.txt","PosortowanyMalejacoMarcin.txt", "PosortowanyMalejacoWspolne.txt",
			"ZeroElement.txt","JedenElement.txt","Jeden.txt"})
	void testFindMin(String file) {
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);
		System.out.println("Dlugosc tablicy testowanej: " + testowaTablica.length);
		int wynikIndex = test.findMin(testowaTablica);
		int oczekiwana = oczekiwanyWynikInt(file); 
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
			"ZeroElement.txt","JedenElement.txt","Jeden.txt"})
	void testBubblesort(String file) {
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);
		String PosortowanaGotowa = oczekiwanyWynikString(file);
		int [] testowaTablicaPosortowana = new ReadArrayFromFile(PosortowanaGotowa).readAndLimitMaxSizeBy(maxArrayCount);	
		test.bubblesort(testowaTablica);
		assertEquals(testowaTablica, testowaTablicaPosortowana);
	}

	@ParameterizedTest
	@ValueSource(strings = {"Grzegorz.txt","Marcin.txt","Wspolne.txt","PosortowanyGrzegorz.txt","PosortowanyMarcin.txt"
			,"PosortowanyWspolne.txt","PosortowanyMalejacoGrzegorz.txt","PosortowanyMalejacoMarcin.txt", "PosortowanyMalejacoWspolne.txt",
			"ZeroElement.txt","JedenElement.txt","Jeden.txt"})
	void testShowList(String file) {
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);	
		test.showList(testowaTablica);
	}

	@ParameterizedTest
	@ValueSource(strings = {"Grzegorz.txt","Marcin.txt","Wspolne.txt","PosortowanyGrzegorz.txt","PosortowanyMarcin.txt"
			,"PosortowanyWspolne.txt","PosortowanyMalejacoGrzegorz.txt","PosortowanyMalejacoMarcin.txt", "PosortowanyMalejacoWspolne.txt",
			"ZeroElement.txt","JedenElement.txt","Jeden.txt"})
	void testIsAscending(String file) {
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);
		Boolean actual = test.isAscending(testowaTablica);
		if (actual) {
			System.out.println("Tablica w " + file + " jest rosnaca");
		} else {
			System.out.println("Tablica w " + file + " nie jest uporzadkowana");
		}
		Boolean expected = oczekiwanyWynikBoolean(file);
		assertEquals(expected, actual);
		
	}

}
