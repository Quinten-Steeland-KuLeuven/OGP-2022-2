import static org.junit.Assert.*;
import java.util.Date;

import org.junit.*;

/**
 * A JUnit test class for testing the public methods of the Directory Class
 * @author Robbe Vanslambrouck
 *
 */
public class DirectoryTest {

    // name
    Date before, after;
    Directory wrongName, dirStr;
    final String VALID_NAME = "Valid_dir-name1";
    final String INVALID_NAME = "Valid_dir.txt";
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
    public void testCorrectName_LegalCase() {
        assertEquals(VALID_NAME, dirStr.getName());

    }

    @Test
    public void testCorrectName_ilLegalCase() {
        before = new Date();
        dirStr = new Directory(INVALID_NAME);
        after = new Date();
        assertNotEquals(INVALID_NAME, dirStr.getName());

    }


}
