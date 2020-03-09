package day06treeagain;

import day06treeagain.TreeAgain.Node;
import day06treeagain.TreeAgain.NodeModificationObserver.Action;
import java.util.ArrayList;
import java.util.Iterator;

class DuplicateValueException extends Exception {
    private Object node;
    public DuplicateValueException(Object node) {
        this.node = node;
    }
    public Object getNode(){ return node; }
}


// tree of unique values
class TreeAgain<K extends Comparable<K>, V> implements Iterable<K> {
    
    class TreeIterator<K> implements Iterator<K> {
        private K[] ArrayK;
        private int currentIndex;
        
        public TreeIterator(K[] ArrayK){
            if(ArrayK == null){
                throw new IllegalArgumentException();
            }
            this.ArrayK = ArrayK;
        }
        
        @Override
        public boolean hasNext(){
            return currentIndex < ArrayK.length; 
        }
        
        @Override
        public K next(){
            return ArrayK[currentIndex++];
        }
    }
    
    public interface NodeModificationObserver<Node> {
        public void nodeModified(Node node, Action action);
        public enum Action { New, Modified, Deleted };
    }
    
    private ArrayList<NodeModificationObserver> observerList = new ArrayList<>();

    void addObserver(NodeModificationObserver observer) {
        observerList.add(observer);
    }

    void callObserver(Node node, NodeModificationObserver.Action action) {
        for (NodeModificationObserver observer : observerList) {
            observer.nodeModified(node, action);
        }
    }
    
    public class Node<K,V>{
        public Node(K key,V value){
            this.key = key;
            this.value = value;
        }
        Node<K,V> left, right, parent;
        K key;
        V value;
       
        @Override
        public String toString(){
            return String.format("Key: %s, Value: %s",key.toString(),value.toString());
        }
    }

    private Node<K,V> root;
    private int nodesCount;

    void put(K key, V value) throws DuplicateValueException {
        if(root == null){ 
            root = new Node<K,V>(key,value);
            callObserver(root,Action.New);
            nodesCount++;
            return;
        }
        
        Node<K,V> parentNode = findParentNodeOfNewNode(root, key);
        Node<K,V> newNode = new Node<>(key,value);
        newNode.key = key;
        
        if(key.compareTo(parentNode.key) < 0 ){
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }
        newNode.parent = parentNode;
        callObserver(newNode,Action.New);
        nodesCount++;
    }
    
    /**
     * Recursive method.
     * If there is a node that has same key, throws DuplicateValueException.
     * Otherwise, return parent node of new key
     * 
     * @param node
     * @param key
     * @return
     * @throws DuplicateValueException 
     */
    Node<K,V> findParentNodeOfNewNode(Node<K,V> node, K key) throws DuplicateValueException{
        if(node == null){return null;}
        
        Node<K,V> childNode = null;
        
        if(key.compareTo(node.key) == 0){
            throw new DuplicateValueException(node);
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

    K[] getListOfAllKeysInOrderOfKeys() {
 
        Node<K,V>[] nodeArray = (Node<K,V>[])new Node[nodesCount];
        getNodeListInOrder(root,0,nodeArray);
        
        K[] keyArray = (K[])new Comparable[nodesCount];// FIXME: use Comparable instead Object
        
        for(int i=0; i<nodesCount; i++){
            keyArray[i] = nodeArray[i].key;
        }
        return keyArray;
    }
    
    private int getNodeListInOrder(Node<K,V> node,int count,Node[] nodeArray){
        if(node == null){ return count; }
        
        if(node.left != null){
            count = getNodeListInOrder(node.left,count,nodeArray);
        }
        
        nodeArray[count++] = node;
        
        if(node.right != null){
            count = getNodeListInOrder(node.right,count,nodeArray);
        }
        return count;
    }

    V[] getListOfAllValuesInOrderOfKeys() {
        Node[] nodeArray = new Node[nodesCount];
        getNodeListInOrder(root,0,nodeArray);
        
        V[] valArray = (V[])new Object[nodesCount];
        
        for(int i=0; i<nodesCount; i++){
            valArray[i] = ((Node<K,V>)nodeArray[i]).value;
        }
        return valArray;
    }

    boolean hasKey(K key) {
        try{
            findParentNodeOfNewNode(root, key);
            return false;
        }catch(DuplicateValueException ex){
            return true;
        }
    }

    private void findNodeWithValue(Node<K,V> node,V value) throws DuplicateValueException{
        if(node == null){ return ; }
        if(value.equals(node.value)){
            throw new DuplicateValueException(node);
        }
        
        if(node.left != null){
            findNodeWithValue(node.left,value);
        }
        
        if(node.right != null){
            findNodeWithValue(node.right,value);
        }
    }
    
    boolean hasValue(V value) {
        try{
            findNodeWithValue(root,value);
            return false;
        }catch(DuplicateValueException ex){
            return true;
        }
    }
    
    private void removeNode(Node<K,V> node){
        
        Node<K,V> idxNode;
        Node<K,V> replaceNode;
        if(node.left != null){
            idxNode = node.left;
            replaceNode = node.left;
            while(idxNode.right != null){
                idxNode = idxNode.right;
            }
            
            if(node.right != null){
                idxNode.right = node.right;
                idxNode.right.parent = idxNode;
            }
            
        }else if (node.right != null){
            replaceNode = node.right;
        }else{
            root = null;
            callObserver(root,Action.Deleted);
            nodesCount--;
            return;
        }
        

        if(node != root){
            if(node.key.compareTo(node.parent.key) < 0){
                node.parent.left = replaceNode;
                
            } else{
                node.parent.right = node.left;
            }
        }else {
            root = replaceNode;
        }
        callObserver(node,Action.Deleted);
        nodesCount--;
        
    }
    
    boolean removeByKey(K key) {
        if(nodesCount == 0){
            return false;
        }
        
        try{
            findParentNodeOfNewNode(root,key);
            return false;
        }catch (DuplicateValueException ex){
            Node<K,V> node = (Node<K,V>)ex.getNode();
            removeNode(node);
            return true;
        }
    } // Difficult

    boolean removeByValue(V value) {
        if(nodesCount == 0){
            return false;
        }
        
        boolean isFound = false;
        do{
            try{
                findNodeWithValue(root,value);
                break;
            }catch (DuplicateValueException ex){
                Node<K,V> node = (Node<K,V>)ex.getNode();
                removeNode(node);
                isFound = true;
            }
        }while(isFound);
        
        return isFound;
    } // Difficult, remove all nodes matching value

    int getNodesCount() {
        return nodesCount;
    }
    
    @Override
    public Iterator<K> iterator(){
        return new TreeIterator(getListOfAllKeysInOrderOfKeys());
    }
}
