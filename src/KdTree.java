/**
 * This file is created by @Muffin_C on 3/5/15 14:54.
 * This file is part of Project algs4partI.
 */
public class KdTree{

    private static class Node{
        private Point2D p;
        private boolean direction;
        private Node leftDown;
        private Node rightUp;
        private Node parent;
        private RectHV line;
        private RectHV rect;


        public Node(Point2D p, boolean direction, Node parent) {
            this.p = p;
            this.direction = direction;
            this.parent = parent;

            if (parent == null) {
                line = new RectHV(p.x(), 0, p.x(), 1);
                rect = new RectHV(0, 0, 1, 1);
            } else {
                if (direction) {
                    if (parent.p.y() > p.y()) {
                        rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y());
                        line = new RectHV(p.x(), parent.rect.ymin(), p.x(), parent.p.y());
                    } else {
                        rect = new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
                        line = new RectHV(p.x(), parent.p.y(), p.x(), parent.rect.ymax());
                    }
                } else {
                    if (parent.p.x() > p.x()) {
                        rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
                        line = new RectHV(parent.rect.xmin(), p.y(), parent.p.x(), p.y());
                    } else {
                        rect = new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
                        line = new RectHV(parent.p.x(), p.y(), parent.rect.xmax(), p.y());
                    }
                }
            }

        }
    }

    private Node head;
    private int size;

    public KdTree() {
        size = 0;
        head = null;
    }                              // construct an empty set of points

    public boolean isEmpty() {
        return head == null;
    }                     // is the set empty?

    public int size() {
        return size;
    }                        // number of points in the set

    private void checkNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    public void insert(Point2D p) {
        checkNull(p);

        if (isEmpty()) {
            head = new Node(p, true, null);
            size++;
        } else {
            if (!contains(p)) {
                insert(p, head, null);
                size++;
            }
        }

    }              // add the point to the set (if it is not already in the set)

    private Node insert(Point2D p, Node root, Node parent) {

        if (root == null) {
            root = new Node(p, !parent.direction, parent);
        } else {
            if (root.direction) {
                if (p.x() < root.p.x()) {
                    root.leftDown = insert(p, root.leftDown, root);
                } else {
                    root.rightUp = insert(p, root.rightUp, root);
                }
            } else {
                if (p.y() < root.p.y()) {
                    root.leftDown = insert(p, root.leftDown, root);
                } else {
                    root.rightUp = insert(p, root.rightUp, root);
                }
            }
        }

        return root;
    }

    public boolean contains(Point2D p) {
        checkNull(p);

        if (isEmpty()) {
            return false;
        } else {
            return find(p, head) != null;
        }

    }          // does the set contain point p?

    private Node find(Point2D p, Node root) {
        if (root == null) {
            return null;
        }

        if (root.p.compareTo(p) == 0) {
            return root;
        } else {
            if (root.direction) {
                if (p.x() < root.p.x()) {
                    return find(p, root.leftDown);
                } else {
                    return find(p, root.rightUp);
                }
            } else {
                if (p.y() < root.p.y()) {
                    return find(p, root.leftDown);
                } else {
                    return find(p, root.rightUp);
                }
            }
        }
    }

    public void draw() {
        if (!isEmpty()) {
            visit(head);
        }
    }                       // draw all points to standard draw

    private void visit(Node root) {

        if (root.leftDown != null) {
            visit(root.leftDown);
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        root.p.draw();

        StdDraw.setPenRadius();
        if (root.parent == null) {
            StdDraw.setPenColor(StdDraw.RED);
        } else {
            if (root.direction) {
                StdDraw.setPenColor(StdDraw.RED);
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
            }
        }
        root.line.draw();


        if (root.rightUp != null) {
            visit(root.rightUp);
        }

    }

    public Iterable<Point2D> range(RectHV rect) {
        checkNull(rect);

        LinkedQueue<Point2D> queue = new LinkedQueue<Point2D>();

        if (!isEmpty()) {
            range(rect, head, queue);
        }

        return queue;
    }            // all points that are inside the rectangle

    private void range(RectHV rect, Node root, LinkedQueue queue) {



    }

    public Point2D nearest(Point2D p) {
        checkNull(p);
        return new Point2D(0,0);
    }            // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
//        String filename = "circle10.txt";
//
//        In in = new In(filename);
//
//        StdDraw.show(0);
//
//        // initialize the data structures with N points from standard input
////        PointSET brute = new PointSET();
//        KdTree kdtree = new KdTree();
//        while (!in.isEmpty()) {
//            double x = in.readDouble();
//            double y = in.readDouble();
//            Point2D p = new Point2D(x, y);
//            kdtree.insert(p);
////            System.out.println("here");
////            brute.insert(p);
//        }
//
//        kdtree.draw();
//
        KdTree kdtree = new KdTree();
        kdtree.insert(new Point2D(0.5, 0.5));
        kdtree.insert(new Point2D(0.9, 0.23));
        kdtree.insert(new Point2D(0.3, 0.4));
        kdtree.insert(new Point2D(0.1, 0.7));
        kdtree.insert(new Point2D(0.23, 0.9));
        kdtree.insert(new Point2D(0.66, 0.563));
//        kdtree.insert(new Point2D(0.123, 0.23));
//        kdtree.insert(new Point2D(0.6, 0.9));

        System.out.println(kdtree.contains(new Point2D(0.6,0.9)));

        StdDraw.show(0);

        kdtree.draw();
        StdDraw.show(50);

    }

}
