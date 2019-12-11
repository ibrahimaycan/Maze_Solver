import java.util.*;

public class UniformCostSearch {
    private int cost;
    private List<String> path=new ArrayList<>();
    private List<String> expandedPath=new ArrayList<>();
    public void solve(Maze maze){
        ArrayList<Square> nextSquares=new ArrayList<>();//Holds the frontiers
        maze.getCurrentSquare().setIsVisited();//Set the current state visited
        expandedPath.add(maze.getCurrentSquare().getRow()+","+maze.getCurrentSquare().getColumn());////adding first square to expanded path

        //Loops until finds Goal State
        while(maze.getCurrentSquare().getStatus().equals("E")==false){
            int currenti=maze.getCurrentSquare().getRow();
            int currentj=maze.getCurrentSquare().getColumn();

            //East
            if(currentj!=maze.getColumnLength()-1&&
                    (maze.getSquare(currenti,currentj).getIsEastWall()==false)&&
                    maze.getSquare(currenti,currentj+1).getIsVisited()==false){

                maze.getSquare(currenti,currentj+1).setCost(maze.getCurrentSquare().getCost()+1);
                maze.getSquare(currenti,currentj+1).setBeforePosition(currenti+","+currentj);
                if(maze.getSquare(currenti,currentj+1).getStatus().equals("T"))
                    maze.getSquare(currenti,currentj+1).setCost(maze.getCurrentSquare().getCost()+7);
                nextSquares.add(maze.getSquare(currenti,currentj+1));

            }

            //South
            if(currenti!=maze.getRowLength()-1&&
                    maze.getSquare(currenti,currentj).getIsSouthWall()==false&&
                    maze.getSquare(currenti+1,currentj).getIsVisited()==false){

                maze.getSquare(currenti+1,currentj).setCost(maze.getCurrentSquare().getCost()+1);
                maze.getSquare(currenti+1,currentj).setBeforePosition(currenti+","+currentj);
                if(maze.getSquare(currenti+1,currentj).getStatus().equals("T"))
                    maze.getSquare(currenti+1,currentj).setCost(maze.getCurrentSquare().getCost()+7);
                nextSquares.add(maze.getSquare(currenti+1,currentj));

            }

            //West
            if((    currentj!=0&&
                    maze.getSquare(currenti,currentj-1).getIsEastWall()==false)&&
                    maze.getSquare(currenti,currentj-1).getIsVisited()==false){
                maze.getSquare(currenti,currentj-1).setBeforePosition(currenti+","+currentj);
                maze.getSquare(currenti,currentj-1).setCost(maze.getCurrentSquare().getCost()+1);
                if(maze.getSquare(currenti,currentj-1).getStatus().equals("T"))
                    maze.getSquare(currenti,currentj-1).setCost(maze.getCurrentSquare().getCost()+7);
                nextSquares.add(maze.getSquare(currenti,currentj-1));

            }
            //North
            if(currenti!=0&&
                    (maze.getSquare(currenti-1,currentj).getIsSouthWall()==false)&&
                    maze.getSquare(currenti-1,currentj).getIsVisited()==false){
                maze.getSquare(currenti-1,currentj).setCost(maze.getCurrentSquare().getCost()+1);
                maze.getSquare(currenti-1,currentj).setBeforePosition(currenti+","+currentj);
                if(maze.getSquare(currenti-1,currentj).getStatus().equals("T"))
                    maze.getSquare(currenti-1,currentj).setCost(maze.getCurrentSquare().getCost()+7);
                nextSquares.add(maze.getSquare(currenti-1,currentj));
            }

            int minIndex=getMinCostIndex(nextSquares,maze);//Get minimum cost of frontiers
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
        //Finds path
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

    public int getCost(){
        return this.cost;
    }
    public List<String> getPath(){
        return this.path;
    }

    public List<String> getExpandedPath(){
        return this.expandedPath;
    }
    private int getMinCostIndex(ArrayList<Square> squares,Maze maze){
        int minIndex;
        minIndex=0;
        int mincost=100;
        for (int i=0;i<squares.size();i++){
            if((squares.get(i).getCost()<mincost)&&(maze.getSquare(squares.get(i).getRow(),squares.get(i).getColumn()).getIsVisited()==false)){
                mincost=squares.get(i).getCost();
                minIndex=i;
            }

        }
        return minIndex;
    }
}
