public class Square {
    private int row;
    private int column;
    private String status;  //S,E,T,N
    private boolean isWestWall;
    private boolean isSouthWall;


    public Square(int row, int column, String status){
        this.row=row;
        this.column=column;
        this.status=status;
        this.isWestWall=false;
        this.isSouthWall=false;
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
}
