package util;

/**
 * @author Kiven Ling
 * @date 2018/4/7 22:48
 * @description Pair 工具类
 */
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }
}
