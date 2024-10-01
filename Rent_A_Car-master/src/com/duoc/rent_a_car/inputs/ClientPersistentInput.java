package com.duoc.rent_a_car.inputs;

import com.duoc.rent_a_car.entites.client.Cliente;
import com.duoc.rent_a_car.entites.client.OperacionesCliente;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientPersistentInput {

    // METODO QUE ME PERMITIRA CARGAR USUARIOS DESDE UN ARCHIVO DE TEXTO
    public List<Cliente> cargarClienteDesdeTxt(OperacionesCliente operacionesCliente, String rutaArchivo) {
        List<Cliente> listaClientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                //Elimino los caracteres de inicio y fin de la linea
                line = line.substring(line.indexOf("{") + 1, line.lastIndexOf("}"));

                //Divido la linea en partes por coma
                String[] partes = line.split(", ");

                //Incializo variables para almacenar datos de cliente
                int idCliente = -1;
                String nombreCliente = null;
                Integer telefono = null;

                //Itero sobre las partes para extraer los valores
                for (String parte : partes) {
                    String[] atributoValor = parte.split("=");
                    String atributo = atributoValor[0].trim();
                    String valor = atributoValor[1].trim();
                    switch (atributo) {

                        case "idCliente":
                            idCliente = Integer.parseInt(valor);
                            break;
                        case "nombreCliente":
                            nombreCliente = valor;
                            break;
                        case "telefono":
                            telefono = Integer.valueOf(valor);
                            break;
                        default:
                            //Aca se tratara cualquier otro atributo de ser necesario
                            break;
                    }
                }
                // Crea y agrega el usuario a la lista
                Cliente cliente = new Cliente(idCliente, nombreCliente, telefono);
                listaClientes.add(cliente);
            }
            //Aca utilizo una expresion lambda para reducir un poquito el codigo, pero basicamente paso la info de una lista a la otra
            listaClientes.forEach(clientes -> operacionesCliente.getListaClientes().add(clientes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }
}
