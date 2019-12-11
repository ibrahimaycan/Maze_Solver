import java.io.Serializable;

public class Square implements Serializable {
    private int row;
    private int column;
    private String status;  //S,E,T,N
    private boolean isEastWall;
    private boolean isSouthWall;
    private boolean isVisited;
    private String beforePosition;
    private Square parentSquare;
    private int cost;

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
