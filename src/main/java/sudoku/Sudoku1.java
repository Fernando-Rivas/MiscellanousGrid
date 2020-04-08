package sudoku;

public class Sudoku1 {
    private int[][] actual = new int[9][9];
    public static int[][][] plausible = new int[9][9][10];
    private int run=0;

    private void initiate(){
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if(actual[x][y]!=0){
                    plausible[x][y]= new int[] {1,actual[x][y]};
                }else{
                    plausible[x][y] = new int[]{9,1,2,3,4,5,6,7,8,9};
                }

            }
        }
    }

    public void setTo(String vvalue){
        int pos = 0;
        for(int x = 0; x <9; x ++){
            for(int y = 0;y<9;y++){
                if(!(vvalue.charAt(pos)==('X'))){
                    int[] numbFill= new int[1];
                    numbFill[0] = Character.getNumericValue(vvalue.charAt(pos));
                    plausible[x][y] = numbFill;
                    actual[x][y] = numbFill[0];
                }
                pos++;
            }
        }
    }

    public void uniqueOutput(){
        int solved = 0;
        for(int x = 0; x <9; x ++){
            for(int y = 0;y<9;y++){
                if(actual[x][y]!=0) {
                    System.out.print("["+actual[x][y]+"] ");
                    solved++;
                }else{
                    System.out.print("[ ] ");
                }
                if(y%3==2){
                    System.out.print("   ");
                }

            }
            System.out.println("");
            if(x%3==2){
                System.out.println();
            }

        }
        System.out.println("Solved numbers = "+ solved);
    }

    public void completeOutput(){
        int possibilities = 0;
        for(int x = 0; x <9; x ++){
            for(int y = 0;y<9;y++){
                System.out.print("\n"+x+","+y+" :");
                if(plausible[x][y][0]==1) {
                    System.out.print("["+plausible[x][y][1]+"] ");
                    possibilities++;
                }else{
                    System.out.print("[ ");
                    for(int i = 1;i<plausible[x][y][0]+1;i++){
                        System.out.print("|"+plausible[x][y][i]+"|");
                        possibilities++;
                }

                    System.out.print("]");


            }


        }

    }
        System.out.println("\nPossible numbers = "+ possibilities);
    }

    public boolean removeNum(int x, int y, int pos) {
        //System.out.println("Removing:"+plausible[x][y][pos]+"from position: "+x+","+y);
        for(int i = pos;i<plausible[x][y][0];i++){
                plausible[x][y][i] = plausible[x][y][i+1];

        }
        plausible[x][y][plausible[x][y][0]]=0;
        plausible[x][y][0]--;

        if(plausible[x][y][0]==1){
            return true;
        }else{
            return false;
        }
    }

    public boolean boxCheck(int x,int y,int num){
        int topx = x;
        int topy = y;
        while(topx%3!=0){
            topx--;
        }
        while(topy%3!=0){
            topy--;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(actual[topx+i][topy+j]==num){
                    return true;
                }
            }

        }
        return false;
    }//This one acts weird?



    public void basicClear(int x,int y){

        for (int i = 0; i < 9; i++) {//horizontal check
            for(int j = 1;j<plausible[x][y][0];j++){
                if(plausible[x][y][j]==actual[x][i]){
                    removeNum(x,y,j);
                }
            }
        }
        for (int i = 0; i < 9; i++) {//vertical check
            for(int j = 1;j<=plausible[x][y][0];j++){
                if(plausible[x][y][j]==actual[i][y]){
                    removeNum(x,y,j);
                }
            }

        }

        for (int i = 1; i <plausible[x][y][0]; i++) {//box check
            if(boxCheck(x,y,plausible[x][y][i+1])){
                if(removeNum(x,y,i+1)){
                    return;
                }
            }
        }


    }

    public int solvePosition(int x,int y){
        if(plausible[x][y][0] == 1){
            //System.out.println("Solved on run: "+run);
            return plausible[x][y][1];
        }else{
            for (int i = 0; i < plausible[x][y][0]; i++) {
                basicClear(x,y);


            }
        }

        return 0;
    }

    private void solve(){
        boolean solved = false;
        int ye = 20;
        while(!solved&&ye!=0){
            for(int x = 0; x <9; x ++){
                for (int y = 0; y < 9; y++) {
                    if(actual[x][y]==0){
                        actual[x][y] = solvePosition(x,y);
                    }
                }
            }
            run++;
            ye--;
        }
    }
    
    public static void main(String[] args){//Test site
        
        //Sudoku1 a = new Sudoku1();
        //a.setTo("X46832X5XX28X45X9XXXX1962XX4X9XX7XX135X9XXX72X8X6XXXXX7XX5XXX2X8XXXX976X6XX37X4X5");
        //a.initiate();

        //a.uniqueOutput();
        //a.completeOutput();

        //a.solve();

        //a.uniqueOutput();
        //a.completeOutput();

    }
}
