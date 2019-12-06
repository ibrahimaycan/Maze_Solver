import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    private Square[][] squares;
    private int rowLength;
    private int columnLength;



    public Maze(String path)throws IOException{
        try {
            BufferedReader file = new BufferedReader(new FileReader(path));
            String line = file.readLine();
            line=file.readLine();
            this.rowLength=Integer.parseInt(line);
            line=file.readLine();
            line=file.readLine();
            this.columnLength=Integer.parseInt(line);
            this.squares=new Square[this.rowLength][this.columnLength];
            for(int i=0;i<rowLength;i++){
                for (int j=0;j<columnLength;j++){
                    this.squares[i][j]=new Square(i,j,"NULL");
                }
            }

            while ((line=file.readLine())!="Ends"){

            }

        }
        catch (Exception ex){
            System.out.println("error");

        }


    }


}
