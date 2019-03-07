import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class USACO{
  public static void main(String[] args) {
    try{
      System.out.println(bronze("example.txt"));
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }
  public static int bronze(String filename) throws FileNotFoundException{
    File fileIn = new File(filename);
    Scanner in = new Scanner(fileIn);
    int rows = Integer.parseInt(in.next());
    int cols = Integer.parseInt(in.next());
    int elevation = Integer.parseInt(in.next());
    int stompnum = Integer.parseInt(in.next());
    int[][] pasture = new int[rows][cols];
    for(int r = 0; r < rows; r++){
      for(int c = 0; c < cols; c++){
        pasture[r][c] = Integer.parseInt(in.next());
      }
    }
    for(int i = 0; i < stompnum; i++){
      int row = Integer.parseInt(in.next());
      int col = Integer.parseInt(in.next());
      int numberStomp = Integer.parseInt(in.next());
      stomp(row,col,numberStomp,pasture);
    }
    for(int r = 0; r < rows; r++){
      for(int c = 0; c < cols; c++){
        int newElev = pasture[r][c] - elevation;
        if(newElev > 0){
          pasture[r][c] = 0;
        }else{
          pasture[r][c] = Math.abs(newElev);
        }
      }
    }
    int height = 0;
    for(int r = 0; r < rows; r++){
      for(int c = 0; c < cols; c++){
        height += pasture[r][c];
      }
    }
    return 72 * 72 * height;
  }
  private static void stomp(int row, int col, int number, int[][] pasture){
    int greatest = findGreatest(row,col,pasture);
    for(int i = 0; i < number; i++){
      greatest = findGreatest(row,col,pasture);
      for(int r = 0; r < 3; r++){
        for(int c = 0; c < 3; c++){
          if(pasture[row + r][col + c] == greatest){
            pasture[row + r][col + c]--;
          }
        }
      }
    }
  }
  private static int findGreatest(int row, int col, int[][] pasture){
    int greatest = 0;
    for(int r = 0; r < 3; r++){
      for(int c = 0; c < 3; c++){
        if(pasture[row + r][col + c] > greatest){
          greatest = pasture[row + r][col + c];
        }
      }
    }
    return greatest;
  }
}
