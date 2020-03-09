/**
 * Description:
 * Custom hash map
 * 
 * Developed by : Donghyeok Seo
 * Date : 2020. 02. 28
 */
package pkgcustomhashmap;

public class CustomHashMapMain {

    public static void main(String[] args) {
        CustomHashMap<String,String> hMap = new CustomHashMap<>(0);
        
        System.out.println("===================");
        System.out.println("Round 1");
        hMap.putValue("key1", "val1");
        hMap.putValue("key2", "val2");
        hMap.putValue("key3", "val3");
        System.out.printf("ItemCount:%d | %s\n",hMap.totalItems,hMap.toString());
        hMap.printDebug();
        
        System.out.println("===================");
        System.out.println("Round 2");
        hMap.putValue("key4", "val1");
        hMap.putValue("key5", "val2");
        hMap.putValue("key6", "val3");
        System.out.printf("ItemCount:%d | %s\n",hMap.totalItems,hMap.toString());
        hMap.printDebug();
        
        System.out.println("===================");
        System.out.println("Round 3");
        hMap.putValue("key7", "val1");
        hMap.putValue("key8", "val2");
        hMap.putValue("key9", "val3");
        System.out.printf("ItemCount:%d | %s\n",hMap.totalItems,hMap.toString());
        hMap.printDebug();
        
        System.out.println("===================");
        System.out.println("Round 4");
        hMap.putValue("key10", "val1");
        hMap.putValue("key11", "val2");
        hMap.putValue("key12", "val3");
        System.out.printf("ItemCount:%d | %s\n",hMap.totalItems,hMap.toString());
        hMap.printDebug();
        
        System.out.println("===================");
        System.out.println("Round 5");
        hMap.putValue("key13", "val1");
        hMap.putValue("key14", "val2");
        hMap.putValue("key15", "val3");
        System.out.printf("ItemCount:%d | %s\n",hMap.totalItems,hMap.toString());
        hMap.printDebug();
    }
    
}
