import static org.junit.Assert.*;
import java.util.Date;

import org.junit.*;

/**
 * A JUnit test class for testing the public methods of the Directory Class
 * @author Robbe Vanslambrouck
 *
 */
public class DirectoryTest {

    Date before, after;
    Directory wrongName, dirStr;
    final String VALID_NAME = "Valid_dir-name1";
    final String INVALID_SYMBOL_NAME = "Valid_dir.txt";
    final String INVALID_EMPTY_NAME = "";
    final String INVALID_NULL_NAME = null;

    // name
    // times (mod and creation)
    // isWritable
    // isRoot
    // using arrayList
    // no items with the same name (don't ignore capital letters)
    // no loops
    // contents is ordered (also after name change) (ignore capital letters)
    // contents contains no null ref
    // contents contains no refs to same item

    // getNbItems
    // getItemAt
    // getItem
    // containsDiskItemWithName
    // getIndexOf
    // hasAsItem

    @Before
    public void setUpFixture() {
        before = new Date();
        dirStr = new Directory(VALID_NAME);
        after = new Date();

    }

    @Test
    public void testIsValidName_LegalCase() {
        assertTrue(Directory.isValidName(VALID_NAME));
    }

    @Test
    public void testIsValidName_IllegalCase() {
        assertFalse(Directory.isValidName(INVALID_SYMBOL_NAME));
        assertFalse(Directory.isValidName(INVALID_EMPTY_NAME));
        assertFalse(Directory.isValidName(INVALID_NULL_NAME));
    }

    @Test
    public void testDirStr_LegalCase() {
        assertEquals(VALID_NAME, dirStr.getName());
        assertTrue(dirStr.isWritable());
        assertTrue(dirStr.isRoot());
        assertNull(dirStr.getParentDirectory());
        assertNull(dirStr.getModificationTime());
        assertFalse(before.after(dirStr.getCreationTime()));
        assertFalse(dirStr.getCreationTime().after(after));
    }

    @Test
    public void testDirStr_IllegalCase() {
        before = new Date();
        dirStr = new Directory(INVALID_SYMBOL_NAME);
        after = new Date();
        assertTrue(Directory.isValidName(dirStr.getName()));
        assertFalse(before.after(dirStr.getCreationTime()));
        assertFalse(dirStr.getCreationTime().after(after));
    }


}
