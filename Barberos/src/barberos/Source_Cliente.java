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
public class Source_Cliente extends java.lang.Thread {
    int tiempo_entrada = 0;
    Barberia barberia;
    
    public Source_Cliente(int tiempo_entrada, Barberia barberia) {
        this.tiempo_entrada = tiempo_entrada;
        this.barberia = barberia;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(tiempo_entrada);
                Thread clienteN = new Thread(barberia);
                clienteN.start();
            } catch (InterruptedException e) {
                System.out.println("Exception error en Cliente run");
            }
        }
    }
}
