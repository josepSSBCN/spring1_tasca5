package ListPackage;

import java.awt.event.ItemEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ToList {
    //region ATTRIBUTES
    private ArrayList<String> elementsDirectory;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    public ToList(){
        this.elementsDirectory = new ArrayList<>();
    }

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    public ArrayList<String> getElementsDirectory() {
        return elementsDirectory;
    }

    public void setElementsDirectory(ArrayList<String> elementsDirectory) {
        this.elementsDirectory = elementsDirectory;
    }

    //endregion GETTERS & SETTERS


    //region METHODS
    public String listing(String path){
        //region DEFINITION VARIABLES
        String text ="";
        File folder;
        File[] listOfFiles;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // 01) Chekings
        if(!path.isEmpty() & !path.isBlank()){
            folder = new File(path);
            if (folder.exists()){
                // 02) Get files
                listOfFiles = folder.listFiles();
                // Pk estan ja ordenats?
                Arrays.stream(listOfFiles).sorted();

                // 03) Printing files
                for (int i = 0; i < listOfFiles.length; i++) {
                    text = String.format("%s\r\n%s ", text, listOfFiles[i].getName());
                }
            }else{
                text = String.format("La carpeta '%s', no existeix o no és correcta!", path);
            }
        }else{
            text = "S'ha de passar un path!";
        }

        //endregion ACTIONS


        // OUT
        return text;


    }

    //endregion METHODS


}
