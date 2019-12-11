import java.io.Serializable;

public class Square implements Serializable {
    private int row;//Row of square
    private int column;//Column of square
    private String status;  //S,E,T,Null
    private boolean isEastWall; //Checks if there is a wall on west of the square
    private boolean isSouthWall;//Checks if there is a wall on south of the square
    private boolean isVisited;//Checks if visited
    private String beforePosition;//Before position of the square
    private Square parentSquare;//Parent of the square
    private int cost;   // Cost of square
    private int gn = 0;  //current cost
    private int hn;  //heuristic cost

    public int getGn(){
        return gn;
    }

    public int getHn(){
        return hn;
    }
    public void addGn(int cost){
        this.gn += cost;
    }

    public void setHn(int hn){
        this.hn = hn;
    }



    public Square getParentSquare(){
        return this.parentSquare;
    }

    public void setParentSquare(Square parentSquare){
        this.parentSquare = parentSquare;
    }

    public String getBeforePosition() {
        return this.beforePosition;
    }

    public void setBeforePosition(String beforePosition) {
        this.beforePosition = beforePosition;
    }

    public Square(int row, int column, String status){
        this.row=row;
        this.column=column;
        this.status=status;
        this.isEastWall=false;
        this.isSouthWall=false;
        this.isVisited=false;
    }
    public void setStatus(String status){this.status=status;}
    public String  getStatus(){
        return this.status;
    }
    public void setEast(){
        this.isEastWall=true;
    }
    public void setSouth(){
        this.isSouthWall=true;
    }
    public boolean getIsEastWall(){
        return this.isEastWall;
    }
    public boolean getIsSouthWall(){
        return this.isSouthWall;
    }
    public boolean getIsVisited(){
        return this.isVisited;
    }
    public void setIsVisited(){
        this.isVisited=true;
    }
    public void resetIsVisited(){
        this.isVisited=false;
    }
    public int getRow(){ return this.row;}
    public int getColumn(){return this.column;}
    public int getCost() {
        return this.cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
}
