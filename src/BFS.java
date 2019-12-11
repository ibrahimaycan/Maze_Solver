import java.util.*;


public class BFS {
    private int cost=0;
    private List<String> path=new ArrayList<>();
    private List<String> expandedPath=new ArrayList<>();
    public void solve(Maze maze){
        Queue<Square> tempQueue=new LinkedList<>();//Holds the frontiers
        tempQueue.add(maze.getCurrentSquare());//Adding first square on frontier
        maze.getCurrentSquare().setIsVisited();//Making current square visited
        expandedPath.add(maze.getCurrentSquare().getRow()+","+maze.getCurrentSquare().getColumn());
        tempQueue.poll();

        //While loop until finding Goal State
        while (maze.getCurrentSquare().getStatus().equals("E")==false){
            int currenti=maze.getCurrentSquare().getRow();
            int currentj=maze.getCurrentSquare().getColumn();

            //Going East
            if(currentj!=maze.getColumnLength()-1&&
                    (maze.getSquare(currenti,currentj).getIsEastWall()==false)&&
                    maze.getSquare(currenti,currentj+1).getIsVisited()==false)
            {
                ((LinkedList<Square>) tempQueue).add(maze.getSquare(currenti,currentj+1));
                maze.getSquare(currenti,currentj+1).setBeforePosition(currenti+","+currentj);
            }
            //Going South
            if(currenti!=maze.getRowLength()-1&&
                    maze.getSquare(currenti,currentj).getIsSouthWall()==false&&
                    maze.getSquare(currenti+1,currentj).getIsVisited()==false)
            {
                ((LinkedList<Square>) tempQueue).add(maze.getSquare(currenti+1,currentj));
                maze.getSquare(currenti+1,currentj).setBeforePosition(currenti+","+currentj);

            }
            //Going West
            if((    currentj!=0&&
                    maze.getSquare(currenti,currentj-1).getIsEastWall()==false)&&
                    maze.getSquare(currenti,currentj-1).getIsVisited()==false)
            {
                ((LinkedList<Square>) tempQueue).add(maze.getSquare(currenti,currentj-1));
                maze.getSquare(currenti,currentj-1).setBeforePosition(currenti+","+currentj);
            }
            //Going North
            if(currenti!=0&&
                    (maze.getSquare(currenti-1,currentj).getIsSouthWall()==false)&&
                    maze.getSquare(currenti-1,currentj).getIsVisited()==false)
            {
                ((LinkedList<Square>) tempQueue).add(maze.getSquare(currenti-1,currentj));
                maze.getSquare(currenti-1,currentj).setBeforePosition(currenti+","+currentj);
            }
            //Setting next square
            maze.setCurrentSquare(tempQueue.peek().getRow(),tempQueue.peek().getColumn());
            if(maze.getCurrentSquare().getIsVisited()==false)
                expandedPath.add(maze.getCurrentSquare().getRow()+","+maze.getCurrentSquare().getColumn());
            maze.getCurrentSquare().setIsVisited();
            tempQueue.poll();


        }
        //Adding to path
        path.add(maze.getCurrentSquare().getRow()+","+maze.getCurrentSquare().getColumn());
        String[] position=maze.getCurrentSquare().getBeforePosition().split(",");
        maze.goSquare(Integer.parseInt(position[0]),Integer.parseInt(position[1]));
        cost++;
        if(maze.getCurrentSquare().getStatus().equals("T"))
            cost+=6;
        path.add(maze.getCurrentSquare().getRow()+","+maze.getCurrentSquare().getColumn());

        //Going from end to Start point to get path
        while(maze.getCurrentSquare().getStatus().equals("S")==false){
            position=maze.getCurrentSquare().getBeforePosition().split(",");
            maze.goSquare(Integer.parseInt(position[0]),Integer.parseInt(position[1]));
            cost++;
            if(maze.getCurrentSquare().getStatus().equals("T"))
                cost+=6;
            path.add(maze.getCurrentSquare().getRow()+","+maze.getCurrentSquare().getColumn());
        }
        Collections.reverse(path);
    }

    public int getCost() {
        return cost;
    }

    public List<String> getPath(){
        return this.path;
    }
    public List<String> getExpandedPath(){
        return this.expandedPath;
    }
}

