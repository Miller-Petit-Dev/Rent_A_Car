package com.duoc.rent_a_car.interfaces;

import com.duoc.rent_a_car.entites.client.OperacionesCliente;

public interface IOperacionesFinancieras {

    public int aplicarIVA(int valorArriendo);

    public int aplicarDescuentoClienteNuevo(int valorArriendo);

    public int calcularTotalServicio(int idCliente, OperacionesCliente operacionesCliente);
    
}
