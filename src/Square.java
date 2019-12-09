public class Square {
    private int row;
    private int column;
    private String status;  //S,E,T,N
    private boolean isWestWall;
    private boolean isSouthWall;
    private boolean isVisited;
    private String beforePosition;
    private int cost;
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
        this.isWestWall=false;
        this.isSouthWall=false;
        this.isVisited=false;
    }
    public void setStatus(String status){this.status=status;}
    public String  getStatus(){
        return this.status;
    }
    public void setWest(){
        this.isWestWall=true;
    }
    public void setSouth(){
        this.isSouthWall=true;
    }
    public boolean getIsWestWall(){
        return this.isWestWall;
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
