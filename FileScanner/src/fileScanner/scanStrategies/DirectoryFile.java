package fileScanner.scanStrategies;

import java.io.File;

import fileScanner.FileInfo;

public class DirectoryFile extends FileInfo {

	private static DirectoryFile instance = new DirectoryFile();

	// make the constructor private so that this class cannot be instantiated
	private DirectoryFile(){}
	
	public static DirectoryFile getInstance(){
		return instance;
	}
	
	@Override
	public String getFileDescription() {
		return "directory";
	}	
	
	@Override
	public long getFileSize(File file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getFileDimension(File file) {
		// TODO Auto-generated method stub
		return new int[]{0,0};
	}

	@Override
	public long getFileCompression(File file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getFileCompression(long fileSize, int[] fileDim) {
		return 0;
	}
}
