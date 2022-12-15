package CryptoPackage;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CryptoAES {
    //region ATTRIBUTES
    private static SecretKey myKey;
    private static String modeAESCBC;
    private static String modeAESECB;

    private static FileInputStream inputStream;
    private static FileOutputStream outputStream;


    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    static {
        modeAESCBC = "AES/CBC/PKCS5Padding";
        modeAESECB = "AES/ECB/PKCS5Padding";

        try {
            myKey = generateKey(256);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    public static String getModeAESCBC() {
        return modeAESCBC;
    }

    public static String getModeAESECB() {
        return modeAESECB;
    }

    //endregion GETTERS & SETTERS


    //region METHODS

    public static String mangeEncryptAES(String nameInFile, String nameOutFile, String algorithm, boolean encryDencry) {
        //region DEFINITION VARIABLES
        String text = "";
        ////*SecretKey myKey;
        File inFile, outFile;
        IvParameterSpec myIV;

        //endregion DEFINITION VARIABLES

        //region ACTIONS
        try {
            // 1) Create file objects
            inFile = new File(nameInFile);
            outFile = new File(nameOutFile);

            // 2) Generate the key
            ////*myKey = generateKey(128);

            // 3) Generate initialization vector
            myIV = generateIv();

            // 4) Call method to do encrypt the file
            encryptAES(algorithm, myKey, inFile, outFile, myIV, encryDencry);

            // 5) End with exit
            if(encryDencry) {
                text = String.format("L'arxiu '%s' s'ha encriptat correctament amb l'algoritme '%s' en l'arxiu '%s'",
                        nameInFile, algorithm, nameOutFile);
            }else{
                text = String.format("L'arxiu '%s' s'ha desencriptat correctament amb l'algoritme '%s' en l'arxiu '%s'",
                        nameInFile, algorithm, nameOutFile);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //endregion ACTIONS


        // OUT
        return text;
    }

    /**
     * Methode to encryption o dencryption with algorithm AES and modes ECB or CBC
     * @param algorithm         => Indicate algorithm mode
     * @param key               =>
     * @param inFile            => File object with file we will encrypt or dencrypt
     * @param outFile           => File object with file we will dencrypt or encrypt
     * @param ivParameterSpec   =>
     * @param encryDencry       => false = encryption file; true = decryption file
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     */
    public static void encryptAES(String algorithm, SecretKey key, File inFile, File outFile,
                                  IvParameterSpec ivParameterSpec, boolean encryDencry)
            throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        //region DEFINITION VARIABLES
        int bytesRead;
        byte[] buffer = new byte[64];
        byte[] outputBytes;
        Cipher cipher;          // + info https://docs.oracle.com/javase/9/docs/api/javax/crypto/Cipher.html

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // 1) Generate Cipher instance
            if (algorithm.equals(modeAESCBC)) {
                cipher = Cipher.getInstance(modeAESCBC);
                cipher.init((!encryDencry) ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, key, ivParameterSpec);
            } else {
                cipher = Cipher.getInstance(modeAESECB);
                cipher.init((!encryDencry) ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, key);
            }

            // 2) Generate files objects
            inputStream = new FileInputStream(inFile);
            outputStream = new FileOutputStream(outFile);

            // 3) Read from input file buffer size
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // 4) Encrypting bytes in bytesREad
                byte[] output = cipher.update(buffer, 0, bytesRead);
                // 5) If isn't null, write on the output file.
                if (output != null) {
                    outputStream.write(output);
                }
            }

            // 6) Conclusion of encription
            outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                outputStream.write(outputBytes);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }

        //endregion ACTIONS


        // OUT


    }

    //endregion METHODS


    //region METHODS PRIVATES

    /**
     * Crea una clau random, mitjançant la llibrerira 'KeyGenerator'
     *
     * @param bitsNumKey => Nº de bits que pot tenir la key; valors: 128, 192, 256
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static SecretKey generateKey(int bitsNumKey) throws NoSuchAlgorithmException {
        //region DEFINITION VARIABLES
        SecretKey myKey;
        KeyGenerator myKeyGenerator;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        myKeyGenerator = KeyGenerator.getInstance("AES");
        myKeyGenerator.init(bitsNumKey);
        myKey = myKeyGenerator.generateKey();

        //endregion ACTIONS


        // OUT
        return myKey;

    }

    /**
     * Mètode x crear el IV vector d'inicialització
     *
     * @return Torna el IV (Initialization Vector)
     */
    public static IvParameterSpec generateIv() {
        //region DEFINITION VARIABLES
        // 16 is because the IV is the same size as the AES encrypthion block
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);

        //endregion DEFINITION VARIABLES


        // OUT
        return new IvParameterSpec(iv);

    }

    //endregion METHODS PRIVATES

}
