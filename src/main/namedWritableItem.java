import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class namedWritableItem extends namedItem {

    public namedWritableItem (String name, boolean isWritable) {
        super(name);
    }

    /**
     * Variable registering whether or not this file is writable.
     */
    private boolean isWritable = true;

    /**
     * Check whether this file is writable.
     */
    @Raw
    @Basic
    public boolean isWritable() {
        return isWritable;
    }

    /**
     * Set the writability of this file to the given writability.
     *
     * @param isWritable
     *        The new writability
     * @post  The given writability is registered as the new writability
     *        for this file.
     *        | new.isWritable() == isWritable
     */
    @Raw
    public void setWritable(boolean isWritable) {
        this.isWritable = isWritable;
    }

}
