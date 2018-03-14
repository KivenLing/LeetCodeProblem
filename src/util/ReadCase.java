package util;
/*
用于读入输出测试数据
 */
public class ReadCase {
    private ReadCase(){}

    public static Point[] readPoints(String points){
        String[] ps = points.split("],");
        Point[] p = new Point[ps.length];
        for (int i = 0; i < ps.length; i++) {
            String point = ps[i];
            int index = 1;
            while (index < point.length() && (Character.isDigit(point.charAt(index)) ||
                    point.charAt(index) == '-')){
                index++;
            }
            int x = Integer.parseInt(point.substring(1, index));
            int beginY = ++index;
            while (index < point.length() && (Character.isDigit(point.charAt(index)) ||
                    point.charAt(index) == '-')){
                index++;
            }
            int y = Integer.parseInt(point.substring(beginY, index));
            p[i] = new Point(x, y);
        }
        return p;
    }
}
