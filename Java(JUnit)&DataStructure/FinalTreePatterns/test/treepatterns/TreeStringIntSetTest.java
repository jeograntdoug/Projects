package treepatterns;

import treepatterns.Pair;
import treepatterns.TreeStringIntSet;
import treepatterns.FileWriterSingleton;
import treepatterns.DuplicateValueException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TreeStringIntSetTest {
    
    TreeStringIntSet tree;
    
    @Before
    public void setUp() {
        tree = new TreeStringIntSet();
    }
    
    @After
    public void tearDown() {
        tree = null;
    }
    
    @Test
    public void addNewNodeIntoTree(){
        try {
            assertEquals(0,tree.getNodesTotal());
            
            tree.add("Jerry",0);
            tree.add("Berry",1);
            tree.add("Aerry",2);
            tree.add("Terry",3);
            tree.add("Gerry",4);
            assertEquals(5,tree.getNodesTotal());
            assertTrue(tree.containsKey("Gerry"));
            assertTrue(tree.containsKey("Terry"));
            assertTrue(tree.containsKey("Aerry"));
            assertTrue(tree.containsKey("Berry"));
            assertTrue(tree.containsKey("Jerry"));
            
            
        } catch (DuplicateValueException ex) {
            assertFalse("This shouldn't show", true);
        }
    }
        
    @Test
    public void throwsDuplicateValueExceptionInSameKeyAndValue(){
        try {
            assertEquals(0,tree.getNodesTotal());
            
            tree.add("Jerry",0);
            tree.add("Berry",1);
            tree.add("Aerry",2);
            tree.add("Terry",3);
            tree.add("Gerry",4);

            tree.add("Jerry",0);
            
            assertFalse("This shouldn't show", true);
        } catch (DuplicateValueException ex) {
            assertTrue("This has to happen",true);
        }
    }

    @Test
    public void keyCanHaveMultipleValues(){
        try {
            assertEquals(0,tree.getNodesTotal());
            
            tree.add("Jerry",0);
            tree.add("Berry",1);
            tree.add("Aerry",2);
            tree.add("Terry",3);
            tree.add("Gerry",4);
            assertEquals(5,tree.getNodesTotal());
            
            tree.add("Gerry",0);
            tree.add("Gerry",1);
            tree.add("Gerry",2);
            tree.add("Gerry",3);
            tree.add("Gerry",5);
            assertEquals(5,tree.getNodesTotal());
            
            List<Integer> valueList = tree.getValuesByKey("Gerry");
            assertEquals(6,valueList.size());
            assertTrue(valueList.contains(0));
            assertTrue(valueList.contains(1));
            assertTrue(valueList.contains(2));
            assertTrue(valueList.contains(3));
            assertTrue(valueList.contains(4));
            assertTrue(valueList.contains(5));
            
        } catch (DuplicateValueException ex) {
            assertFalse("This shouldn't show", true);
        }
    }
    
    @Test
    public void canGetAllKeyList(){
        try {
            assertEquals(0,tree.getNodesTotal());
            
            tree.add("Jerry",0);
            tree.add("Berry",1);
            tree.add("Aerry",2);
            tree.add("Terry",3);
            tree.add("Gerry",4);
            assertEquals(5,tree.getNodesTotal());
            
            tree.add("Gerry",0);
            tree.add("Gerry",1);
            tree.add("Gerry",2);
            tree.add("Gerry",3);
            tree.add("Gerry",5);
            assertEquals(5,tree.getNodesTotal());
            
            List<String> keyList = tree.getAllKeys();
            assertEquals(5,keyList.size());
            assertTrue(keyList.contains("Jerry"));
            assertTrue(keyList.contains("Berry"));
            assertTrue(keyList.contains("Aerry"));
            assertTrue(keyList.contains("Terry"));
            assertTrue(keyList.contains("Gerry"));
            
        } catch (DuplicateValueException ex) {
            assertFalse("This shouldn't show", true);
        }
    }
    
    @Test
    public void canGetAllKeyListContainsValue(){
        try {
            tree.add("Jerry",0);
            tree.add("Jerry",1);
            
            tree.add("Aerry",2);
            tree.add("Aerry",3);
            tree.add("Aerry",4);
            
            tree.add("Gerry",0);
            tree.add("Gerry",1);
            tree.add("Gerry",2);
            tree.add("Terry",0);
            tree.add("Lerry",0);
            
            assertEquals(5,tree.getNodesTotal());
            
            List<String> keyList = tree.getKeysContainingValue(0);
            assertEquals(4,keyList.size());
            assertTrue(keyList.contains("Jerry"));
            assertTrue(keyList.contains("Gerry"));
            assertTrue(keyList.contains("Terry"));
            assertTrue(keyList.contains("Lerry"));
            
            keyList = tree.getKeysContainingValue(2);
            assertEquals(2,keyList.size());
            assertTrue(keyList.contains("Aerry"));
            assertTrue(keyList.contains("Gerry"));
            
        } catch (DuplicateValueException ex) {
            assertFalse("This shouldn't show", true);
        }
    }
    
    @Test
    public void modifyingTreeCallsObserver(){
        try{
            tree.setObserver((node, action, value) -> {
                try{
                    BufferedWriter fileOutput = FileWriterSingleton.getFileReaderSingleTon();
                    String actionStr = action.equals(TreeStringIntSet.TreeModificationObserver.Action.Failure)?
                            "AddFailDuplicate": "Add";
                            
                    String str = String.format(
                            "Event: %S, nodeKey=%s, value=%d\n",
                            actionStr,node.key,value
                            
                    );
                    fileOutput.write(str);
                }catch (IOException ex){
                    ex.printStackTrace();
                    System.out.println("Cannot open the file");                
                }
            });
        
            assertEquals(0,tree.getNodesTotal());

            tree.add("Jerry",0);
            tree.add("Berry",1);
            tree.add("Aerry",2);
            tree.add("Terry",3);
            tree.add("Gerry",4);

            tree.add("Jerry",0);

            assertFalse("This shouldn't show", true);
            
        } catch (DuplicateValueException ex) {
            assertTrue("This has to happen",true);
        }
        
        
        try{
            FileWriterSingleton.closeFileReaderSingleTon();
        } catch (IOException ex){
            ex.printStackTrace();
            assertFalse("This shouldn't show", true);
        }
    }
    
    @Test
    public void treeIsIteratable(){
        try {
            tree.add("Jerry",0);
            tree.add("Jerry",1);
            
            tree.add("Aerry",2);
            tree.add("Aerry",3);
            tree.add("Aerry",4);
            
            tree.add("Gerry",0);
            tree.add("Gerry",1);
            tree.add("Gerry",2);
            tree.add("Terry",0);
            tree.add("Lerry",0);
            
            assertEquals(5,tree.getNodesTotal());
            
            for(Pair<String,Integer> pair : tree){
                System.out.println(pair);
                
                assertTrue(tree.getValuesByKey(pair.key).contains(pair.value));
            }
            
        } catch (DuplicateValueException ex) {
            ex.printStackTrace();
            assertFalse("This shouldn't show", true);
        }
    }
    
    @Test
    public void visitorCanVisitAllNodes(){
        try {
            tree.add("Jerry",0);
            tree.add("Berry",1);
            tree.add("Aerry",2);
            tree.add("Terry",3);
            tree.add("Gerry",4);
            
            tree.add("Gerry",-1);    
            tree.add("Gerry",0);    
            tree.add("Gerry",1);
            tree.add("Gerry",2);
            tree.add("Gerry",3);
            tree.add("Gerry",5);
            
            
            tree.visitEachNode(new TreeStringIntSet.NodeVisitorInt() {
                @Override
                public void visitNode(TreeStringIntSet.Node node) {
                    ArrayList<Integer> intList = new ArrayList<>();
                    
                    for(int value : node.valuesSet){
                        if(value <= 0){ intList.add(value); }
                        
                        if(!isPrime(value)){
                            intList.add(value);
                        }
                    }
                    for(int removeInt : intList){
                        node.valuesSet.remove(removeInt);
                    }
                }
                
                private boolean isPrime(int value) {
                    for (int i = 2; i < value; i++) {
                        if (value % i == 0) {
                            return false;
                        }
                    }
                    return true;
                }
            });
            
            List<Integer> valueList = tree.getValuesByKey("Gerry");
            assertEquals(4, valueList.size());
            assertTrue(valueList.contains(1));
            assertTrue(valueList.contains(2));
            assertTrue(valueList.contains(3));
            assertTrue(valueList.contains(5));
            assertFalse(valueList.contains(-1));
            assertFalse(valueList.contains(0));
            assertFalse(valueList.contains(4));
            
            
        } catch (DuplicateValueException ex) {
            assertFalse("This shouldn't show", true);
        }
        
        
    }
    
}

