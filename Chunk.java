/* 
 * Chunks are the divided up parts of a file being transmitted. 
 */

public class Chunk{

	private byte[] content; 
	private boolean hasContent;
	private boolean requested;

	/*
	 * Constructor. Creates empty chunk.
	 */
	public Chunk(int chunkSize){
		content = new byte[chunkSize];
		hasContent = false;
		requested = false;
	}

	/*
	 * Constructor. Creates chunk with some content.
	 * @param content: the file content the chunk will store.
	 */
	public Chunk(byte[] content){
		this.content = content;
		hasContent = true;
		requested = false;
	}

	/*
	 * Getter for the content of a chunk.
	 * @return byte[]: the content of the chunk.
	 */
	public byte[] getContent(){

		if(hasContent){
			return content;
		} else{
			return null;
		}
	}

	/*
	 * Setter for the chunk content.
	 * @param content: the content the chunk will store.
	 */
	public void setContent(byte[] content){

		this.content = content;
		hasContent = true;
	}

	/*
	 * Return whether a chunk has content
	 * @return boolean: whether or not a chunk has content
	 */
	public boolean hasContent(){
		return hasContent;
	}

	/*
	 * Return the "requested" variable which is a bool that says whether a chunk was requested.
	 * @return boolean: whether or not the chunk was requested.
	 */
	public boolean requested(){
		return requested;
	}

	/*
	 * Sets requested to true.
	 */
	public void hasBeenRequested(){
		requested = true;
	}



}
