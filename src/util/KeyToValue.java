package util;

/*
键值对，key可以包含重复元素
实现比较方法
 */
public class KeyToValue implements Comparable<KeyToValue>{
    private int key;
    private int value;

    public KeyToValue() {
    }

    public KeyToValue(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(KeyToValue kv) {
        if (this.key > kv.key)
            return 1;
        else if (this.key < kv.key){
            return -1;
        }else {
            if (this.value < kv.value)
                return -1;
            else
                return 1;
        }
    }
}
