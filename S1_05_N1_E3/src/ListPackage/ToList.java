package ListPackage;

import ExceptionPackage.ErrorPathException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class ToList {
    //region ATTRIBUTES
    private String text;
    private int tabSpaceLevel;
    private SimpleDateFormat dateFormat;


    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    public ToList() {
        text = "";
        dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        tabSpaceLevel = 1;
    }

    //endregion CONSTRUCTOR


    //region METHODS
    public String listing(String path) {
        //region DEFINITION VARIABLES
        String text = "";
        File[] listOfFiles;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // 1) Read fils of path
            listOfFiles = readFiles(path);

            // 2) Create list
            for (File listOfFile : listOfFiles) {
                text = String.format("%s\r\n%s", text, listOfFile.getName());
            }


        } catch (ErrorPathException ex) {
            text = ex.getMessage();
        }

        //endregion ACTIONS


        // OUT
        return text;


    }


    public String listingTree(String path) {
        //region DEFINITION VARIABLES
        String fileFolder, lastModification, tabSpaceText;
        File[] listOfFiles;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // 00) Initialization
            // Create left tab
            tabSpaceText = ("----".repeat(Math.max(0, tabSpaceLevel)));

            // 01) Read fils of path
            listOfFiles = readFiles(path);

            // 02) Create list
            for (int i = 0; i < listOfFiles.length; i++) {
                // 03) Get modification date
                lastModification = dateFormat.format(listOfFiles[i].lastModified());

                // 04) Is file or directory?
                if (listOfFiles[i].isFile()) {
                    // 04.1) Create a new text line for file
                    fileFolder = "(F)";
                    text = String.format("%s\r\n%s %s %-100s %s", text,tabSpaceText, fileFolder, listOfFiles[i].getName(), lastModification);
                } else if (listOfFiles[i].isDirectory()) {
                    // 4.1) Create a new text line for directory
                    fileFolder = "(D)";
                    text = String.format("%s\r\n%s %s %-100s %s", text,tabSpaceText, fileFolder, listOfFiles[i].getName(), lastModification);

                    // 4.2) Call this same method to create a new subdirectory
                    // Increment left space in 1 level
                    tabSpaceLevel++;
                    listingTree(listOfFiles[i].getCanonicalPath());
                    // Decrement left space in 1 level
                    tabSpaceLevel--;
                }

            }

        } catch (ErrorPathException ex) {
            text = ex.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //endregion ACTIONS


        // OUT
        return text;


    }

    //endregion METHODS


    //region METHODS PRIVATS
    private File[] readFiles(String path) throws ErrorPathException {
        //region DEFINITION VARIABLES
        File folder;
        File[] listOfFiles;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // 01) Chekings
        if (!path.isEmpty() & !path.isBlank()) {
            folder = new File(path);
            if (folder.exists()) {
                // 02) Get files
                listOfFiles = folder.listFiles();
                // Pk estan ja ordenats?
                Arrays.stream(listOfFiles).sorted();

            } else {
                throw new ErrorPathException(String.format("La carpeta '%s', no existeix o no és correcta!", path));
            }
        } else {
            throw new ErrorPathException("S'ha de passar un path!");
        }

        //endregion ACTIONS


        // OUT
        return listOfFiles;

    }

    //endregion METHODS PRIVATS


}
