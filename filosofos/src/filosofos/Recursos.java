/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filosofos;


import static filosofos.Interfaz.btnTen1;
import static filosofos.Interfaz.btnTen2;
import static filosofos.Interfaz.btnTen3;
import static filosofos.Interfaz.btnTen4;
import static filosofos.Interfaz.btnTen5;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jairo Hernandez G
 */
public class Recursos extends Thread{
    private int pausa;
    
    public Recursos(int pausa){
        this.pausa=pausa;
    }
    
    @Override
    public void run(){
        while(true){
            
            try {
                    for(Tenedor x: Interfaz.Tenedores){
                        if (!x.getUsado()){
                            pintarTenedor(Color.GREEN,x.getNumero(),x.getDueno());
                        }else{
                            pintarTenedor(Color.BLUE,x.getNumero(),x.getDueno());
                        }
                }
                Thread.sleep(this.pausa);
            } catch (InterruptedException ex) {
                Logger.getLogger(Recursos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void pintarTenedor(Color colorin, Integer tenedor, String dueno){
        switch (tenedor){
            case 1:
                btnTen1.setText("Ten1 \n"+dueno);
                btnTen1.setBackground(colorin);
                break;
            case 2:
                btnTen2.setText("Ten2 \n"+dueno);
                btnTen2.setBackground(colorin);
                break;
            case 3:
                btnTen3.setText("Ten3 \n"+dueno);
                btnTen3.setBackground(colorin);
                break;
            case 4:
                btnTen4.setText("Ten4 \n"+dueno);
                btnTen4.setBackground(colorin);
                break;
            case 5:
                btnTen5.setText("Ten5 \n"+dueno);
                btnTen5.setBackground(colorin);
                break;
        }
    }
}
