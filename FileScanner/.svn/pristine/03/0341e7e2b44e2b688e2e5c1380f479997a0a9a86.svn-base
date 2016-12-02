/**
 * The FileData class saves the file informations
 *
 * @author Rene Frank
 * @version 1.0
 */

package fileScanner.dataStructure;

public class FileData {

	private String name;
	private int type;
	private String typeDesc;
	private long length;
	private int [] size;
	private long compression;
	
	/**
	 * Default constructor to create an empty data object
	 */	
	public FileData() {}

	/**
	 * Constructor to create a data object with initialized values
	 *
	 * @param name name of the file
	 * @param type intern numeric file type
 	 * @param typeDesc description of the file type
	 * @param length the size of the file 
	 * @param size the dimension of the file
	 * @param compression the compression rate of the file
	 */	
	public FileData(String name, int type, String typeDesc, long length, int[] size, long compression) {
		this.name = name;
		this.type = type;
		this.typeDesc = typeDesc;
		this.length = length;
		this.size = size;
		this.compression = compression;
	}	

	/**
	 * Returns the saved file name
	 * 
	 * @return name of the file
	 */	
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the file object
	 * 
	 * @param name name of the file
	 */		
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the numeric type of the file
	 * 
	 * @return the numeric type
	 */		
	public int getType() {
		return type;
	}
	
	/**
	 * Sets the numeric file type
	 * 
	 * @param type the numeric file type
	 */		
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * Returns the file description
	 * 
	 * @return the description of the file
	 */		
	public String getTypeDesc() {
		return typeDesc;
	}
	
	/**
	 * Sets the description of the file
	 * 
	 * @param typeDesc the description of the file
	 */		
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}	
	
	/**
	 * Returns the length of the file
	 * 
	 * @return the length of the file
	 */		
	public long getLength() {
		return length;
	}
	
	/**
	 * Sets the length value
	 * 
	 * @param length the new length value
	 */		
	public void setLength(long length) {
		this.length = length;
	}
	
	/**
	 * Returns the size of the file
	 * 
	 * @return the size of the file
	 */			
	public int[] getSize() {
		return size;
	}
	
	/**
	 * Sets the size value
	 * 
	 * @param size the new size value
	 */		
	public void setSize(int[] size) {
		this.size = size;
	}
	
	/**
	 * Returns the compression of the file
	 * 
	 * @return the compression of the file
	 */			
	public long getCompression() {
		return compression;
	}
	
	/**
	 * Sets the compression value
	 * 
	 * @param compression the new compression value
	 */	
	public void setCompression(long compression) {
		this.compression = compression;
	}
	
	/**
	 * Adds length information to saved length value
	 * 
	 * @param len the additional length value
	 */	
	public void addToLength(long len){
		length += len; 
	}

	/**
	 * Adds compression information to saved compression value
	 * 
	 * @param comp the additional compression value
	 */
	public void addToComp(long comp){
		compression += comp; 
	}	
	
	/**
	 * Adds size information to saved size value
	 * 
	 * @param size the additional size value
	 */		
	public void addToSize(int [] size){
		this.size[0]+=size[0];
		this.size[1]+=size[1];
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("=======================================================\n");
		sb.append("name: " + name + ", type: " + typeDesc + "\n");
		sb.append("  length:      " + length + "\n");
		sb.append(size == null ?  "  size:        error\n" : "  size:        " + size[0] + " x " + size[1] + "\n");
		sb.append("  compression: " + compression + "%\n");
		sb.append("=======================================================\n");		
		return sb.toString();
	}
}
