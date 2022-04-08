import be.kuleuven.cs.som.annotate.Basic;

import java.util.ArrayList;
import java.util.List;

public class Directory extends NamedWritableItem {

    private boolean isRoot = false;
    private List<NamedItem> contents = new ArrayList<>();

    public Directory(Directory dir, String name, boolean writable) {
        super(name, writable);
        setParentDir(dir);
        setIsRoot(false);
    }

    public Directory(Directory dir, String name) {
        super(name, true);
        setParentDir(dir);

        setIsRoot(false);
    }

    public Directory(String name, boolean writable) {
        super(name, writable);
        setIsRoot(true);

        setParentDir(this);
    }

    public  Directory(String name) {
        super(name, true);
        setIsRoot(true);

        setParentDir(this);
    }

    private void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    @Basic
    public boolean isRoot() {
        return isRoot;
    }
}
