package com.duoc.rent_a_car.inputs;

import com.duoc.rent_a_car.entites.cars.AutoSedan;
import com.duoc.rent_a_car.entites.cars.Camion;
import com.duoc.rent_a_car.entites.cars.Furgon;
import com.duoc.rent_a_car.entites.cars.OperacionesVehiculo;
import com.duoc.rent_a_car.entites.cars.Vehiculo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehiclePersistentInput {

    //METODO PARA CARGAR VEHICULOS DESDE UN ARCHIVO TXT
    public List<Vehiculo> cargarVehiculoDesdeTxt(OperacionesVehiculo operacionesVehiculo, String rutaArchivo) {
        List<Vehiculo> listVehiculos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                //Elimino los caracteres de inicio y fin de la linea
                line = line.substring(line.indexOf("{") + 1, line.lastIndexOf("}"));

                //Divido la linea en partes por coma
                String[] partes = line.split(", ");

                //Incializo variables para almacenar datos de cliente
                String patente = null;
                Integer capacidadPasajeros = null;
                String marca = null;
                String categoria = null;
                Integer valorArriendo = null;
                String tipoVehiculo = null;
                Integer diasArriendo = null;

                //Itero sobre las partes para extraer los valores
                for (String parte : partes) {
                    String[] atributoValor = parte.split("=");
                    String atributo = atributoValor[0].trim();
                    String valor = atributoValor[1].trim();

                    switch (atributo) {
                        case "patente":
                            patente = valor;
                            break;
                        case "capacidadPasajeros":
                            capacidadPasajeros = Integer.parseInt(valor);
                            break;
                        case "marca":
                            marca = valor;
                        case "categoria":
                            categoria = valor;
                            break;
                        case "valorArriendo":
                            valorArriendo = Integer.parseInt(valor);
                            break;
                        case "tipoVehiculo":
                            tipoVehiculo = valor;
                            break;
                        case "diasArriendo":
                            diasArriendo = Integer.parseInt(valor);
                            break;
                        default:
                            //Aca se tratara cualquier otro atributo de ser necesario
                            break;
                    }
                }
                switch (tipoVehiculo) {
                    case "Furgon":
                        Furgon furgon = new Furgon(patente, capacidadPasajeros, marca, categoria, valorArriendo, tipoVehiculo, diasArriendo);
                        listVehiculos.add(furgon);
                        break;

                    case "Camion":
                        Camion camion = new Camion(patente, capacidadPasajeros, marca, categoria, valorArriendo, tipoVehiculo, diasArriendo);
                        listVehiculos.add(camion);
                        break;

                    case "Automovil":
                        AutoSedan auto = new AutoSedan(patente, capacidadPasajeros, marca, categoria, valorArriendo, tipoVehiculo, diasArriendo);
                        listVehiculos.add(auto);
                        break;
                }
            }
            // Iterar sobre la lista de vehículos y añadir cada vehículo al mapa
            listVehiculos.forEach(vehiculo -> operacionesVehiculo.getListAllVehicules().put(vehiculo.getPatente(), vehiculo));
            //COPIO LA DATA A LA LISTA SYNCORNIZADA DE TODOS LOS VEHICULOS
            listVehiculos.forEach(vehiculo -> operacionesVehiculo.getSyncListAllVehicule().put(vehiculo.getPatente(), vehiculo));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lo sentimos, no se pudo crgar el archivo txt de vehiculos");
        }
        return listVehiculos;
    }
}
