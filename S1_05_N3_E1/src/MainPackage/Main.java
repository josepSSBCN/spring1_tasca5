package MainPackage;

import IOPackage.ReadWriteTXT;
import ListPackage.ToList;

import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        //region DEFINITION VARIABLES
        String pathUser, text, pathConfigFile = "src/app.cnfg";
        ToList myToList = new ToList();
        ReadWriteTXT myReadWriteTXT = new ReadWriteTXT();
        FileInputStream fis = null;
        Properties myProp = new Properties();
        Properties myProp2 = new Properties();

        //endregion DEFINITION VARIABLES


        //region ACTION
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
                text =  ex.getMessage();
            }
        }

        //endregion ACTIONS


        // OUT
        System.out.println(text);
        System.out.println("\r\n\r\n__ GAME OVER__");
    }

}