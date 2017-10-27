import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class FileManager {
	private ArrayList<Chunk> chunks; 
	private Bitfield bitfield;
	private int numChunksDownloaded;
	
	/* Constructor. Sets up bitfield and Chunk Arraylist.
	 * If process has files, breaks file into chunks and stores them. 
	 * @param numChunks: the num of chunks that compose the file.
	 * @param fileSize: the size of the file.
	 * @param hasFile: a bool representing whether or not the process has the file.
	 * @param fileName: the name of the file as a string.
	 */
	public FileManager(int numChunks, int fileSize, boolean hasFile, String fileName) {

		bitfield = new Bitfield(numChunks, hasFile);

		if (hasFile) {	//fill chunks array with file data.
			chunks = new ArrayList<Chunk>();
			readFile(numChunks);
		} else {		//fill chunks array with empty chunks.
			chunks = new ArrayList<Chunk>();
			for (int i = 0; i < numChunks; i++) {
				chunks.add(new Chunk());
			}
		}

		numChunksDownloaded = 0;
		
	}

	/* Gets called if process is the one that starts with the file. 
	 * Breaks the file into chunks and stores them.
	 * @param numChunks: the num of chunks that compose the file.
	 * @param fileSize: the size of the file.
	 * @param fileName: the name of the file as a string.
	 */
	private void readFile(int numChunks, int fileSize, String fileName) {

		FileInputStream fis = null;
		byte[] content;

		try {
			fis = new FileInputStream(new File(fileName));
			content = new byte[filesize];
			fis.read(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//for each chunk i
		for (int i = 0; i < numChunks; i++) {

			int chunkSize = filesize/numChunks;
			byte[] chunkData = new byte[chunkSize];
			
			//for each byte j of the chunk i
			for (int j = 0; j < chunkSize; j++) {
				int chunkContentIndex = i * chunkContent.length
				//If the file has been fully chunked and we are now just at chunk slack space
				if (i * chunkData.length + j >= content.length) {
					chunkData[j] = (byte) 0;
				} else {	//else put the actual data in the chunk
					chunkData[j] = data[i * chunkData.length + j];
				}
			}

			chunks.add(new Chunk(chunkData));
		}
	}
	
	/* Receive chunk from peer. 
	 *
	 */
	public void receiveChunk(int index, byte[] data) {
			
		chunks.get(index).setChunkData(data);
		bitfield.markChunkAsObtained(index);

		for (int i = 0; i < PeerProcess.connections.size(); i++) {
			
			//TODO: remove index from each interestedChunks.

		}		

		numChunkDownloaded++;
	}

	/* Returns whether the file has been fully obtained or not. 
	 * @return boolean: whether the bitfield is fully true, which means the whole file has been obtained.
	 */
	public boolean isComplete()
	{
		return bitfield.isComplete();
	}

	/* Getter for the file chunks.
	 * @return Arraylist<Chunk>: all of the file chunks. Chunks may be empty.
	 */
	public ArrayList<Chunk> getChunks() {
		return chunks;
	}

	/* Setter for chunks list.
	 * @param chunks: the chunks list to set this object's to.
	 */
	public void setChunks(ArrayList<Chunk> chunks) {
		this.chunks = chunks;
	}

	/* Getter for FileManager's bitfield object.
	 * @return bitfield: this object's bitfield.
	 */
	public Bitfield getBitfield() {
		return bitfield;
	}
	
	/* Setter for FileManager's bitfield object.
	 * @param bitfield: the bitfield to set FileManager's to.
	 */
	public void setBitfield(Bitfield bitfield) {
		this.bitfield = bitfield;
	}
	
	/* Getter for chunk.
	 * @return chunk: the chunk at the index. 
	 */
	public Chunk getChunk(int index) {	
		return chunks.get(index);
	}
	
	/* Getter for numChunksChunksDownloaded.
	 * @return int the numChunksDownloaded.
	 */
	public int getNumChunksDownloaded() {
		return numChunksDownloaded;
	}
}
