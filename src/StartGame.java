import java.io.IOException;
import java.security.CodeSigner;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
public class StartGame {
    public static void main(String []args) throws IOException{
        /*
        Stack<Square> path=new Stack<Square>();
        ArrayList<Square> expandedPath=new ArrayList<Square>();
        int cost=0;

        IDS.solve(maze, path, expandedPath);
        System.out.println("Cost="+maze.calculateCost(path));
        /* ibo

        DFS.solve(maze,path,expandedPath,cost);
        System.out.println("Cost="+maze.calculateCost(path));
        System.out.println("Expanded Path is:");
        for(int i=0;i<expandedPath.size();i++){
            System.out.println(expandedPath.get(i).getRow()+","+expandedPath.get(i).getColumn());
        }
        //maze.resetMaze();


*/
        Maze maze=new Maze("input.txt");

        //IDS
        Maze m = (Maze)maze.deepClone(maze);
        IDS ids = new IDS(m);
        Maze idsMaze = ids.solve();
        Queue<Square> idsPath = ids.getPath(idsMaze);
        Queue<Square> idsExpandedPath = ids.getExpandedPath();
        System.out.println("IDS path:");
        ids.printPath(idsPath);
        System.out.println("IDS extended path:");
        ids.printPath(idsExpandedPath);
        System.out.println("IDS cost : " + ids.calculateCost(idsPath));;

        //DFS
        m = (Maze)maze.deepClone(maze);
        DFS dfs =new DFS();
        dfs.solve(m);
        Stack<Square> path=dfs.getPath();
        ArrayList<Square> expandedPath=dfs.getExpandedPath();
        int DFSCost=maze.calculateCost(path);
        System.out.println("DFS path="+path);
        System.out.println("DFS Expanded Path=");
        for(int i=0;i<expandedPath.size();i++){
            System.out.println(expandedPath.get(i).getRow()+","+expandedPath.get(i).getColumn());
        }
        System.out.println("DFS Cost="+DFSCost);


        //BFS
        m = (Maze)maze.deepClone(maze);
        BFS bfs=new BFS();
        bfs.solve(m);
        List<String>BFSPath=bfs.getPath();
        List<String>BFSExpandedPath=bfs.getExpandedPath();
        int BFSCost=bfs.getCost();
        System.out.println("BFS PATH=");
        System.out.println(BFSPath);
        System.out.println("BFS Expanded PATH=");
        System.out.println(BFSExpandedPath);
        System.out.println("BFS Cost="+BFSCost);


        //UNIFORM COST SEARCH
        m = (Maze)maze.deepClone(maze);
        UniformCostSearch uniformCostSearch=new UniformCostSearch();
        uniformCostSearch.solve(maze);
        List<String> UniformCostSearchPath=uniformCostSearch.getPath();
        List<String> UniformCostSearchExpandedPath=uniformCostSearch.getExpandedPath();
        int UniformCostSearchCost=uniformCostSearch.getCost();
        System.out.println("Uniformed Cost Search PATH=");
        System.out.println(UniformCostSearchPath);
        System.out.println("Uniformed Cost Search Expanded PATH=");
        System.out.println(UniformCostSearchExpandedPath);
        System.out.println("Uniformed Cost Search Cost="+UniformCostSearchCost);

    }


    public static void toString(Stack<Square> path){
        for(int i = 0; i <path.size(); i++){
            System.out.print(path.get(i).getRow() + "," + path.get(i).getColumn()+ " ");
        }
        System.out.println();
    }


}
