/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filosofos;

import static filosofos.Interfaz.jButton1;
import static filosofos.Interfaz.jButton2;
import static filosofos.Interfaz.jButton3;
import static filosofos.Interfaz.jButton4;
import static filosofos.Interfaz.jButton5;
import static filosofos.Interfaz.btnTen1;
import static filosofos.Interfaz.btnTen2;
import static filosofos.Interfaz.btnTen3;
import static filosofos.Interfaz.btnTen4;
import static filosofos.Interfaz.btnTen5;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jairo Hernandez G
 */
public class Filosofo extends Thread{
    private final String nombre;
    private int estado = 0;
    private int pausa;
    private int turnos_espera=0;
    private  Tenedor tenedor_derecha;
    private  Tenedor tenedor_izquierda;
    //1 es comiendo, 0 es en espera
    public Filosofo(int estado, String nombre, int pausa, Tenedor tenedor_derecha, Tenedor tenedor_izquierda){
        this.estado = estado;
        this.nombre = nombre;
        this.pausa=pausa;
        this.tenedor_derecha =tenedor_derecha;
        this.tenedor_izquierda=tenedor_izquierda;
    }
    public int getEstado(){
        return this.estado;
    }
    public void setPausa(int pausa){
        this.pausa = pausa;
    }
    
    private void alimentar(){
        //System.out.println("dentro de alimentar");
        for(Tenedor x: Interfaz.Tenedores){
            //System.out.println("en el for");
            if(x.getNumero()==this.tenedor_derecha.getNumero()){
                if(!x.getUsado()){
                    x.setUsado(true);
                    x.setDueno(this.nombre);
                    this.tenedor_derecha.setUsado(true);
                    //pintarTenedor(Color.GREEN,x.getNumero());
                }else{
                    if (this.estado==1){
                        this.tenedor_derecha.setUsado(false);
                        if (x.getDueno().equals(this.nombre)){
                            x.setUsado(false);
                            x.setDueno("");
                        }   
                        //x.setUsado(false);
                        //pintarTenedor(Color.RED,x.getNumero());
                    }
                }
            }
            if(x.getNumero()==this.tenedor_izquierda.getNumero()){
                if(!x.getUsado()){
                    x.setUsado(true);
                    x.setDueno(this.nombre);
                    //si derecha e izquierda son true el men puede comer
                    this.tenedor_izquierda.setUsado(true);
                    //pintarTenedor(Color.GREEN,x.getNumero());
                    
                }else{
                    
                    if (this.estado==1){
                        this.tenedor_izquierda.setUsado(false);
                        if (x.getDueno().equals(this.nombre)){
                            x.setUsado(false);
                            x.setDueno("");
                        }
                        //x.setUsado(false);
                        //pintarTenedor(Color.RED,x.getNumero());
                    }
                    
                }
            }
        }
        if(!this.tenedor_derecha.getUsado() || !this.tenedor_izquierda.getUsado()){
            System.out.println(this.nombre+" tiene hambre! izq:" + Boolean.toString(this.tenedor_izquierda.getUsado())+" der:"+Boolean.toString(this.tenedor_derecha.getUsado()) );
            this.estado=0;
            turnos_espera++;
            mostrandoEjecucion(Color.YELLOW);            
        }
        if(this.tenedor_derecha.getUsado() && this.tenedor_izquierda.getUsado()){
            System.out.println(this.nombre+" ya esta comiendo izq:" + Boolean.toString(this.tenedor_izquierda.getUsado())+" der:"+Boolean.toString(this.tenedor_derecha.getUsado()) );
            this.estado=1;
            turnos_espera=0;
            mostrandoEjecucion(Color.GREEN);            
        }
        liberarTenedor();
    }
    //En caso de que pasen 6 turnos y el usuario no haya comido quiere decir que se entro en un deadlock
    //y como es un filosofo piensa que es mejor compartir y suelta su tenedor.
    public void liberarTenedor(){
        if (turnos_espera==6){
            for(Tenedor x: Interfaz.Tenedores){
                if(x.getNumero()==this.tenedor_derecha.getNumero()){
                    this.tenedor_derecha.setUsado(false);
                    if (x.getDueno().equals(this.nombre)){
                            x.setUsado(false);
                            x.setDueno("Ten"+String.valueOf(x.getNumero()));
                    }
                }
                if(x.getNumero()==this.tenedor_izquierda.getNumero()){
                    this.tenedor_izquierda.setUsado(false);
                    if (x.getDueno().equals(this.nombre)){
                            x.setUsado(false);
                            x.setDueno("Ten"+String.valueOf(x.getNumero()));
                    }
                }
            }
        }
    }
    
    public void pintarTenedor(Color colorin, Integer tenedor){
        switch (tenedor){
            case 1:
                btnTen1.setText("Ten1\n"+nombre);
                btnTen1.setBackground(colorin);
                break;
            case 2:
                btnTen2.setText("Ten2\n"+nombre);
                btnTen2.setBackground(colorin);
                break;
            case 3:
                btnTen3.setText("Ten3\n"+nombre);
                btnTen3.setBackground(colorin);
                break;
            case 4:
                btnTen4.setText("Ten4\n"+nombre);
                btnTen4.setBackground(colorin);
                break;
            case 5:
                btnTen5.setText("Ten5\n"+nombre);
                btnTen5.setBackground(colorin);
                break;
        }
    }
    public void mostrandoEjecucion(Color colorin){

        switch (this.nombre){
            case "Socrates":
                jButton1.setText(this.nombre);
                jButton1.setBackground(colorin);
                //btnTen1.setBackground(Color.GREEN);
                break;
            case "Aristoteles":
                jButton2.setText(this.nombre);
                jButton2.setBackground(colorin);
                break;
            case "Platon":
                jButton3.setText(this.nombre);
                jButton3.setBackground(colorin);
                break;
            case "Pitagoras":
                jButton4.setText(this.nombre);
                jButton4.setBackground(colorin);
                break;
            case "Goku":
                jButton5.setText(this.nombre);
                jButton5.setBackground(colorin);
                break;
        }
    }
    @Override
    public void run(){
        
        while(true){
            //System.out.println("corriendo "+this.nombre);
            try {
                //Thread.sleep(this.pausa);
                if (this.estado == 0){
                    //System.out.println(this.nombre+" tiene hambre! izq:" + Boolean.toString(this.tenedor_izquierda.getUsado())+" der:"+Boolean.toString(this.tenedor_derecha.getUsado()) );
                    alimentar();
                    //mostrandoEjecucion(Color.YELLOW);
                }else{
                    //mostrandoEjecucion(Color.GREEN);
                    //System.out.println(this.nombre+" ya esta comiendo izq:" + Boolean.toString(this.tenedor_izquierda.getUsado())+" der:"+Boolean.toString(this.tenedor_derecha.getUsado()) );
                    alimentar();
                }
                Thread.sleep(Interfaz.velocidadHilos);
                //Thread.sleep(this.pausa);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
