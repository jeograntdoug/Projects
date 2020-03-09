package pkgcustomhashmap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CustomHashMapTest {
    
    private CustomHashMap<String,String> hMap;
    private int initialSize;
    
    public CustomHashMapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        hMap = new CustomHashMap<>(0);// Hash table sizelevel: 0, size: 3
        for(int i = 0 ; i < 6 ; i++){// input data into hashmap right before it expends its size 
            hMap.putValue("i" + i, i + "i");
        }
        hMap.putValue("First","First");
        hMap.putValue("Second", "Second");
        hMap.putValue("Third", "Third");
        initialSize = 9;
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void putKeyValueIntoHashMap(){
        assertEquals(initialSize, hMap.getTotalItems());
        assertEquals("First",hMap.getValue("First"));
        assertEquals("Second",hMap.getValue("Second"));
        assertEquals("Third",hMap.getValue("Third"));
    }
    
    @Test
    public void putValueIntoExistingKey(){
        assertEquals(initialSize, hMap.getTotalItems());
        
        hMap.putValue("First","Hello");
        hMap.putValue("Second", "World");
        hMap.putValue("Third", "!");
        
        assertEquals(initialSize, hMap.getTotalItems());
        
        assertEquals("Hello",hMap.getValue("First"));
        assertEquals("World",hMap.getValue("Second"));
        assertEquals("!",hMap.getValue("Third"));
    }
    
    @Test
    public void deleteByKeyValue(){
        assertEquals(initialSize,hMap.getTotalItems());
        hMap.printDebug();
        for(int i = 0 ; i < 6 ; i++){// input data into hashmap right before it expends its size 
            assertTrue(hMap.deleteByKey("i" + i));
        }
        
        assertTrue(hMap.deleteByKey("First"));
        assertTrue(hMap.deleteByKey("Second"));
        assertTrue(hMap.deleteByKey("Third"));
        assertFalse(hMap.deleteByKey("What?"));
        
        assertNotEquals(hMap.getValue("First"), "First");
        assertFalse(hMap.deleteByKey("First"));
        
        assertEquals(0, hMap.getTotalItems());
        
    }
    
    @Test
    public void clearKeyValue(){
        hMap.clear();
        
        assertEquals(0, hMap.getTotalItems());
        
        assertNull(hMap.getValue("First"));
        assertNull(hMap.getValue("Second"));
        assertNull(hMap.getValue("Third"));
        
        hMap.putValue("First","Hello");
        hMap.putValue("Second", "World");
        hMap.putValue("Third", "!");
        
        assertEquals(3, hMap.getTotalItems());
        
        assertNotEquals("First",hMap.getValue("First"));
        assertNotEquals("Second",hMap.getValue("Second"));
        assertNotEquals("Third",hMap.getValue("Third"));
    }
}
