/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/**
 *
 * @author frank
 */
public class cliente {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("PROGRAMA CLIENTE\n");
        
        try {
            
            DatagramSocket Cliente = new DatagramSocket();
            int puerto = 5001;
            InetAddress host = InetAddress.getLocalHost();
            String line;
            
              do{
                
                BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Digite una cadena: ");
                line = entrada.readLine();
                
                byte [] mensaje= line.getBytes();
            
                DatagramPacket peticion = new DatagramPacket(mensaje, line.length(), host, puerto);
                Cliente.send(peticion);

                DatagramPacket respuesta = new DatagramPacket(mensaje, line.length());
                Cliente.receive(respuesta);

                System.out.println("Mensaje del server: " + new String(respuesta.getData(), 0, respuesta.getLength()));
                
            }while(!"".equals(line));
            
            
        } catch (SocketException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
    }
        
    }
    
}
