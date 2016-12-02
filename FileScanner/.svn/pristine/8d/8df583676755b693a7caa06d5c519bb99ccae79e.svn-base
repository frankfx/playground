/**
 * FileTree data structure
 *
 * @author Rene Frank
 * @version 1.0
 */

package fileScanner.dataStructure;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileTree implements TreeModel {
	private Node<FileData> root;

	/**
	 * FileTree constructor creates a new FileTree with a root node and an empty child list
	 *
	 * @param rootData data to create the root node
	 */
	public FileTree(FileData rootData) {
		root = new Node<FileData>(rootData);
		root.setChildren(new ArrayList<Node<FileData>>());
	}
	
	/**
	 * Sorts the tree elements to a list with preorder traversal
	 * 
	 * @param node the node of the branch where the operation starts
	 * @param list the list where all tree elements will be placed in
	 */			
	public void preOrder(Node<FileData> node, List<FileData> list) {
		list.add(node.getData());
		if (node.getChildren() != null)
			for (Node<FileData> tmp : node.getChildren())
				preOrder(tmp, list);
	}

	/**
	 * Sorts the tree elements to a list with postorder traversal
	 * 
	 * @param node the node of the branch where the operation starts
	 * @param list the list where all tree elements will be placed in
	 */			
	public void postOrder(Node<FileData> node, List<FileData> list) {
		if (node.getChildren() != null)
			for (Node<FileData> tmp : node.getChildren())
				postOrder(tmp, list);
		list.add(node.getData());
	}	
	
	/**
	 * Returns the root element as type Node
	 * 
	 * @return the root of the tree
	 */			
	public Node<FileData> getRootElement(){
		return root;
	}
	
	/**
	 * Returns the root element as type Object (important for TreeModel and JTree) 
	 * 
	 * @return the root of the tree
	 */			
	@Override
	public Object getRoot() {
		return this.root;
	}

	/**
	 * Returns the child of a given Object 
	 * 
	 * @param parent the node of the branch where the operation starts 
	 * @param index the index of the child in parents' child list
	 * 
	 * @return parents' child at the given index
	 */		
	@Override
	public Object getChild(Object parent, int index) {
		if (parent instanceof Node<?>)
			return ((Node<?>) parent).getChildren().get(index);
		return null;
	}

	/**
	 * Returns the number of children of a node
	 * 
	 * @param parent the node of the branch where the operation starts 
	 * 
	 * @return the size of the child list
	 */		
	@Override
	public int getChildCount(Object parent) {
		if (parent instanceof Node<?>)
			return ((Node<?>) parent).getChildren().size();
		return -1;
	}

	/**
	 * Checks if a node is a leaf
	 * 
	 * @param node the node to check if it is a leaf
	 * 
	 * @return true if the node is a leaf otherwise false
	 */		
	@Override
	public boolean isLeaf(Object node) {
		// TODO Auto-generated method stub
		if (node instanceof Node<?>)
			return ((Node<?>) node).isLeaf();
		return false;
	}

	/**
	 * Returns the index of a child node
	 * 
	 * @param parent the node of the branch where the operation starts
	 * @param child node to search for 
	 * 
	 * @return the index of the child if it was found, otherwise -1
	 */		
	@Override
	public int getIndexOfChild(Object parent, Object child) { 
		if (parent instanceof Node<?> && parent instanceof Node<?>){
			Node<?> parNode = (Node<?>) parent;
			Node<?> childNode = (Node<?>) child;
			
			for (int i=0; i<parNode.getChildren().size(); i++){
				if (parNode.getChildren().get(i).equals(childNode))
					return i;
			}
		}
		return -1;
	}
	
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {}

	@Override
	public void addTreeModelListener(TreeModelListener l) { }

	@Override
	public void removeTreeModelListener(TreeModelListener l) {}
}