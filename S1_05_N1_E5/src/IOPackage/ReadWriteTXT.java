package IOPackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadWriteTXT {
	//region ATTRIBUTES


	//endregion ATTRIBUTES


	//region CONSTRUCTOR
    public ReadWriteTXT() {
    }

	//endregion CONSTRUCTOR


	//region METHODS PUBLICS
	public String readTXT() throws IOException {
        //region DEFINITION VARIABLES
        String text = "";
        File myFile;
        Scanner myScanner = null;

        //endregion DEFINITION VARIABLES

        //region ACTIONS
        // 1) Create file object
        myFile = new File("src/S105N1E3.txt");

        // 2) Initial checkings
        try {
            if (myFile.exists()) {
                // 3) Create reader object
                myScanner = new Scanner(myFile);

                // 4) Read line to line
                while (myScanner.hasNext()) {
					text = String.format("%s\r\n%s", text, myScanner.nextLine());
                }
            }
        } finally {
            // 5) Close file
            if (myScanner != null) {
                myScanner.close();
            }
        }

        //endregion ACTIONS


        // OUT
        return text;

    }

    public void writeTXT(String text, boolean appendEnd) throws IOException {
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
            myFileWrite = new FileWriter(myFile, appendEnd);

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

	//endregion METHODS PUBLICS


	//region METHODS PRIVATES

	//endregion METHODS PRIVATES

}
