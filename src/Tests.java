import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Tests {
    public static void main(String[] args) {
        HashTable<Integer, String> table = new HashTable<>();
        table.put(1, "a");
        table.put(2, "a");
        System.out.println(table.get(2));
        System.out.println(table.containsKey(1));
        System.out.println(table.containsKey(0));
        table.remove(1);
        table.put(1, "a");
        table.put(2, "a");
        table.put(4, "c");
        table.put(6, "b");
        table.remove(4);
        table.put(8, "x");
        for(Map.Entry<Integer, String> entry: table.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println(table.containsKey(1));
        System.out.println(table.containsKey(4));
        System.out.println(table.containsValue("b"));
        table.put(8, "z");
        for(Integer set: table.keySet()){
            System.out.println(set);
        }
        for (String s: table.values()){
            System.out.println(s);
        }
    }
}
