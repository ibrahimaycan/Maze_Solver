import java.util.*;
public class DFS {
    public static void solve(Maze maze,Stack<Square> path,ArrayList<Square>expandedPath,int cost){

        path.push(maze.getCurrentSquare());
        expandedPath.add(maze.getCurrentSquare());
        maze.getCurrentSquare().setIsVisited();

        while(maze.getCurrentSquare().getStatus().equals("E")==false){
            int currenti=maze.getCurrentSquare().getRow();
            int currentj=maze.getCurrentSquare().getColumn();
            if((    currentj!=0&&
                    maze.getSquare(currenti,currentj-1).getIsWestWall()==false)&&
                    maze.getSquare(currenti,currentj-1).getIsVisited()==false
                    ){
                maze.goEast();
                path.push(maze.getCurrentSquare());
                expandedPath.add(maze.getCurrentSquare());
                maze.getCurrentSquare().setIsVisited();
            }
            else if(currenti!=maze.getRowLength()-1&&
                    (maze.getSquare(currenti,currentj).getIsSouthWall()==false)&&
                    maze.getSquare(currenti+1,currentj).getIsVisited()==false
                    ){
                maze.goSouth();
                path.push(maze.getCurrentSquare());
                expandedPath.add(maze.getCurrentSquare());
                maze.getCurrentSquare().setIsVisited();
            }

            else if(currentj!=maze.getColumnLength()-1&&
                    (maze.getSquare(currenti,currentj).getIsWestWall()==false)&&
                    maze.getSquare(currenti,currentj+1).getIsVisited()==false
                    ){
                maze.goWest();
                path.push(maze.getCurrentSquare());
                expandedPath.add(maze.getCurrentSquare());
                maze.getCurrentSquare().setIsVisited();
            }
            else if(currenti!=0&&
                    (maze.getSquare(currenti-1,currentj).getIsSouthWall()==false)&&
                    maze.getSquare(currenti-1,currentj).getIsVisited()==false
                    ){
                maze.goNorth();
                path.push(maze.getCurrentSquare());
                expandedPath.add(maze.getCurrentSquare());
                maze.getCurrentSquare().setIsVisited();
            }
            else{
                path.pop();
                Square s=path.peek();
                maze.setCurrentSquare(s.getRow(),s.getColumn());
                expandedPath.add(maze.getCurrentSquare());
            }


        }
        System.out.println("");



    }


}
