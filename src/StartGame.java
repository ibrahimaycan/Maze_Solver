import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class StartGame {
    public static void main(String []args) throws IOException{
        Maze maze = new Maze("input.txt");
        Stack<Square> path=new Stack<Square>();
        ArrayList<Square> expandedPath=new ArrayList<Square>();
        int cost=0;
        DFS.solve(maze,path,expandedPath,cost);
        System.out.println("Cost="+maze.calculateCost(path));
        System.out.println("Expanded Path is:");
        for(int i=0;i<expandedPath.size();i++){
            System.out.println(expandedPath.get(i).getRow()+","+expandedPath.get(i).getColumn());
        }
        //maze.resetMaze();

        //BFS
        maze=new Maze("input.txt");
        List<String> pathBFS=new ArrayList<>();
        int costBFS=0;
        BFS.solve(maze,pathBFS,costBFS);
        for (int i=0;i<pathBFS.size();i++){
            System.out.println(pathBFS.get(i));
        }
        System.out.println("Cost="+cost);

    }


}
