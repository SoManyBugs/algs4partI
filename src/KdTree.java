/**
 * This file is created by @Muffin_C on 3/5/15 14:54.
 * This file is part of Project algs4partI.
 */
public class KdTree{

    private static class Node{
        private Point2D point;
        private boolean direction;
        private Node leftDown;
        private Node rightUp;
        private Node parent;
        private RectHV line;
        private RectHV rect;


        public Node(Point2D point, boolean direction, Node parent) {
            this.point = point;
            this.direction = direction;
            this.parent = parent;

            if (parent == null) {
                line = new RectHV(point.x(), 0, point.x(), 1);
                rect = new RectHV(0, 0, 1, 1);
            } else {
                if (direction) {
                    if (parent.point.y() > point.y()) {
                        rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(),
                                parent.rect.xmax(), parent.point.y());
                        line = new RectHV(point.x(), parent.rect.ymin(),
                                point.x(), parent.point.y());
                    } else {
                        rect = new RectHV(parent.rect.xmin(), parent.point.y(),
                                parent.rect.xmax(), parent.rect.ymax());
                        line = new RectHV(point.x(), parent.point.y(),
                                point.x(), parent.rect.ymax());
                    }
                } else {
                    if (parent.point.x() > point.x()) {
                        rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(),
                                parent.point.x(), parent.rect.ymax());
                        line = new RectHV(parent.rect.xmin(), point.y(),
                                parent.point.x(), point.y());
                    } else {
                        rect = new RectHV(parent.point.x(), parent.rect.ymin(),
                                parent.rect.xmax(), parent.rect.ymax());
                        line = new RectHV(parent.point.x(), point.y(),
                                parent.rect.xmax(), point.y());
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
    }

    public boolean isEmpty() {
        return head == null;
    }                     // is the set empty?

    public int size() {
        return size;
    }

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

    }

    private Node insert(Point2D p, Node root, Node parent) {

        if (root == null) {
            root = new Node(p, !parent.direction, parent);
        } else {
            if (root.direction) {
                if (p.x() < root.point.x()) {
                    root.leftDown = insert(p, root.leftDown, root);
                } else {
                    root.rightUp = insert(p, root.rightUp, root);
                }
            } else {
                if (p.y() < root.point.y()) {
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

    }

    private Node find(Point2D p, Node root) {
        if (root == null) {
            return null;
        }

        if (root.point.compareTo(p) == 0) {
            return root;
        } else {
            if (root.direction) {
                if (p.x() < root.point.x()) {
                    return find(p, root.leftDown);
                } else {
                    return find(p, root.rightUp);
                }
            } else {
                if (p.y() < root.point.y()) {
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
    }

    private void visit(Node root) {

        if (root.leftDown != null) {
            visit(root.leftDown);
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        root.point.draw();

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

        range(rect, head, queue);

        return queue;
    }

    private void range(RectHV rect, Node root, LinkedQueue<Point2D> queue) {
        if (root != null) {
            if (rect.contains(root.point)) {
                queue.enqueue(root.point);
            }
            if (rect.intersects(root.rect)) {
                range(rect, root.leftDown, queue);
                range(rect, root.rightUp, queue);
            }
        }
    }

    public Point2D nearest(Point2D p) {
        checkNull(p);
        return nearest(p, null, Double.MAX_VALUE, head);
    }            // a nearest neighbor in the set to point point; null if the set is empty

    private Point2D nearest(Point2D p, Point2D champion, double nearestDistance, Node root) {
        if (root != null) {
            double distance = root.point.distanceTo(p);

            if (nearestDistance > distance) {
                champion = root.point;
                nearestDistance = distance;
            }

            if (nearestDistance > root.rect.distanceTo(p)) {
                Point2D championLD = nearest(p, champion, nearestDistance, root.leftDown);
                Point2D championRU = nearest(p, champion, nearestDistance, root.rightUp);

                champion = championLD.distanceTo(p) > championRU.distanceTo(p) ? championRU : championLD;
            }
        }
        return champion;
    }

    public static void main(String[] args) {
    }
}
