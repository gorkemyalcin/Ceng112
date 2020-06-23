package businessLayer;

import java.util.List;

/**
 * @author Ben-Malik_Gorkem
 *
 */
public interface IBinarySearchTree<K extends Comparable<? super K>, V> {
	
	/** Detects the value associated with the specified key.	
	 * @param key The key object whose value is to be detected.
	 * @return The associated value.
	 * Pre-conditions: The tree is not empty.
	 * 				   @throws EmptyTreeException in accordance.
	 * 				   The given key appears in the tree.
	 * 				   @throws NoSuchKeyException in accordance.
	 * Post-conditions: The tree nodes number is intact.
	 */
	public V get(K key);
	
	public void add(K key, V value);
	
	/**Removes the value associated with the specified key.
	 * @param key The key object whose value is to be removed.
	 * Pre-conditions: The tree is not empty.
	 * 				   @throws EmptyTreeException in accordance.
	 * 				   Given key exists in the tree.
	 * 				   @throws NoSuchKeyException accordingly.
	 * Post-conditions: The tree nodes number is decremented by one.
	 */
	public void remove(K key);
	 
	/**Updates the value associated with ​ key ​ from old value to ​ value ​ .
	 * @param key The key object whose old value is to be updated.
	 * @param value The value object to be updated.
	 * Pre-conditions: The tree is not empty.
	 * 				   @throws EmptyTreeException in accordance.
	 * 				   Given key is in the tree.
	 * 				   @throws NoSuchKeyException accordingly.
	 * Post-conditions: The tree nodes number is intact.
	 */
	public void update(K key, V value);
	
	/**Returns all the keys in the tree.
	 * @return A list of all keys.
	 * Pre-conditions: The tree is not empty.
	 * 				   @throws EmptyTreeException.
	 *Post-conditions: The trees nodes size intact.
	 */
	public List<K> keys();
	
	/**Detects all the values in the tree.
	 * @return A list of all values in the tree.
	 * Pre-conditions: The is not empty.
	 * 				   @throws EmptyTreeException in accordance.
	 *Post-conditions: The trees node are all intact.
	 */
	public List<V> values();
	
	/**Detects the value associated with the smallest key.
	 * @return value associated with the smallest key.
	 * Pre-conditions: The is not empty.
	 * 				   @throws EmptyTreeException in accordance.
	 *Post-conditions: The trees node are all intact.
	 */
	public V min();
	
	/**Detects the value associated with the largest key.
	 * @return the value associated with the largest key in the tree.
	 *  Pre-conditions: The is not empty.
	 *                 @throws EmptyTreeException in accordance.
	 *Post-conditions: The trees node are all intact.
	 */
	public V max();
	
	/**Detects all the values associated with a smaller key than ​ key ​ .
	 * @param key The key object whose smaller keys are to be found.
	 * @return A list of detected keys.
	 * Pre-conditions: The tree is not empty.
	 *                @throws EmptyTreeException in accordance.
	 *               
	 *Post-conditions: The trees node are all intact.
	 */
	public List<V> lessThan(K key);
	
	/**Detects all the values associated with a larger key than ​ key ​ .
	 * @param key The key object whose greater keys are to be found.
	 * @return A list of detected keys.
	 */
	public List<V> greaterThan(K key);
	
	/**Detects the number of values (or keys) in the tree.
	 * @return The integer number of the values or keys in the tree.
	 */
	public int size();
	
	/**Returns the maximum number of values that appear between the
	 * root and any leaf (root and leaf are counted as well).
	 * @return The integer number of the maximum number of values.
	 */
	public int height();
	
	/**Prints the tree to the console.	 */
	public void display();
	
	/** Determines whether the tree is full (it is full if and only if every
	 *	node other than leaves has exactly two children and all the leaves are
	 *	on the same level).
	 * @return True if so and false otherwise.
	 */
	public boolean isFullTree();
	

} // end IBinarySearchTree.
