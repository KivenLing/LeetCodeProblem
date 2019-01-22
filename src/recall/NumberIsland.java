package recall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kiven Ling
 * 2019/1/21 16:16
 * ID: 200 130 417
 */
public class NumberIsland {
    private static final int[][] DIRECT = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int m;
    private static int n;
    /**
     * ID: 200
     * Given a 2d grid map of '1's (land) and '0's (water), count the number
     * of islands. An island is surrounded by water and is formed by connecting
     * adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.
     * Example 1:
     * Input:
     * 11110
     * 11010
     * 11000
     * 00000
     * Output: 1
     *
     * Example 2:
     * Input:
     * 11000
     * 11000
     * 00100
     * 00011
     * Output: 3
     */
    public static int numIslands(char[][] grid) {
        if (grid.length == 0)
            return 0;
        m = grid.length;
        n = grid[0].length;
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    sum++;
                    dfs(grid, i, j);
                }
            }
        }
        return sum;
    }

    private static void dfs(char[][] grid, int x, int y) {
        grid[x][y] = '0';
        for (int i = 0; i < 4; i++) {
            int newX = x + DIRECT[i][0];
            int newY = y + DIRECT[i][1];
            if (inArea(newX, newY) && grid[newX][newY] == '1')
                dfs(grid, newX, newY);
        }
    }

    private static boolean inArea(int x, int y){
        return x >= 0 && x < m && y >= 0 && y < n;
    }


    /**
     * ID: 130
     * Given a 2D board containing 'X' and 'O' (the letter O),
     * capture all regions surrounded by 'X'.
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     * Example:
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     *
     * After running your function, the board should be:
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * Explanation:
     *
     * Surrounded regions shouldn’t be on the border, which means that any 'O' on
     * the border of the board are not flipped to 'X'. Any 'O' that is not on the
     * border and it is not connected to an 'O' on the border will be flipped to 'X'.
     * Two cells are connected if they are adjacent cells connected horizontally or
     * vertically.
     */
    public static void solve(char[][] board) {
        if (board.length == 0)
            return;
        m = board.length;
        n = board[0].length;
        boolean[][] isO = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            // 假设都是X
            Arrays.fill(isO[i], false);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 检查是否应该是'O'
                checkO(board, isO, i, j);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !isO[i][j])
                    board[i][j] = 'X';
            }
        }
    }

    //检查'O'是否被'X'包围
    private static void checkO(char[][] board, boolean[][] isO, int i, int j){
        if (board[i][j] == 'O' && !isO[i][j]) {
            for (int k = 0; i < 4; k++) {
                int newX = i + DIRECT[k][0];
                int newY = j + DIRECT[k][1];
                if (!inArea(newX, newY)) {//周围不被X包围，说明不符合条件，深度遍历
                    findUnsurroundedO(board, isO, i, j);
                    return;//检查是未被包围的O，无需再检查
                }
            }
        }
    }
    //深度遍历未被'X'包围的'O'
    private static void findUnsurroundedO(char[][] board, boolean[][] isO, int x, int y) {
        isO[x][y] = true;//这里O
        for (int i = 0; i < 4; i++) {
            int newX = x + DIRECT[i][0];
            int newY = y + DIRECT[i][1];
            if (inArea(newX, newY) && board[newX][newY] == 'O' && !isO[newX][newY])
                findUnsurroundedO(board, isO, newX, newY);
        }
    }

    /**
     * ID: 417
     * Given an m x n matrix of non-negative integers representing the height of each
     * unit cell in a continent, the "Pacific ocean" touches the left and top edges of
     * the matrix and the "Atlantic ocean" touches the right and bottom edges.
     * Water can only flow in four directions (up, down, left, or right) from a cell
     * to another one with height equal or lower.
     * Find the list of grid coordinates where water can flow to both the Pacific and
     * Atlantic ocean.
     *
     * Note:
     * The order of returned grid coordinates does not matter.
     * Both m and n are less than 150.
     *
     * Example:
     * Given the following 5x5 matrix:
     * Pacific ~   ~   ~   ~   ~
     *      ~  1   2   2   3  (5) *
     *      ~  3   2   3  (4) (4) *
     *      ~  2   4  (5)  3   1  *
     *      ~ (6) (7)  1   4   5  *
     *      ~ (5)  1   1   2   4  *
     *         *   *   *   *   * Atlantic
     * Return:
     * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
     * (positions with parentheses in above matrix).
     */
    public static List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix.length == 0)
            return res;
        m = matrix.length;
        n = matrix[0].length;
        // 分别记录可以流到P和A的陆地
        boolean[][] flowToP = new boolean[m][n];
        boolean[][] flowToA = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(flowToP[i], false);
            Arrays.fill(flowToA[i], false);
        }
        dfsPandA(matrix, flowToP, flowToA, 0, n - 1);
        dfsPandA(matrix, flowToP, flowToA, m - 1, 0);
        for (int i = 0; i < m; i++) {
            dfsPorA(matrix, flowToP, i, 0);
            dfsPorA(matrix, flowToA, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            dfsPorA(matrix, flowToP, 0, i);
            dfsPorA(matrix, flowToA, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (flowToP[i][j] && flowToA[i][j])
                    res.add(new int[]{i, j});
            }
        }
        return res;
    }

    private static void dfsPorA(int[][] matrix, boolean[][] flowToAorP,
                                int x, int y){
        flowToAorP[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + DIRECT[i][0];
            int newY = y + DIRECT[i][1];
            if (inArea(newX, newY) && matrix[x][y] <= matrix[newX][newY] &&
                    !flowToAorP[newX][newY]){
                dfsPorA(matrix, flowToAorP, newX, newY);
            }
        }
    }

    private static void dfsPandA(int[][] matrix, boolean[][] flowToP, boolean[][] flowToA,
                                 int x, int y){
        flowToP[x][y] = true;
        flowToA[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + DIRECT[i][0];
            int newY = y + DIRECT[i][1];
            if (inArea(newX, newY) && matrix[x][y] <= matrix[newX][newY] &&
                    (!flowToP[newX][newY] || !flowToA[newX][newY])){
                dfsPandA(matrix, flowToP, flowToA, newX, newY);
            }
        }
    }

    public static void main(String[] args) {
        //[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
        int[][] matrix = {{1,2,2,3,5},
                          {3,2,3,4,4},
                          {2,4,5,3,1},
                          {6,7,1,4,5},
                          {5,1,1,2,4}};
        List<int[]> res = pacificAtlantic(matrix);
        System.out.println(res.size());
    }
}
