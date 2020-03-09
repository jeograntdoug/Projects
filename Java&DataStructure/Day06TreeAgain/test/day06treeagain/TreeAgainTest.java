
package day06treeagain;

import day06treeagain.TreeAgain.Node;
import day06treeagain.TreeAgain.NodeModificationObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TreeAgainTest {
    TreeAgain<String,Integer> tree;
    
    @Before
    public void setUp() {
        tree = new TreeAgain<String,Integer>();
    }
    
    @After
    public void tearDown() {
    }
//
//    @Test
//    public void compareNodes(){
//        String[] keyArray = new String[]{"Jerry","Terry","Berry","Gerry"};
//        Node<String,Integer>[] nodeArray = new Node[4];
//        for(int i = 0 ; i < keyArray.length;i++){
//            nodeArray[i] = new Node<>(keyArray[i],i);
//        }
//        
//        assertTrue(nodeArray[0].key.compareTo(nodeArray[1].key) < 0);
//        assertTrue(nodeArray[1].key.compareTo(nodeArray[2].key) > 0);
//        assertTrue(nodeArray[2].key.compareTo(nodeArray[3].key) < 0);
//        assertTrue(nodeArray[3].key.compareTo(nodeArray[0].key) < 0);
//    }
    @Test
    public void putNodeIntoTreeAgain(){
        try{
            assertEquals(0,tree.getNodesCount());
            tree.put("Jerry", 1);
            assertEquals(1,tree.getNodesCount());
            tree.put("Berry", 2);
            assertEquals(2,tree.getNodesCount());
            tree.put("Terry", 3);
            assertEquals(3,tree.getNodesCount());
            tree.put("Gerry", 4);
            assertEquals(4,tree.getNodesCount());
            
            // same key throws  DuplicateValueException
            tree.put("Gerry", 4);
            
            assertFalse("This shouldn't be shown",true);
        } catch(DuplicateValueException ex) {
            assertTrue("DuplicateValueException thrown",true);
        }
    }
    
    @Test
    public void isDuplicateValueExceptionThrownWithNode(){
        try {
            tree.put("Jerry", 1);
            assertEquals(1,tree.getNodesCount());
            tree.put("Berry", 2);
            assertEquals(2,tree.getNodesCount());
            tree.put("Terry", 3);
            assertEquals(3,tree.getNodesCount());
            tree.put("Gerry", 4);
            assertEquals(4,tree.getNodesCount());
            
            // DuplicateValueException thrown
            tree.put("Gerry", 6);
            
            assertFalse("This shouldn't be shown",true);
        } catch (DuplicateValueException ex) {
            TreeAgain.Node node = (TreeAgain.Node)ex.getNode();
            assertEquals("Gerry",node.key);
            assertEquals(4,node.value);
        }
    }
    
    @Test
    public void getAllKeysInOrder(){
        try {
            tree.put("Jerry", 1);
            assertEquals(1,tree.getNodesCount());
            tree.put("Berry", 2);
            assertEquals(2,tree.getNodesCount());
            tree.put("Terry", 3);
            assertEquals(3,tree.getNodesCount());
            tree.put("Gerry", 4);
            assertEquals(4,tree.getNodesCount());
            
            assertArrayEquals(
                    new String[]{"Berry","Gerry","Jerry","Terry"},
                        tree.getListOfAllKeysInOrderOfKeys()
            );
        } catch (DuplicateValueException ex) {
            assertFalse("This shouldn't be shown",true);
        }
    }
    
    @Test
    public void getAllValuesInOrderByKey(){
        try {
            tree.put("Jerry", 1);
            assertEquals(1,tree.getNodesCount());
            tree.put("Berry", 2);
            assertEquals(2,tree.getNodesCount());
            tree.put("Terry", 3);
            assertEquals(3,tree.getNodesCount());
            tree.put("Gerry", 4);
            assertEquals(4,tree.getNodesCount());
            
            assertArrayEquals(
                    new Integer[]{2,4,1,3},
                        tree.getListOfAllValuesInOrderOfKeys()
            );
        } catch (DuplicateValueException ex) {
            assertFalse("This shouldn't be shown",true);
        }
    }
    
    @Test
    public void isKeyInTree(){
        try {
            tree.put("Jerry", 1);
            tree.put("Berry", 2);
            tree.put("Terry", 3);
            tree.put("Gerry", 4);
            
            assertTrue(tree.hasKey("Jerry"));
            assertTrue(tree.hasKey("Berry"));
            assertTrue(tree.hasKey("Terry"));
            assertTrue(tree.hasKey("Gerry"));
            
            assertFalse(tree.hasKey("terry"));
            assertFalse(tree.hasKey("gerry"));
        } catch (DuplicateValueException ex) {
            assertFalse("This shouldn't be shown",true);
        }
    }
    
    @Test
    public void isValueInTree(){
        try {
            tree.put("Jerry", 1);
            tree.put("Berry", 2);
            tree.put("Terry", 3);
            tree.put("Gerry", 4);
            
            assertTrue(tree.hasValue(3));
            assertTrue(tree.hasValue(2));
            
            assertFalse(tree.hasValue(0));
        } catch (DuplicateValueException ex) {
            assertFalse("This shouldn't be shown",true);
        }
    }
    
    @Test
    public void removeNodeByKey(){
        try {
            tree.put("Jerry", 1);
            tree.put("Berry", 2);
            tree.put("Terry", 3);
            tree.put("Gerry", 4);
            tree.put("Aerry", 5);
            assertEquals(5,tree.getNodesCount());
            
            // 1. remove middle Node
            assertTrue(tree.removeByKey("Berry"));
            
            assertEquals(4,tree.getNodesCount());
            assertFalse(tree.hasValue(2));
            assertFalse(tree.removeByKey("Berry"));
            assertFalse(tree.hasKey("Berry"));
            
            // 2. remove root node
            assertTrue(tree.removeByKey("Jerry"));
            assertEquals(3,tree.getNodesCount());
            assertFalse(tree.hasValue(1));
            assertFalse(tree.removeByKey("Jerry"));
            assertFalse(tree.hasKey("Jerry"));
            
            // 3. remove last node
            assertTrue(tree.removeByKey("Aerry"));
            assertEquals(2,tree.getNodesCount());
            assertFalse(tree.hasValue(5));
            assertFalse(tree.removeByKey("Aerry"));
            assertFalse(tree.hasKey("Aerry"));
            
        } catch (DuplicateValueException ex) {
            assertFalse("This shouldn't be shown",true);
        }
    }
    
    @Test
    public void isTreeIterable(){
        try{
            tree.put("g", 0);
            tree.put("c", 1);
            tree.put("b", 2);
            tree.put("a", 3);
            tree.put("j", 4);
            tree.put("m", 5);
            
            int index = 0;
            String[] strArray = new String[tree.getNodesCount()];
            
            for(String str : tree){
                assertTrue(tree.hasKey(str));
                strArray[index++] = str;
            }
            
            assertArrayEquals(tree.getListOfAllKeysInOrderOfKeys(), strArray);
        } catch ( DuplicateValueException ex){
            assertFalse("This shouldn't be shown",true);
        }
    }
    
    @Test
    //FIXME: How to test observer properly
    public void setObserverToCheckTheTreeModifying(){
        tree.addObserver((node, action) -> {
            System.out.printf(
                    "%s performed. %s from Ramda\n",
                    action,node);
        });
        
        tree.addObserver(new NodeModificationObserver<Node>(){
            @Override
            public void nodeModified(Node node,Action action){
                System.out.printf(
                    "%s performed. %s from Normal\n",
                    action,node
                );
            }
        });
        removeNodeByKey();
        
    }
}
