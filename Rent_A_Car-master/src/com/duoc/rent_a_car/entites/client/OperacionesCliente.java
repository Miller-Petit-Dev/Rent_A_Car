package com.duoc.rent_a_car.entites.client;

import com.duoc.rent_a_car.entites.cars.OperacionesVehiculo;
import com.duoc.rent_a_car.interfaces.IOperacionesCliente;
import com.duoc.rent_a_car.outputs.ClientPersistentOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class OperacionesCliente implements IOperacionesCliente {

    private static final Scanner sc = new Scanner(System.in);
    private Cliente cliente;
    private List<Cliente> listaClientes;

    public OperacionesCliente() {
        this.listaClientes = new ArrayList<>();
    }

    public OperacionesCliente(Cliente cliente, List<Cliente> listaClientes) {
        this.cliente = cliente;
        this.listaClientes = listaClientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Cliente> mostrarClientes() {
        System.out.println("Mostrando id de clientes");
        for (Cliente c : listaClientes) {
            System.out.println("Id Cliente: " + c.getIdCliente() + " Nombre cliente: " + c.getNombreCliente());
        }
        return listaClientes;
    }

    //Metodo implementdo por interface
    //CON CADA LLAMADA AL METODO CREARÉ NUEVOS USUARIOS QUE ALACENARÉ EN MI LISTA
    @Override
    public void registrarUsuario(ClientPersistentOutput clientPersistent) {
        Cliente cliente = new Cliente();
        Boolean cicloDoWhile = true;
        Random rand = new Random();
        int min = 1;
        int max = 10000;
        int idAleatorio = rand.nextInt(max - min + 1) + min;
        cliente.setIdCliente(idAleatorio);
        System.out.println("Id creado para usuario: " + cliente.getIdCliente());
        do {
            System.out.println("Ingrese nombre de usuario a registrar");
            String nombre = sc.nextLine();
            if (nombre.isBlank() || nombre.isEmpty()) {
                System.out.println("Debe ingresar un nombre valido, por favor vuelva a intentarlo");
            } else {
                cliente.setNombreCliente(nombre);
                System.out.println("Nombre registrado !");
                cicloDoWhile = false;
            }
        } while (cicloDoWhile);
        cicloDoWhile = true; //Reseteo mi variable para que continue de manera correcta el flujo del programa

        do {
            try {
                System.out.println("Ingrese telefono de usuario a registrar (9 digitos)");
                int telefono = sc.nextInt();

                String telefonoACadena = String.valueOf(telefono);
                if (telefonoACadena.isBlank() || telefonoACadena.isEmpty()) {
                    System.out.println("Debe ingresar un numero valido, por favor vuelva a intentarlo");
                } else {
                    cliente.setTelefono(telefono);
                    System.out.println("Telefono registrado !");
                    //ALMACENO MI USUARIO EN LA LISTA
                    listaClientes.add(cliente);
                    //GUARDO ESA LISTA EN UN TXT
                    clientPersistent.almacenarUsuariosEnTxt(cliente);
                    System.out.println("USUARIO REGISTRADO !");
                    cicloDoWhile = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR, debe ingresar solo numeros");
                e.printStackTrace();
                sc.next(); //LIMPIO BUFFER
            }
        } while (cicloDoWhile);

    }

    @Override
    public Cliente obtenerClientePorId(int idCliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    public void generarBoleta(int idCliente, Finanzas finanzas, OperacionesVehiculo operacionesVehiculo) {
        String direccion = "Valparaiso, Indepencia 1239";
        String telefono = "(+56)967675424";
        // Obtengo la fecha y hora actuales
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaFormateada = fechaActual.format(formatter);
        //Identifico el cliente 
        Cliente clienteSeleccionado = obtenerClientePorId(idCliente);
        //Detecto el numero de vehiculos que posee
        int cantidad = clienteSeleccionado.getListaVehiculos().size();
        //Por medio del metodo de finanzas, obtengo el subTotal;
        double subtotal = finanzas.calcularTotalServicio(idCliente, this);

        //Enseño el iva del servicio
        double iva = subtotal * 0.19;
        //Aplico descuento por compra
        double descuento = finanzas.aplicarDescuentoClienteNuevo((int) subtotal);
        //Genero el total de la compra
        double total = (subtotal + iva) - descuento;

        // Imprimir la boleta
        System.out.println("==============================================");
        System.out.println("|                  BriefDrive                |");
        System.out.println("==============================================");
        System.out.println("| Dirección: " + direccion + "                    ");
        System.out.println("| Teléfono: " + telefono + "                         ");
        System.out.println("| Fecha: " + fechaFormateada + "                ");
        System.out.println("==============================================");
        System.out.println("| Cliente: " + obtenerClientePorId(idCliente).getNombreCliente() + "                           ");
        System.out.println("==============================================");
        System.out.println("| Producto/Servicio        | " + operacionesVehiculo.identificarVehiculos(this, idCliente) + "              ");
        System.out.println("| Detalle                  | Cantidad: " + cantidad + "              ");
        System.out.println("|                          | Precio Unitario: " + operacionesVehiculo.valoresUnitarios(this, idCliente) + "         ");
        System.out.println("| Dias de arriendo         | " + operacionesVehiculo.diasArriendo(this, idCliente) );
        System.out.println("==============================================");
        System.out.println("| Subtotal                 | " + subtotal + "                ");
        System.out.println("| Descuento cliente nuevo  | " + descuento+ "             ");
        System.out.println("| IVA                      | " + iva + "                 ");
        System.out.println("| Total                    | " + total + "                ");
        System.out.println("==============================================");

    }
}
