package recall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kiven Ling
 * 2019/1/21 15:01
 * ID: 79 212
 */
public class WordSearch {
    private static final int[][] DIRECT = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * ID: 79
     * Given a 2D board and a word, find if the word exists in the grid.
     * The word can be constructed from letters of sequentially adjacent cell,
     * where "adjacent" cells are those horizontally or vertically neighboring.
     * The same letter cell may not be used more than once.
     * Example:
     * board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     *
     * Given word = "ABCCED", return true.
     * Given word = "SEE", return true.
     * Given word = "ABCB", return false.
     */
    public static boolean exist(char[][] board, String word) {
        int sizeX = board.length;
        int sizeY = board[0].length;
        boolean[][] used = new boolean[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            Arrays.fill(used[i], false);
        }
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (match(board, word, used, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    //从board[x][y] 开始匹配 word[index]
    private static boolean match(char[][] board, String word, boolean[][] used,
                                 int x, int y, int index) {
        if (board[x][y] != word.charAt(index)) { //不匹配返回
            System.out.println(board[x][y] + " != " + word.charAt(index));
            return false;
        }
        //若board[x][y] 匹配 word[index]
        used[x][y] = true;
        if (index == word.length() - 1)//已经匹配到最后一个字符
            return true;
        // 从四个方向开始匹配下一个字符
        for (int i = 0; i < 4; i++) {
            int newX = x + DIRECT[i][0];
            int newY = y + DIRECT[i][1];
            if (inArea(newX, newY, board.length, board[0].length) && !used[newX][newY]
                    && match(board, word, used, newX, newY, index + 1)){
                return true;
            }
        }
        used[x][y] = false;
        return false;
    }

    private static boolean inArea(int x, int y, int maxX, int maxY){
        return x >= 0 && x < maxX && y >= 0 && y < maxY;
    }

    /**
     * ID： 212
     * Given a 2D board and a list of words from the dictionary,
     * find all words in the board.
     * Each word must be constructed from letters of sequentially adjacent cell,
     * where "adjacent" cells are those horizontally or vertically neighboring.
     * The same letter cell may not be used more than once in a word.
     * Example:
     * Input:
     * words = ["oath","pea","eat","rain"] and board =
     * [
     * ['o','a','a','n'],
     * ['e','t','a','e'],
     * ['i','h','k','r'],
     * ['i','f','l','v']
     * ]
     * Output: ["eat","oath"]
     */
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        //todo
        return res;
    }

    public static void main(String[] args) {
        String word = "ABCCED";
        char[][] board = {{'A','B','C','E'},
                          {'S','F','C','S'},
                          {'A','D','E','E'}};
        System.out.println(exist(board, word));
    }
}
