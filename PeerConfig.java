//package

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class PeerConfig {

	private ArrayList<PeerInfo> peers = new ArrayList<PeerInfo>();

	/**
	 * Constructor. 
	 */
	public PeerConfig(){

		try {
			FileReader fr = new FileReader("PeerInfo.cfg");
			BufferedReader br = new BufferedReader(fr);

			String currentLine;
			String[] words;

			int peerID;
			String hostName;
			int listeningPort;
			boolean hasFile;

			int lineNumber = 0;
			while((currentLine = br.readLine()) != null) {
				
				words = currentLine.split("\\s+");
                
                peerID = Integer.parseInt(words[0]);
                hostName = words[1];
                listeningPort = Integer.parseInt(words[2]);
                hasFile = Integer.parseInt(words[3]);
				
				peers.add(new PeerInfo(peerID, hostName, listeningPort, hasFile));
			}
		}

		catch (Exception e){
			System.out.println(e);
		}

	}

     /**
	 * getter for peers.
	 * @return peers
	 */
	public ArrayList<PeerInfo> getPeers() {
		return peers;
	}

	/**
     * setter for peers.
     * @param peers to set 
     */
	public void setPeers(ArrayList<PeerInfo> peers) {
		this.peers = peers;
	}

	/**
	 * Outputs all of the PeerInfo data as a string.
	 * @return collection of PeerInfo data as string.
	 */
	public String toString() {
		String str = "";

		for(PeerInfo info : peers) {
			str += info.toString() + "\n";
		}

		return str;
	}
}