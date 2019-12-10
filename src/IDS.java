import java.util.*;

public class IDS {

    private final int limit = 1024;
    private static boolean solnFind = false;
    private Queue<Square> frontier = new LinkedList<>();
    private Queue<Square> path = new LinkedList<>();
    private ArrayList<Square> expandedPath = new ArrayList<>();
    private Maze maze;
    private Maze tempMaze;
    public IDS(Maze maze){
        try
        {
            this.maze =  (Maze)maze.clone();
        }
        catch(Exception e)
        {
            System.out.println("CLONING ERROR");
        }
    }

    public Maze solve() {
        expandedPath.add(maze.getCurrentSquare());
        for(int i = 0; i < limit; i++){
            try
            {
                tempMaze =  (Maze)maze.clone();
            }
            catch(Exception e)
            {
                System.out.println("CLONING ERROR");
            }

            frontier.add(maze.getCurrentSquare());
            maze.getCurrentSquare().setIsVisited();

            DLS(tempMaze, frontier, i);
            if(solnFind)
                break;
        }
        return tempMaze;
    }

    private void DLS(Maze maze, Queue<Square> frontier,  int tempLimit){
        recursiveDLS(maze, frontier, tempLimit);
    }

    private String recursiveDLS(Maze maze, Queue<Square> frontier ,int limit){
        if(maze.getCurrentSquare().getStatus().equals("E") == true){
            solnFind = true;
            return "soln";
        }
        else if(limit == 0){
            return "cutoff";
        }
        else{
            int currenti=maze.getCurrentSquare().getRow();
            int currentj=maze.getCurrentSquare().getColumn();
            //for East
            if(currentj!=maze.getColumnLength()-1&&
                    (maze.getSquare(currenti,currentj).getIsEastWall()==false)&&
                    maze.getSquare(currenti,currentj + 1).getIsVisited()==false){
                frontier.add(maze.getSquare(currenti,currentj + 1));
                //maze.getSquare(currenti,currentj + 1).setBeforePosition(currenti+","+currentj);
                maze.getSquare(currenti,currentj + 1).setParentSquare(maze.getCurrentSquare());
            }
            //for South
            if(currenti!=maze.getRowLength() - 1 &&
                    (maze.getSquare(currenti, currentj).getIsSouthWall()==false)&&
                    maze.getSquare(currenti + 1, currentj).getIsVisited()==false){
                frontier.add(maze.getSquare(currenti + 1, currentj));
                //maze.getSquare(currenti + 1,currentj ).setBeforePosition(currenti+","+currentj);
                maze.getSquare(currenti + 1,currentj).setParentSquare(maze.getCurrentSquare());
            }
            //for West
            if(currentj!=0&&
                    (maze.getSquare(currenti, currentj -1).getIsEastWall()==false)&&
                    maze.getSquare(currenti,currentj-1).getIsVisited()==false){
                frontier.add(maze.getSquare(currenti,currentj-1));
                //maze.getSquare(currenti,currentj -1).setBeforePosition(currenti+","+currentj);
                maze.getSquare(currenti,currentj - 1).setParentSquare(maze.getCurrentSquare());
            }
            //for North
            if(currenti!=0&&
                    (maze.getSquare(currenti-1,currentj).getIsSouthWall()==false)&&
                    maze.getSquare(currenti-1,currentj).getIsVisited()==false){
                frontier.add(maze.getSquare(currenti-1,currentj));
                //maze.getSquare(currenti - 1,currentj).setBeforePosition(currenti+","+currentj);
                maze.getSquare(currenti - 1,currentj).setParentSquare(maze.getCurrentSquare());
            }
            maze.setCurrentSquare(frontier.peek().getRow(),frontier.peek().getColumn());
            maze.getCurrentSquare().setIsVisited();
            frontier.poll();
            recursiveDLS(maze, frontier,limit - 1);

        }

        return "fail";
    }

    public Queue<Square> getPath(Maze maze){
        while(maze.getCurrentSquare().getStatus().equals("S")==false){
            path.add(maze.getCurrentSquare());
            maze.goSquare(maze.getCurrentSquare().getParentSquare().getRow(),maze.getCurrentSquare().getParentSquare().getColumn());
        }
        //to add Starting Point
        path.add(maze.getCurrentSquare());
        Collections.reverse((LinkedList)path);
        return path;
    }

    public void printPath(Queue<Square> path){
        while (path.size()>0){
            System.out.println(path.peek().getRow() + "," + path.peek().getColumn());
            path.poll();
        }
    }
}


