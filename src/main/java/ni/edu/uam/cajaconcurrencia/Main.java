package ni.edu.uam.cajaconcurrencia;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Objeto compartido por todos los Threads (hilos)
        Caja caja = new Caja();

        // Runnable representa la tarea que ejecutará cada uno de los vendedores
        Runnable vendedor1 = () -> procesarVentas("Vendedor 1", caja);
        Runnable vendedor2 = () -> procesarVentas("Vendedor 2", caja);
        Runnable vendedor3 = () -> procesarVentas("Vendedor 3", caja);

        // Crear los Threads (hilos)
        Thread hilo1 = new Thread(vendedor1);
        Thread hilo2 = new Thread(vendedor2);
        Thread hilo3 = new Thread(vendedor3);

        System.out.println("---- Inicio del proceso de ventas ----");

        /**
         * start() es el encargado de iniciar el proceso del hilo
         * Los tres vendedores comienzan a procesar sus ventas concurrentemente
         */
        hilo1.start();
        hilo2.start();
        hilo3.start();

        /**
         * join() permite qye el hilo main espere el proceso
         * hasta que terminen los otros Threads
         */
        hilo1.join();
        hilo2.join();
        hilo3.join();

        System.out.println("---- Termina el proceso de ventas ----");
        System.out.println("Cantidad de ventas: " + caja.getCantidadVentas());
        System.out.println("Total recaudado: C$" +caja.getTotalRecaudado());


    }

    /**
     * Método que simula las ventas realizadas por cada vendedor
     * @param vendedor
     * @param caja
     */
    private static void procesarVentas(String vendedor, Caja caja) {
        // Simulando un total de cinco ventas
        for(int i=1; i <= 5; i++){
            try{
                Thread.sleep(100);
                caja.registrarVentas(vendedor, 100);
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println(vendedor + " fue interrumpido");
                return;
            }
        }
    }

    /*
     En esta actividad utilizamos tres hilos que representan a los vendedores, donde cada uno realiza 5
     ventas de C$100. Utilizamos synchronized para evitar que varios hilos modifiquen la cantidad de
     ventas y el total recaudado al mismo tiempo, evitando así el problema de race condition.

     También utilizamos join() para esperar que los tres vendedores terminen sus ventas antes de
     mostrar los resultados finales. De esta manera logramos registrar correctamente las 15 ventas
     y obtener un total recaudado de C$1500.
     */
}

