/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remote;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author frank
 */
public class server {
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         System.out.println("SERVER EN FUNCIONAMIENTO");
        
        try {
            DatagramSocket server = new DatagramSocket(5001);
            byte[] buffer = new byte[1024];
            
              while (true) {   
                
                DatagramPacket peticion = new DatagramPacket(buffer,buffer.length);
                server.receive(peticion);
                
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();
                
                System.out.println("Mensaje: "+ new String(peticion.getData(),0,peticion.getLength()));
                
                byte [] buff = new byte[1024];
                
                String mensaje = new String(peticion.getData()); 
                
                buff = mensaje.getBytes();
                
                DatagramPacket respuesta = new DatagramPacket(buff, mensaje.length(), direccion, puertoCliente);
                server.send(respuesta);
                
            }
            
        } catch (SocketException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }
}
