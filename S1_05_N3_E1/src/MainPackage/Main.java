package MainPackage;

import CryptoPackage.CryptoAES;
import IOPackage.ReadWriteTXT;
import ListPackage.ToList;
import PersonPackage.Persona;

import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        //region DEFINITION VARIABLES
        boolean endPrg = false;
        byte userOption;
        String text = "";

        //endregion DEFINITION VARIABLES


        //region ACTION
        try {
            do {
                // 1) Main menu
                userOption = gestioMenuPrincipal();

                // 2) Option selected
                switch (userOption) {
                    case 0 -> endPrg = true;
                    case 1 -> text = listDirectoryElements();
                    case 2 -> text = listDirectoryTree();
                    case 3 -> text = saveToTXT();
                    case 4 -> text = readFromTXT();
                    case 5 -> serialObject();
                    case 6 -> text = propertiesJavaFile();
                    case 7 -> text = encryptFilesAESECB();
                    case 8 -> text = encryptFilesAESCBC();
                    case 9 -> text = dencryptFilesAESECB();
                    case 10 -> text = dencryptFilesAESCBC();
                }

                // Print result
                System.out.println(text + "\r\n\r\n");
                text = "";

                ToolsGlbl.polsarTeclaPerContinuar("");

            } while (!endPrg);

        } catch (Exception ex) {
            text = ex.getMessage();
        }

        //endregion ACTIONS


        // OUT
        System.out.println(text);
        System.out.println("\r\n\r\n__GAME OVER__");
    }


    private static byte gestioMenuPrincipal() {
        //region ATTRIBUTES
        byte opcio = -1;
        String text;

        //endregion ATTRIBUTES


        //region METHODS
        // 1) Create menu
        text = "*** MENÚ PRINCIPAL ***\r\n"
                + "1.- Llistar directori                                (S105N1E1).\r\n"
                + "2.- Llistar directori en format arbre                (S105N1E2).\r\n"
                + "3.- Guardar llistat en fitxer '.txt'                 (S105N1E3).\r\n"
                + "4.- Llegir fitxer '.txt'                             (S105N1E4).\r\n"
                + "5.- Serialitzar objecte                              (S105N1E5).\r\n"
                + "6.- Guardar llistat en fitxer '.txt' amb Propierties (S105N2E1).\r\n"
                + "7.- Encriptar fitxer amb algoritme AES/ECB           (S105N3E1).\r\n"
                + "8.- Encriptar fitxer amb algoritme AES/CBC           (S105N3E1).\r\n"
                + "9.- Desencriptar fitxer amb algoritme AES/ECB        (S105N3E1).\r\n"
                + "10.- Desencriptar fitxer amb algoritme AES/CBC       (S105N3E1).\r\n"
                + "0.- Sortir.\r\n\r\n"
                + "Selecciona una de les opcions";

        // 2) Ask user option
        opcio = (byte) ToolsGlbl.scannerInt(text, 0, 10);

        //endregion METHODS


        // out
        return opcio;

    }

    private static String listDirectoryElements() {
        //region DEFINITION VARIABLES
        String path, text;
        ToList myToList = new ToList();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // 1) Ask path to customer.
        text = "OPCIO 1: Llistar directori.\r\nEntri el path de la carpeta que s'ha de llistar.";
        path = ToolsGlbl.scannerStrg(text, true);

        // 2) Call method listing directory.
        text = myToList.listing(path);

        //endregion ACTIONS


        // OUT
        return text;

    }

    private static String listDirectoryTree() {
        //region DEFINITION VARIABLES
        String path, text;
        ToList myToList = new ToList();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // 1) Ask path to customer.
        text = "OPCIO 2: Llistar directori en forma d'arbre.\r\nEntri el path de la carpeta que s'ha de llistar.";
        path = ToolsGlbl.scannerStrg(text, true);

        // 2) Call method listing directory.
        text = myToList.listingTree(path);

        //endregion ACTIONS


        // OUT
        return text;

    }

    private static String saveToTXT() {
        //region DEFINITION VARIABLES
        String text, path;
        ToList myToList = new ToList();
        ReadWriteTXT myReadWriteTXT = new ReadWriteTXT();


        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // 1) Ask path to customer.
            text = "OPCIO 3: Guardar llistat en fitxer '.txt'.\r\nEntri el path de la carpeta que s'ha de llistar.";
            path = ToolsGlbl.scannerStrg(text, true);

            // 2) Call to method of listing directory.
            text = myToList.listingTree(path);

            // 3) Call to method of write to txt
            myReadWriteTXT.writeTXT(text, false, "src/S105N1E3.txt");

            text = "Llistat creat i guardat en el txt, satisfactoriament.";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //endregion ACTIONS


        // OUT
        return text;


    }

    private static String readFromTXT() {
        //region DEFINITION VARIABLES
        String text;
        ReadWriteTXT myReadWriteTXT = new ReadWriteTXT();


        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // 1) Read txt file
            text = myReadWriteTXT.readTXT();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //endregion ACTIONS


        // OUT
        return text;

    }

    private static String serialObject() {
        //region DEFINITION VARIABLES
        String text = "";

        FileOutputStream fos = null;
        ObjectOutputStream outObject = null;
        FileInputStream fis = null;
        ObjectInputStream inObject = null;
        Persona myPerson;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // 1) Create necessary objects
            System.out.println("OPCIO 5: Serialitzar objecte.");
            fos = new FileOutputStream("src/persones.ser");
            outObject = new ObjectOutputStream(fos);
            fis = new FileInputStream("src/persones.ser");
            inObject = new ObjectInputStream(fis);

            // 2) Create objects & write to file
            myPerson = new Persona("Carlos", "Gomez", "47568154");
            System.out.println("Primer objecte persona: " + myPerson.toString());
            outObject.writeObject(myPerson);
            System.out.println("Objecte persona 1, serialitzat i guardat correctament.");

            myPerson = new Persona("Laura", "Vilaplana", "41784358");
            System.out.println("Segon objecte persona: " + myPerson.toString());
            outObject.writeObject(myPerson);
            System.out.println("Objecte persona 1, serialitzat i guardat correctament.");

            // 3) Read file with serialization objects
            System.out.println("\r\nLlegim del fitxer el primera objecte persona " +
                    "(NOTA! L'atribut DNI és 'transient', x això és null)");
            myPerson = (Persona) inObject.readObject();
            System.out.println("Primera objecte persona: " + myPerson.toString());
            System.out.println("Llegim del fitxer el segon objecte persona " +
                    "(NOTA! L'atribut DNI és 'transient', x això és null)");
            myPerson = (Persona) inObject.readObject();
            System.out.println("Segon objecte persona: " + myPerson.toString());

        } catch (Exception ex) {
            text = ex.getMessage();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                text = ex.getMessage();
            }
        }
        //endregion ACTIONS

        // OUT
        return text;

    }

    private static String propertiesJavaFile() {
        //region DEFINITION VARIABLES
        String pathUser, text, pathConfigFile = "src/app.cnfg";
        ToList myToList = new ToList();
        ReadWriteTXT myReadWriteTXT = new ReadWriteTXT();
        FileInputStream fis = null;
        Properties myProp = new Properties();
        Properties myProp2 = new Properties();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // 1) Ask path to user
            pathUser = ToolsGlbl.scannerStrg("Entri el path de la carpeta que s'ha de llistar.", true);

            // 2) Set a properties
            myProp.setProperty("DIRECTORI", pathUser);
            myProp.setProperty("PATH_FITXER", "src/S105N2E1.txt");

            // 3) Read directory elements
            text = myToList.listingTree(myProp.getProperty("DIRECTORI"));

            // 5) Write directory elements on txt file
            myReadWriteTXT.writeTXT(text, false, myProp.getProperty("PATH_FITXER"));

            // 6) Save properties on config file
            myProp.store(new FileOutputStream(pathConfigFile), null);

            // 7) Print propierties from 'myProp'
            System.out.println("\r\nPropietats de 'myProp'");
            System.out.println("myProp.DIRECTORI: " + myProp.getProperty("DIRECTORI"));
            System.out.println("myProp.PATH_FITXER: " + myProp.getProperty("PATH_FITXER"));

            // 8) Read properties from config file
            fis = new FileInputStream(pathConfigFile);
            myProp2.load(fis);

            // 9) Print propierties from 'myProp2'
            System.out.println("\r\nPropietats de 'myProp2'");
            System.out.println("myProp2.DIRECTORI: " + myProp2.getProperty("DIRECTORI"));
            System.out.println("myProp2.PATH_FITXER: " + myProp2.getProperty("PATH_FITXER"));

            text = "";
        } catch (Exception ex) {
            text = ex.getMessage();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                text = ex.getMessage();
            }
        }
        //endregion ACTIONS


        // OUT
        return text;

    }

    private static String encryptFilesAESECB() {
        //region DEFINITION VARIABLES
        String text = "", pathInFIle="", pathOutFile ="", mode ="";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            text = "OPCIO 7: Encriptar fitxer amb algoritme AES/ECB.\r\n";

            // 1) Ask user what file would encrypt
            pathInFIle = ToolsGlbl.scannerStrg(text + "\r\nEntri el path complet de l'arxiu que vol encriptar (p.ex. D:\\Temp\\S105N1E3.txt)",true);
            pathOutFile = ToolsGlbl.scannerStrg("\r\nEntri el path complet de l'arxiu on vol deixar l'enctriptat. (p.ex. D:\\Temp\\S105N1E3_enc.txt)",true);

            pathInFIle = "D:\\Temp\\S105N1E3.txt";
            pathOutFile = "D:\\Temp\\S105N1E3_encECB.cyp";


            // 2) Call methode form
            text = CryptoAES.mangeEncryptAES(pathInFIle, pathOutFile, CryptoAES.getModeAESECB(), false);

        }catch (Exception ex){
            text = ex.getMessage();
        }

        //endregion ACTIONS


        // OUT
        return text;

    }

    private static String encryptFilesAESCBC() {
        //region DEFINITION VARIABLES
        String text = "", pathInFIle="", pathOutFile ="";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            text = "OPCIO 8: Encriptar fitxer amb algoritme AES/CBC.\r\n";

            // 1) Ask user what file would encrypt
            pathInFIle = ToolsGlbl.scannerStrg(text + "\r\nEntri el path complet de l'arxiu que vol encriptar (p.ex. D:\\Temp\\S105N1E3.txt)",true);

            pathOutFile = ToolsGlbl.scannerStrg("\r\nEntri el path complet de l'arxiu on vol deixar l'enctriptat. (p.ex. D:\\Temp\\S105N1E3_enc.txt)",true);

            ////* temp
            //pathInFIle="D:\\Temp\\S105N1E3.txt";
            //pathOutFile="D:\\Temp\\S105N1E3_encCBC.cyp";

            // 2) Call methode form
            text = CryptoAES.mangeEncryptAES(pathInFIle, pathOutFile,CryptoAES.getModeAESCBC(),false);

        }catch (Exception ex){
            text = ex.getMessage();
        }

        //endregion ACTIONS


        // OUT
        return text;

    }

    private static String dencryptFilesAESECB() {
        //region DEFINITION VARIABLES
        String text = "", pathInFIle="", pathOutFile ="", mode ="";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            text = "OPCIO 9: Desencriptar fitxer amb algoritme AES/ECB.\r\n";

            // 1) Ask user what file would encrypt
            pathInFIle = ToolsGlbl.scannerStrg(text + "\r\nEntri el path complet de l'arxiu que vol encriptar (p.ex. D:\\Temp\\S105N1E3_encECB.cyp)",true);
            pathOutFile = ToolsGlbl.scannerStrg("\r\nEntri el path complet de l'arxiu on vol deixar l'enctriptat. (p.ex. D:\\Temp\\S105N1E3_dencECB.txt)",true);

            ////* temp
            //pathInFIle = "D:\\Temp\\S105N1E3_encECB.cyp";
            //pathOutFile = "D:\\Temp\\S105N1E3_dencECB.txt";


            // 2) Call methode form
            text = CryptoAES.mangeEncryptAES(pathInFIle, pathOutFile, CryptoAES.getModeAESECB(),true);

        }catch (Exception ex){
            text = ex.getMessage();
        }

        //endregion ACTIONS


        // OUT
        return text;

    }

    private static String dencryptFilesAESCBC() {
        //region DEFINITION VARIABLES
        String text = "", pathInFIle="", pathOutFile ="";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            text = "OPCIO 10: Desencriptar fitxer amb algoritme AES/CBC.\r\n";

            // 1) Ask user what file would encrypt
            pathInFIle = ToolsGlbl.scannerStrg(text + "\r\nEntri el path complet de l'arxiu que vol encriptar (p.ex. D:\\Temp\\S105N1E3_encCBC.cyp)",true);
            pathOutFile = ToolsGlbl.scannerStrg("\r\nEntri el path complet de l'arxiu on vol deixar l'enctriptat. (p.ex. D:\\Temp\\S105N1E3_dencCBC.txt)",true);

            ////* temp
            //pathInFIle = "D:\\Temp\\S105N1E3_encCBC.cyp";
            //pathOutFile = "D:\\Temp\\S105N1E3_dencCBC.txt";


            // 2) Call methode form
            text = CryptoAES.mangeEncryptAES(pathInFIle, pathOutFile,CryptoAES.getModeAESCBC(),true);

        }catch (Exception ex){
            text = ex.getMessage();
        }

        //endregion ACTIONS


        // OUT
        return text;

    }
}