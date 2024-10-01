package com.duoc.rent_a_car.interfaces;

import com.duoc.rent_a_car.entites.cars.Vehiculo;
import com.duoc.rent_a_car.entites.client.OperacionesCliente;
import com.duoc.rent_a_car.outputs.VehiclePersistentOuput;
import java.util.Map;

public interface IOperacionesVehiculo {
    public String generarPatente();
    public void arrendarVehiculo(int idCliente, OperacionesCliente operacionesCliente, VehiclePersistentOuput vehiclePersistentOuput);
    public Map<String,Vehiculo> listarTodosVehiculos();
    public Vehiculo buscarVehiculoPorPatente(String patente);
    public String identificarVehiculos(OperacionesCliente operacionesCliente, int idUsuario);
    public String valoresUnitarios(OperacionesCliente operacionesCliente, int idUsuario);
    public String diasArriendo(OperacionesCliente operacionesCliente, int idUsuario);
}
