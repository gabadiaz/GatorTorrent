/**
 *Abstract base class for the messages. Extended by ActualMessage and HandShakeMessage
 */

public abstract class Message {

	//Note: We use a byte array for messageBytes instead of a ByteBuffer because output streams only accept byte arrays.
	//		We do later use a ByteBuffer to help with populating the messageBytes array though 
	protected byte[] messageBytes; //actual content of message

	/**
	 * Getter for messageBytes
	 * @return the messageBytes array.
	 */
	public byte[] getMessageBytes() {
		return messageBytes;
	}

}