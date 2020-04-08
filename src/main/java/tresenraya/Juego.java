package tresenraya;

import java.util.Scanner;


public class Juego {
    static Scanner kb = new Scanner(System.in);
    public static String[][] tablero = new String[3][3];

    public void iniciar(){
        for(int i = 0;i<3;i++){
            for(int j =  0;j<3;j++){
                tablero[i][j] = "vacio";
            }
        }
    }


    public  void imprimirCompleto(){
        System.out.println("   4  5  6");
        for(int i = 0;i<3;i++){
            System.out.print(i+" ");
            for(int j = 0;j<3;j++){
                if(tablero[i][j].equals("vacio")){
                    System.out.print("[ ]");
                }else {
                    System.out.print("[" + tablero[i][j]+"]");
                }
            }
            System.out.println("");
        }
    }

    private boolean lleno(int x,int y){
        if(tablero[x][y].equals("vacio")){
            return false;
        }else{
            return true;
        }
    }


    public void imprimirGanador(String jugador){
        System.out.println("====================================");
        System.out.println("Ha ganado la "+jugador);
        System.out.println("====================================");
        imprimirCompleto();
    }

    public int pedirNum(int min,int max){
        Integer result = 0;
        do{
            try{
                System.out.println("Introduzca un numero entre el "+min+" y el "+max);
                result = kb.nextInt();
                if(result<min||result>max){
                    System.out.println("Numero fuera de rango");
                    result = null;
                }
            }catch(Exception e){
                System.out.println("Formato del numero invalido");
                result = null;
            }
            kb.nextLine();
        }while(result==null);
        return result;
    }

    public void ponerFichaAleatoria(String jugador){
        int[] result = new int[2];
        boolean posValida;
        do{
            posValida = true;
            result[0] = (int) (Math.random()*3);
            result[1] = (int) (Math.random()*3);

            if(lleno(result[0],result[1])){
                posValida=false;
            }else{
                tablero[result[0]][result[1]] = jugador;
            }

        }while(!posValida);
    }


    public void ponerFicha(String jugador){
        int[] result = new int[2];
        boolean posValida;

        do{
            posValida = true;
            System.out.println("Introduce la posicion desea");
            result[0] = pedirNum(0,2);
            result[1] = pedirNum(4,6);
            result[1] -=4;
            if(lleno(result[0],result[1])){
                posValida=false;
                System.out.println("Ahi ya hay una ficha");
            }else{
                tablero[result[0]][result[1]] = jugador;
                System.out.println("Ficha colocada correctamente");
            }

        }while(!posValida);
    }


    private String horizontales(){
        
        for(int i = 0;i<3;i++){
                String quien = tablero[i][0];
                if(tablero[i][0].equals(quien)&&tablero[i][1].equals(quien)&&tablero[i][2].equals(quien)){
                    return quien;
            }
        }
        return "nadie";

    }
    private String vertical(){
        
        for(int i = 0;i<3;i++){
            String quien = tablero[0][i];
            if(tablero[0][i].equals(quien)&&tablero[1][i].equals(quien)&&tablero[2][i].equals(quien)){
                return quien;
            }
        }
        return "nadie";

    }
    
    private String diagonales(){
        String quien = tablero[1][1];
        if(tablero[0][0].equals(quien)&&tablero[1][1].equals(quien)&&tablero[2][2].equals(quien)){
            return quien;
        }
        if(tablero[2][0].equals(quien)&&tablero[1][1].equals(quien)&&tablero[0][2].equals(quien)){
            return quien;
        }
        return "nadie";
        
    }
    
  public String ganador(){
      if(diagonales().equals("nadie")){
          if(horizontales().equals("nadie")){
              if(vertical().equals("nadie")){
                  return "nadie";
              }else{
                  return vertical();
              }
          }else{
              return horizontales();
          }
      }else{
          return diagonales();
      }
  }

}
