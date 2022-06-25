package edu.cofc.csci230;

/**
 * 
 * Binary search that does not allow two (or more) binary nodes 
 * in the search tree to have the same element value.
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 *
 * @param <AnyType>
 */
public class BinarySearchTree <AnyType extends Comparable<AnyType>> {
	
	// --------------------------------------
	// instance variable
	private BinaryNode<AnyType> root;
	
	/**
	 *
	 * Constructor with one parameter that
	 * sets the root node of the BST.
	 * 
	 * @param rootElement
	 * @throws NullBinaryNodeException
	 */
	public BinarySearchTree( AnyType rootElement ) throws NullBinaryNodeException {
		
		if ( rootElement == null ) {
			throw new NullBinaryNodeException();
		}
		
		this.root = new BinaryNode<AnyType>( rootElement ) ;
		
	} // end constructor
	
	/**
	 * If the BST does not have a root node, then it is empty. 
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		
		return ( root == null );
		
	} // end isEmpty() method
	
	/**
	 * Make the BST empty, i.e. a BST that has 
	 * no nodes (including a root node).
	 * 
	 */
	public void clear() {
		
		root = null;
		
	} // end clear() method
	
	/**
	 * Method that inserts a new node with the specified element 
	 * value in the BST.
	 * 
	 * This method has one parameter:
	 *  1) The element value to be added
	 * 
	 * If the BST has an existing node with the same element 
	 * value, throw an duplicate element exception.
	 * 
	 * @param element
	 * @throws DuplicateElementException
	 * @throws NullBinaryNodeException
	 */
	public void insert( AnyType element ) throws NullBinaryNodeException, DuplicateElementException {
		
		if ( element == null )
			throw new NullBinaryNodeException();
		
		if ( root == null )
			this.root = new BinaryNode<AnyType>( element );
		else
			insert( root, element );

	} // end insert() method
	
	/**
	 * Private recursive method that inserts a new node (with the 
	 * specified element value) in the BST.
	 * 
	 * This method has two parameters:
	 *  1) The node visited while recursively walking the BST
	 *  2) The element value to be added
	 * 
	 * If the BST has an existing node with the same element 
	 * value, throw an duplicate element exception.
	 * 
	 * @param node
	 * @param element
	 * @throws DuplicateElementException
	 */
	private void insert( BinaryNode<AnyType> node, AnyType element )  throws DuplicateElementException {
		
		if(element.compareTo(node.getElement()) == 0) {
			throw new DuplicateElementException();
		}
		
		if(element.compareTo(node.getElement()) > 0) {
			if(node.getRight() == null) {
				node.setRight(new BinaryNode(element));
				node.getRight().setParent(node);
			}
			else {
				insert(node.getRight(), element);
			}
		}
		
		if(element.compareTo(node.getElement()) < 0) {
			if(node.getLeft() == null) {
				node.setLeft(new BinaryNode(element));
				node.getLeft().setParent(node);
			}
			else {
				insert(node.getLeft(), element);
			}
		}
		
		/**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution MUST USE recursion
		 * 
		 */
	} // end insert() overloaded method
	
	/**
	 * Method that determines if a node with the specified element value 
	 * exists in the BST. 
	 * 
	 * This method has one parameter:
	 *  1) The element value that is being searched.
	 * 
	 * If the BST is empty, throw an empty binary search tree
	 * exception.
	 * 
	 * @param element
	 * @throws EmptyBSTException
	 * @throws NullBinaryNodeException
	 * @return
	 */
	public boolean search( AnyType element ) throws NullBinaryNodeException, EmptyBSTException {
		
		if ( element == null )
			throw new NullBinaryNodeException();
		if ( isEmpty() )
			throw new EmptyBSTException();
		
		return search( root, element );
		
	} // end search() method
	
	/**
	 * Private recursive method that determines if the element is 
	 * currently stored in the BST.
	 * 
	 * This method has two parameters:
	 *  1) The node visited while recursively walking the BST
	 *  2) The element value that is being searched.
	 *  
	 *  Hint: use the compareTo() method
	 * 
	 * @param element
	 * @param node
	 * @return
	 */
	private boolean search( BinaryNode<AnyType> node, AnyType element ) {
		
		boolean returnValue = false;
		
		if(element.compareTo(node.getElement()) == 0) {
			returnValue = true;
		}
		
		else if(element.compareTo(node.getElement()) < 0) {
			if(node.getElement() == null) {
				returnValue = false;
			}
			else if(node.getLeft() != null) {
				returnValue = search(node.getLeft(), element);
			}
		}
		
		else if(element.compareTo(node.getElement()) >= 0) {
			if(node.getElement() == null) {
				returnValue = false;
			}
			else if(node.getRight() != null) {
				returnValue = search(node.getRight(), element);
			}
		}
		
		return returnValue;
		
		/**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution MUST USE recursion
		 * 
		 */
	} // end search() overloaded method
	
	/**
	 * Find the node with the minimum element value in the BST.
	 * 
	 * This method has no parameters.
	 * 
	 * If the BST is empty, throw an empty binary search tree
	 * exception.
	 * 
	 * @return
	 * @throws EmptyBSTException
	 */
	public AnyType min() throws EmptyBSTException {
		
		if ( isEmpty() )
			throw new EmptyBSTException();
		
		return min( root ).getElement();
		
	} // end findMin() method
	
	/**
	 * Private recursive method that walks the BST searching
	 * for the node with the minimum element value.
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 * 
	 * @param node
	 * @return
	 */
	private BinaryNode<AnyType> min( BinaryNode<AnyType> node ) {
		
		BinaryNode<AnyType> returnNode = node;
		if(node.getLeft() != null) {
			returnNode = min(node.getLeft());
		}
		return returnNode;
		/**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution MUST USE recursion
		 * 
		 */
	} // end min() method
	
	/**
	 * Find the node with the maximum element value in the BST.
	 * 
	 * This method has no parameters.
	 * 
	 * If the BST is empty, throw an empty binary search tree
	 * exception.
	 * 
	 * @return
	 * @throws EmptyBSTException
	 */
	public AnyType max() throws EmptyBSTException {
		
		if ( isEmpty() )
			throw new EmptyBSTException();
		
		return max( root ).getElement();
		
	} // end max() method
	
	/**
	 * Private recursive method that walks the BST searching
	 * for the node with the maximum element value.
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 * 
	 * @param node
	 * @return
	 */
	private BinaryNode<AnyType> max( BinaryNode<AnyType> node ) {
		
		BinaryNode<AnyType> returnNode = node;
		if(node.getRight() != null) {
			returnNode = max(node.getRight());
		}
		return returnNode;
		
		/**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution must use recursion
		 * 
		 */
	} // end max() method

	/**
	 * Delete the node with the specified element value in the BST.
	 * 
	 * This method has one parameter:
	 * 	1) the element value to be deleted
	 * 
	 * This method performs the following delete operations
	 * 	1) delete node with no children (case 1)
	 * 	2) delete node with one child (case 2)
	 * 	3) delete node with two children (case 3)
	 * 
	 * If the BST is empty, throw an empty binary search tree
	 * exception.
	 * 
	 * If the element is null, throw a null binary node 
	 * exception
	 *  
	 * @param element
	 * @throws EmptyBSTException
	 * @throws NullBinaryNodeException
	 */
	public AnyType delete( AnyType element ) throws EmptyBSTException, NullBinaryNodeException {
		
		if ( element == null )
			throw new NullBinaryNodeException();
		if ( isEmpty() )
			throw new EmptyBSTException();
		
		return delete( root, element ).getElement();
		
	} // end delete() method


	/**
	 * Private recursive method that walk the BST looking for 
	 * the specified element value to be removed.
	 * 
	 * This method has two parameters:
	 *  1) The node visited while recursively walking the BST
	 *  2) The element value that is being removed.
	 *  
	 * @param node
	 * @param element
	 */
	private BinaryNode<AnyType> delete( BinaryNode<AnyType> node, AnyType element ) {
				
		if(node.getLeft() != null && node.getLeft().getElement().compareTo(element) == 0) {
			if(node.getLeft().getLeft() == null && node.getLeft().getRight() == null) {
				node.setLeft(null);
			}
			else if(node.getLeft().getRight() == null) {
				node.setLeft(node.getLeft().getLeft());
			}
			else if(node.getLeft().getLeft() == null) {
				node.setLeft(node.getLeft().getRight());
			}
			else {
				BinaryNode<AnyType> temp = min(node.getLeft().getRight());
				node.getLeft().setElement(temp.getElement());
				BinaryNode<AnyType> tempParent = temp.getParent();
				if(temp.getRight() != null) {
					tempParent.setLeft(temp.getRight());
					temp.getRight().setParent(tempParent);
				}
				delete(min(node.getLeft().getRight()).getParent(), temp.getElement());
			}
		}
		
		if(node.getRight() != null && node.getRight().getElement().compareTo(element) == 0) {
			if(node.getRight().getLeft() == null && node.getRight().getRight() == null) {
				node.setRight(null);
			}
			else if(node.getRight().getLeft() == null) {
				node.setRight(node.getRight().getRight());
			}
			else if(node.getRight().getRight() == null) {
				node.setRight(node.getRight().getLeft());
			}
			else {
				BinaryNode<AnyType> temp = min(node.getRight().getRight());
				node.getRight().setElement(temp.getElement());
				BinaryNode<AnyType> tempParent = temp.getParent();
				if(temp.getRight() != null) {
					tempParent.setLeft(temp.getRight());
					temp.getRight().setParent(tempParent);
				}
				delete(min(node.getRight().getRight()).getParent(), temp.getElement());
			}
		}
		
		if(element.compareTo(node.getElement()) > 0 && node.getRight() != null) {
			delete(node.getRight(), element);
		}
		else if(element.compareTo(node.getElement()) < 0 && node.getLeft() != null) {
			delete(node.getLeft(), element);
		}
		else if(element.compareTo(root.getElement()) == 0) {
			if(node.getLeft() == null && node.getRight() == null) {
				root = null;
			}
			else if(node.getLeft() == null) {
				root = node.getRight();
			}
			else if(node.getRight() == null) {
				root = node.getLeft();
			}
			else {
				root = node.getRight();
				node.getLeft().setParent(node.getRight());
				node.getRight().setLeft(node.getLeft());
			}
		}
		else if(element.compareTo(node.getElement()) != 0) {
			return null;
		}
		
		
		return node;
		
		/**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution MUST USE recursion
		 * 
		 */
	} // end delete() method


	/**
	 * Recursive method that traverses the BST 
	 * dynamically creating a string with the
	 * element values stored in an pre-order 
	 * tree traversal format.
	 * 
	 * The return string MUST be formated as follows:
	 * <element>,<element>,<element>,<element>, .... <element>
	 * where <element> is the element value
	 * For example,
	 * 	2,1,3
	 * Hint: you may want to use regular expressions
	 * 
	 * Discussed in class, please review 
	 * your notes
	 * 
	 * If the BST is empty, throw an empty binary search 
	 * tree exception
	 * 
	 * @throws EmptyBSTException 
	 * @return 
	 * 
	 */
	public String preOrder() throws EmptyBSTException {
		
		if ( isEmpty() )
			throw new EmptyBSTException();
		
		return preOrder( root );
		
	} // end preOrder() method
	
	/**
	 * Private recursive method that traverses the BST 
	 * dynamically creating a string with the
	 * element values stored in an pre-order 
	 * tree traversal format. 
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 *  
	 * @param node
	 * @return
	 * 
	 */
	private String preOrder( BinaryNode<AnyType> node ) {
		
		String returnString = ""+node.getElement()+",";
		
		if(node.getLeft() != null) {
			returnString += preOrder(node.getLeft());
		}
		if(node.getRight() != null) {
			returnString += preOrder(node.getRight());
		}
		
		return returnString;
		
		/**
		 * ------------------------------------
		 * TODO: You complete the code.
		 * 
		 * Note: Your solution MUST USE recursion
		 * 
		 */
	} // end preOrder() method

	
	/**
	 * 
	 * You may modify this (no restrictions) to help debug your code.
	 * 
	 */
	public String toString() {
		
		return null;

	} // end toString() method
	
	
	/**
	 * 
	 * You may modify this (no restrictions) to help debug your code.
	 * 
	 */
	public void printBST() {
		
	} // end printBST() method

	
	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args ) {
		
		/**
		 * ------------------------------------
		 * TODO: You put your test cases here
		 * 
		 * 
		 */
		
		// this will get you started :)
		
		BinarySearchTree<Integer> bst;
		
		
		//FUNCTIONALITY TEST CASES
		try {
			bst = new BinarySearchTree<Integer>( 10 );
			bst.insert(3);
			bst.insert(7);
			bst.insert(1);
			bst.insert(-21);
			bst.insert(0);
			bst.insert(-6);
			bst.insert(20);
			System.out.println(bst.preOrder());
			bst.delete(3);
			System.out.println(bst.preOrder());
			System.out.println(bst.min());
			System.out.println(bst.max());
			System.out.println(bst.search(8));
			System.out.println(bst.search(3));
			System.out.println(bst.search(10));
			bst.delete(-21);
			System.out.println(bst.preOrder());
			bst.delete(10);
			System.out.println(bst.preOrder());
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
		//EXCEPTION CHECKING TEST CASES
		//DuplicateElementException
		try {
			bst = new BinarySearchTree<Integer>( 10 );
			bst.insert(10);
		} catch(Exception e) {
			System.out.println(e);
		}
		
		//NullPointerException
		try {
			bst = new BinarySearchTree<Integer>( 10 );
			bst.delete(21);
			System.out.println(bst.preOrder());
		} catch(Exception e) {
			System.out.println(e);
		}
		
		//NullBinaryNodeException
		try {
			bst = new BinarySearchTree<Integer>( 10 );
			bst.delete(null);
		} catch(Exception e) {
			System.out.println(e);
		}
		
		//EmptyBSTException
		try {
			bst = new BinarySearchTree<Integer>( 10 );
			bst.delete(10);
			bst.min();
			System.out.println(bst.preOrder());
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
	} // end main method
	

} // end BinarySearchTree class
