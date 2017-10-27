/**
 *Extension of base abstract message class into actual message.
 */

import java.nio.ByteBuffer;	//Makes it really easy to fill the message byte array with the right information.

public class ActualMessage extends Message {

	private byte messageType;
	private byte[] messagePayload;	//Note: not every message has a payload

        /**
         *Constructor. Composes message and sets messageType and messagePayload to the inputted ones.
         *messagePayload may be empty.
         *
         *Composition of actual message is as follows:
         *Bytes 0-4 (4 bytes): message length.
	 *Byte 5 (1 byte): messageType.
	 *Bytes 6+ (Variable number of bytes): Optional messagePayload.
	 *Variable total of bytes.
	 *
	 *@param messageType: the type of message. Possible values of 0-7.
         *@param messagePayload: the message payload. Not all messages have one.
         */
	public ActualMessage(byte messageType, byte[] messagePayload) {
		
		int messageLength = messagePayload.length + 1;
		ByteBuffer bb = ByteBuffer.allocate(messageLength + 4);

		bb.putInt(messageLength);
		bb.put(messageType);
		bb.put(messagePayload);

		messageBytes = bb.array();
		this.messageType = messageType;
		this.messagePayload = messagePayload;

	}

	/**
	 * Getter for messageType
	 * @return byte: the messageType.
	 */
	public byte getMessageType() {
		return messageType;
	}

	/**
	 * Getter for messagePayload
	 * @return byte[]: the messagePayload.
	 */
	public byte[] getMessagePayload() {
		return messagePayload;
	}

}
