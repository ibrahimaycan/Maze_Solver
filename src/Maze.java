import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Maze {

    private Square[][] squares;
    private int rowLength;
    private int columnLength;
    private Square currentSquare;
    private int currenti,currentj;


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
            this.squares[Integer.parseInt(position[0])][Integer.parseInt(position[1])].setStatus("S");
            currentSquare=this.squares[Integer.parseInt(position[0])][Integer.parseInt(position[1])];
            currenti=Integer.parseInt(position[0]);
            currentj=Integer.parseInt(position[1]);
            line=file.readLine();//Ends

            while ((line=file.readLine()).equals("Traps")==false){
                position=line.split(",");
                this.squares[Integer.parseInt(position[0])][Integer.parseInt(position[1])].setStatus("E");
            }

            while ((line=file.readLine()).equals("Walls")==false){
                position=line.split(",");
                this.squares[Integer.parseInt(position[0])][Integer.parseInt(position[1])].setStatus("T");
            }

            while ((line=file.readLine())!=null){
                position=line.split(",");
                if (position[2].equals("East")){
                    this.squares[Integer.parseInt(position[0])][Integer.parseInt(position[1])].setWest();
                }
                else if (position[2].equals("South")){
                    this.squares[Integer.parseInt(position[0])][Integer.parseInt(position[1])].setSouth();
                }

            }
            printMaze();

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

    private void printMaze(){
        for(int i=0;i<this.rowLength;i++){
            for (int j=0;j<this.columnLength;j++){
                System.out.print(this.squares[i][j].getStatus());
            }
            System.out.println();
        }

    }
    public void goEast(){
        this.currentSquare=this.squares[this.currenti][this.currentj-1];
        this.currentj--;
    }
    public void goSouth(){
        this.currentSquare=this.squares[this.currenti+1][this.currentj];
        this.currenti++;
    }
    public void goWest(){
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

}
