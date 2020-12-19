/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filosofos;

/**
 *
 * @author Jairo Hernandez G
 */
public class Tenedor {
    private boolean usado;
    private final int numero;
    private String dueno;
    public Tenedor(boolean usado, int numero){
        this.usado = usado;
        this.numero = numero;
    }
    public void setUsado(boolean usado){
        this.usado=usado;
    }
    public void setDueno(String dueno){
        this.dueno = dueno;
    }
    
    public String getDueno(){
        return this.dueno;
    }
    public int getNumero(){
        return this.numero;
    }
    public boolean getUsado(){
        return this.usado;
    }
}
