package MainPackage;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Eines comunes a tots els programes
 * 
 * @author josep
 */
public class ToolsGlbl {

	//region SCANNER METHODS
	/**
	 * Mètode per demanar a l'usuari que introdueixi un int, crida al mètode que
	 * scannerInt complet.
	 * 
	 * @return Torna el int introduït per l'usuari, si no està torna -1
	 */
	public static int scannerInt(String missatge) {
		return scannerInt(missatge, 0, 0, "");
	}

	/**
	 * Mètode per demanar a l'usuari que introdueixi un int, crida al mètode que
	 * scannerInt complet.
	 * 
	 * @param missatge Missatge capçalera x introduïr el valor.
	 * @param min      Valor mínim del int que s'ha d'introduïr, si min i max = 0,
	 *                 no compte
	 * @param max      Valor màxim del int que s'ha d'introduïr, si min i max = 0,
	 *                 no compte
	 * @return Torna el int introduït per l'usuari, si no està torna -1
	 */
	public static int scannerInt(String missatge, int min, int max) {
		return scannerInt(missatge, min, max, "");
	}

	/**
	 * Mètode per demanar a l'usuari que introdueixi un int, amb un límits min i
	 * max, i missatge d'error.
	 * 
	 * @param missatge      missatge que es mostrarà a l'usuari
	 * @param min           Valor mínim del int que s'ha d'introduir, si min i max =
	 *                      0, no es comprova
	 * @param max           Valor màxim del int que s'ha d'introduir, si min i max =
	 *                      0, no es comprova
	 * @param missatgeError En cas d'error de format o fora de límits, si és "" no
	 *                      es mostra res
	 * @return Torna el int introduït per l'usuari, si no està torna -1
	 */
	public static int scannerInt(String missatge, int min, int max, String missatgeError) {
		// DEFINICIÓ VARIABLES
		boolean correcte = false;
		int myInt = -1;
		Scanner myScanner;

		// ACCIONS
		// 1) Preparació
		// En cas que no hi hagi missatge
		if (missatgeError.isEmpty()) {
			missatgeError = "ERROR: Valor incorrecte.";
		}
		System.out.println(missatge);

		// 2) Es demana al usuari entri 1 número
		while (!correcte) {
			try {
				myScanner = new Scanner(System.in);
				myInt = myScanner.nextInt();

				// Si arriba aquí, el format és correcte
				if (min == 0 & max == 0) {
					correcte = true;
				} else if (myInt >= min & myInt <= max) {
					correcte = true;
				} else {
					System.out.println(missatgeError);
				}
			} catch (InputMismatchException ex) {
				System.out.println(missatgeError);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

		// SORTIDA
		return myInt;

	}

	/**
	 * Mètode per demanar a l'usuari que introdueixi un string
	 * 
	 * @param missatge Què s'ha de mostrar.
	 * @param linia    false=sols 1 paraula; true=fins intro
	 * @return El string que ha afegit l'usuari.
	 */
	public static String scannerStrg(String missatge, boolean linia) {
		// DEFINICIÓ VARIABLES
		boolean correcte = false;
		String myString = "";
		Scanner myScanner;

		System.out.println(missatge);
		while (!correcte) {
			try {
				myScanner = new Scanner(System.in);
				if (linia) {
					myString = myScanner.nextLine();
				} else {
					myString = myScanner.next();
				}

				correcte = true;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return myString;

	}

	public static boolean llegirSiNo(String missatge) {
		// DECLARACIÓ VARIABLES
		boolean correcte = false, booleanOut = false;
		String myString = "";
		Scanner myScanner = new Scanner(System.in);

		// ACCIONS
		// Estarem en el bucle fins que el format sigui correcte
		while (!correcte) {
			try {
				System.out.println(missatge);

				myString = myScanner.next();

				if (myString.length() == 1) {
					if((myString.charAt(0) == 's' || myString.charAt(0) == 'n')) {
						correcte = true;
						booleanOut = (myString.charAt(0) == 's')? true:false;
					}else {
						throw new Exception();
					}
				} else {
					throw new Exception();
				}
			} catch (Exception ex) {
				System.out.println("Error de format, torna-ho a provar");
			}
		}

		// SORTIDA
		return booleanOut;

	}

	//endregion SCANNER METHODS

	/**
	 * Mètode que espera que l'usuari polsi qualsevol tecla i després intro
	 * 
	 * @param missatge El missatge que es mostrarà abans del "Per continuar..."
	 */
	public static void polsarTeclaPerContinuar(String missatge) {
		// DEFINICIONS
		String text;

		// ACCIONS
		do {
			missatge = (!missatge.isEmpty()) ? missatge = "\r\n" + missatge + "\r\n\r\n" : "\r\n";

			text = scannerStrg(String.format("%sPer continuar, polsar qualsevol tecla + intro.", missatge), false);
		} while (text.length() == 0);
	}
}
