package fileScanner;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public abstract class FileInfo {
	public final static int UNKNOWN = -1;
	public final static int DIRECTORY = 0;
	public final static int GIF_PICTURE = 1;
	public final static int JPEG_PICTURE = 2;
	
	/**
	 * Returns the numeric type of the given file
	 *
	 * @param file file to determine the type
	 * 
	 * @return the numeric type of the file
	 */
	public static int getFileType(File file) {
		byte[] fileStart = new byte[0];

		if (file.isDirectory()) {
			return DIRECTORY;
		}

		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream dis = new DataInputStream(bis);

			fileStart = new byte[(int) Math.min(file.length(), 10)];

			dis.read(fileStart);
			dis.close();
			bis.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (fileStart.length >= 6) {
			if ((fileStart[0] == (byte) 'G') && (fileStart[1] == (byte) 'I') && (fileStart[2] == (byte) 'F')
					&& (fileStart[3] == (byte) '8') && (fileStart[5] == (byte) 'a')) {
				return GIF_PICTURE;
			}
		}

		if (fileStart.length >= 10) {
			if ((fileStart[0] == (byte) 0xFF) && (fileStart[1] == (byte) 0xD8) && (fileStart[2] == (byte) 0xFF)
					&& (fileStart[3] == (byte) 0xE0) && (fileStart[6] == (byte) 'J') && (fileStart[7] == (byte) 'F')
					&& (fileStart[8] == (byte) 'I') && (fileStart[9] == (byte) 'F')) {
				return JPEG_PICTURE;
			}
		}
		return UNKNOWN;
	}

	/**
	 * Returns the name of the given file
	 *
	 * @param file to determine the name
	 * 
	 * @return the name of the file
	 */	
	public String getName(File file){
		return file.getName();
	}
	
	/**
	 * Returns the description of the file
	 *
	 * @return the description of the file
	 */		
	public abstract String getFileDescription();

	/**
	 * Returns the size of the file
	 *
	 * @param file to determine the length
	 *
	 * @return the size (length) of the file
	 */		
	public abstract long getFileSize(File file);

	/**
	 * Returns the dimension of the file
	 *
	 * @param file to determine the dimension
	 *
	 * @return the dimension of the file
	 */		
	public abstract int[] getFileDimension(File file); 

	/**
	 * Returns the compression rate of the file
	 *
	 * @param file to determine the compression rate
	 * 
	 * @return the compression rate of the file
	 */		
	public abstract long getFileCompression(File file);
	
	/**
	 * Returns the compression rate based on the given file size and dimension
	 *
	 * @param fileSize the size (length) of the file
	 * @param fileDim the dimension of the file
	 * 
	 * @return the compression rate of the file
	 */			
	public abstract long getFileCompression(long fileSize, int [] fileDim);
	
}
