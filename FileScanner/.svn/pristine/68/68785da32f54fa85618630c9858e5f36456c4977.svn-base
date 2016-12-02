package fileScanner.scanStrategies;

import java.io.File;

import fileScanner.FileInfo;

public class UnknownFile extends FileInfo {

	private static UnknownFile instance = new UnknownFile();

	// make the constructor private so that this class cannot be instantiated
	private UnknownFile(){}
	
	public static UnknownFile getInstance(){
		return instance;
	}	
	
	@Override
	public String getFileDescription() {
		// TODO Auto-generated method stub
		return "unknown";
	}

	@Override
	public long getFileSize(File file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getFileDimension(File file) {
		// TODO Auto-generated method stub
		return new int []{0,0};
	}

	@Override
	public long getFileCompression(File file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getFileCompression(long fileSize, int[] fileDim) {
		// TODO Auto-generated method stub
		return 0;
	}

}
