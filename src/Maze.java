import java.io.*;
import java.util.List;
import java.util.Stack;


public class Maze implements Serializable{

    private Square[][] squares;

    public Square[][] getSquares() {
        return squares;
    }

    public int getCurrenti() {
        return currenti;
    }

    public int getCurrentj() {
        return currentj;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    private int rowLength;
    private int columnLength;
    private Square currentSquare;
    private int currenti,currentj;
    private int startRow,startColumn;

    public Maze(){

    }

    public Maze(Square[][] squares, int rowLength, int columnLength, Square currentSquare, int currenti, int currentj, int startRow, int startColumn) {
        this.squares = squares;
        this.rowLength = rowLength;
        this.columnLength = columnLength;
        this.currentSquare = currentSquare;
        this.currenti = currenti;
        this.currentj = currentj;
        this.startRow = startRow;
        this.startColumn = startColumn;
    }

    public Maze(String path)throws IOException{
        try {
            String[] position;
            BufferedReader file = new BufferedReader(new FileReader(path));
            String line = file.readLine();//Rowlength
            line=file.readLine();
            this.rowLength=Integer.parseInt(line);
            line=file.readLine();//ColumnLength
            line=file.readLine();
            this.columnLength=Integer.parseInt(line);
            this.squares=new Square[this.rowLength][this.columnLength];
            for(int i=0;i<rowLength;i++){
                for (int j=0;j<columnLength;j++){
                    this.squares[i][j]=new Square(i,j," ");
                }
            }
            line=file.readLine();//Start
            line=file.readLine();
            position=line.split(",");

            startRow=Integer.parseInt(position[0]);
            startColumn=Integer.parseInt(position[1]);
            this.squares[startRow][startColumn].setStatus("S");
            this.squares[startRow][startColumn].setCost(0);
            currentSquare=this.squares[startRow][startColumn];
            currenti=startRow;
            currentj=startColumn;
            line=file.readLine();//Ends

            while ((line=file.readLine()).equals("Traps")==false){
                position=line.split(",");
                this.squares[Integer.parseInt(position[0])][Integer.parseInt(position[1])].setStatus("E");
            }

            while ((line=file.readLine()).equals("Walls")==false){
                position=line.split(",");
                this.squares[Integer.parseInt(position[0])][Integer.parseInt(position[1])].setStatus("T");
            }

            while ((line=file.readLine())!=null) {
                position = line.split(",");
                if (position[2].equals("East")) {
                    this.squares[Integer.parseInt(position[0])][Integer.parseInt(position[1])].setEast();
                } else if (position[2].equals("South")) {
                    this.squares[Integer.parseInt(position[0])][Integer.parseInt(position[1])].setSouth();
                }

            }

        }
        catch (Exception ex){
            System.out.println("error");

        }


    }
    public Square getSquare(int i,int j){
        return this.squares[i][j];
    }

    public Square getCurrentSquare(){
        return this.currentSquare;
    }
    public void setCurrentSquare(int i, int j){
        currentSquare=squares[i][j];
        currenti=i;
        currentj=j;
    }


    public void goSquare(int i,int j){
        this.currentSquare=this.squares[i][j];
    }
    public void goWest(){
        this.currentSquare=this.squares[this.currenti][this.currentj-1];
        this.currentj--;
    }
    public void goSouth(){
        this.currentSquare=this.squares[this.currenti+1][this.currentj];
        this.currenti++;
    }
    public void goEast(){
        this.currentSquare=this.squares[this.currenti][this.currentj+1];
        this.currentj++;
    }
    public void goNorth(){
        this.currentSquare=this.squares[this.currenti-1][this.currentj];
        this.currenti--;
    }
    public int getRowLength(){
        return this.rowLength;
    }
    public int getColumnLength(){
        return this.columnLength;
    }
    public void resetMaze(){
        for(int i=0;i<this.rowLength;i++){
            for (int j=0;j<this.columnLength;j++){
                squares[i][j].resetIsVisited();
                squares[i][j].setBeforePosition(null);
            }
        }
        this.currenti=startRow;
        this.currentj=startColumn;

    }
    public int calculateCost(Stack<Square> path){
        Stack<Square>temp=path;
        int cost=0;
        while (true){
            temp.pop();
            if(temp.isEmpty())
                break;
            cost++;
            if(squares[temp.peek().getRow()][temp.peek().getColumn()].getStatus().equals("T"))
                cost=cost+6;
        }
        return cost;
    }

    public Object deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
/*    public int calculateCost(List<String> path){
        int cost=0;
        String[]position;
        for (int i=0;i<path.size();i++){
            position=path.get(i).split();
            cost++;


        }
    }*/

}
