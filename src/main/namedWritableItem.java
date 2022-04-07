import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.Date;

public class namedWritableItem extends namedItem {

    public namedWritableItem (String name, boolean isWritable) {
        super(name);
        setWritable(isWritable);
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

    /**
     * Variable referencing the time of the last modification,
     * possibly null.
     */
    private Date modificationTime = null;

    /**
     * Change the name of this file to the given name.
     *
     * @param	name
     * 			The new name for this file.
     * @effect  The name of this file is set to the given name,
     * 			if this is a valid name and the file is writable,
     * 			otherwise there is no change.
     * 			| if (isValidName(name) && isWritable())
     *          | then setName(name)
     * @effect  If the name is valid and the file is writable, the modification time
     * 			of this file is updated.
     *          | if (isValidName(name) && isWritable())
     *          | then setModificationTime()
     * @throws ItemNotWritableException(this)
     *          This file is not writable
     *          | ! isWritable()
     */
    public void changeName(String name) throws ItemNotWritableException {
        if(!isWritable()) throw new ItemNotWritableException(this);
        if (!isValidName(name)) return;
        setName(name);
        setModificationTime();
    }

    /**********************************************************
     * modificationTime
     **********************************************************/


    /**
     * Return the time at which this file was last modified, that is
     * at which the name or size was last changed. If this file has
     * not yet been modified after construction, null is returned.
     */
    @Raw @Basic
    public Date getModificationTime() {
        return modificationTime;
    }

    /**
     * Check whether this file can have the given date as modification time.
     *
     * @param	date
     * 			The date to check.
     * @return 	True if and only if the given date is either not effective
     * 			or if the given date lies between the creation time and the
     * 			current time.
     *         | result == (date == null) ||
     *         | ( (date.getTime() >= getCreationTime().getTime()) &&
     *         |   (date.getTime() <= System.currentTimeMillis())     )
     */
    @Raw
    public boolean canHaveAsModificationTime(Date date) {
        return (date == null) ||
                ( (date.getTime() >= getCreationTime().getTime()) &&
                        (date.getTime() <= System.currentTimeMillis()) );
    }

    /**
     * Set the modification time of this file to the current time.
     *
     * @post   The new modification time is effective.
     *         | new.getModificationTime() != null
     * @post   The new modification time lies between the system
     *         time at the beginning of this method execution and
     *         the system time at the end of method execution.
     *         | (new.getModificationTime().getTime() >=
     *         |                    System.currentTimeMillis()) &&
     *         | (new.getModificationTime().getTime() <=
     *         |                    (new System).currentTimeMillis())
     */
    @Model
    protected void setModificationTime() {
        modificationTime = new Date();
    }

    /**********************************************************
     * parentDirectory
     **********************************************************/

    private Directory parentDirectory = null;

    private void setParentDir(Directory parentDirectory) {
        this.parentDirectory = parentDirectory;
    }
}
