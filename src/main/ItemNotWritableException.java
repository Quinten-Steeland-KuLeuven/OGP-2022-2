import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signaling illegal attempts to change a item.
 *
 *
 * @author  Quinten Steeland
 * @author  Robbe Vanslambrouck
 * @author  Wout Voet
 * @version	2.2
 */
public class ItemNotWritableException extends RuntimeException {

	/**
	 * Required because this class inherits from Exception
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable referencing the item to which change was denied.
	 */
	private final NamedWritableItem item;

	/**
	 * Initialize this new item not writable exception involving the
	 * given item.
	 * 
	 * @param	item
	 * 			The item for the new item not writable exception.
	 * @post	The item involved in the new item not writable exception
	 * 			is set to the given item.
	 * 			| new.getItem() == item
	 */
	public ItemNotWritableException(NamedWritableItem item) {
		this.item = item;
	}
	
	/**
	 * Return the file involved in this file not writable exception.
	 */
	@Basic @Immutable
	public NamedWritableItem getItem() {
		return item;
	}
	
}
