package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List<String> readTestCase(String filename){
        String cases = readCaseByFile(filename).replaceAll("\"", "");
        String[] strs = cases.split(",");
        List<String> testcases = new ArrayList<>(Arrays.asList(strs));
        return testcases;
    }

    private static String readCaseByFile(String fileName){
        File file = new File(fileName);
        StringBuffer stringBuffer = new StringBuffer();
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempChar;
            while ((tempChar = reader.read()) != -1){
                stringBuffer.append((char)tempChar);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String filename = "testcase.txt";
        List<String> cases = readTestCase(filename);
        System.out.println(cases);
    }
}
