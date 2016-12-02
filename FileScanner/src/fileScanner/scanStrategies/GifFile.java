package fileScanner.scanStrategies;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import fileScanner.FileInfo;

public class GifFile extends FileInfo {
	
	private static GifFile instance = new GifFile();

	// make the constructor private so that this class cannot be instantiated
	private GifFile(){}
	
	public static GifFile getInstance(){
		return instance;
	}	
	
	@Override
	public String getFileDescription() {
		return "GIF-image";
	}	
	
	@Override
	public long getFileSize(File file) {
		return file.length();
	}

	@Override
	public int[] getFileDimension(File file) {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			dis.skip(6);
			int wLow = dis.readUnsignedByte();
			int wHigh = dis.readUnsignedByte();
			int hLow = dis.readUnsignedByte();
			int hHigh = dis.readUnsignedByte();

			int[] result = new int[2];
			result[0] = (wHigh << 8) | (wLow & 0xFF);
			result[1] = (hHigh << 8) | (hLow & 0xFF);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (dis != null)
					dis.close();
				if (bis != null)
					bis.close();
				if (fis != null)
					fis.close();
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	@Override
	public long getFileCompression(File file) {
		int [] dim = getFileDimension(file);
		if (dim != null){
			long fileSize = getFileSize(file);
			return fileSize * 100 / (dim[0] * dim[1]);
		}
		return -1;
	}	
	
	@Override
	public long getFileCompression(long fileSize, int [] fileDim){
		return fileDim != null ? fileSize * 100 / (fileDim[0] * fileDim[1]) : -1;
	}
	
}
