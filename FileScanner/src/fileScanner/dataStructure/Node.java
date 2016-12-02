package fileScanner.dataStructure;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
	private T data;
    private List<Node<T>> children;

	/**
	 * Constructor to create a node object
	 * 
	 * @param data the data to be saved in the node
	 */	    
    public Node(T data){
    	this.data = data;
    }    
    
	/**
	 * Checks if the current node is a leaf
	 * 
	 * @return true if the node is a leaf otherwise false
	 */       
    public boolean isLeaf(){
    	if (children == null)
    		return true;
    	return children.isEmpty();
    }
    
	/**
	 * Returns the information if the node has children
	 * 
	 * @return true if the node has children otherwise false
	 */       
    public boolean hasChildren(){
    	return children != null && !children.isEmpty();
    }    
    
	/**
	 * Returns the number of all children (directories, files, ...)
	 * 
	 * @return the number of the node's children
	 */         
    public int getCildrenSize(){
    	return getCildrenSize(this, 0);
    }
    
    private int getCildrenSize(Node<T> node, int i){
    	if (!hasChildren())
    		return 0;
    	else{
    		i+= node.getChildren().size();
    		for(Node<T> n : node.getChildren()){
    			if (n.hasChildren())
    				i+= getCildrenSize(n,0);
    		}
    	}
    	return i;
    }

	/**
	 * Returns the number of all leaf nodes (without directories)
	 * 
	 * @return the number of the node's leafs
	 */       
    public int getCildrenLeafCnt(){
    	return getCildrenLeafCnt(this, 0);
    }    
    
    private int getCildrenLeafCnt(Node<T> node, int i){
    	if (node.getChildren() == null)
    		return 1;
    	else{
    		for(Node<T> n : node.getChildren()){
    			i+= getCildrenLeafCnt(n, 0);
    		}
    	}
    	return i;
    } 
    
    
	/**
	 * Adds an empty child branch if it does not exist
	 */    
    public void addEmptyChildBranch(){
        if (children == null) {
            children = new ArrayList<Node<T>>();
        }
    }
    
	/**
	 * Adds new child node to this node
	 * 
	 * @param child the new child of this node
	 */	
    public void addChild(Node<T> child) {
        addEmptyChildBranch();
        children.add(child);
    }    
    
	/**
	 * Removes a child at the given index
	 * 
	 * @param index the index of the child to be removed
	 */       
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }    
    
    
	/**
	 * Returns the data of this node
	 * 
	 * @return the data Object within this node
	 */       
	public T getData() {
		return data;
	}
	
	/**
	 * Sets a data Object to the node
	 * 
	 * @param data the data Object to be stored in the node
	 */   	
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * Returns the children of the node
	 * 
	 * @return the list of children of the node
	 */  	
	public List<Node<T>> getChildren() {
		return children;
	}
	
	/**
	 * Sets the children of the node
	 * 
	 * @param children the list of children of the node
	 */ 	
	public void setChildren(List<Node<T>> children) {
		this.children = children;
	}

	
	@Override
	public String toString(){
		return getData().toString();
	}
}
