import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
public class StartGame {
    public static void main(String []args) throws IOException{
        Maze maze = new Maze("input.txt");
        Stack<Square> path=new Stack<Square>();
        ArrayList<Square> expandedPath=new ArrayList<Square>();
        int cost=0;
        DFS.solve(maze,path,expandedPath,cost);
        for(int i=0;i<expandedPath.size();i++){
            System.out.println(expandedPath.get(i).getRow()+","+expandedPath.get(i).getColumn());
        }

    }
}
