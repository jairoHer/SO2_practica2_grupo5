# Manual Tecnico practica 2 grupo 5


### Integrantes
| Carnet | Nombre |
| ------ | ------ |
| 201404423 | Jairo Pablo Hernández Guzmán |
| 201504042 | Julio Estuardo Gómez Alonzo  |
| 201503750 | Carlos Eduardo Carías Salan |

## Problema 3 (Barberos)

Existe una barbería en donde existen 2 barberos que la atienden y cortan el cabello a los
clientes que llegan y cuando no hay ninguno, se ponen a dormir. Los barberos tienen una
silla para cortar el cabello que es donde atiende a un cliente y una sala de espera con 20
sillas en donde pueden sentarse los clientes que llegan mientras esperan. Cuando un
barbero termina de cortar el cabello a un cliente, regresa a la sala de espera a ver si hay
personas esperando, si las hay trae consigo a una persona para cortarle el cabello. Si no
hay clientes esperando, se pone a dormir en la silla para cortar cabello.

Cada cliente que llega a la barbería observa lo que los barberos están haciendo. Si algún
barbero se encuentra durmiendo, el cliente lo despierta y se sienta en la silla para cortar el
cabello.Si los barberos se encuentran ocupados, entonces el cliente se coloca en una silla
de la sala de espera. Si no hay sillas disponibles, entonces el cliente se va del lugar.
```
public class Barbero implements Runnable

 @Override
    public void run() {
        try {
            this.frame.EstadoBarbero(2, num_barbero);
            this.cortando_pelo = true;
            Thread.sleep(5000); //Proceso de cortar el pelo
            this.frame.EstadoBarbero(3, num_barbero);
            this.acabarCorte(this.num_barbero);
            Thread.sleep(1000); //Proceso de cortar el pelo
            this.frame.EstadoBarbero(1, num_barbero);
            Thread.sleep(1000); //Proceso de cortar el pelo
            this.cortando_pelo = false;
        } catch (InterruptedException e) {
            System.out.println("Error Exception en Barbero run... :(");
        }
    }
```
Este es el metodo de thread de los barberos, con este metodo se simula el proceso de que un barbero corte el pelo, tenemos al principio un thread sleep de 5 segundos que simula el tiempo de un barbero cortando el pelo.
Luego de eso tenemos que acaba el corte y cambia el estado del barbero en la interfaz grafica, y con unos threads mas  simulamos el tiempo en el que se va el cliente.

```
public class Barberia implements Runnable{
@Override
    public void run() {
        this.clientes++;
        int cliente = this.clientes;
        if (sillas_ocupadas < sillas_espera) {
            System.out.println(">_Cliente No: "+cliente+" entro al establecimiento");
            sillas_ocupadas++;
            this.frame.NumFila(sillas_ocupadas);
            System.out.println(">_Cliente No: "+cliente+" se sienta en la silla de espera");
            
            while(this.barbero1.silla_barbero && this.barbero2.silla_barbero) {
                
            }
            sillas_ocupadas--;
            this.frame.NumFila(sillas_ocupadas);
            
            pelo_cortado = false;
            if(!this.barbero1.silla_barbero){
                this.frame.EstadoSilla(1, 1, cliente);
                this.barbero1.silla_barbero = true;
                if(this.barbero1.barbero_durmiendo) {
                    System.out.println(">_Cliente No: "+cliente+" despierta al barbero");
                    try {
                        this.barbero1.despertar_barbero();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println(">_Cliente No:"+cliente+" listo para que le corte el pelo el barbero 1");
                Thread corte_barbero1 = new Thread(barbero1);
                corte_barbero1.start();
                try {
                    corte_barbero1.join();
                    this.frame.EstadoBarbero(1,1);
                    this.frame.EstadoSilla(2, 1, cliente);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(!this.barbero2.silla_barbero){
                this.frame.EstadoSilla(1, 2, cliente);
                this.barbero2.silla_barbero = true;
                if(this.barbero2.barbero_durmiendo) {
                    System.out.println(">_Cliente No: "+cliente+" despierta al barbero");
                    try {
                        this.barbero2.despertar_barbero();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println(">_Cliente No:"+cliente+" listo para que le corte el pelo el barbero 2");
                Thread corte_barbero2 = new Thread(barbero2);
                corte_barbero2.start();
                try {
                    corte_barbero2.join();
                    this.frame.EstadoBarbero(1,2);
                    this.frame.EstadoSilla(2, 2, cliente);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println(">_Cliente No: "+cliente+" se va con el pelo cortado! :D");
        } else {
            System.out.println(">_Cliente No: "+cliente+" no pudo entrar y se fue a otro establecimiento :(");
        }
    }   
```
En este codigo se simula la entrada de los clientes a la barberia y el proceso de esperar en la cola, sentarse en la silla, despertar al barbero etc. Este es un hilo que se va generando por cada cliente que ingrese a la barberia

### Partes de codigo concurrente y paralelo
La parte simultanea se genera en la clase de GenerarCliente que cada vez que entra un cliente se genera un hilo paralelo a todos los demas clientes en donde cada cliente tiene diferentes situaciones.

La concurrencia ocurre cada vez que los clientes quieren entrar a cortarse el pelo donde el hilo del barbero se ve afectado por cada cliente que entra y entra en el proceso de cortar el pelo solo 1 a la vez.

### Comunicacion entre procesos y sincronizacion
Para la comunicacion entre procesos se creo una sola clase con su constructor en donde los objetos comparten sus variables y se comunican por medio de sus atributos

### Situacion de deadlock y condiciones de carrera
Ocurria un deadlock cuando todos los barberos estan ocupados y los clientes se quedan esperando.

## Variable o datos que eran necesario compartir entre procesos
Los datos necesarios a compartir son el numero de cliente, el estado del cliente y el estado del barbero ya que si el barbero esta ocupado se le debe comunicar eso a los clientes para que esperen.


