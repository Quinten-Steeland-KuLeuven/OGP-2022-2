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
    Directory dirStr;
    Directory dirStrBool;
    Directory dirDirStr;
    Directory dirDirStrBool;

    File file1, file2;
    Directory dir1, dir2;

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
        assertTrue(dirStr.isWritable());
        assertTrue(dirStr.isRoot());
        assertNull(dirStr.getParentDirectory());
        assertNull(dirStr.getModificationTime());
        assertFalse(before.after(dirStr.getCreationTime()));
        assertFalse(dirStr.getCreationTime().after(after));
    }
    @Test
    public void testDirStrBool_LegalCase() {
        before = new Date();
        dirStrBool = new Directory(VALID_NAME, true);
        after = new Date();
        assertEquals(VALID_NAME, dirStrBool.getName());
        assertTrue(dirStrBool.isWritable());
        assertTrue(dirStrBool.isRoot());
        assertNull(dirStrBool.getParentDirectory());
        assertNull(dirStrBool.getModificationTime());
        assertFalse(before.after(dirStrBool.getCreationTime()));
        assertFalse(dirStrBool.getCreationTime().after(after));
    }

    @Test
    public void testDirStrBool_IllegalCase() {
        before = new Date();
        dirStrBool = new Directory(INVALID_SYMBOL_NAME, false);
        after = new Date();
        assertTrue(Directory.isValidName(dirStrBool.getName()));
        assertFalse(dirStrBool.isWritable());
        assertTrue(dirStrBool.isRoot());
        assertNull(dirStrBool.getParentDirectory());
        assertNull(dirStrBool.getModificationTime());
        assertFalse(before.after(dirStrBool.getCreationTime()));
        assertFalse(dirStrBool.getCreationTime().after(after));
    }

    @Test
    public void testAddNamedItem() {
        dirStr = new Directory(VALID_NAME);
        file1 = new File(dirStr, "file1", FileType.TXT);
        file2 = new File(dirStr, "file2", FileType.TXT);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    final File file3 = file2;
                    dirStr.addNamedItem(file3);
                });

        assertThrows(IllegalArgumentException.class,
                () -> {final File file4 = new File(dirStr, "file2", FileType.JAVA);
        });

        //TODO add dir loop test
    }


}
