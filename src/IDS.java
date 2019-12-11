import java.util.*;

public class IDS {

    private final int limit = 1024;
    private static boolean solnFind = false;
    private Queue<Square> frontier = new LinkedList<>();
    private LinkedList<Square> path = new LinkedList<>();
    private LinkedList<Square> expandedPath = new LinkedList<>();
    private Maze maze;
    private Maze tempMaze;
    public IDS(Maze maze){
        this.maze = (Maze)maze.deepClone(maze);
    }

    public Maze solve() {

        expandedPath.add(maze.getCurrentSquare());
        for(int i = 0; i < limit; i++){
            tempMaze = (Maze)maze.deepClone(maze);
            //tempMaze = maze;
            frontier.add(tempMaze.getCurrentSquare());
            tempMaze.getCurrentSquare().setIsVisited();

            DLS(tempMaze, frontier, i);
            if(solnFind)
                break;
            frontier.clear();
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
            addExpandedPath(expandedPath,maze.getCurrentSquare());
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
        Iterator iter = path.iterator();
        Square square;
        while (iter.hasNext()){
            square = ((Square)iter.next());
            System.out.print("(" +square.getRow() + "," + square.getColumn() + ") ");
        }
        System.out.println();
    }

    public int calculateCost(Queue<Square> path){
        Iterator iter = path.iterator();
        Square square;
        int cost = 0;
        iter.next();
        while(iter.hasNext()){
            square = ((Square)iter.next());
            cost++;
            if(square.getStatus().equals("T"))
                cost += 6;
        }
        return cost;
    }

    public LinkedList<Square> getExpandedPath() {
        return expandedPath;
    }

    private void addExpandedPath(LinkedList<Square> expandedPath, Square square){
        Iterator iter = expandedPath.iterator();
        Square tempSquare;
        boolean exist=false;
        while (iter.hasNext()){
            tempSquare = ((Square)iter.next());
            if(square.getRow()==tempSquare.getRow() && square.getColumn()==tempSquare.getColumn()){
                exist = true;
                break;
            }
        }
        if(exist){
            return;
        }
        else if(!exist){
            expandedPath.add(square);
        }
    }
}


