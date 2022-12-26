package MainPackage;

import IOPackage.ReadWriteTXT;
import ListPackage.ToList;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //region DEFINITION VARIABLES
        boolean endPrg = false;
        byte userOption;
        String path = "", text = "";

        //endregion DEFINITION VARIABLES


        //region ACTION
        try {
            //*//*args = new String[]{"D:\\IT ACADEMY\\EXERCICIS\\SPRINT_1"};
            path = (args.length == 1) ? args[0] : "";

            do {
                // 1) Show the  Main menu
                userOption = gestioMenuPrincipal(path);

                // 2) Option selected
                switch (userOption) {
                    case 0 -> endPrg = true;
                    case 1 -> text = listDirectoryElements(path);
                    case 2 -> text = listDirectoryTree(path);
                    case 3 -> text = saveToTXT(path);
                }

                // Print result
                System.out.println(text + "\r\n\r\n");
                text = "";

                if (userOption != 0) {
                    ToolsGlbl.polsarTeclaPerContinuar("");
                }

            } while (!endPrg);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        //endregion ACTIONS


        // OUT
        System.out.println("__GAME OVER__");
    }

    private static byte gestioMenuPrincipal(String path) {
        //region ATTRIBUTES
        byte opcio = -1;
        String text;

        //endregion ATTRIBUTES


        //region METHODS
        // 1) Create menu
        text = String.format("*** MENÚ PRINCIPAL ***\r\n"
                + "1.- Llistar directori                    (S105N1E1).\r\n"
                + "2.- Llistar directori en format arbre    (S105N1E2).\r\n"
                + "3.- Guardar llistat en fitxer '.txt      (S105N1E3).\r\n"
                + "0.- Sortir.\r\n\r\n"
                + "%s"
                + "Selecciona una de les opcions", (path.length() > 0) ? "PATH SELECCIONAT: '" + path + "'\r\n\r\n" : "");

        // 2) Ask user option
        opcio = (byte) ToolsGlbl.scannerInt(text, 0, 7);

        //endregion METHODS


        // out
        return opcio;

    }

    private static String listDirectoryElements(String path) {
        //region DEFINITION VARIABLES
        String text;
        ToList myToList = new ToList();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        if (path.isEmpty()) {
            // 1) Ask path to customer.
            text = "OPCIO 1: Llistar directori.\r\nEntri el path de la carpeta que s'ha de llistar.";
            path = ToolsGlbl.scannerStrg(text, true);
        }

        // 2) Call method listing directory.
        text = myToList.listing(path);

        //endregion ACTIONS


        // OUT
        return text;

    }

    private static String listDirectoryTree(String path) {
        //region DEFINITION VARIABLES
        String text;
        ToList myToList = new ToList();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        if (path.isEmpty()) {
            // 1) Ask path to customer.
            text = "OPCIO 2: Llistar directori en forma d'arbre.\r\nEntri el path de la carpeta que s'ha de llistar.";
            path = ToolsGlbl.scannerStrg(text, true);
        }

        // 2) Call method listing directory.
        text = myToList.listingTree(path);

        //endregion ACTIONS


        // OUT
        return text;

    }

    private static String saveToTXT(String path) {
        //region DEFINITION VARIABLES
        String text;
        ToList myToList = new ToList();
        ReadWriteTXT myReadWriteTXT = new ReadWriteTXT();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            if (path.isEmpty()) {
                // 1) Ask path to customer.
                text = "OPCIO 3: Guardar llistat en fitxer '.txt'.\r\nEntri el path de la carpeta que s'ha de llistar.";
                path = ToolsGlbl.scannerStrg(text, true);
            }

            // 2) Call to method of listing directory.
            text = myToList.listingTree(path);

            // 3) Call to method of write to txt
            myReadWriteTXT.escriureTXT(text);

            text = "Llistat creat i guardat en el txt, satisfactoriament.";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //endregion ACTIONS


        // OUT
        return text;

    }
}