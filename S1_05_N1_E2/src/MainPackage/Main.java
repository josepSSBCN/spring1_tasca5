package MainPackage;

import ListPackage.ToList;

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
                }

                // Print result
                System.out.println(text + "\r\n\r\n");
                text = "";

            } while (!endPrg);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        //endregion ACTIONS


        // OUT
        System.out.println("__ GAME OVER__");
    }

    private static byte gestioMenuPrincipal() {
        //region ATTRIBUTES
        byte opcio = -1;
        String text;

        //endregion ATTRIBUTES


        //region METHODS
        // 1) Create menu
        text = "*** MENÚ PRINCIPAL ***\r\n"
                + "1.- Llistar directori                    (S105N1E1).\r\n"
                + "2.- Llistar directori en format arbre    (S105N1E2).\r\n"
                + "0.- Sortir.\r\n\r\n"
                + "Selecciona una de les opcions";

        // 2) Ask user option
        opcio = (byte) ToolsGlbl.scannerInt(text, 0, 7);

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

}