
package treepatterns;

import treepatterns.TreeStringIntSet.TreeModificationObserver.Action;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

class DuplicateValueException extends Exception {
    public DuplicateValueException(String msg){
        super(msg);
    }
}

class TreeStringIntSet implements Iterable<Pair> {
    class Node {
        public Node(String key,int value){
            this.key = key;
            valuesSet.add(value);
        }
        Node left, right;
        String key; // keys are unique
        // HashSet is like ArrayList except it does not hold duplicates
        HashSet<Integer> valuesSet = new HashSet<>(); // unique only
    }

    private Node root;
    private int nodesTotal;

    void add(String key, int value) throws DuplicateValueException { // throws DuplicateValueException
        if(nodesTotal == 0){
            root = new Node(key,value);
            callObserver(root,Action.Add,value);
            nodesTotal++;
            return;
        }

        Node idxNode = findParentNodeOfNewNode(root,key);
        
        if(key.compareTo(idxNode.key) == 0){
            
            if(idxNode.valuesSet.contains(value)){
                callObserver(new Node(key,value),Action.Failure,value);
                throw new DuplicateValueException("Value is already exist");
            }
            
            idxNode.valuesSet.add(value);
            return;
        } else if(key.compareTo(idxNode.key) < 0){
            idxNode.left = new Node(key,value);
            callObserver(idxNode.left,Action.Add,value);
            
        } else if(key.compareTo(idxNode.key) > 0){
            idxNode.right = new Node(key,value);
            callObserver(idxNode.right,Action.Add,value);
        }
        
        nodesTotal++;
    }

    // if there is a key in the tree, return that node
    // Otherwise return parent node where new node will be located
    private Node findParentNodeOfNewNode(Node node, String key){
        if(node == null){return null;}

        Node childNode = null;

        if(key.compareTo(node.key) == 0){
            return node;
        }else if(key.compareTo(node.key) < 0){
            childNode = findParentNodeOfNewNode(node.left, key);
        }else {
            childNode = findParentNodeOfNewNode(node.right, key);
        }

        if(childNode == null){
            return node;
        } else{
            return childNode;
        }
    }
    
    int getNodesTotal(){return nodesTotal; }

    boolean containsKey(String key) { 
        Node idxNode = findParentNodeOfNewNode(root,key);
        
        return key.equals(idxNode.key);
    }

    List<Integer> getValuesByKey(String key) { 
        Node idxNode = findParentNodeOfNewNode(root,key);
        if(key.equals(idxNode.key)){
            return new ArrayList<Integer>(idxNode.valuesSet);
        }
        return new ArrayList<Integer>();
    }

    List<String> getKeysContainingValue(int value) { 
        ArrayList<Node> nodeList = new ArrayList<>();
        getAllNodeList(root,nodeList);
        ArrayList<String> keyList = new ArrayList<>();
        for(Node node: nodeList){
            if(node.valuesSet.contains(value)){
                keyList.add(node.key);
            }
        }
        return keyList;
    }

    List<String> getAllKeys() { 
        ArrayList<Node> nodeList = new ArrayList<>();
        getAllNodeList(root,nodeList);
        ArrayList<String> keyList = new ArrayList<>();
        for(Node node: nodeList){
            keyList.add(node.key);
        }
        return keyList;
    }
    
    private void getAllNodeList(Node node,List<Node> nodeList){
        if(node == null){return;}
        
        getAllNodeList(node.left,nodeList);
        nodeList.add(node);
        getAllNodeList(node.right,nodeList);
    }

    
    ////////////////////////////////////
    // TreeModificationObserver start
    ////////////////////////////////////
    interface TreeModificationObserver {
        void modified(Node node,Action action,int value);
        enum Action { Add, Failure };
    }
    
    TreeModificationObserver observer;
    
    public void setObserver(TreeModificationObserver observer){
        this.observer = observer;
    }
    private void callObserver(Node node,Action action,int value){
        if(observer != null){
            observer.modified(node, action,value);
        }
    }
    ////////////////////////////////////
    // TreeModificationObserver end
    ////////////////////////////////////
    
    ////////////////////////////////////
    // Iterator start
    ////////////////////////////////////
    class TreeIterator<Pair> implements Iterator<Pair> {
        private Pair[] ArrayPair;
        private int currentIndex;
        
        public TreeIterator(Pair[] ArrayPair){
            if(ArrayPair == null){
                throw new IllegalArgumentException();
            }
            this.ArrayPair = ArrayPair;
        }
        
        @Override
        public boolean hasNext(){
            return currentIndex < ArrayPair.length; 
        }
        
        @Override
        public Pair next(){
            return ArrayPair[currentIndex++];
        }
    }
    
    @Override
    public Iterator<Pair> iterator(){
        List<Node> nodeList = new ArrayList<>();
        getAllNodeList(root, nodeList);
        
        List<Pair> pairList = new ArrayList<>();
        for(Node node:nodeList){
            for(int value : node.valuesSet){
                pairList.add(new Pair<String,Integer>(node.key,value));
            }
        }
        Pair[] pairArray = pairList.toArray(new Pair[0]);
        return new TreeIterator(pairArray);
    }
    ////////////////////////////////////
    // Iterator end
    ////////////////////////////////////
    
    
    ////////////////////////////////////
    // Visitor Start
    ////////////////////////////////////
    
    // add code for visitor that is called on each node - parameter is node

    // Note: only one observer and one visitor at a time is supported.

    void visitEachNode(NodeVisitorInt visitor) {
        ArrayList<Node> nodeList = new ArrayList<>();
        getAllNodeList(root, nodeList);
        for(Node node : nodeList ){
            visitor.visitNode(node);
        }
    }

    interface NodeVisitorInt {
            void visitNode(Node node);
    }
    
    ////////////////////////////////////
    // Visitor End
    ////////////////////////////////////

}

class Pair<K,V> {
    K key;
    V value;
    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public String toString() {
        return String.format("(%s=>%s)", key.toString(), value.toString());
    }
}
