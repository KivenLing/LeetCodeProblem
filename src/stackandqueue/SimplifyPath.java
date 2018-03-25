package stackandqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
ID:71
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes
'/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class SimplifyPath {
    public static String simplifyPath(String path){
        Stack<String> pathStack = new Stack<>();
        StringBuffer p = new StringBuffer();
        String[] paths = path.split("/");
        for (String s : paths) {
            if (s.equals("/") || s.equals("."))
                continue;
            if (s.equals("..")){
                if(!pathStack.isEmpty())
                    pathStack.pop();
                continue;
            }
            if (s.equals(""))
                continue;
            pathStack.push(s);
        }
        StringBuffer ans = new StringBuffer();
        List<String> folder = new ArrayList<>();
        while (!pathStack.isEmpty()){
            folder.add(pathStack.pop());
        }
        if (folder.size() == 0)
            return "/";
        for (int i = folder.size() - 1; i >= 0 ; i--) {
            ans.append("/");
            ans.append(folder.get(i));
        }
        return ans.toString();
    }

    public static void main(String[] args){
        String path = "/...";
        String ans = simplifyPath(path);
        System.out.println(ans);
    }
}
