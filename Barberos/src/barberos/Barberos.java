/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barberos;

/**
 *
 * @author carlo
 */
public class Barberos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Ventana frame = new Ventana();
        frame.show();
        Barberia establecimiento = new Barberia(20,frame);

        Source_Cliente source = new Source_Cliente(2500, establecimiento);
        source.start();
    }
    
}