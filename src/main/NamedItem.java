import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.Date;

public abstract class NamedItem {

    /**
     * Variable referencing the name of this file.
     * @note		See Coding Rule 32, for information on the initialization of fields.
     */
    private String name = null;

    /**
     * Variable referencing the time of creation.
     */
    private final Date creationTime = new Date();

    public NamedItem(String name) {
        setName(name);
    }

    public NamedItem(Directory dir, String name) {
        setParentDir(dir);
        setName(name);
    }

    /**
     * Return the name of this file.
     * @note		See Coding Rule 19 for the Basic annotation.
     */
    @Raw @Basic
    public String getName() {
        return name;
    }

    /**
     * Set the name of this namedItem to the given name.
     *
     * @param   name
     * 			The new name for this namedItem.
     * @post    If the given name is valid, the name of
     *          this namedItem is set to the given name,
     *          otherwise the name of the namedItem is set to a valid name (the default).
     *          | if (isValidName(name))
     *          |      then new.getName().equals(name)
     *          |      else new.getName().equals(getDefaultName())
     */
    @Raw
    @Model
    protected void setName(String name) {
        if (isValidName(name)) {
            this.name = name;
        } else {
            this.name = getDefaultName();
        }
    }

    /**
     * Check whether the given name is a legal name for a namedItem.
     *
     * @param  	name
     *			The name to be checked
     * @return	True if the given string is effective, not
     * 			empty and consisting only of letters, digits, dots,
     * 			hyphens and underscores; false otherwise.
     * 			| result ==
     * 			|	(name != null) && name.matches("[a-zA-Z_0-9.-]+")
     */
    public static boolean isValidName(String name) {
        return (name != null && name.matches("[a-zA-Z_0-9.-]+"));
    }


    /**
     * Return the name for a new namedItem which is to be used when the
     * given name is not valid.
     *
     * @return   A valid namedItem name.
     *         | isValidName(result)
     */
    @Model
    private static String getDefaultName() {
        //TODO: check for conflicting names in parent directory -> change default name to avoid conflicts
        return "new_item";
    }

    /**********************************************************
     * creationTime
     **********************************************************/

    /**
     * Return the time at which this file was created.
     */
    @Raw @Basic @Immutable
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * Check whether the given date is a valid creation time.
     *
     * @param  	date
     *         	The date to check.
     * @return 	True if and only if the given date is effective and not
     * 			in the future.
     *         	| result ==
     *         	| 	(date != null) &&
     *         	| 	(date.getTime() <= System.currentTimeMillis())
     */
    public static boolean isValidCreationTime(Date date) {
        return 	(date!=null) &&
                (date.getTime()<=System.currentTimeMillis());
    }


    /**********************************************************
     * parentDirectory
     **********************************************************/

    private Directory parentDirectory = null;

    protected void setParentDir(Directory parentDirectory) {
        this.parentDirectory = parentDirectory;
    }

    protected Directory getParentDirectory() {
        return parentDirectory;
    }



    //TODO docs
    public Directory getRoot() {
        Directory rootDir = this.getParentDirectory();
        while (!rootDir.isRoot()) {
            rootDir = rootDir.getParentDirectory();
        }
        return rootDir;
    }

    //TODO docs
    public boolean isDirectOrIndirectChildOf(Directory dir) {
        Directory parDir = getParentDirectory();
        while (true) {
            if (parDir.equals(dir)) return true;
            else if (parDir.isRoot()) return false;
            parDir = parDir.getParentDirectory();
        }
    }

    //TODO docs
    public String getAbsolutePath() {
        //TODO account for file extension
        StringBuilder path = new StringBuilder(this.getName());
        Directory parDir = this.parentDirectory;
        path.insert(0, parDir.getName() + "/");
        while (!parDir.isRoot()) {
                parDir = parDir.getParentDirectory();
                path.insert(0, parDir.getName() + "/");
            }
        return "/" + path;
    }

}
