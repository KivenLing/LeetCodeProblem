package recall;

import java.util.Arrays;

/**
 * @author Kiven Ling
 * 2019/1/21 16:16
 * ID: 200 130
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
        //todo
    }
}
