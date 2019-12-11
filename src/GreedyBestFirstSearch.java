import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreedyBestFirstSearch {
    private int cost=0;
    private List<String> path=new ArrayList<>();//Holds path
    private List<String> expandedPath=new ArrayList<>();//Holds expanded path
    public void solve(Maze maze){
        ArrayList<Square> nextSquares=new ArrayList<>();
        maze.getCurrentSquare().setIsVisited();
        expandedPath.add(maze.getCurrentSquare().getRow()+","+maze.getCurrentSquare().getColumn());
        //Loops until finding Goal state
        while(maze.getCurrentSquare().getStatus().equals("E")==false){
            int currenti=maze.getCurrentSquare().getRow();
            int currentj=maze.getCurrentSquare().getColumn();
            Square square;
            //East
            if(currentj!=maze.getColumnLength()-1&&
                    (maze.getSquare(currenti,currentj).getIsEastWall()==false)&&
                    maze.getSquare(currenti,currentj+1).getIsVisited()==false){
                maze.getSquare(currenti,currentj+1).setBeforePosition(currenti+","+currentj);
                maze.getSquare(currenti,currentj+1).setCost(maze.getCurrentSquare().getCost()+1);
                if(maze.getSquare(currenti,currentj+1).getStatus().equals("T"))
                    maze.getSquare(currenti,currentj+1).setCost(maze.getCurrentSquare().getCost()+7);
                nextSquares.add(maze.getSquare(currenti,currentj+1));
            }
            //South
            if(currenti!=maze.getRowLength()-1&&
                    maze.getSquare(currenti,currentj).getIsSouthWall()==false&&
                    maze.getSquare(currenti+1,currentj).getIsVisited()==false){

                maze.getSquare(currenti+1,currentj).setBeforePosition(currenti+","+currentj);
                maze.getSquare(currenti+1,currentj).setCost(maze.getCurrentSquare().getCost()+1);
                if(maze.getSquare(currenti+1,currentj).getStatus().equals("T"))
                    maze.getSquare(currenti+1,currentj).setCost(maze.getCurrentSquare().getCost()+7);
                nextSquares.add(maze.getSquare(currenti+1,currentj));
            }
            //West
            if((    currentj!=0&&
                    maze.getSquare(currenti,currentj-1).getIsEastWall()==false)&&
                    maze.getSquare(currenti,currentj-1).getIsVisited()==false){
                maze.getSquare(currenti,currentj-1).setCost(maze.getCurrentSquare().getCost()+1);
                maze.getSquare(currenti,currentj-1).setBeforePosition(currenti+","+currentj);
                if(maze.getSquare(currenti,currentj-1).getStatus().equals("T"))
                    maze.getSquare(currenti,currentj-1).setCost(maze.getCurrentSquare().getCost()+7);
                nextSquares.add(maze.getSquare(currenti,currentj-1));
            }
            //North
            if(currenti!=0&&
                    (maze.getSquare(currenti-1,currentj).getIsSouthWall()==false)&&
                    maze.getSquare(currenti-1,currentj).getIsVisited()==false){
                maze.getSquare(currenti-1,currentj).setBeforePosition(currenti+","+currentj);
                maze.getSquare(currenti-1,currentj).setCost(maze.getCurrentSquare().getCost()+1);
                if(maze.getSquare(currenti-1,currentj).getStatus().equals("T"))
                    maze.getSquare(currenti-1,currentj).setCost(maze.getCurrentSquare().getCost()+7);
                nextSquares.add(maze.getSquare(currenti-1,currentj));
            }
            int minIndex=getMinCostIndex(nextSquares,maze);
            maze.setCurrentSquare(  nextSquares.get(minIndex).getRow(),nextSquares.get(minIndex).getColumn());
            maze.getCurrentSquare().setIsVisited();
            expandedPath.add(maze.getCurrentSquare().getRow()+","+maze.getCurrentSquare().getColumn());
            nextSquares.remove(minIndex);
        }
        cost=0;
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
            if(maze.getCurrentSquare().getStatus().equals("T"))
                cost+=6;
            cost++;
            path.add(maze.getCurrentSquare().getRow()+","+maze.getCurrentSquare().getColumn());
        }
        Collections.reverse(path);
    }
    public int getMinCostIndex(ArrayList<Square>squares,Maze maze){
        int minIndex;
        minIndex=0;
        int mincost=100;
        for (int i=0;i<squares.size();i++){
            if(maze.findMinCost(squares.get(i))<mincost&&(maze.getSquare(squares.get(i).getRow(),squares.get(i).getColumn()).getIsVisited()==false)){
                mincost=maze.findMinCost(squares.get(i));
                minIndex=i;
            }
        }
        return  minIndex;

    }
    public int getCost(){
        return this.cost;
    }
    public List<String> getPath(){
        return this.path;
    }

    public List<String> getExpandedPath(){
        return this.expandedPath;
    }

}
