/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author Estuardo
 */
public class Caja extends Thread{
    private String texto;
    Caja(String texto){
        this.texto=texto;
    }
    
    public String mostrar(){
        return this.texto;
    }
}
