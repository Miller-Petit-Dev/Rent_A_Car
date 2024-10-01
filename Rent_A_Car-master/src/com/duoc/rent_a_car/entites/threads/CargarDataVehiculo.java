package com.duoc.rent_a_car.entites.threads;

import com.duoc.rent_a_car.entites.cars.OperacionesVehiculo;
import com.duoc.rent_a_car.inputs.VehiclePersistentInput;

public class CargarDataVehiculo implements Runnable {

    private VehiclePersistentInput persistentInput;
    private OperacionesVehiculo operacionesVehiculo;
    static String archivoVehiculos = "lista_vehiculos.txt";

    public CargarDataVehiculo(VehiclePersistentInput persistentInput) {
        this.persistentInput = persistentInput;
    }

    public CargarDataVehiculo(VehiclePersistentInput persistentInput, OperacionesVehiculo operacionesVehiculo) {
        this.persistentInput = persistentInput;
        this.operacionesVehiculo = operacionesVehiculo;
    }

    @Override
    public void run() {
        synchronized (persistentInput) {
            try {
                persistentInput.wait();
                System.out.println("Cargando informacion de vehiculos...");
                persistentInput.cargarVehiculoDesdeTxt(operacionesVehiculo, archivoVehiculos);
                System.out.println("Informacion cargada");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Lo sentimos, no se ha podido cargar la informacion de vehiculos. Procure tener el archivo txt con informacion.");
                Thread.currentThread().interrupt(); // Volver a interrumpir el hilo
            }
        }
    }
}
