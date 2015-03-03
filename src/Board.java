/**
 * This file is created by @Muffin_C on 3/3/15 16:16.
 * This file is part of Project algs4partI.
 */
public class Board {

    private final int[][] blocks;
    private final int N;


    public Board(int[][] blocks) {
        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
        N = blocks.length;
    }          // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)

    public int dimension() {
        return N;
    }                // board N N

    public int hamming() {
        int foo = 0;

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != i * N + j + 1) {
                    foo++;
                }
            }
        }
        return foo;
    }                  // number of blocks out of place

    public int manhattan() {
        int foo = 0;
        int x;
        int y;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != 0) {
                    x = (blocks[i][j] - 1) / N;
                    y = blocks[i][j] - x * N - 1;
                    foo += Math.abs(x - i) + Math.abs(y - j);
                }
            }
        }
        return foo;
    }                // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0 ; j < N; j++) {
                if (blocks[i][j] != i * N + j + 1) {
                    if (i != N - 1 && j != N - 1) {
                        return false;
                    } else {
                        return blocks[i][j] == 0;
                    }
                }
            }
        }
        return true;
    }               // is this board the goal board?

    public Board twin() {
        Board twinBoard = new Board(blocks);

        int x;
        int y;
        int exchange;
        do {
            x = StdRandom.uniform(N);
            y = StdRandom.uniform(N);
            if (y != 0) {
                exchange = y - 1;
            } else {
                exchange = y + 1;
            }
        } while (blocks[x][y] * blocks[x][exchange] == 0);

        twinBoard.exch(x ,y ,x, exchange);

        return twinBoard;
    }                   // a board that is obtained by exchanging two adjacent blocks in the same row

    public boolean equals(Object y) {
        return y instanceof Board
               && ((Board) y).dimension() == N
               && toString().equals(y.toString());
    }       // does this board equal y?

    public Iterable<Board> neighbors() {
        LinkedQueue<Board> queue = new LinkedQueue<Board>();

        int zeroX = 0;
        int zeroY = 0;

        FOUND:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                    break FOUND;
                }
            }
        }


        Board newBoard1 = new Board(blocks);
        boolean succeed1 = newBoard1.exch(zeroX, zeroY, zeroX - 1, zeroY);
        if (succeed1) {
            queue.enqueue(newBoard1);
        }

        Board newBoard2 = new Board(blocks);
        boolean succeed2 = newBoard2.exch(zeroX, zeroY, zeroX + 1, zeroY);
        if (succeed2) {
            queue.enqueue(newBoard2);
        }

        Board newBoard3 = new Board(blocks);
        boolean succeed3 = newBoard3.exch(zeroX, zeroY, zeroX, zeroY - 1);
        if (succeed3) {
            queue.enqueue(newBoard3);

        }

        Board newBoard4 = new Board(blocks);
        boolean succeed4 = newBoard4.exch(zeroX, zeroY, zeroX, zeroY + 1);
        if (succeed4) {
            queue.enqueue(newBoard4);

        }
        return queue;
    }    // all neighboring boards

    private boolean exch(int x1, int y1, int x2, int y2) {
        if (check(x1) && check(y1) && check(x2) && check(y2)) {
            int temp = blocks[x1][y1];
            blocks[x1][y1] = blocks[x2][y2];
            blocks[x2][y2] = temp;
            return true;
        } else {
            return false;
        }
    }

    private boolean check(int x) {
        return x >= 0 && x < N;
    }

    public String toString() {
        String foo = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                foo += " " + blocks[i][j] + " ";
            }
            foo += "\n";
        }
        return foo;
    }              // string representation of this board (in the output format specified below)

    public static void main(String[] args) {

        args = new String[]{"puzzle00.txt","test.txt"};

        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        In in2 = new In(args[1]);
        int N2 = in2.readInt();
        int[][] blocks2 = new int[N2][N2];
        for (int i = 0; i < N2; i++)
            for (int j = 0; j < N2; j++)
                blocks2[i][j] = in2.readInt();
        Board initial2 = new Board(blocks2);


        System.out.println(initial2.equals(initial));
        System.out.println(initial2.isGoal());
        System.out.println(initial2.hamming());
        System.out.println(initial2.manhattan());
        System.out.println(initial.twin());


        for(Board b : initial2.neighbors()) {
            System.out.println(b);
        }
    }// unit tests (not graded)
}
