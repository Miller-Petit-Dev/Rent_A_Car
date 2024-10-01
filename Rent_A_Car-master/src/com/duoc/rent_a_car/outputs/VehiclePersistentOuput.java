package com.duoc.rent_a_car.outputs;

import com.duoc.rent_a_car.entites.cars.Vehiculo;
import java.io.FileWriter;
import java.io.IOException;

public class VehiclePersistentOuput {

    public void almacenarVehiculoEnTxt(Vehiculo vehiculo) {
        String salto = System.lineSeparator();
        try (FileWriter writer = new FileWriter("lista_vehiculos.txt", true)) {
            writer.write(vehiculo.toString() + salto);
        }catch(IOException e){
        e.printStackTrace();
            System.out.println("Lo sentimos, no se ah podido generar archivo txt");
        }
    }
}
