package com.duoc.rent_a_car.entites.cars;

import com.duoc.rent_a_car.entites.client.Cliente;
import com.duoc.rent_a_car.entites.client.OperacionesCliente;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import com.duoc.rent_a_car.interfaces.IOperacionesVehiculo;
import com.duoc.rent_a_car.outputs.VehiclePersistentOuput;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class OperacionesVehiculo implements IOperacionesVehiculo {

    private Map<String, Vehiculo> syncListVehiculeClient;
    private Map<String, Vehiculo> listAllVehicules;
    private Map<String, Vehiculo> syncListAllVehicule;

    public OperacionesVehiculo() {
        listAllVehicules = new HashMap<>();
        syncListAllVehicule = new HashMap<>();
    }

    public OperacionesVehiculo(Map<String, Vehiculo> syncListVehiculeClient, Map<String, Vehiculo> listAllVehicules, Map<String, Vehiculo> syncListAllVehicule) {
        this.syncListVehiculeClient = syncListVehiculeClient;
        this.listAllVehicules = listAllVehicules;
        this.syncListAllVehicule = syncListAllVehicule;
    }

    public Map<String, Vehiculo> getSyncListVehiculeClient() {
        return syncListVehiculeClient;
    }

    public void setSyncListVehiculeClient(Map<String, Vehiculo> syncListVehiculeClient) {
        this.syncListVehiculeClient = syncListVehiculeClient;
    }

    public Map<String, Vehiculo> getListAllVehicules() {
        return listAllVehicules;
    }

    public void setListAllVehicules(Map<String, Vehiculo> listAllVehicules) {
        this.listAllVehicules = listAllVehicules;
    }

    public Map<String, Vehiculo> getSyncListAllVehicule() {
        return syncListAllVehicule;
    }

    public void setSyncListAllVehicule(Map<String, Vehiculo> syncListAllVehicule) {
        this.syncListAllVehicule = syncListAllVehicule;
    }

    @Override
    public String generarPatente() {
        Random numero = new Random();
        int min = 1;
        int max = 1000;
        int patenteNumero = numero.nextInt(max - min + 1) + min;
        String patenteAString = String.valueOf(patenteNumero);
        String patenteGenerada = "FG-" + patenteAString;
        return patenteGenerada;
    }

    @Override
    public void arrendarVehiculo(int idCliente, OperacionesCliente operacionesCliente, VehiclePersistentOuput vehiclePersistentOuput) {
        //EN ESTA LINEA CAPTURO UN CLIENTE BUSCADO EN LA LISTA DE CLIENTES POR MEDIO DE UN ID
        int diasArriendo = 0;
        Cliente clienteActual = operacionesCliente.obtenerClientePorId(idCliente);
        if (clienteActual != null) {
            try {
                System.out.println("Por favor elija la categoria del vehiculo que desea arrendar escribiendo el numero segun corresponda");
                System.out.println("1: Turismo. \n"
                        + "2: Carga pesada. \n"
                        + "3: Auto particular. ");
                Scanner sc = new Scanner(System.in);
                int opcionCategoria = sc.nextInt();
                switch (opcionCategoria) {
                    case 1:
                        Furgon furgon = new Furgon(generarPatente(), 10, "Toyota", "Turismo", 60000, "Furgon");
                        listAllVehicules.put(furgon.getPatente(), furgon); //AGREGO EL VEHICULO CREADO A UNA LISTA GENERAL DE VEHICULOS
                        //Paso toda lista de vehiculos a una lista syncronizada DE TODOS LOS VEHICULOS
                        syncListAllVehicule = Collections.synchronizedMap(listAllVehicules);
                        System.out.println("Modelo para arrendar:");
                        System.out.println(furgon.toString());
                        System.out.println("Indique la cantidad de dias, que desea utilizar el vehiculo");
                        diasArriendo = sc.nextInt();
                        furgon.setDiasArriendo(diasArriendo);
                        //GUARDO EL VEHICULO EN LA LIST DEL CLIENTE ACTUAL
                        clienteActual.getListaVehiculos().put(furgon.getPatente(), furgon);
                        //AGREGO EL VEHICULO CREADO AL ARCHIVO TXT
                        vehiclePersistentOuput.almacenarVehiculoEnTxt(furgon);
                        //USO DE LISTA SYNCRONIZADA (PASO LA LISTA DE VEHICULOS A UNA LISTA SINCRONIZADA)
                        syncListVehiculeClient = Collections.synchronizedMap(clienteActual.getListaVehiculos());
                        break;
                    case 2:
                        Camion camion = new Camion("8 toneladas ", generarPatente(), 5, "Mercedez", "Carga pesada", 120000, "Camion");
                        listAllVehicules.put(camion.getPatente(), camion); //AGREGO EL VEHICULO CREADO A UNA LISTA GENERAL DE VEHICULOS
                        System.out.println("Modelo para arrendar:");
                        System.out.println(camion.toString());
                        System.out.println("Indique la cantidad de dias, que desea utilizar el vehiculo");
                        diasArriendo = sc.nextInt();
                        camion.setDiasArriendo(diasArriendo);
                        //AGREGO EL VEHICULO CREADO AL ARCHIVO TXT
                        vehiclePersistentOuput.almacenarVehiculoEnTxt(camion);
                        clienteActual.getListaVehiculos().put(camion.getPatente(), camion);
                        //USO DE LISTA SYNCRONIZADA (PASO LA LISTA DE VEHICULOS A UNA LISTA SINCRONIZADA)
                        syncListVehiculeClient = Collections.synchronizedMap(clienteActual.getListaVehiculos());
                        break;
                    case 3:
                        AutoSedan autoSedan = new AutoSedan(generarPatente(), 5, "Kia", "Particular", 50000, "Autmovil");
                        listAllVehicules.put(autoSedan.getPatente(), autoSedan); //AGREGO EL VEHICULO CREADO A UNA LISTA GENERAL DE VEHICULOS
                        System.out.println("Modelo para arrendar:");
                        System.out.println(autoSedan.toString());
                        System.out.println("Indique la cantidad de dias, que desea utilizar el vehiculo");
                        diasArriendo = sc.nextInt();
                        autoSedan.setDiasArriendo(diasArriendo);
                        //AGREGO EL VEHICULO CREADO AL ARCHIVO TXT
                        vehiclePersistentOuput.almacenarVehiculoEnTxt(autoSedan);
                        clienteActual.getListaVehiculos().put(autoSedan.getPatente(), autoSedan);
                        //USO DE LISTA SYNCRONIZADA (PASO LA LISTA DE VEHICULOS A UNA LISTA SINCRONIZADA)
                        syncListVehiculeClient = Collections.synchronizedMap(clienteActual.getListaVehiculos());
                        break;
                    default:
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR debe ingresar un numero del 1 al 3");
            }
        } else {
            System.out.println("Lo sentimos, el id ingresado no ha sido encontrado");
        }
    }

    @Override
    public Map<String, Vehiculo> listarTodosVehiculos() {
        for (Entry<String, Vehiculo> e : syncListAllVehicule.entrySet()) {
            System.out.println(e);
        }
        return syncListAllVehicule;
    }

    @Override
    public Vehiculo buscarVehiculoPorPatente(String patente) {
        for (Entry<String, Vehiculo> e : syncListAllVehicule.entrySet()) {
            if (e.getValue().getPatente().equals(patente)) {
                System.out.println("Vehiculo encontrado");
                return e.getValue();
            }
        }
        System.out.println("Lo sentimos, no hemos encontrado un vehiculo registrado con la patente ingresada");
        return null;
    }

    @Override
    public String identificarVehiculos(OperacionesCliente operacionesCliente, int idUsuario) {
        Cliente clienteSeleccionado = operacionesCliente.obtenerClientePorId(idUsuario);
        StringBuilder tiposVehiculos = new StringBuilder();
        for (Entry<String, Vehiculo> vehiculo : clienteSeleccionado.getListaVehiculos().entrySet()) {
            tiposVehiculos.append(vehiculo.getValue().getTipoVehiculo()).append(", ");
        }
        // Elimina la última coma y espacio
        if (tiposVehiculos.length() > 0) {
            tiposVehiculos.setLength(tiposVehiculos.length() - 2);
        }
        return tiposVehiculos.toString();
    }

    @Override
    public String valoresUnitarios(OperacionesCliente operacionesCliente, int idUsuario) {
        Cliente clienteSeleccionado = operacionesCliente.obtenerClientePorId(idUsuario);
        StringBuilder valoresUnitarios = new StringBuilder();
        for (Entry<String, Vehiculo> vehiculos : clienteSeleccionado.getListaVehiculos().entrySet()) {
            valoresUnitarios.append("$").append(vehiculos.getValue().getValorArriendo()).append(", ");
        }
        // Elimina la última coma y espacio
        if (valoresUnitarios.length() > 0) {
            valoresUnitarios.setLength(valoresUnitarios.length() - 2);
        }
        return valoresUnitarios.toString();
    }

    //OBTENGO LOS DIAS DE ARRIENDO
    @Override
    public String diasArriendo(OperacionesCliente operacionesCliente, int idUsuario) {
        Cliente clienteSeleccionado = operacionesCliente.obtenerClientePorId(idUsuario);
        StringBuilder diasArriendo = new StringBuilder();
        for (Entry<String, Vehiculo> vehiculos : clienteSeleccionado.getListaVehiculos().entrySet()) {
            diasArriendo.append(vehiculos.getValue().getDiasArriendo()).append(", ");
        }
        // Elimina la última coma y espacio
        if (diasArriendo.length() > 0) {
            diasArriendo.setLength(diasArriendo.length() - 2);
        }
        return diasArriendo.toString();
    }

    public void filtrarArriendosPorMasDeUnaSemana() {
        for (Entry<String, Vehiculo> vehiculos : syncListAllVehicule.entrySet()) {
            if (vehiculos.getValue().getDiasArriendo() >= 6) {
                System.out.println(vehiculos.toString());
            }
        }
    }

}
