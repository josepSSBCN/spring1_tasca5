package MainPackage;


import ListPackage.ToList;



public class Main {
    public static void main(String[] args) {
        //region DEFINITION VARIABLES
        boolean endPrgFromTerminal = false, endPrgFromIDE = false;
        String path, text;
        ToList myToList = new ToList();

        //endregion DEFINITION VARIABLES


        //region ACTION
        do {
            // 0) Execute from ID or terminal
            if (args.length == 0) {
                // 1) Ask path to customer.
                path = ToolsGlbl.scannerStrg("Entri el path da la carpeta que s'ha de llistar.", true);
            } else {
                path = args[0];
                endPrgFromTerminal = true;
            }

            // 2) Call method listing directory.
            text = myToList.listing(path);

            // 3) Print result on output.
            System.out.println(text);

            // 4) Continuous program?
            endPrgFromIDE = (args.length > 0 && args[0].isEmpty()) ?
                    false : ToolsGlbl.llegirSiNo("\r\nVols llistar un altre directori? (Sí(s)/No(n)");
        } while (endPrgFromTerminal || endPrgFromIDE);

        //endregion ACTIONS


        // OUT
        System.out.println("\r\n\r\n__ GAME OVER__");
    }

}