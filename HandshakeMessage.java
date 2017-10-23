/**
 *Extension of base abstract message class into handshake message.
 */

import java.nio.ByteBuffer;	//Makes it really easy to fill the message byte array with the right information.

public class HandshakeMessage extends Message {

	private int peerID;	//Sender's peerID

	/**
     *Constructor. Composes message and sets peerID to inputted peerID.
     *
     *Composition of handshake message is as follows:
     *Bytes 0-17 (18 bytes): the handshake header which is P2PFILESHARINGPROJ.
     *Bytes 18-27 (10 bytes): 0.
     *Bytes 28-31 (4 bytes): peerID.
     *Total of 32 bytes.
     *
     *@param peerID: the sender's peerID
     */
	public HandshakeMessage(int peerID) {
		
		ByteBuffer bb = ByteBuffer.allocate(32);

		bb.put("P2PFILESHARINGPROJ".getBytes());
		bb.put(ByteBuffer.allocate(10));	//Adds 10 zero bytes.
		bb.putInt(peerID);

		messageBytes = bb.array();

		this.peerID = peerID;

	}

	/**
	 * Getter for peerID
	 *@return the peerID, stored in the message, of the message sender.
	 */
	public int getPeerID() {
		return peerID;
	}

}