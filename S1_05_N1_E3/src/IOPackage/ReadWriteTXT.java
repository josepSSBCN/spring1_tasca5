package IOPackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReadWriteTXT {
	// CONSTRUCTORA
	public ReadWriteTXT() {
	}

	// METODES
	/*
	public String  llegirCSV() throws IOException {
		// DEFINICIO VARIABLES
		String[] words;
		ArrayList<Persona> persones = new ArrayList<Persona>();
		File myFile;
		Scanner myScanner = null;

		// ACCIONS
		// 1) Es crea
		myFile = new File("src/persones.csv");

		// 2) Es comprova si existeix el fitxer
		try {
			if (myFile.exists()) {
				// 3) Es llegeix tot el fitxer
				myScanner = new Scanner(myFile);

				// 4) Es llegeix les files
				while (myScanner.hasNext()) {
					words = myScanner.nextLine().split(";");
					// 5) Es llegeix les paraules de la línia
					if (words.length == 3) {
						persones.add(new Persona(words[0], words[1], words[2]));
					}
				}
			}
		} finally {
			// Tanquem el fitxer si està creat
			if (myScanner != null) {
				myScanner.close();
			}
		}

		// SORTIDA
		return persones;

	}
*/

	public void escriureTXT(String text) throws IOException {
		// DEFINICIO VARIABLES
		File myFile;
		FileWriter myFileWrite = null;

		// ACCIONS
		try {
			// 1) Es crea l'objecte file
			myFile = new File("src/S105N1E3.txt");

			// 2) S'ha de crear el fitxer?
			if (!myFile.exists()) {
				myFile.createNewFile();
			}

			// 3) Es crea l'objecte x escriure en el fitxer
			myFileWrite = new FileWriter(myFile, true);

			// 4) S'afegeix la nova línia
			////*myFileWrite.write(String.format("%s;%s;%s\r\n", persona.getNom(), persona.getCognom(), persona.getDni()));
			myFileWrite.write(text);

		} finally {
			// 5) Es tanca el fitxer
			if (myFileWrite != null) {
				myFileWrite.close();
			}
		}

	}
}
