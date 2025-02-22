import java.util.Scanner;

public class InterfazPokemon {

    /**
     *@param teclado
     * @return para intrucir datos mediante el uso del teclado
     */
    private Scanner teclado;

    public InterfazPokemon() {
        teclado = new Scanner(System.in);
    }

    public void Juego() {
        Pokemon jugador = menuCreacionPokemonJugador();

        for (int i = 1; i <= 3; i++) {
            Pokemon rival = siguientePokemonRival(i);
            System.out.println("Aguante de " + jugador.getNombre() + " : " + jugador.getAguante());
            System.out.println("Aguante de " + rival.getNombre() + " : " + rival.getAguante());

            Pokemon ganador = Partida(jugador, rival);
            if (ganador == null) {
                mostrarFinPartida(); // Game over para el jugador
                return;
            } else {
                jugador = ganador;
            }

        }

        mostrarJuegoSuperado();
    }

    /**
     * Este metodo muestra por pantalla al jugador oponente
     * @return Además inicialisará el metodo Ronda
     */

    private Pokemon Partida(Pokemon pokemonJugador, Pokemon pokemonRival) {
        Combate combate = new Combate(pokemonJugador, pokemonRival);
        Pokemon ganador = null;

        while ((ganador = combate.Ganador()) == null) {
            System.out.println("\nPresentación del pokemon oponente: \nNombre: " + pokemonRival.getNombre());
            combate.Ronda();
        }

        return ganador;
    }

    /**
     * Este metodo es para crear el pokemon como su nombre , tipo , etc.
     * @return Permite crear el pokemon
     */

    private Pokemon menuCreacionPokemonJugador() {

        System.out.println("******************************");
        System.out.println("¡Bienvenido al juego Pokémon!");
        System.out.println("******************************");
        System.out.print("Introduze un nombre: ");
        String nombre = teclado.nextLine();

        System.out.println("Elige su tipo: \n1-Agua \n2-Fuego \n3-Tierra ");
        String tipo = teclado.nextLine();

        // Usando switch para validar el tipo
        while (true) {
            switch (tipo) {
                case "1":
                    tipo = "Agua";
                    return new Pokemon(nombre, tipo);
                case "2":
                    tipo = "Fuego";
                    return new Pokemon(nombre, tipo);
                case "3":
                    tipo = "Tierra";
                    return new Pokemon(nombre, tipo);
                default:
                    System.out.println("Tipo inválido. Elige su tipo: \n-Agua \n-Fuego \n-Tierra ");
                    tipo = teclado.nextLine();
            }
        }
    }

    /**
     * Este metodo almacena los oponentes al cual el jugador enfrentará
     * @return Pasa al siguiente oponente
     */

    public Pokemon siguientePokemonRival(int numero) {
        Pokemon rival = null;

        switch (numero) {
            case 1:
                rival = new Pokemon("Caterpie", "tierra", 1, 2);
                break;
            case 2:
                rival = new Pokemon("Bulbasur", "agua", 2, 5);
                break;
            case 3:
                rival = new Pokemon("Charmander", "fuego", 3, 8);
                break;
        }
        return rival;
    }

    /**
     * Mostrará por pantalla que has ganado
     * @return Que bien ,has ganadp
     */

    public static void mostrarJuegoSuperado() {
        System.out.println("++++++++++ ENHORABUENA +++++++++++");
        System.out.println("+++++ HAS SUPERADO EL JUEGO ++++++");
        System.out.println("++++ ERES UN MAESTRO POKEMON +++++");
    }

    /**
     * Mostrará por pantalla que has perdido
     * @return Que mal ,has perdido
     */

    public static void mostrarFinPartida() {
        System.out.println("++++++++++ LO SIENTO +++++++++++");
        System.out.println("+++++ HAS SIDO ELIMINADO ++++++");
        System.out.println("+++++ VUELVE A INTENTARLO +++++");
    }

}
