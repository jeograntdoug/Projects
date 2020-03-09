/**
 * Description:
 * Custom hash map
 * 
 * Developed by : Donghyeok Seo
 * Date : 2020. 02. 28
 */
package pkgcustomhashmap;

public class CustomHashMapStringString {
    private class Container {
        Container(Container next,String key,String value){
            this.next = next;
            this.key = key;
            this.value = value;
        }
        
        Container next;
        String key;
        String value;
        
        @Override
        public String toString(){
            return key + " => " + value;
        }
    }
    int tableSize = 7;
    Container [] hashTable = new Container[tableSize];

    int totalItems = 0;

    private int computeHash(String input) {
        int hashNum = 0;
        for(char c : input.toCharArray()){
            hashNum += c;
            hashNum = hashNum << 1;
        }
        return hashNum % tableSize;
    }

    public String getValue(String key) {
        int hashKey = computeHash(key);
        
        if(hashTable[hashKey] == null){
            return null;
        }
        
        Container idxCont = hashTable[hashKey];
        
        while(idxCont != null){
            if(idxCont.key.equals(key)){ return idxCont.value; }
            idxCont = idxCont.next;
        }
        return null;
    }
        
    public void putValue(String key, String value) { 
        int hashKey = computeHash(key);
        
        if(hashTable[hashKey] == null){
            hashTable[hashKey] = new Container(null,key,value);
            totalItems++;
            return;
        }
        
        Container idxCont = hashTable[hashKey];
        
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

    public boolean hasKey(String key) {
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
            System.out.printf("Entry %d: %d items\n%s",i,count,keyValStr);
            while( idxCont != null){
                keyValStr += String.format(
                        "- Key: %3s, Value: %5s\n",
                        idxCont.key,idxCont.value
                );
                idxCont = idxCont.next;
                count++;
            }
            
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
