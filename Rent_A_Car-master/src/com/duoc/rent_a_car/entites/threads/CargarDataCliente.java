package com.duoc.rent_a_car.entites.threads;

import com.duoc.rent_a_car.entites.client.OperacionesCliente;
import com.duoc.rent_a_car.inputs.ClientPersistentInput;

public class CargarDataCliente implements Runnable {
    private OperacionesCliente operacionesCliente;
    static String archivo = "lista_clientes.txt";
    ClientPersistentInput clientPersistentInput;

    public CargarDataCliente(ClientPersistentInput clientPersistentInput) {
        this.clientPersistentInput = clientPersistentInput;
    }

    public CargarDataCliente(OperacionesCliente operacionesCliente, ClientPersistentInput clientPersistentInput) {
        this.operacionesCliente = operacionesCliente;
        this.clientPersistentInput = clientPersistentInput;
    }

    @Override
    public void run() {
        System.out.println("Iniciando sistema..\n"
                + "cargando informacion de clientes...");
        synchronized (clientPersistentInput) {
            try {
                clientPersistentInput.cargarClienteDesdeTxt(operacionesCliente, archivo);
                Thread.sleep(1000);
                clientPersistentInput.notifyAll();
                System.out.println("Informacion de clientes cargada");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Lo sentimos, no se ha podido cargar la informacion de clientes. Verifique el archivo txt.");
                Thread.currentThread().interrupt(); // Volver a interrumpir el hilo
            }
        }
    }
}
