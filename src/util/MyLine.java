package util;



public class MyLine{
    private int a;
    private int b;
    private int c;

    public MyLine() {}

    public MyLine(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public MyLine (Point p1, Point p2){
        a = p2.y - p1.y;
        b = p1.x - p2.x;
        c = p2.x * p1.y - p1.x * p2.y;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyLine))
            return false;
        if (a * ((MyLine) obj).b == ((MyLine) obj).a * b &&
                a * ((MyLine) obj).c == ((MyLine) obj).a * c &&
                b * ((MyLine) obj).c == ((MyLine) obj).b * c)
            return true;
        return false;
    }
}
