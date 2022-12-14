package ExceptionPackage;

public class ErrorPathException extends Exception{
    public ErrorPathException(String missatge){
        super("EXCEPTION! " + missatge);
    }
}
