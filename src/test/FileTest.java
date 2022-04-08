import static org.junit.Assert.*;
import java.util.Date;

import org.junit.*;

/**
 * A JUnit test class for testing the public methods of the File Class  
 * @author Tommy Messelis
 *
 */
public class FileTest {

	File fileStringIntBoolean;
	File fileString;
	Date timeBeforeConstruction, timeAfterConstruction;
	
	File fileNotWritable;
	Date timeBeforeConstructionNotWritable, timeAfterConstructionNotWritable;

	Directory containingDir;
	
	@Before
	public void setUpFixture(){

		containingDir = new Directory("Dir", true);
		timeBeforeConstruction = new Date();
		fileStringIntBoolean = new File(containingDir, "bestand.txt",100, true, FileType.TXT);
		fileString = new File(containingDir, "bestand.txt", FileType.TXT);
		timeAfterConstruction = new Date();

		timeBeforeConstructionNotWritable = new Date();
		fileNotWritable = new File(containingDir, "bestand.txt",100,false, FileType.TXT);
		timeAfterConstructionNotWritable = new Date();
	}

	@Test
	public void testFileStringIntBoolean_LegalCase() {
		assertEquals("bestand.txt",fileStringIntBoolean.getName());
		assertEquals(fileStringIntBoolean.getSize(),100);
		assertTrue(fileStringIntBoolean.isWritable());
		assertNull(fileStringIntBoolean.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileStringIntBoolean.getCreationTime()));
		assertFalse(fileStringIntBoolean.getCreationTime().after(timeAfterConstruction));
	}
	
	@Test
	public void testFileStringIntBoolean_IllegalCase() {
		timeBeforeConstruction = new Date();
		fileStringIntBoolean = new File(
				containingDir, "$IllegalName$",File.getMaximumSize(), false, FileType.TXT);
		timeAfterConstruction = new Date();
		assertTrue(File.isValidName(fileStringIntBoolean.getName()));
		assertEquals(File.getMaximumSize(),fileStringIntBoolean.getSize());
		assertFalse(fileStringIntBoolean.isWritable());
		assertNull(fileStringIntBoolean.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileStringIntBoolean.getCreationTime()));
		assertFalse(fileStringIntBoolean.getCreationTime().after(timeAfterConstruction));
	}

	@Test
	public void testFileString_LegalCase() {
		assertEquals("bestand.txt",fileString.getName());
		assertEquals(0,fileString.getSize());
		assertTrue(fileString.isWritable());
		assertNull(fileString.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileString.getCreationTime()));
		assertFalse(fileString.getCreationTime().after(timeAfterConstruction));
	}
	
	@Test
	public void testFileString_IllegalCase() {
		timeBeforeConstruction = new Date();
		fileString = new File(containingDir, "$IllegalName$", FileType.TXT);
		timeAfterConstruction = new Date();
		assertTrue(File.isValidName(fileString.getName()));
		assertEquals(0,fileString.getSize());
		assertTrue(fileString.isWritable());
		assertNull(fileString.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileString.getCreationTime()));
		assertFalse(fileString.getCreationTime().after(timeAfterConstruction));
	}

	@Test
	public void testIsValidName_LegalCase() {
		assertTrue(File.isValidName("abcDEF123-_."));
	}

	@Test
	public void testIsValidName_IllegalCase() {
		assertFalse(File.isValidName(null));
		assertFalse(File.isValidName(""));
		assertFalse(File.isValidName("%illegalSymbol"));
		
	}

	@Test
	public void testChangeName_LegalCase() {
		Date timeBeforeSetName = new Date();
		fileString.changeName("NewLegalName");
		Date timeAfterSetName = new Date();
		
		assertEquals("NewLegalName",fileString.getName());
		assertNotNull(fileString.getModificationTime());
		assertFalse(fileString.getModificationTime().before(timeBeforeSetName));
		assertFalse(timeAfterSetName.before(fileString.getModificationTime()));
	}
	
	@Test (expected = ItemNotWritableException.class)
	public void testChangeName_FileNotWritable() {
		fileNotWritable.changeName("NewLegalName");
	}
	
	@Test
	public void testChangeName_IllegalName() {
		fileString.changeName("$IllegalName$");
		assertEquals("bestand.txt",fileString.getName());
		assertNull(fileString.getModificationTime());
	}

	@Test
	public void testIsValidSize_LegalCase() {
		assertTrue(File.isValidSize(0));
		assertTrue(File.isValidSize(File.getMaximumSize()/2));
		assertTrue(File.isValidSize(File.getMaximumSize()));
	}
	
	@Test
	public void testIsValidSize_IllegalCase() {
		assertFalse(File.isValidSize(-1));
		if (File.getMaximumSize() < Integer.MAX_VALUE) {
			assertFalse(File.isValidSize(File.getMaximumSize()+1));
		}
	}

	@Test
	public void testEnlarge_LegalCase() {
		File file = new File(containingDir, "bestand.txt",File.getMaximumSize()-1,true, FileType.TXT);
		Date timeBeforeEnlarge = new Date();
		file.enlarge(1);
		Date timeAfterEnlarge = new Date();		
		assertEquals(file.getSize(),File.getMaximumSize());
		assertNotNull(file.getModificationTime());
		assertFalse(file.getModificationTime().before(timeBeforeEnlarge));
		assertFalse(timeAfterEnlarge.before(file.getModificationTime()));  
	}
	
	@Test (expected = ItemNotWritableException.class)
	public void testEnlarge_FileNotWritable() {
		fileNotWritable.enlarge(1);
	}
	
	@Test
	public void testShorten_LegalCase() {
		fileStringIntBoolean.shorten(1);
		Date timeAfterShorten = new Date();		
		assertEquals(fileStringIntBoolean.getSize(),99);
		assertNotNull(fileStringIntBoolean.getModificationTime());
		assertFalse(fileStringIntBoolean.getModificationTime().before(timeAfterConstruction));
		assertFalse(timeAfterShorten.before(fileStringIntBoolean.getModificationTime()));
	}
	
	@Test (expected = ItemNotWritableException.class)
	public void testShorten_FileNotWritable() {
		fileNotWritable.shorten(1);
	}

	@Test
	public void testIsValidCreationTime_LegalCase() {
		Date now = new Date();
		assertTrue(File.isValidCreationTime(now));
	}
	
	@Test
	public void testIsValidCreationTime_IllegalCase() {
		assertFalse(File.isValidCreationTime(null));
		Date inFuture = new Date(System.currentTimeMillis() + 1000*60*60);
		assertFalse(File.isValidCreationTime(inFuture));		
	}
	
	@Test
	public void testCanHaveAsModificationTime_LegalCase() {
		assertTrue(fileString.canHaveAsModificationTime(null));
		assertTrue(fileString.canHaveAsModificationTime(new Date()));
	}
	
	@Test
	public void testCanHaveAsModificationTime_IllegalCase() {
		assertFalse(fileString.canHaveAsModificationTime(new Date(timeAfterConstruction.getTime() - 1000*60*60)));
		assertFalse(fileString.canHaveAsModificationTime(new Date(System.currentTimeMillis() + 1000*60*60)));
	}

	@Test
	public void testHasOverlappingUsePeriod_UnmodifiedFiles() {
		// one = implicit argument ; other = explicit argument
		File one = new File(containingDir, "one", FileType.TXT);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		File other = new File(containingDir, "other", FileType.TXT);
		
		//1 Test unmodified case
		assertFalse(one.hasOverlappingUsePeriod(other));
		
		//2 Test one unmodified case
		other.enlarge(File.getMaximumSize());
		assertFalse(one.hasOverlappingUsePeriod(other));
		
		//3 Test other unmodified case
		//so re-initialise the other file
		other = new File(containingDir, "other", FileType.TXT);
		one.enlarge(File.getMaximumSize());
		assertFalse(one.hasOverlappingUsePeriod(other));
		
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedNoOverlap() {
		// one = implicit argument ; other = explicit argument
		File one, other;
		one = new File(containingDir, "one", FileType.TXT);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new File(containingDir, "other", FileType.TXT);
		
		//1 Test one created and modified before other created and modified case
		one.enlarge(File.getMaximumSize());
        sleep();
        //re-initialise the other
        other = new File(containingDir, "other", FileType.TXT);
        other.enlarge(File.getMaximumSize());
	    assertFalse(one.hasOverlappingUsePeriod(other));
	    
	    //2 Test other created and modified before one created and modified
		other.enlarge(File.getMaximumSize());
        sleep();
        one = new File(containingDir, "one", FileType.TXT);
        one.enlarge(File.getMaximumSize());
        assertFalse(one.hasOverlappingUsePeriod(other));
	
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedOverlap_A() {
		// one = implicit argument ; other = explicit argument
		//A Test one created before other created before one modified before other modified
	    File one, other;
		one = new File(containingDir, "one", FileType.TXT);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new File(containingDir, "other", FileType.TXT);
	
		one.enlarge(File.getMaximumSize());
        sleep();
        other.enlarge(File.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedOverlap_B() {
		// one = implicit argument ; other = explicit argument
		//B Test one created before other created before other modified before one modified
       	File one, other;
		one = new File(containingDir, "one", FileType.TXT);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new File(containingDir, "other", FileType.TXT);
	
		other.enlarge(File.getMaximumSize());
        sleep();
        one.enlarge(File.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedOverlap_C() {
		// one = implicit argument ; other = explicit argument
		//C Test other created before one created before other modified before one modified
        File one, other;
		other = new File(containingDir, "other", FileType.TXT);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		one = new File(containingDir, "one", FileType.TXT);
		
		other.enlarge(File.getMaximumSize());
        sleep();
        one.enlarge(File.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedOverlap_D() {
		// one = implicit argument ; other = explicit argument
		//D Test other created before one created before one modified before other modified
		File one, other;
		other = new File(containingDir, "one", FileType.TXT);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		one = new File(containingDir, "other", FileType.TXT);
	
		one.enlarge(File.getMaximumSize());
        sleep();
        other.enlarge(File.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}

	@Test
	public void testSetWritable() {
		fileString.setWritable(false);
		fileNotWritable.setWritable(true);
		assertFalse(fileString.isWritable());
		assertTrue(fileNotWritable.isWritable());
	}
	
	private void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
