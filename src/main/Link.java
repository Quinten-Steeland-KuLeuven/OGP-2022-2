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
    public Link(Directory dir, String name, NamedWritableItem linkedItem) {
        super(name, dir);
        this.linkedItem = linkedItem;
    }

    public boolean isLinkValid() {
        if ( /*TODO: valid link */ true) {
            return true;
        } else {
            return false;
        }
    }

}