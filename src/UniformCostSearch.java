import java.util.*;

public class UniformCostSearch {
    private int cost;
    private List<String> path=new ArrayList<>();
    public void solve(Maze maze){
        ArrayList<Square> nextSquares=new ArrayList<>();
        maze.getCurrentSquare().setIsVisited();

        while(maze.getCurrentSquare().getStatus().equals("E")==false){
            int currenti=maze.getCurrentSquare().getRow();
            int currentj=maze.getCurrentSquare().getColumn();

            //East
            if(currentj!=maze.getColumnLength()-1&&
                    (maze.getSquare(currenti,currentj).getIsWestWall()==false)&&
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
                    maze.getSquare(currenti,currentj-1).getIsWestWall()==false)&&
                    maze.getSquare(currenti,currentj-1).getIsVisited()==false){
                maze.getSquare(currenti,currentj-1).setCost(maze.getCurrentSquare().getCost()+1);
                maze.getSquare(currenti,currentj-1).setBeforePosition(currenti+","+currentj);
                if(maze.getSquare(currenti,currentj-1).getStatus().equals("T"))
                    maze.getSquare(currenti,currentj-1).setCost(maze.getCurrentSquare().getCost()+7);
                nextSquares.add(maze.getSquare(currenti,currentj-1));
                getMinCostIndex(nextSquares);
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
            int minIndex=getMinCostIndex(nextSquares);
            maze.setCurrentSquare(  nextSquares.get(minIndex).getRow(),nextSquares.get(minIndex).getColumn());
            maze.getCurrentSquare().setIsVisited();
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
    private int getMinCostIndex(ArrayList<Square> squares){
        int minIndex;
        minIndex=0;
        int mincost=squares.get(0).getCost();
        for (int i=1;i<squares.size();i++){
            if(squares.get(i).getCost()<mincost){
                mincost=squares.get(i).getCost();
                minIndex=i;
            }

        }
        return minIndex;
    }
}
