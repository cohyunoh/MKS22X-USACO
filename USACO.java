import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class USACO{
  public static int bronze(String filename){
    return 0;
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
