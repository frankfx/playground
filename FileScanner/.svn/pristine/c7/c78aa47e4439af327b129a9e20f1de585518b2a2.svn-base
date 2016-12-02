/**
 * The Scanner class implements methods to scan a file system
 *
 * @author Rene Frank
 * @version 1.0
 */

package fileScanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import fileScanner.dataStructure.FileData;
import fileScanner.dataStructure.FileTree;
import fileScanner.dataStructure.Node;
import fileScanner.scanStrategies.DirectoryFile;
import fileScanner.scanStrategies.GifFile;
import fileScanner.scanStrategies.JpegFile;
import fileScanner.scanStrategies.UnknownFile;

public class Scanner {

	/**
	 * Scans a file system and builds a file tree with full directory information
	 *
	 * @param file file to scan
	 * 
	 * @return a tree object with FileData elements
	 */
	public FileTree scanFull(File file){
		// create tree with root node
		FileTree fileTree = new FileTree(scanFile(file));
		
		// scan the file/directory and build a file tree in memory
		scanDir(file, fileTree.getRootElement());
		
		// compute the sum of the file sizes in the directory and its subdirectories, 
		updateDirectory(fileTree.getRootElement());
		
		// compute the average image dimensions and average compression rates
		updateDirAverage(fileTree.getRootElement());
		
		return fileTree;
	}	

	/**
	 * Scans a file system and builds the file tree with FileData elements
	 *
	 * @param file file to scan
	 * @param node the node of the branch where the construction starts (root)
	 */	
	public void scanDir(File file, Node<FileData> node){
		if (file.isDirectory()){
			if (node.getChildren() == null)
				node.addEmptyChildBranch();
			for (File subFile : file.listFiles()){
				Node <FileData> childNode = new Node<FileData>(scanFile(subFile));
				
				node.addChild(childNode);
				scanDir(subFile, childNode);
			}
		}
	}	
	
	/**
	 * Scans a single file
	 *
	 * @param file file to scan
	 * 
	 * @return the information of the scanned file
	 */		
	public FileData scanFile(File file){
		int type = FileInfo.getFileType(file);
		
		switch (type) {
		case FileInfo.DIRECTORY:
			return _scanFileInfo(file, type, DirectoryFile.getInstance());
		case FileInfo.GIF_PICTURE:
			return _scanFileInfo(file, type, GifFile.getInstance());
		case FileInfo.JPEG_PICTURE:
			return _scanFileInfo(file, type, JpegFile.getInstance());
		default :
			return _scanFileInfo(file, type, UnknownFile.getInstance());
		}
	}	
	
	private FileData _scanFileInfo(File file, int type, FileInfo info){	
		long fileSize  = info.getFileSize(file);
		int [] fileDim = info.getFileDimension(file);
		
		return new FileData(info.getName(file), type, info.getFileDescription(), 
				fileSize, fileDim, info.getFileCompression(fileSize, fileDim));
	}	
	
	/**
	 * Computes the sum of the file sizes in the directory and its sub-directories, 
	 * the average image dimensions and average compression rates
	 *
	 * @param node the node of the branch where the operation starts
	 * 
	 * @return updated node with complete directory information
	 */		
	public Node<FileData> updateDirectory(Node<FileData> node){
		if(nodeIsDirectory(node) && node.hasChildren()){
			for (Node<FileData> curNode : node.getChildren())
				if (nodeIsDirectory(curNode)){
					FileData tmp = updateDirectory(curNode).getData();
					addDirectoryData(node, tmp);
				}
				else
					addDirectoryData(node, curNode.getData());
		}
		return node;
	}	

	private boolean nodeIsDirectory(Node<FileData> node){
		return node.getData().getType() == FileInfo.DIRECTORY;
	}
	
	private void addDirectoryData(Node<FileData> node, FileData dat){
		node.getData().addToLength(dat.getLength());
		node.getData().addToSize(dat.getSize());
		node.getData().addToComp(dat.getCompression());
	}

	private void updateDirAverage(Node<FileData> node){
		if (nodeIsDirectory(node) && node.hasChildren()){
			node.getData().setCompression(node.getData().getCompression() / node.getCildrenLeafCnt());
			
			int [] dim = node.getData().getSize();
			int leafCnt = node.getCildrenLeafCnt();
			int w = dim[0] / leafCnt;
			int h = dim[1] / leafCnt;
			
			node.getData().setSize(new int []{w,h});
		
			for (Node<FileData> curNode : node.getChildren())
				updateDirAverage(curNode);
		}
	}
	
	/**
	 * Prints the information of the file tree
	 *
	 * @param fileTree the tree to be printed
	 */	
	public void printFileTree(FileTree fileTree){
		List<FileData> list = new ArrayList<FileData>();
		fileTree.preOrder(fileTree.getRootElement(), list);
		System.out.println(list);		
	}	

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner();
		for (String arg : args){
			FileTree fileTree = scanner.scanFull(new File(arg));
			
			scanner.printFileTree(fileTree);
		}
	}
}
