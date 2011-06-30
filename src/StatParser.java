import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * StatParser will be a parser to generate command strings to apply
 * stats to objects in sharune. Files need to be in the following
 * format:
 * 
 * The different objects are seperated by an empty line. For every 
 * object, the first line contains the vnum, the second line contains
 * the wear location with least EP for this object, the third to last
 * line contain a modifier (integral) and a representation of the
 * stat that should have the modifier. The order does not matter.
 * 
 * Example:
 * 
 * 12345
 * across back
 * 41 AGI
 * ARMOR 12
 * 
 * @author Milean
 *
 */
public class StatParser {

	/**
	 * @param args A single filename containing the input file.
	 */
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++){
			File lInput =  new File(args[i]);
			try {
				System.out.println(StatParser.parseStats(lInput));
			}
			catch(FileNotFoundException ex){
				System.out.println("Faulty argument, '"+args[i]+"' is not a file.");
			}
		}
	}
	
	/**
	 * @param pInput The file that contains a list of item-stats combinations.
	 * @return A command string that will put the appropriate stats into oedit.
	 * @throws FileNotFoundException 
	 */
	public static String parseStats(File pInput) throws FileNotFoundException{
		Scanner lScanner = new Scanner(pInput);
		//Do some magic.
		return "";
	}

}
