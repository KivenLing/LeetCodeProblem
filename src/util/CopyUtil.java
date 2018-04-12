package util;

import java.io.*;
import java.util.List;

/**
 * @author Kiven Ling
 * 2018/4/13 0:24
 * 使用序列化深度拷贝对象
 */
public class CopyUtil {
    /**
     *
     * @param src 源list
     * @param <T> 泛型
     * @return 返回src list的深度拷贝，即重新分配内存空间
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
}
