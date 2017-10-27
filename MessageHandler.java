/* MESSAGE HANDLER generates the different messages
*/

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;


public class MessageHandler{

	/* MESSAGE SEND sends message to remote peer
	 * Sending message to the socket ID identified in the DataOutputStream
	 * Sending Message mssg
	*/
	private static void messageSend(Message mssg, DataOutputStream outputData){

		outputData.write(mssg.getMessageBytes(), 0, mssg.getMessageBytes().length);	
	} 

	/* MESSAGE RECEIVE creates a received messaged from the content received
	 * MessageContent is passed 
	*/
	public static Message messageReceive(DataInputStream inputData){

		byte[] arrayOfBytes = new byte[18];
		
		inputData.readFully(arrayOfBytes);

		String s = new String(arrayOfBytes);

		if(s.equals("P2PFILESHARINGPROJ")){

			ByteBuffer b = ByteBuffer.allocate(14);
			inputData.readFully(b.array());

			int peerID = buffer.getInt(10);

			return new HandshakeMessage(peerID);
		}
		else{

			ByteBuffer b = ByteBuffer.allocate(18);
			b.put(arrayOfBytes);
			b.position(0);
			int length = b.getInt();
			byte type = buffer.get();

			byte[] payload = new byte[length - 1];
			inputData.readFully(payload);

			return new actualMessage(type, payload);
		}

	}

		/* HANDSHAKE message sent to remote peer
	 * Parameters include the socket of the current peer and the peerID of the remote peer
	*/
	public static void handshake(DataOutputStream outputData, int peerID){

		Message mssg = new HandshakeMessage(int peerID);

		messageSend(outputData, mssg);
	}

	/* 0 CHOKE: has no payload
	 * All neighbors previously unchoked but not selected as preferred neighbors
	 * should be choked unless it is an optimistically unchoked neighbor.
	 * To choke neighbors, peer A sends a 'choke' message and stops sending pieces
	 * Message is sent to the peer with the socket ID passed as a parameter
	*/
	public static void choke(DataOutputStream outputData){

		byte messageType = 0;
		byte[] payload = new byte[0]

		Message chokeMessage = new actualMessage( messageType, payload);

		messageSend(outputData, chokeMessage);
	}

	/* 1 UNCHOKE: has no payload
	 * The neighbors that each peer uploads its pieces to are unchoked.
	 * There is an unchoking time interval, once it is done, the peer unchokes 
	 * preferred neighbors by sending 'unchoke' message
	 * Peer expects to receive 'request' messages from unchoked neighbors
	 * Message is sent to the peer with the socket ID passed as a parameter
	*/
	public static void unchoke(DataOutputStream outputData){

		byte messageType = 1;
		byte[] payload = new byte[0];

		Message unchokeMessage = new actualMessage( messageType, payload);

		messageSend(outputData, unchokeMessage);
	}

	/* 2 INTERESTED: has no payload
	 * If a neighbor has some interesting pieces, then peer sends 'interested' message to neighbor. 
	 * Whenever a peer receives 'bitfield' or 'have' message from neighbor,
	 * it determines if it should send an 'interested' message.
	 * Message is sent to the peer with the socket ID passed as a parameter
	*/
	public static void interested(DataOutputStream outputData){

		byte messageType = 2;
		byte[] payload = new byte[0];

		Message interestedMessage = new actualMessage( messageType, payload);

		messageSend(outputData, interestedMessage);
	}

	/* 3 NOT INTERESTED: has no payload
	 * Each peer maintains bitfields for all neighbors and updates them with 'have' messages.
	 * If a neighbor doesn't have any interesting pieces, then the peer sends 'not interested' message.
	 * When a peer receives a piece completely, it checks bitfield of its neighbors
	 * and decides if it should send 'not interested' messages to some neighbors.
	 * Message is sent to the peer with the socket ID passed as a parameter
	*/
	public static void notInterested(DataOutputStream outputData){

		byte messageType = 3;
		byte[] payload = new byte[0];

		Message notInterestedMessage = new actualMessage( messageType, payload);

		messageSend(outputData, notInterestedMessage);
	}

	/* 4 HAVE: have a payload that contains a 4-byte piece index field.
	 * Peer A can receive a 'have' message from a remote peer that contains the index
	 * of a peer not in Peer A. Peer A will send 'interested' message to remote peer.
	 * Message is sent to the peer with the Socket ID passed as a parameter
	 * chunkLocation is the place of the piece they have
	*/
	public static void have(DataOutputStream outputData, int chunkLocation){

		byte messageType = 4;
		ByteBuffer b = ByteBuffer.allocate(4);
		b.putInt(chunkLocation);

		Message haveMessage = new actualMessage( messageType, b.array());

		messageSend(outputData, haveMessage);
	}

	/* 5 BITFIELD: bitfield as its payload
	 * Each bit in the bitfield payload represents whether the peer has the corresponding piece.
	 * First byte of the bitfield corresponds to chunks located in indices 0-7 from high bit to low bit
	 * The next one corresponds to chunks located in indicies 8-15...etc
	 * Peers that don't have anything may skip a 'bitfield' message.
	 * Message is sent to the peer with the socket ID passed as a parameter
	 * Bitfield parameter is the chunks that remote peer has
	*/
	public static void bitfield(dataOutputStream outputData, byte[] bitfield){

		byte messageType = 5;

		Message bitfieldMessage = new actualMessage( messageType, bitfield);

		messageSend(outputData, bitfieldMessage);
	}

	/* 6 REQUEST: payload consists of 4-byte piece index field. 
	 * Don't divide a piece into smaller subpieces.
	 * When a connection is unchoked by a neighbor, a peer sends a 'request' message
	 * requesting a piece that it does not have and has not requested from other neighbors. 
	 * Using a random selection strategy
	 * Exchange continues until peer is choked or it does not have any more interesting pieces. 
	 * Message is sent to the peer with the socket ID passed as a parameter
	*/
	public static void request(DataOutputStream outputData, int chunkLocation){

		byte messageType = 6; 

		/* TO DO: 
		 * Check to see if another peer has requested the chunk 
		 * If not, request it
		 * Send Message 
		*/
	}

	/* 7 CHUNK: piece messages have a payload which consists of 4-byte chunk index field and the content of the chunk
	 * This sends the chunk requested to the peer
	 * Message is sent to the peer with the socket ID passed as a parameter
	 * chunkLocation is the location of the chunk being sent
	 * Payload is passed as a paramter
	*/
	public static void chunk(DataOutputStream outputData, int chunkLocation, byte[] payload){

		byte messageType = 7;
		ByteBuffer b = ByteBuffer.allocate(payload.length + 4);
		b.putInt(chunkLocation);
		b.put(payload);

		Message chunkMessage = new actualMessage( messageType, b.array());

		messageSend(outputData, chunkMessage);
	}




























}