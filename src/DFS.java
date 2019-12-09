import java.util.*;
public class DFS {

    private Stack<Square> path=new Stack<Square>();
    private ArrayList<Square> expandedPath=new ArrayList<Square>();
    public void solve(Maze maze){

        path.push(maze.getCurrentSquare());
        expandedPath.add(maze.getCurrentSquare());
        maze.getCurrentSquare().setIsVisited();

        while(maze.getCurrentSquare().getStatus().equals("E")==false){
            int currenti=maze.getCurrentSquare().getRow();
            int currentj=maze.getCurrentSquare().getColumn();

            //East
            if(currentj!=maze.getColumnLength()-1&&
                    (maze.getSquare(currenti,currentj).getIsWestWall()==false)&&
                    maze.getSquare(currenti,currentj+1).getIsVisited()==false
            ){
                maze.goEast();
                path.push(maze.getCurrentSquare());
                expandedPath.add(maze.getCurrentSquare());
                maze.getCurrentSquare().setIsVisited();
            }

            //South
            else if(currenti!=maze.getRowLength()-1&&
                    (maze.getSquare(currenti,currentj).getIsSouthWall()==false)&&
                    maze.getSquare(currenti+1,currentj).getIsVisited()==false
                    ){
                maze.goSouth();
                path.push(maze.getCurrentSquare());
                expandedPath.add(maze.getCurrentSquare());
                maze.getCurrentSquare().setIsVisited();
            }
            //West
            else if((    currentj!=0&&
                    maze.getSquare(currenti,currentj-1).getIsWestWall()==false)&&
                    maze.getSquare(currenti,currentj-1).getIsVisited()==false
            ){
                maze.goWest();
                path.push(maze.getCurrentSquare());
                expandedPath.add(maze.getCurrentSquare());
                maze.getCurrentSquare().setIsVisited();
            }

            //North
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
       //         expandedPath.add(maze.getCurrentSquare());
            }
        }
        System.out.println("");



    }
    public Stack<Square> getPath(){
        return this.path;
    }
    public ArrayList<Square> getExpandedPath(){
        return this.expandedPath;
    }



}
