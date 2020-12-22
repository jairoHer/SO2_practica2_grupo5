# Manual técnico práctica 2, Problema  2
### Integrantes
| Carnet | Nombre |
| ------ | ------ |
| 201404423 | Jairo Pablo Hernández Guzmán |
| 201504042 | Julio Estuardo Gómez Alonzo  |
| 201503750 | Carlos Eduardo Carías Salan |

___

## Procesos concurrentes y/o paralelos

```
public void cambiando()throws InterruptedException{
        while(true){
            if(this.est.getllegada()>0 && this.est.getcajas()<20){
                this.est.agregarcajas(1);
                this.est.agregarllegada(-1);
            }
            if(this.est.getsalida()>0 && this.est.getcajas()>0){
                this.est.agregarcajas(-1);
                this.est.agregarsalida(-1);
            }
            
            Thread.sleep(100);
        }
    }
```
Descripción: Este proceso es el encargado de verificar que hay una persona lista para entregar una caja o lista para llevarse una caja, también hace el cambio de los valores en la cantidad de cajas y cantidad de personas esperando.

```
public void iniciar()throws InterruptedException{
        while(true){
            int valordado = (int) Math.floor(Math.random()*10);
            if(valordado>4){
                // Llega a dejar una caja
                System.out.println("Se llego a dejar una caja");
                llegada lleg= new llegada(this);
                lleg.start();
                Thread.sleep(1000);
            }else{
                // Llega a recoger una caja
                System.out.println("Se llego a recoger una caja");                
                salida sal= new salida(this);
                sal.start();
                Thread.sleep(1000);
                
            }
        }
    }
```
Descripción: Este proceso es el encargado de elegir o generar las personas que llegan a dejar cajas y a recogerlas, dicho proceso también se encarga de generar otros hilos para las animaciones de llegada y salida.

```
public void camino()throws InterruptedException{
        int tiempo=100;
        jLabel4.setText("|");
        Thread.sleep(tiempo);
        jLabel4.setText("");
        jLabel5.setText("|");
        Thread.sleep(tiempo);
        jLabel5.setText("");
        jLabel6.setText("|");
        Thread.sleep(tiempo);
        jLabel6.setText("");
        jLabel7.setText("|");
        Thread.sleep(tiempo);
        jLabel7.setText("");
        jLabel8.setText("|");
        Thread.sleep(tiempo);
        jLabel8.setText("");
        jLabel9.setText("|");
        Thread.sleep(tiempo);
        jLabel9.setText("");
        jLabel10.setText("|");
        Thread.sleep(tiempo);
        jLabel10.setText("");
        this.est.agregarllegada(1);
    }
```

Descripción: Este proceso era el encargado de hacer la animación de llegada de una persona que llega a dejar una caja.

```
 public void camino()throws InterruptedException{
        int tiempo=100;
        jLabel11.setText("|");
        Thread.sleep(tiempo);
        jLabel11.setText("");
        jLabel12.setText("|");
        Thread.sleep(tiempo);
        jLabel12.setText("");
        jLabel13.setText("|");
        Thread.sleep(tiempo);
        jLabel13.setText("");
        jLabel14.setText("|");
        Thread.sleep(tiempo);
        jLabel14.setText("");
        jLabel15.setText("|");
        Thread.sleep(tiempo);
        jLabel15.setText("");
        jLabel16.setText("|");
        Thread.sleep(tiempo);
        jLabel16.setText("");
        jLabel17.setText("|");
        Thread.sleep(tiempo);
        jLabel17.setText("");
        this.est.agregarsalida(1);
    }
```
Descripción: Este proceso era el encargado de hacer la animcación de llegada de una persona que llega a recoger una caja.

```
public void cambiando()throws InterruptedException{
        while(true){
            jTextField1.setText(String.valueOf(this.est.getllegada()));
            jTextField2.setText(String.valueOf(this.est.getsalida()));
            jTextField3.setText(String.valueOf(this.est.getcajas()));
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(false);
            jRadioButton6.setSelected(false);
            jRadioButton7.setSelected(false);
            jRadioButton8.setSelected(false);
            jRadioButton9.setSelected(false);
            jRadioButton10.setSelected(false);
            jRadioButton11.setSelected(false);
            jRadioButton12.setSelected(false);
            jRadioButton13.setSelected(false);
            jRadioButton14.setSelected(false);
            jRadioButton15.setSelected(false);
            jRadioButton16.setSelected(false);
            jRadioButton17.setSelected(false);
            jRadioButton18.setSelected(false);
            jRadioButton19.setSelected(false);
            jRadioButton20.setSelected(false);
            switch(this.est.getcajas()){
                case 20:
                   jRadioButton20.setSelected(true);
                case 19:
                   jRadioButton19.setSelected(true);
                case 18:
                   jRadioButton18.setSelected(true);
                case 17:
                   jRadioButton17.setSelected(true);
                case 16:
                   jRadioButton16.setSelected(true);
                case 15:
                   jRadioButton15.setSelected(true);
                case 14:
                   jRadioButton14.setSelected(true);
                case 13:
                   jRadioButton13.setSelected(true);
                case 12:
                   jRadioButton12.setSelected(true);
                case 11:
                   jRadioButton11.setSelected(true);
                case 10:
                   jRadioButton10.setSelected(true);
                case 9:
                   jRadioButton9.setSelected(true);
                case 8:
                   jRadioButton8.setSelected(true);
                case 7:
                   jRadioButton7.setSelected(true);
                case 6:
                   jRadioButton6.setSelected(true);
                case 5:
                   jRadioButton5.setSelected(true);
                case 4:
                   jRadioButton4.setSelected(true);
                case 3:
                   jRadioButton3.setSelected(true);
                case 2:
                   jRadioButton2.setSelected(true);
                case 1:
                   jRadioButton1.setSelected(true);
            }
            Thread.sleep(100);
        }
    }
```
Descripción: Este proceso se encargaba de imprimir las cantidades en los cuadros de texto y de mostrar la posición de las cajas en la estantería.

## Comunicación y sincronización entre procesos


```
public Estanteria(){
        this.listacajas= 0;
        this.dejar=0;
        this.recoger=0;
        this.caja=new Caja(this);
        this.caja.start();
        this.pintar=new Pintar(this);
        this.pintar.start();
    }
```
La comunicación y sincronización entre procesos se resolvió mediante el paso de parametros en los constructores de las clases que realizaban los distintos procesos, se le pasaba el apuntador de la clase Estantería a todo aquel proceso que necesitara de las variables globales a utilizar (cajas, llegadas, salidas, etc).


## Situacion de deadlock y condiciones de carrera


En este problema no ocurría ninguna situación de deadlock, pero si se podía dar una condición de carrera al momento de aumentar o disminuir la cantidad de personas que llegaban a dejar cajas, llegaban a recibir cajas o se modificaba el valor de las cajas totales en la estantería, la manera en la que se solucionó esto fue mediante un "ReentrantLock" que bloqueaba las porciones de codigo para que no se entrelazaran con otras cuando se realizaban operaciones que afectaran los valores globales del programa.


## Variable o datos que eran necesario compartir entre procesos


Era necesario compartir las variables que manejaban la cantidad total de personas que llegaban para recoger y retirar cajas y la cantidad de cajas en la estantería, para compartir estas variables se pasaba como parametro la instancia de la clase Estantería que contenía todos estos datos necesarios para las funciones independientes que hacía cada hilo.


