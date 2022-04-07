import java.util.ArrayList;

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

    public  Directory(String name, boolean writable) {
        super(name, writable);
        setIsRoot(true);
    }

    public  Directory(String name) {
        super(name, true);
        setIsRoot(true);
    }

    private void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }
}
