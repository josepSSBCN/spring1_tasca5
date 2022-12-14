package MainPackage;

import ListPackage.ToList;

public class Main {
    public static void main(String[] args) {
        //region DEFINITION VARIABLES
        boolean endPrg;
        String path, text;
        ToList myToList = new ToList();

        //endregion DEFINITION VARIABLES


        //region ACTION
        do {
            // 1) Ask path to customer.
            path = ToolsGlbl.scannerStrg("Entri el path da la carpeta que s'ha de llistar.", true);

            // 2) Call method listing directory.
            text = myToList.listing(path);

            // 3) Print result on output.
            System.out.println(text);

            // 4) Continuos program?
            endPrg = ToolsGlbl.llegirSiNo("\r\nVols llistar un altre directori? (Sí(s)/No(n)");
        } while (endPrg);

        //endregion ACTIONS


        // OUT
        System.out.println("\r\n\r\n__ GAME OVER__");
    }

}