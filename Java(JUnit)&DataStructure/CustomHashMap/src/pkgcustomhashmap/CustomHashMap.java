/**
 * Description:
 * Custom hash map
 * 
 * Developed by : Donghyeok Seo
 * Date : 2020. 02. 28
 */
package pkgcustomhashmap;

public class CustomHashMap<K,V> {
    
    private class Container<K,V> {
        Container(Container next,K key,V value){
            this.next = next;
            this.key = key;
            this.value = value;
        }
        
        Container next;
        K key;
        V value;
        
        @Override
        public String toString(){
            return key + " => " + value;
        }
    }

    public CustomHashMap(int tableSizeLevel) {
        this.tableSize = primeSizes[tableSizeLevel];
        this.tableSizeLevel = tableSizeLevel;
        hashTable = new Container[tableSize];
    }
    
    int[] primeSizes = { 3,7,13,29,61,127,251,503,1009,2003,3001,4001 };// prime number
    int tableSizeLevel;
    int tableSize;
    Container<K,V> [] hashTable;

    int totalItems = 0;

    private int computeHash(K input) {
        int hashNum = 0;
        for(char c : input.toString().toCharArray()){
            hashNum += c;
            hashNum = hashNum << 1;
        }
        return Math.abs(hashNum % tableSize);
    }

    public V getValue(K key) {
        int hashKey = computeHash(key);
        
        if(hashTable[hashKey] == null){
            return null;
        }
        
        Container<K,V> idxCont = hashTable[hashKey];
        
        while(idxCont != null){
            if(idxCont.key.equals(key)){ return idxCont.value; }
            idxCont = idxCont.next;
        }
        return null;
    }
        
    public void putValue(K key, V value) {
        if((tableSize * 3 < totalItems) && (tableSizeLevel < primeSizes.length)){
            printDebug();
            expandHashTableBy(++tableSizeLevel);
        }
        
        int hashKey = computeHash(key);
        
        if(hashTable[hashKey] == null){
            hashTable[hashKey] = new Container(null,key,value);
            totalItems++;
            return;
        }
        
        Container<K,V> idxCont = hashTable[hashKey];
        
        while(idxCont.next != null){
            if(idxCont.key.equals(key)){
                idxCont.value = value;
                return;
            }
            idxCont = idxCont.next;
        }
        
        if(idxCont.key.equals(key)){
            idxCont.value = value;
        } else {
            idxCont.next = new Container(null,key,value);
            totalItems++;
        }
    }
    // LATER: expand hashTable by about *2 when totalItems > 3*hashTable.length

    public boolean deleteByKey(K key){
        Container idxCont = hashTable[computeHash(key)];
        if(idxCont == null){
            return false;
        }
        if(idxCont.key.equals(key)){
            hashTable[computeHash(key)] = hashTable[computeHash(key)].next;
            totalItems--;
            return true;
        }
        
        while(idxCont.next != null){
            if(idxCont.next.key.equals(key)){
                idxCont.next = idxCont.next.next;
                totalItems--;
                return true;
            }
            idxCont = idxCont.next;
        }
        return false;
    }
    
    Container<K,V>[] getHashTable(){ return this.hashTable; }
    private void expandHashTableBy(int tableSizeLevel){
        
        CustomHashMap<K,V> newHashMap = new CustomHashMap<>(tableSizeLevel);
        
        for(Container<K,V> c: hashTable){
            if(c == null){ continue; }
            while(c != null){
                newHashMap.putValue(c.key, c.value);
                c = c.next;
            }
        }
        hashTable = newHashMap.getHashTable();
        tableSize = newHashMap.getHashTableSize();
    }
    
    public boolean hasKey(K key) {
        if(getValue(key) == null){ return false; }
        return true;
    }
    
    public void clear() {
        hashTable = new Container[tableSize];
        totalItems = 0;
    }
    
    public int getHashTableSize(){ return tableSize; }

    public int getTotalItems(){ return totalItems; }
    
    public void printDebug() {
        for(int i = 0; i < hashTable.length ; i++){
            if(hashTable[i] == null){
                System.out.printf("Entry %d: no items\n",i);
                continue;
            }
            
            Container idxCont = hashTable[i];
            String keyValStr = "";
            int count = 0;
            while( idxCont != null){
                keyValStr += String.format(
                        "- Key: %3s, Value: %5s\n",
                        idxCont.key,idxCont.value
                );
                idxCont = idxCont.next;
                count++;
            }
            System.out.printf("Entry %d: %d items\n%s",i,count,keyValStr);
        }
    } // print hashTable content, one entry per line, with all items in it.

    @Override
    public String toString() { 
        String outStr = "[";
        boolean isFirst = true;
        for(int i = 0; i < hashTable.length; i++){
            Container idxCont = hashTable[i];
            if(idxCont == null) { continue; }
            while(idxCont != null){
                if(isFirst){
                    outStr += "";
                    isFirst = false;
                } else{
                    outStr += ", ";
                }
                outStr += idxCont;
                idxCont = idxCont.next;
            }
        }
        outStr += "]";
        return outStr;
    }
    // e.g. [ Key1 => Val1, Key2 => Val2, ... ]
}
