class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class ClosestPair {

    private static double euclideanDistance(Point p, Point q) {
        int x = p.x - q.x;
        int y = p.y - q.y;
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static void main(String[] args) {
        Point[] points = new Point[3];
        Point p1 = new Point(2, 3);
        Point p2 = new Point(3, 2);
        Point p3 = new Point(2, 1);
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        double minDist = Integer.MAX_VALUE;
        String closestPair = "";
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                Point p = points[i];
                Point q = points[j];
                double distance = euclideanDistance(p, q);
                if (distance < minDist) {
                    minDist = distance;
                    closestPair = "(" + p.x + "," + p.y + ") and (" + q.x + "," + q.y + ")";
                }
            }
        }
        System.out.println(closestPair);
    }
}
