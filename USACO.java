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
    int[][] p = new int[5][5];
    p[2][2] = 1;
    System.out.println(iteration(0, p));
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
      int row = Integer.parseInt(in.next()) - 1;
      int col = Integer.parseInt(in.next()) - 1;
      int numberStomp = Integer.parseInt(in.next());
      stomp(row,col,numberStomp,pasture);
      //System.out.println(stringPasture(pasture));
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
    //System.out.println(stringPasture(pasture));
    return 72 * 72 * height;
  }

  public static int silver(String filename) throws FileNotFoundException{
    File fileIn = new File(filename);
    Scanner in = new Scanner(fileIn);
    int rows = Integer.parseInt(in.next());
    int cols = Integer.parseInt(in.next());
    int time = Integer.parseInt(in.next());
    String[][] pasture = new String[rows][cols];
    for(int r = 0; r < rows; r++){
      for(int c = 0; c < cols; c++){
        pasture[r][c] = in.next();
      }
    }
    int startR = Integer.parseInt(in.next());
    int startC = Integer.parseInt(in.next());
    int endR = Integer.parseInt(in.next());
    int endC = Integer.parseInt(in.next());
    int[][] movePasture = new int[rows][cols];
    movePasture[startR][startC] = 1;
    movePasture = iteration(time, movePasture);
    for(int r = 0; r < rows; r++){
      for(int c = 0; c < cols; c++){
        if(pasture[r][c].equals("*")){
          movePasture[r][c] = 0;
          if(r + 1 < pasture.length){
            if(movePasture[r + 1][c] - 1 < 0){
              movePasture[r + 1][c] = 0;
            }else{
              movePasture[r + 1][c] --;
            }
          }
          if(r - 1 >=0){
            if(movePasture[r - 1][c] - 1 < 0){
              movePasture[r - 1][c] = 0;
            }else{
              movePasture[r - 1][c] --;
            }
          }
          if(c + 1 < pasture[0].length){
            if(movePasture[r][c + 1] - 1 < 0){
              movePasture[r][c + 1] = 0;
            }else{
              movePasture[r][c + 1] --;
            }
          }
          if(c - 1 >=0){
            if(movePasture[r][c - 1] - 1 < 0){
              movePasture[r][c - 1] = 0;
            }else{
              movePasture[r][c - 1] --;
            }
          }
        }
      }
    }
    return movePasture[endR][endC];
  }

  private static void stomp(int row, int col, int number, int[][] pasture){
    int greatest = findGreatest(row,col,pasture);
    for(int i = 0; i < number; i++){
      greatest = findGreatest(row,col,pasture);
      for(int r = 0; r < 3; r++){
        for(int c = 0; c < 3; c++){
          if(row + r < pasture.length && col + c < pasture[row].length){
            if(pasture[row + r][col + c] == greatest){
              pasture[row + r][col + c]--;
            }
          }
        }
      }
    }
  }
  private static int findGreatest(int row, int col, int[][] pasture){
    int greatest = 0;
    for(int r = 0; r < 3; r++){
      for(int c = 0; c < 3; c++){
        if(row + r < pasture.length && col + c < pasture[row].length){
          if(pasture[row + r][col + c] > greatest){
            greatest = pasture[row + r][col + c];
          }
        }
      }
    }
    return greatest;
  }
  private static String stringPasture(int[][] pasture){
    int rows = pasture.length;
    int cols = pasture[0].length;
    String ans = "";
    for(int r = 0; r < rows; r++){
      String line = "";
      for(int c = 0; c < cols; c++){
        if(pasture[r][c] == 0){
          line += "-- ";
        }else{
          line += " " + pasture[r][c] + " ";
        }
      }
      line += "\n";
      ans += line;
    }
    return ans;
  }

  private static int[][] iteration(int i, int[][] pasture){
    if(i == 0){
      //System.out.println(0);
      //System.out.println(stringPasture(pasture));
      return pasture;
    }
    int moves = 1;
    while (moves <= i){
      int[][] newPasture = new int[pasture.length][pasture[0].length];
      for(int r = 0; r < pasture.length; r++){
        for(int c = 0; c < pasture[0].length; c++){
          if(pasture[r][c] != 0){
            if(r + 1 < pasture.length){
              newPasture[r + 1][c] += pasture[r][c];
            }
            if(r - 1 >=0){
              newPasture[r - 1][c] += pasture[r][c];
            }
            if(c + 1 < pasture[0].length){
              newPasture[r][c + 1] += pasture[r][c];
            }
            if(c - 1 >=0){
              newPasture[r][c - 1] += pasture[r][c];
            }
          }
        }
      }
      pasture = newPasture;
      //System.out.println(moves);
      moves ++;
      //System.out.println(stringPasture(pasture));
    }
    return pasture;
  }


}
