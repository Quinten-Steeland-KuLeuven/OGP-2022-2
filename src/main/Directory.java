import be.kuleuven.cs.som.annotate.Basic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author  Quinten Steeland
 * @author  Robbe Vanslambrouck
 * @author  Wout Voet
 */

public class Directory extends NamedWritableItem {

    private ArrayList<NamedItem> contents = new ArrayList<>();

    public Directory(Directory dir, String name, boolean writable) {
        super(name, writable);
        setParentDir(dir);
    }

    public Directory(Directory dir, String name) {
        super(name, true);
        setParentDir(dir);
    }

    public Directory(String name, boolean writable) {
        super(name, writable);
        setParentDir(null);
    }

    public Directory(String name) {
        super(name, true);
        setParentDir(null);
    }

    public boolean isRoot() {
        return getParentDirectory() == null;
    }

    public List<NamedItem> getContents() {
        return contents;
    }

    protected void addNamedItem(NamedItem item) throws ItemNotWritableException, IllegalArgumentException {
        if(canAddItem(item)) insertItem(item);
    }

    private boolean canAddItem(NamedItem item) throws ItemNotWritableException, IllegalArgumentException {
        if(item == null) throw new IllegalArgumentException("null-reference");
        if(!isWritable()) throw new ItemNotWritableException(this);
        if(hasAsItem(item)) throw new IllegalArgumentException("item already in map");
        if(item.getClass() == Directory.class) {
            if(((Directory) item).hasRootTo(this)) throw new IllegalArgumentException("creates a loop");
        }
        return true;
    }

    private void insertItem(NamedItem item) throws IllegalArgumentException {
        int index;
        for(index = 0; index < contents.size(); index++) {
            int comp = contents.get(index).compareTo(item);
            if(comp == 0)
                throw new IllegalArgumentException("item has the same name as an other item");
            if(comp < 0) {
                break;
            }
        }
        item.setParentDir(this);
        contents.add(index, item);
    }

    public boolean hasRootTo(Directory dir) {
        if(hasAsItem(dir)) return true;
        for(NamedItem item: contents) {
            if(item.getClass() == Directory.class) return hasRootTo((Directory) item);
        }
        return false;
    }

    public void printContents() {
        for (int i=0; i < contents.size(); i++) {
            System.out.println(contents.get(i).getName());
        }
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
        for(NamedItem item: contents) {
            if(item.getName() == name) return item;
        }
        return null;
    }

    public boolean containsDiskItemWithName(String name) {
        for(NamedItem item: getContents()){
            if (item.getName().toLowerCase() == name.toLowerCase()) return true;
        }
        return false;
    }

    public int getIndexOf(NamedItem item) {
        return getContents().indexOf(item);
    }

    public boolean hasAsItem(NamedItem item) {
        return contents.contains(item);
    }
}
