import java.util.*;


public class BFS {
    public static void solve(Maze maze, List<String>path,int cost){
        Queue<Square> tempQueue=new LinkedList<>();
        tempQueue.add(maze.getCurrentSquare());
        maze.getCurrentSquare().setIsVisited();
        tempQueue.poll();

        while (maze.getCurrentSquare().getStatus().equals("E")==false){
            int currenti=maze.getCurrentSquare().getRow();
            int currentj=maze.getCurrentSquare().getColumn();
            //East
            if((    currentj!=0&&
                    maze.getSquare(currenti,currentj-1).getIsWestWall()==false)&&
                    maze.getSquare(currenti,currentj-1).getIsVisited()==false)
            {
                ((LinkedList<Square>) tempQueue).add(maze.getSquare(currenti,currentj-1));
                maze.getSquare(currenti,currentj-1).setBeforePosition(currenti+","+currentj);
            }
            //South
            if(currenti!=maze.getRowLength()-1&&
                    maze.getSquare(currenti,currentj).getIsSouthWall()==false&&
                    maze.getSquare(currenti+1,currentj).getIsVisited()==false)
            {
                ((LinkedList<Square>) tempQueue).add(maze.getSquare(currenti+1,currentj));
                maze.getSquare(currenti+1,currentj).setBeforePosition(currenti+","+currentj);

            }
            //West
            if(currentj!=maze.getColumnLength()-1&&
                    (maze.getSquare(currenti,currentj).getIsWestWall()==false)&&
                    maze.getSquare(currenti,currentj+1).getIsVisited()==false)
            {
                ((LinkedList<Square>) tempQueue).add(maze.getSquare(currenti,currentj+1));
                maze.getSquare(currenti,currentj+1).setBeforePosition(currenti+","+currentj);
            }
            if(currenti!=0&&
                    (maze.getSquare(currenti-1,currentj).getIsSouthWall()==false)&&
                    maze.getSquare(currenti-1,currentj).getIsVisited()==false)
            {
                ((LinkedList<Square>) tempQueue).add(maze.getSquare(currenti-1,currentj));
                maze.getSquare(currenti-1,currentj).setBeforePosition(currenti+","+currentj);
            }
            maze.setCurrentSquare(tempQueue.peek().getRow(),tempQueue.peek().getColumn());
            maze.getCurrentSquare().setIsVisited();
            tempQueue.poll();


        }
        path.add(maze.getCurrentSquare().getRow()+","+maze.getCurrentSquare().getColumn());
        String[] position=maze.getCurrentSquare().getBeforePosition().split(",");
        maze.goSquare(Integer.parseInt(position[0]),Integer.parseInt(position[1]));
        cost++;
        if(maze.getCurrentSquare().getStatus().equals("T"))
            cost+=6;
        path.add(maze.getCurrentSquare().getRow()+","+maze.getCurrentSquare().getColumn());
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
}


