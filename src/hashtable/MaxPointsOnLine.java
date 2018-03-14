package hashtable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
ID:149
Given n points on a 2D plane, find the maximum
number of points that lie on the same straight line.
 */
public class MaxPointsOnLine {
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
                Line line = lineOfTwoPoints(points[i], points[j]);
                if (lineRecord.contains(line))
                    continue;
                lineRecord.add(line);
                for (int k = j + 1; k < size; k++) {
                    while (k < size && points[k].x == points[j].x &&
                            points[k].y == points[j].y){
                        visited[k] = true;
                        k++;
                        sumOfPoint++;
                    }
                    if (k < size &&
                            line.equals(lineOfTwoPoints(points[j],points[k]))) {
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

    public static Line lineOfTwoPoints(Point a, Point b){
        Line line = new Line();
        if (a.x == b.x){
            line.slope = Integer.MAX_VALUE;
            line.intercept = a.x;
        }else {
            line.slope = (a.y - b.y) / (a.x - b.x);
            line.intercept = (b.y * a.x - a.y * b.x) / (a.x - b.x);
        }
        return line;
    }

    public static void main(String[] args){
        Point p2 = new Point(-8, -582);
        Point p4 = new Point(-9,-651);
        Point p5 = new Point(9, 591);
        Point p1 = new Point(-4, -4);
        Point p3 = new Point(-3, 3);
        Point[] poins = {p1, p2, p3, p4, p5};
        System.out.println(maxPoints(poins));
    }
}
class Point {
      int x;
      int y;
      Point() {
          x = 0;
          y = 0;
      }
      Point(int a, int b) {
          x = a;
          y = b;
      }
}

class Line{
    double slope;//斜率
    double intercept;//截距
    Line(){}
    Line(double a, double b){
        slope = a;
        intercept = b;
    }

    @Override
    public boolean equals(Object obj) {
        return  obj instanceof Line &&
                slope == ((Line) obj).slope && intercept == ((Line) obj).intercept;
    }
}