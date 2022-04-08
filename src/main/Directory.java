import java.util.ArrayList;
import java.util.List;

public class Directory extends NamedWritableItem {

    private boolean isRoot = false;
    private ArrayList<NamedItem> contents = new ArrayList<>();

    public Directory(Directory dir, String name, boolean writable) {
        super(name, writable);
        setParentDir(dir);
        setIsRoot(false);
    }

    public Directory(Directory dir, String name) {
        super(name, true);
        setParentDir(dir);
    }

    public Directory(String name, boolean writable) {
        super(name, writable);
        setIsRoot(true);
    }

    public Directory(String name) {
        super(name, true);
        setIsRoot(true);
    }

    private void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    private List<NamedItem> getContents() {
        return contents;
    }

    /**
     * Check whether the given name is a legal name for a Directory.
     *
     * @param  	name
     *			The name to be checked
     * @return	True if the given string is effective, not
     * 			empty and consisting only of letters, digits,
     * 			hyphens and underscores; false otherwise.
     * 			| result ==
     * 			|	(name != null) && name.matches("[a-zA-Z_0-9-]+")
     */
    public static boolean isValidName(String name) {
        return (name != null && name.matches("[a-zA-Z_0-9-]+"));
    }

    @Override
    public void setName(String name) {
        if(isValidName(name)) super.setName(name);
        else super.setName(NamedItem.getDefaultName());
    }

    public int getNbItems() {
        return getContents().size();
    }

    public NamedItem getItemAt(int index) {
        return getContents().get(index);
    }

    public NamedItem getItem(String name) {
        return getContents().get(0); //TODO
    }

    public boolean containsDiskItemWithName(String name) {
        return true; //TODO
    }

    public int getIndexOf(NamedItem item) {
        return 0; //TODO
    }

    public boolean hasAsItem(NamedItem item) {
        return true;
    }
}
