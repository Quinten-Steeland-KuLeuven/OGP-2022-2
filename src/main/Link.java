import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class Link extends NamedItem {

    /**
     * Variable of the item the link points at.
     */
    private final NamedWritableItem linkedItem;

    /**
     * Variable for tracking if a link is valid.
     */
    private boolean isValidLink;

    /**
     * Initialize a Link with given directory, name and linked item.
     * @param   dir
     *          The directory the link should be in.
     * @param   name
     *          The name of the link.
     * @param   linkedItem
     *          The item the link links to.
     */
    @Raw
    public Link(Directory dir, String name, NamedWritableItem linkedItem) {
        super(dir, name);
        this.linkedItem = linkedItem;
        this.isValidLink = true;
    }

    /**
     * Getter for the linked item.
     * @return  The item the link links to.
     */
    @Basic
    public NamedWritableItem getLinkedItem() {
        return linkedItem;
    }

    /**
     * Getter for the link validness.
     * @return True if valid link.
     */
    @Basic
    public boolean isValidLink() {
        return isValidLink;
    }

    private void setValidLink(boolean validLink) {
        isValidLink = validLink;
    }

    public boolean isLinkStillValid() {
        if ( /*TODO: valid link */ true) {
            return true;
        } else {
            return false;
        }
    }

}
