package sudoku.base;

import java.util.Scanner;

public class Grid {
    public static int[][][] tablero = new int[9][9][];
    private static Scanner kb = new Scanner(System.in);
    public void startProcess(){
        int[] realm = {1,2,3,4,5,6,7,8,9};

        for(int i = 0;i<9;i++){
            for(int j = 0;j<9;j++){
                if(tablero[i][j]==null)
                    tablero[i][j] = realm;
            }
        }
    }

    public int[][][] getTablero(){
        return tablero;
    }

    public void setTestCoords(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j< 9; j++) {
                tablero[i][j] = new int[]{i, j};
            }
        }
    }

    public void outputVector(int[] workingVector){
        System.out.print("[");
        for(int i = 0;i<workingVector.length;i++){
            System.out.print(workingVector[i]);
        }
        System.out.print("] ");
    }

    public void fullOutput(){
        for(int i = 0;i<9;i++){
            for(int j = 0;j<9;j++){
                outputVector(tablero[i][j]);
                if(j%3==2){
                    System.out.print("   ");
                }

            }
            System.out.println("");
            if(i%3==2){
                System.out.println();
            }

        }
    }

    public void uniqueOutput(){
        for(int i = 0;i<9;i++){
            for(int j = 0;j<9;j++){
                if(tablero[i][j].length==1) {
                    outputVector(tablero[i][j]);
                }else{
                    System.out.print("[?] ");
                }
                if(j%3==2){
                    System.out.print("   ");
                }

            }
            System.out.println("");
            if(i%3==2){
                System.out.println();
            }

        }
    }

    public void init(){
        String end = "";
        do{
            int coord1 = pedirNum(1,9,"Enter your first co-ordinate: ");
            int coord2 = pedirNum(1,9,"Enter your second co-ordinate: ");
            int value = pedirNum(1,9,("Enter the value of square at position: "+coord1+","+coord2));
            tablero[coord1-1][coord2-1] = new int[]{value};
            System.out.println("Enter end if you are done, if not enter anything else");
            end = kb.next();
        }while(!end.equals("end"));
    }

    public void setTo(String vvalue){
        int pos = 0;
        for(int i = 0;i<9;i++){
            for(int j = 0;j<9;j++){
                if(!(vvalue.charAt(pos)==('X'))){
                    int[] numbFill= new int[1];
                    numbFill[0] = Character.getNumericValue(vvalue.charAt(pos));
                    tablero[i][j] = numbFill;
                }
                pos++;
            }
        }
    }

    public int pedirNum(int min, int max,String prompt){
        int response=0;
        boolean invalid = true;
        do{
            try{
                System.out.println(prompt);
                response = kb.nextInt();
                invalid = false;
            }catch(Exception e){
                System.out.println("Invalid value");
                response = 0;
                kb.next();

            }
        }while(invalid && (response<min||response>max));
        return response;
    }

    public static void main(String[] args) {
        Grid a = new Grid();
        //a.init();
        a.setTestCoords();
        //a.setTo("X46832X5XX28X45X9XXXX1962XX4X9XX7XX135X9XXX72X8X6XXXXX7XX5XXX2X8XXXX976X6XX37X4X5");
        a.startProcess();
        a.fullOutput();
        System.out.println("|||||||||||||||");
        a.uniqueOutput();

    }
}
