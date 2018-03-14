package hashtable;

import util.Point;
import util.ReadCase;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
ID:149
Given n points on a 2D plane, find the maximum
number of points that lie on the same straight line.
 */

public class MaxPointsOnLine {
    //有一个测试用例通不过不知道为什么
    public static int maxPoints(Point[] points) {
        int maxP = 0;
        int size = points.length;
        if (size <= 2)
            return size;
        //记录一条直线上访问的点
        boolean[] visited = new boolean[size];
        Set<Line> lineRecord = new HashSet<>();
        for (int i = 0; i < size - 1; i++) {
            visited[i] = true;
            int sumOfPoint = 1;
            for (int j = i + 1; j < size; j++) {
                sumOfPoint = 1;
                if (visited[j])
                    continue;
                //排除两点相同的情况
                while (j < size && points[j].x == points[i].x &&
                        points[j].y == points[i].y){
                    visited[j] = true;
                    j++;
                    sumOfPoint++;
                }
                if (j >= size) {
                    maxP = Math.max(maxP, sumOfPoint);
                    break;
                }
                visited[j] = true;
                sumOfPoint++;
                Line line = new Line(points[i], points[j]);
                if (lineRecord.contains(line))
                    continue;
                lineRecord.add(line);
                for (int k = j + 1; k < size; k++) {
                    if (points[k].x == points[i].x &&
                            points[k].y == points[i].y){
                        visited[k] = true;
                        sumOfPoint++;
                        continue;
                    }
                    if (line.equals(new Line(points[i],points[k]))) {
                        visited[k] = true;
                        sumOfPoint++;
                    }
                }
                maxP = Math.max(maxP, sumOfPoint);
            }
            Arrays.fill(visited, false);
        }
        return maxP;
    }

    public static void main(String[] args){
        String cases = "[40,-23],[9,138],[429,115],[50,-17],[-3,80]," +
                "[-10,33],[5,-21],[-3,80],[-6,-65],[-18,26],[-6,-65]," +
                "[5,72],[0,77],[-9,86],[10,-2],[-8,85],[21,130],[18,-6]," +
                "[-18,26],[-1,-15],[10,-2],[8,69],[-4,63],[0,3],[-4,40]," +
                "[-7,84],[-8,7],[30,154],[16,-5],[6,90],[18,-6],[5,77]," +
                "[-4,77],[7,-13],[-1,-45],[16,-5],[-9,86],[-16,11],[-7,84]," +
                "[1,76],[3,77],[10,67],[1,-37],[-10,-81],[4,-11],[-20,13]," +
                "[-10,77],[6,-17],[-27,2],[-10,-81],[10,-1],[-9,1],[-8,43]," +
                "[2,2],[2,-21],[3,82],[8,-1],[10,-1],[-9,1],[-12,42],[16,-5]," +
                "[-5,-61],[20,-7],[9,-35],[10,6],[12,106],[5,-21],[-5,82],[6,71]," +
                "[-15,34],[-10,87],[-14,-12],[12,106],[-5,82],[-46,-45],[-4,63]," +
                "[16,-5],[4,1],[-3,-53],[0,-17],[9,98],[-18,26],[-9,86],[2,77]," +
                "[-2,-49],[1,76],[-3,-38],[-8,7],[-17,-37],[5,72],[10,-37],[-4,-57]," +
                "[-3,-53],[3,74],[-3,-11],[-8,7],[1,88],[-12,42],[1,-37],[2,77],[-6,77]," +
                "[5,72],[-4,-57],[-18,-33],[-12,42],[-9,86],[2,77],[-8,77],[-3,77]," +
                "[9,-42],[16,41],[-29,-37],[0,-41],[-21,18],[-27,-34],[0,77],[3,74]," +
                "[-7,-69],[-21,18],[27,146],[-20,13],[21,130],[-6,-65],[14,-4],[0,3]," +
                "[9,-5],[6,-29],[-2,73],[-1,-15],[1,76],[-4,77],[6,-29]";
        String cases2 = "[0,0],[1,65536],[65536,0]";
        Point[] poins = ReadCase.readPoints(cases);
        System.out.println(maxPoints(poins));
    }
}
class Line{
    public long a;
    public long b;
    public long c;

    public Line() {}

    public Line(long a, long b, long c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Line (Point p1, Point p2){
        a = p2.y - p1.y;
        b = p1.x - p2.x;
        c = (long)p2.x * p1.y - (long)p1.x * p2.y;
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Line))
            return false;
        if (a * ((Line) obj).b == ((Line) obj).a * b &&
                a * ((Line) obj).c == ((Line) obj).a * c &&
                b * ((Line) obj).c == ((Line) obj).b * c)
            return true;
        return false;
    }
}



