/* 
 * This class manages the bitfield. The bitfield is a set of bits that records which chunk of the file a process has received.
 */

import java.util.ArrayList;
import java.util.BitSet;

public class Bitfield{

	private BitSet bitfield;	// The array of bits that indicate which chunks a process is in possession of.

	/*
	 * Constructor. Sets all bits to false. We have two constructors just for ease of use later on.
	 * @param numberOfBits: size of the bitfield.
	 */
	public Bitfield(int numberOfBits){

		this(numberOfBits, false);

	}

	/*
	 * Constructor. Sets all bits to true or false depending on the boolean passed in.
	 * @param numberOfBits: size of the bitfield.
	 * @param hasFile: whether or not the process has the file.
	 */
	public Bitfield(int numberOfBits, boolean hasFile){

		bitfield = new BitSet(numberOfBits);

		for(int i = 0; i < numberOfBits; i++){

			bitfield.set(i, hasFile);
		}

	}

	/* Getter for bitfield 
	 * @return BitSet: the actual bitfield
	 */
	public BitSet getBitfield(){
		synchronized(this){		//do we need this to be synchronized?
			return bitfield;
		}
	}

	/* Setter for bitfield
	 * @param bitfield: the bitfield to set this object's to.
	 */
	public void setBitfield(BitSet bitfield){
		this.bitfield = bitfield;
	}

	/* Sets a bit to the inputted boolean value.
	 * @param index: the index to alter.
	 * @param b: the boolean value to set the bit at the index to.
	 */
	public void setBit(int index, boolean b) {
		this.bitfield.set(index, b);
	}
	
	/* Sets a bit to true to indicate that its corresponding chunk was received.
	 * @param index: the index to mark as true.
	 */
	public void markChunkAsObtained(int index){
		setBit(index, true);
	}

	/* Checks whether process has received whole file by iterating over bitfield and looking for falses.
	 * @return boolean: whether or not the bitfield is set to all trues. In that case, the whole file has been received.
	 */
	public boolean isComplete(){

		for(int i = 0; i < bitfield.length(); i++){

			if(bitfield.get(i) == false){
				return false;
			}
		}

		return true;
	}

	/* Compares bitfields 1 and 2 and returns a list of indexes that are false for bitfield1 but true for bitfield2.
	 * These are the pieces bitfield1 will request from bitfield2 later.
	 * @param bitfield2: the second bitfield the method will compare to. 
	 * @return 
	 */
	public ArrayList<Integer> compareTo(BitSet bitfield2){
		ArrayList<Integer> interestedChunks = new ArrayList<Integer>();

		for(int i = 0; i < bitfield2.length(); i++){
			if((bitfield.get(i) == false ) && (bitfield2.get(i) == true)){
				interestedChunks.add(i);
			}
		}

		return interestedChunks;
	}

	/*
	 * Converts 8 bits of the bitfield into a byte to send out in a message.
	 * @param index: the index to start from. Will convert index - index + 7 to a byte and return the byte.
	 * @return byte: the byte representation of 8 consecutive bits in the bitset. 
	 */
	public byte toByte(int index) {

		int sum = 0;
		for (int i = 0; i < 8; i++) {

			if (bitfield.get(index + i) && bitfield.get(index + i) == true) {
				sum += java.lang.Math.pow(2, i);
			}			
		}

		if (sum > 127) {
			int diff = sum - 127;
			sum = -129 + diff;
		}

		return (byte) sum;
	}

}
