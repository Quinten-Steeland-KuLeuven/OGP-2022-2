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
