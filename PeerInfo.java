import java.util.ArrayList;


public class PeerInfo {

	private int peerID;
	private String hostName;
	private int listeningPort;
    
    private Bitfield bitfield;
	private Integer chunkRate; 
	private boolean interested;
	private boolean choked;
	
	ArrayList<Integer> interestedChunk;
    
    /**
     * Default constructor.
     */
	public PeerInfo() {
		this.peerID = -1;
		this.hostName = null;
		this.listeningPort = -1;
		this.interested = false;
		this.chunkRate = 0;
		this.bitfield = new Bitfield(PeerProcess.myConfig.FileSize, false);
		this.choked = true;
		this.interestedChunk = new ArrayList<Integer>();
	}

    /**
	 * Constructor for PeerInfo. Sets the properties accordingly.
	 * @param peerID: the unique number that represents a peer.
	 * @param hostName: address of peer.
	 * @param listeningPort: port to communicate with.
	 * @param hasFile: does per have full file or not?
	 */
	public PeerInfo(int peerID, String hostName, int listeningPort, boolean hasFile) {
		this.peerID = peerID;
		this.hostName = hostName;
		this.listeningPort = listeningPort;
		this.interested = false;
		this.chunkRate = 0;
		this.bitfield = new Bitfield(PeerProcess.myConfig.FileSize, hasFile);
		this.choked = true;
		this.interestedChunk = new ArrayList<Integer>();
	}


    /**
	 * getter for peerID.
	 * @return PeerID
	 */
	public int getPeerID() {
		return peerID;
	}

    /**
     * setter for peerID.
     * @param peerID: unique number to set peerID to.
     */
	public void setPeerID(int peerID) {
		this.peerID = peerID;
	}

    /**
	 * getter for the HostName.
	 * @return hostName
	 */
	public String getHostName() {
		return hostName;
	}

    /**
     * setter for host name.
     * @param hostName: address to set hostname to. 
     */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}


    /**
	 * getter for listeningPort.
	 * @return listeningPort.
	 */
	public int getListeningPort() {
		return listeningPort;
	}

    /**
     * setter for listeningPort.
     * @param listeningPort: the port to set this to.
     */
	public void setListeningPort(int listeningPort) {
		this.listeningPort = listeningPort;
	}

    
    /**
	 * getter for bitfield.
	 * @return bitfield.
	 */
    public Bitfield getBitfield() {
		return bitfield;
	}

	/**
     * setter for bitfield.
     * @param bitfield to set this to. 
     */
	public void setBitfield(Bitfield bitfield) {
		this.bitfield = bitfield;
	}

	/**
	 * getter for hasFile.
	 * @return hasFile.
	 */
	public boolean hasFile() {
		return bitfield.isComplete();
	}

    /**
	 * getter for chunkRate.
	 * @return chunkRate.
	 */
	public Integer getchunkRate() {
		return chunkRate;
	}

    /**
     * setter for chunkRate.
     * @param chunkRate: new rate. 
     */
	public void setDownloadRate(Integer chunkRate) {
		this.chunkRate = chunkRate;
	}
	
	/**
	 * getter for interested.
	 * @return interested.
	 */
	public boolean isInterested() {
		return interested;
	}

    /**
     * setter for interested.
     * @param b: boolean to set interested to.
     */
	public void setInterested(boolean b) {
		this.interested = b;
	}

    /**
	 * getter for choked.
	 * @return choked.
	 */
	public boolean isChoked() {
			return choked;
		
	}

	/**
     * setter for choked.
     * @param b: boolean to set choked to.
     */
	public void setChoked(boolean b) {
		this.choked = b;
	}

	/**
	 * Outputs PeerInfo data as a string.
	 * @return PeerInfo data as string.
	 */
	public String toString() {
		String str = peerID + " " + hostName + " " + listeningPort + " ";
		if( hasFile() )
			str += "1";
		else
			str += "0";

		return str;
	}
}
