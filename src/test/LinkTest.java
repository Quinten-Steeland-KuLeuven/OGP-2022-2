import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinkTest {

    Directory containingDir;
    File randomFileToLinkTo;
    Link aLink;

    @Before
    public void setUpFixture() {
        containingDir = new Directory("Dir", true);
        randomFileToLinkTo = new File(containingDir, "File", FileType.TXT);

        aLink = new Link(containingDir, "First", randomFileToLinkTo);

    }

    @Test
    public void testConstructor() {
        assertEquals(aLink.getName(), "First");
        assertEquals(aLink.getParentDirectory(), containingDir);
        assertEquals(aLink.getLinkedItem(), randomFileToLinkTo);
        assertTrue(aLink.isValidLink());
    }

    @Test
    public void testInvalidName() {
        Link newLink = new Link(containingDir, "!@#$%^&*", randomFileToLinkTo);
        assertEquals(newLink.getName(), NamedItem.getDefaultName());
    }

}
