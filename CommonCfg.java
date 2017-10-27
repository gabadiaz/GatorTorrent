/**
 *This class reads Common.cfg and records the variables.
 */

import java.io.BufferedReader;
import java.io.FileReader;

public class CommonCfg {
	
	private int numberOfPreferredNeighbors;
	private int unchokingInterval;
	private int optimisticUnchokingInterval;
	private String fileName;
	private int fileSize;
	private int pieceSize;

	/**
	 *Constructor. Reads the file and stores the variables.
	 */
	public CommonCfg() {

		try {
			FileReader fr = new FileReader("Common.cfg");
			BufferedReader br = new BufferedReader(fr);

			String currentLine;
			String[] words;

			int lineNumber = 0;
			while((currentLine = br.readLine()) != null) {
				
				words = currentLine.split("\\s+");

				switch(lineNumber) {
					case 0: numberOfPreferredNeighbors = Integer.parseInt(words[1]);
						break;
					case 1: unchokingInterval = Integer.parseInt(words[1]);
						break;
					case 2: optimisticUnchokingInterval = Integer.parseInt(words[1]);
						break;
					case 3: fileName = words[1];
						break;
					case 4: fileSize = Integer.parseInt(words[1]);
						break;
					case 5: pieceSize = Integer.parseInt(words[1]);
						break;
				}

				lineNumber++;
			}
		}

		catch (Exception e){
			System.out.println(e);
		}

	}

	/**
	 *Prints out the recorded variables. Used for testing.
	 */
	public void printSettingsForTesting() {
		System.out.println(numberOfPreferredNeighbors);
		System.out.println(unchokingInterval);
		System.out.println(optimisticUnchokingInterval);
		System.out.println(fileName);
		System.out.println(fileSize);
		System.out.println(pieceSize);
	}

	/**
	 *Getter for numberOfPreferredNeighbors.
	 *@return int: the numberOfPreferredNeighbors.
	 */
	public int getNumberOfPreferredNeighbors() {
		return numberOfPreferredNeighbors;
	}

	/**
	 *Getter for unchokingInterval.
	 *@return int: the unchokingInterval.
	 */
	public int getUnchokingInterval() {
		return unchokingInterval;
	}

	/**
	 *Getter for optimisticUnchokingInterval.
	 *@return int: the optimisticUnchokingInterval.
	 */
	public int getOptimisticUnchokingInterval() {
		return optimisticUnchokingInterval;
	}

	/**
	 *Getter for fileName.
	 *@return String: the fileName.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 *Getter for fileSize.
	 *@return int: the fileSize.
	 */
	public int getFileSize() {
		return fileSize;
	}

	/**
	 *Getter for pieceSize.
	 *@return int: the pieceSize.
	 */
	public int getPieceSize() {
		return pieceSize;
	}

}
