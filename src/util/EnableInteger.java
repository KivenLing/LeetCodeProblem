package util;

/**
 * 可以更改int内容，替代泛型中使用Integer无法改变值
 * @author Kiven Ling
 * 2018/5/4 10:29
 */
public class EnableInteger{
    private int val;

    public EnableInteger(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
