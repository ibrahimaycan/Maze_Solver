import java.util.*;

public class AStar {
    private List<Square> frontier = new ArrayList<>();
    private List<Square> path = new ArrayList<>();
    private List<Square> expandedPath = new ArrayList<>();
    private  Maze maze;
    public AStar(Maze maze){
        //cloning the maze object so that it does not change the original maze and read the input file repeatedly
        this.maze = (Maze)maze.deepClone(maze);
    }

    public Maze solve(){
        while(maze.getCurrentSquare().getStatus().equals("E")==false){
            int currenti=maze.getCurrentSquare().getRow();
            int currentj=maze.getCurrentSquare().getColumn();
            //for East
            if(currentj!=maze.getColumnLength()-1&&
                    (maze.getSquare(currenti,currentj).getIsEastWall()==false)&&
                    maze.getSquare(currenti,currentj + 1).getIsVisited()==false){

                maze.getSquare(currenti, currentj + 1).addGn(1);  //add to cost 1 default
                if (maze.getSquare(currenti, currentj + 1).getStatus().equals("T"))     //add to cost 6 if the square contains trap
                    maze.getSquare(currenti, currentj + 1).addGn(6);
                maze.getSquare(currenti, currentj + 1).setHn(maze.findMinCost(maze.getSquare(currenti, currentj+1)));   //calculate h(n) function
                maze.getSquare(currenti, currentj + 1).setCost(maze.getSquare(currenti, currentj + 1).getGn()
                        + maze.getSquare(currenti , currentj + 1).getHn());  //calculate the heuristic function
                frontier.add(maze.getSquare(currenti,currentj + 1));
                maze.getSquare(currenti,currentj + 1).setParentSquare(maze.getCurrentSquare());
            }
            //for South
            if(currenti!=maze.getRowLength() - 1 &&
                    (maze.getSquare(currenti, currentj).getIsSouthWall()==false)&&
                    maze.getSquare(currenti + 1, currentj).getIsVisited()==false){
                maze.getSquare(currenti + 1, currentj ).addGn(1);
                if (maze.getSquare(currenti+ 1, currentj ).getStatus().equals("T"))
                    maze.getSquare(currenti + 1, currentj ).addGn(6);
                maze.getSquare(currenti + 1, currentj ).setHn(maze.findMinCost(maze.getSquare(currenti +1, currentj)));
                maze.getSquare(currenti + 1, currentj ).setCost(maze.getSquare(currenti + 1, currentj ).getGn() + maze.getSquare(currenti + 1 , currentj ).getHn());
                frontier.add(maze.getSquare(currenti + 1,currentj ));
                maze.getSquare(currenti + 1,currentj).setParentSquare(maze.getCurrentSquare());
            }
            //for West
            if(currentj!=0&&
                    (maze.getSquare(currenti, currentj -1).getIsEastWall()==false)&&
                    maze.getSquare(currenti,currentj-1).getIsVisited()==false){
                maze.getSquare(currenti, currentj - 1).addGn(1);
                if (maze.getSquare(currenti, currentj - 1).getStatus().equals("T"))
                    maze.getSquare(currenti, currentj - 1).addGn(6);
                maze.getSquare(currenti, currentj - 1).setHn(maze.findMinCost(maze.getSquare(currenti, currentj-1)));
                maze.getSquare(currenti, currentj - 1).setCost(maze.getSquare(currenti, currentj - 1).getGn() + maze.getSquare(currenti , currentj - 1).getHn());
                frontier.add(maze.getSquare(currenti,currentj - 1));  //key value
                maze.getSquare(currenti,currentj - 1).setParentSquare(maze.getCurrentSquare());
            }
            //for North
            if(currenti!=0&&
                    (maze.getSquare(currenti-1,currentj).getIsSouthWall()==false)&&
                    maze.getSquare(currenti-1,currentj).getIsVisited()==false){
                maze.getSquare(currenti - 1, currentj ).addGn(1);
                if (maze.getSquare(currenti- 1, currentj ).getStatus().equals("T"))
                    maze.getSquare(currenti - 1, currentj ).addGn(6);
                maze.getSquare(currenti - 1, currentj ).setHn(maze.findMinCost(maze.getSquare(currenti -1, currentj)));
                maze.getSquare(currenti - 1, currentj ).setCost(maze.getSquare(currenti - 1, currentj ).getGn() + maze.getSquare(currenti - 1 , currentj ).getHn());
                frontier.add(maze.getSquare(currenti - 1,currentj ));
                maze.getSquare(currenti - 1,currentj).setParentSquare(maze.getCurrentSquare());
            }

            maze.getCurrentSquare().setIsVisited();     //set the visited status
            addExpandedPath(expandedPath, maze.getCurrentSquare());
            //choose the best square to move
            maze.setCurrentSquare(frontier.get(getMinSquare(frontier)).getRow(), frontier.get(getMinSquare(frontier)).getColumn());
            frontier.remove(getMinSquare(frontier));
        }
        return maze;
    }

    //add the square a expanded list if does not exist
    private void addExpandedPath(List<Square> expandedPath, Square square){
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
    public List<Square> getExpandedPath() {
        return expandedPath;
    }

    //to choose square which we move
    private int getMinSquare(List<Square> frontier){
        int index = 0;
        int minCost = 1024;
        for(int i=0 ; i < frontier.size(); i++){
            if(frontier.get(i).getCost() < minCost){
                minCost = frontier.get(i).getCost();
                index = i;
            }
        }
        return index;
    }

    //create the solution path with using goal node and parent nodes
    public List<Square> getPath(Maze maze){
        while(maze.getCurrentSquare().getStatus().equals("S")==false){
            path.add(maze.getCurrentSquare());
            maze.goSquare(maze.getCurrentSquare().getParentSquare().getRow(),maze.getCurrentSquare().getParentSquare().getColumn());
        }
        //to add Starting Point
        path.add(maze.getCurrentSquare());
        Collections.reverse(path);
        return path;
    }

    public void printPath(List<Square> path){
        Iterator iter = path.iterator();
        Square square;
        while (iter.hasNext()){
            square = ((Square)iter.next());
            System.out.print("(" +square.getRow() + "," + square.getColumn() + ") ");
        }
        System.out.println();
    }

    public int calculateCost(List<Square> path){
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
}
