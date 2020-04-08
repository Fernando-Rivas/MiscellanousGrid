package tresenraya;

public class Main {
    public static void main(String[] args) {
        Juego nuevoJuego = new Juego();
        String jugador = "X";
        boolean noHayGanador = true;
        nuevoJuego.iniciar();
        do {
            nuevoJuego.iniciar();
            do {
                nuevoJuego.imprimirCompleto();
                if(jugador.equals("X")){
                    nuevoJuego.ponerFicha("X");
                }else{
                    nuevoJuego.ponerFichaAleatoria("O");
                }
                switch (nuevoJuego.ganador()) {
                    case "X":
                        nuevoJuego.imprimirGanador("X");
                        noHayGanador = false;
                        break;
                    case "O":
                        nuevoJuego.imprimirGanador("O");
                        noHayGanador = false;

                    default:
                        break;
                }
                if (jugador.equals("X")) {
                    jugador = "O";
                } else {
                    jugador = "X";
                }
            } while (noHayGanador);
            System.out.println("Introduzca 0 si desea volver a jugar");
        } while (nuevoJuego.pedirNum(0, 1) != 0);
    }
}
