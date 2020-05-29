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
	final int testSizeZero = 0;
	final int testSizeTen = 10;
	final int testSizeIntegerMax = Integer.MAX_VALUE;
	final int testSizeMinusFive = -5;



	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// metoda zostala uzyta tylko raz, aby sprawdzic limity
		System.out.println("Konfigurowanie testu");
		int max_size = 0;
		int i = 1;
		while(true)
		{
			try {
				while(true) {
					max_size += i;
					System.out.print("Próba dla: " + (max_size));
					int [] array = new int[max_size];
					System.out.println(" - UDANA.");
					i*=2;
					array = null;
				}
			} catch (OutOfMemoryError e ) {
				if(i == 1 ){
					System.out.println(" - NIE UDANA.");
					max_size -= i;
					i=0;
					System.out.println("Maksymalna dlugosc to " + (max_size));
					maxArrayCount = max_size;
				}
				else{
					System.out.println(" - NIE UDANA.");
					max_size -= i;
					i=1;
				}
			}

			if(i==0) break;
		}
	}
	private int oczekiwanyWynikInt(String file) {
		switch (file) {
		case "Wspolne.txt":
		case "PosortowanyWspolne.txt":
		case "PosortowanyMalejacoWspolne.txt":
			return -2147010079;
		case "Milion.txt":
		case "PosortowanyMilion.txt":
		case "PosortowanyMalejacoMilion.txt":
			return -2147469396;
		case "JedenElement.txt":
			return -2147383980;
		case "Jeden.txt":
			return 1;
		default:
			return 0;
		}
	}

	private String oczekiwanyWynikString(String file) {
		switch (file) {
		case "Milion.txt":
		case "PosortowanyMilion.txt":
		case "PosortowanyMalejacoMilion.txt":
			return "PosortowanyMilion.txt";
		case "Wspolne.txt":
		case "PosortowanyWspolne.txt":
		case "PosortowanyMalejacoWspolne.txt":
			return "PosortowanyWspolne.txt";
		case "JedenElement.txt":
			return "JedenElement.txt";
		case "Jeden.txt":
			return "Jeden.txt";
		default:
			return "brakpliku.txt";
		}
	}

	private Boolean oczekiwanyWynikBoolean(String file) {
		switch (file) {
		case "PosortowanyWspolne.txt":
		case "PosortowanyMilion.txt":
		case "JedenElement.txt":
		case "Jeden.txt":
			return true;
		default:
			return false;
		}
	}

	@ParameterizedTest
	@ValueSource(strings = {"Wspolne.txt","PosortowanyWspolne.txt","PosortowanyMalejacoWspolne.txt", "Milion.txt", "PosortowanyMilion.txt", "PosortowanyMalejacoMilion.txt",
			"JedenElement.txt","Jeden.txt"})
	void testFindMin(String file) {
		System.out.println("testFindMin dla " + file);
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);
		int wynikIndex = test.findMin(testowaTablica);
		int oczekiwana = oczekiwanyWynikInt(file); 
		int wynik = testowaTablica[wynikIndex];
		assertEquals(oczekiwana, wynik);

	}

	@ParameterizedTest
	@ValueSource(strings = {"Milion.txt","Wspolne.txt","Jeden.txt"})
	void testBadResizeForArgumentEqualsZero(String file) {
		System.out.println("testBadResizeForArgumentEqualsZero dla " + file);
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);
		test.badResize(testowaTablica, testSizeZero);
		int actual = testowaTablica.length;
		int expected = testSizeZero;
		assertNotEquals(expected, actual);
	}
	@ParameterizedTest
	@ValueSource(strings = {"Milion.txt","Wspolne.txt","Jeden.txt"})
	void testBadResizeForArgumentEqualsTen(String file) {
		System.out.println("testBadResizeForArgumentEqualsTen dla " + file);
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);
		test.badResize(testowaTablica, testSizeTen);
		int actual = testowaTablica.length;
		int expected = testSizeTen;
		assertNotEquals(expected, actual);
	}
	@ParameterizedTest
	@ValueSource(strings = {"Milion.txt","Wspolne.txt","Jeden.txt"})
	void testBadResizeForArgumentEqualsIntegerMax(String file) {
		System.out.println("testBadResizeForArgumentEqualsIntegerMax dla " + file);
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);
		assertThrows(OutOfMemoryError.class, () -> {
			test.badResize(testowaTablica, testSizeIntegerMax);
		});
	}

	@ParameterizedTest
	@ValueSource(strings = {"Milion.txt","Wspolne.txt","Jeden.txt"})
	void testGoodResizeEqualsZero(String file) {
		System.out.println("testGoodResizeEqualsZero dla " + file);
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);
		int [] zwroconaTablica = test.goodResize(testowaTablica, testSizeZero);
		int actual = zwroconaTablica.length;
		int expected = testSizeZero;
		assertEquals(expected, actual);
	}
	@ParameterizedTest
	@ValueSource(strings = {"Milion.txt","Wspolne.txt","Jeden.txt"})
	void testGoodResizeEqualsTen(String file) {
		System.out.println("testGoodResizeEqualsTen dla " + file);
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);
		int [] zwroconaTablica = test.goodResize(testowaTablica, testSizeTen);
		int actual = zwroconaTablica.length;
		int expected = testSizeTen;
		assertEquals(expected, actual);
	}
	@ParameterizedTest
	@ValueSource(strings = {"Milion.txt","Wspolne.txt","Jeden.txt"})
	void testGoodResizeEqualsIntegerMax(String file) {
		System.out.println("testGoodResizeEqualsIntegerMax dla " + file);
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);
		assertThrows(OutOfMemoryError.class, () -> {
			int [] zwroconaTablica = test.goodResize(testowaTablica, testSizeIntegerMax);
		});
	}

	@ParameterizedTest
	@ValueSource(strings = {"Wspolne.txt","PosortowanyWspolne.txt","PosortowanyMalejacoWspolne.txt", "Milion.txt", "PosortowanyMilion.txt", "PosortowanyMalejacoMilion.txt",
			"JedenElement.txt","Jeden.txt"})
	void testFindAndPrintPairs(String file) {
		System.out.println("testFindAndPrintPairs dla " + file);
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);
		test.findAndPrintPairs(testowaTablica, 10000);
	}

	@ParameterizedTest
	@ValueSource(strings = {"Wspolne.txt","PosortowanyWspolne.txt","PosortowanyMalejacoWspolne.txt", "Milion.txt", "PosortowanyMilion.txt", "PosortowanyMalejacoMilion.txt",
			"JedenElement.txt","Jeden.txt"})
	void testBubblesort(String file) {
		System.out.println("testBubblesort dla " + file);
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);
		String PosortowanaGotowa = oczekiwanyWynikString(file);
		int [] testowaTablicaPosortowana = new ReadArrayFromFile(PosortowanaGotowa).readAndLimitMaxSizeBy(maxArrayCount);	
		test.bubblesort(testowaTablica);
		assertArrayEquals(testowaTablica, testowaTablicaPosortowana);
	}

	@ParameterizedTest
	@ValueSource(strings = {"Wspolne.txt","PosortowanyWspolne.txt","PosortowanyMalejacoWspolne.txt", "Milion.txt", "PosortowanyMilion.txt", "PosortowanyMalejacoMilion.txt",
			"JedenElement.txt","Jeden.txt"})
	void testShowList(String file) {
		System.out.println("testShowList dla " + file);
		ArrayExamples test = new ArrayExamples();
		int [] testowaTablica = new ReadArrayFromFile(file).readAndLimitMaxSizeBy(maxArrayCount);	
		test.showList(testowaTablica);
	}

	@ParameterizedTest
	@ValueSource(strings = {"Wspolne.txt","PosortowanyWspolne.txt","PosortowanyMalejacoWspolne.txt", "Milion.txt", "PosortowanyMilion.txt", "PosortowanyMalejacoMilion.txt",
			"JedenElement.txt","Jeden.txt"})
	void testIsAscending(String file) {
		System.out.println("testIsAscending dla " + file);
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
