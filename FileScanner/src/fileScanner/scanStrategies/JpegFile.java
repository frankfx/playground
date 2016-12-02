package fileScanner.scanStrategies;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import fileScanner.FileInfo;

public class JpegFile extends FileInfo {
	
	private static JpegFile instance = new JpegFile();

	// make the constructor private so that this class cannot be instantiated
	private JpegFile(){}
			
	public static JpegFile getInstance(){
		return instance;
	}	
	
	@Override
	public String getFileDescription() {
		return "JPEG-image";
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

			if (dis.readUnsignedByte() != 0xFF)
				return null;
			if (dis.readUnsignedByte() != 0xD8)
				return null;
			while (true) {
				int value = dis.readUnsignedByte();
				while (value == 0xFF) {
					value = dis.readUnsignedByte();
				}
				int size = dis.readUnsignedShort();
				if ((value >= 0xC0) && (value <= 0xC3)) {
						dis.readByte();
						int s1 = dis.readUnsignedShort();
						int s2 = dis.readUnsignedShort();
						return new int[]{s2, s1};
					} else {
						dis.skip(size - 2);
					}
				}
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
			return fileSize * 100 / (dim[0] * dim[1] * 3);
		}
		return -1;
	}
	
	@Override
	public long getFileCompression(long fileSize, int [] fileDim){
		return fileDim != null ? fileSize * 100 / (fileDim[0] * fileDim[1] * 3) : -1;
	}
}
