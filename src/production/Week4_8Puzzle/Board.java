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
    }

    public int dimension() {
        return N;
    }

    public int hamming() {
        int foo = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != i * N + j + 1) {
                    foo++;
                }
            }
        }
        return foo;
    }

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
    }

    public boolean isGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0 ; j < N; j++) {
                if (i == N - 1 && j == N - 1) {
                    return blocks[i][j] == 0;
                } else {
                    if (blocks[i][j] != i * N + j + 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

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
    }

    public boolean equals(Object y) {
        return y instanceof Board
               && ((Board) y).dimension() == N
               && toString().equals(y.toString());
    }

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
        String foo = N + "\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                foo += " " + blocks[i][j] + " ";
            }
            foo += "\n";
        }
        return foo;
    }

    public static void main(String[] args) {
    }
}
