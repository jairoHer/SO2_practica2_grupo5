# Manual Tecnico practica 2 grupo 5


### Integrantes
| Carnet | Nombre |
| ------ | ------ |
| 201404423 | Jairo Pablo Hernández Guzmán |
| 201504042 | Julio Estuardo Gómez Alonzo  |
| 201503750 | Carlos Eduardo Carías Salan |

## Problema 1 (Filosofos)
Esta aplicacion contiene la solucion del siguiente problema

Había una vez 5 filósofos que vivían juntos, la vida de cada filósofo consiste básicamente en pensar y comer, la única comida que contribuida a sus esfuerzos pensantes era el espagueti, por lo que todas las noches se sentaban los 5 a cenar. En la cena, sentados los 5 filósofos en
una mesa redonda que tiene una fuente de espagueti, van a existir 5 platos uno para cada filósofo, van a existir 5 tenedores, uno al lado de cada plato (izquierda y derecha). Entonces cada que un filósofo quiera ir a comer irá al lugar asignado en la mesa, y usando los dos tenedores que se encuentran al lado de cada plato, los usará para comer el espagueti. Dos filósofos no pueden utilizar el mismo tenedor a la vez.

Para solucionar esto se creo la clase Filosofo la cual contendra todas las acciones que puede realizar un filosofo. Esta clase extiende de la clase Thread, esto para que cada uno se comporte como un proceso independiente.
```
public class Filosofo extends Thread
```
Tambien fue creada una clase **Tenedor**, esta almacenaba la informacion de quien la estaba utilizando, su posicion y si estaba disponible u ocupado
```
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
```

Cada filosofo poseia la siguiente informacion, la cual es utilizada para ver el estado del filosofo y los recursos que utiliza. El nombre indica almacena el nombre del filosofo, el estado indica si esta comiendo (1) o si esta esparando a comer (0), turnos de espera sirve para solucionar el deadlock, tenedor_derecha y tenedor_izquierda sirve para tener control de que tenedores esta utilizando el filosofo y pausa determina el tiempo del delay del proceso.
```
    private final String nombre;
    private int estado = 0;
    private int pausa;
    private int turnos_espera=0;
    private  Tenedor tenedor_derecha;
    private  Tenedor tenedor_izquierda;
```

El metodo run es el que inicia la ejecucion del filosofo, en su interior contiene un while que se ejecutara constantemente y se encaraga de que el filosofo tenga vida. Tambien contiene la llamada al metodo alimentar.
```
public void run(){
        
        while(true){
            try {
                alimentar();
                Thread.sleep(Interfaz.velocidadHilos);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
```
El metodo **alimentar** es el que se encarga de verificar si los tenedores que estan a los lados de cada filosofo esta ocupado o disponible para su uso. En caso de que encuentre al tenedor libre, este lo reserva para el filosofo. Tambien determina el estado del filosofo, siendo estado = 0 (hambriento) y estado = 1 (comiendo). Para que se pase del estado hambriento a comiendo el metodo verifica si el filosofo tienen dos tenedores asignados. El metodo tambien se encarga de liberar los tenendores utilizados por el filosofo, lo hace si encuentra que el filosofo se encuentra en un estado 1 (comiendo), indicando que ya comio, por lo que libera sus tenedores, haciendolos disponibles para que otro filosofo haga uso
de ellos.
```
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
```
### Partes de codigo concurrente y paralelo
Donde se ejecutan los procesos de forma simultanea es en **interfaz**, aqui se crean los hilos que ejecutaran las funciones de cada unos de los filosofos.

Aca se declaran cada uno de los hilos que se ejecutaran, cada uno representa a un filosofo, al momento de crear un hilo se le pasa a su constructor su estado inicial, nombre, velocidad de hilo, tenedor izquierdo y tenedor derecho.

Tenedores es donde esta almacenados los tenedores (recursos) los cuales seran compartidos por los 5 filosofos.

Aparte de los 5 filosofos hay un hilo extra llamado **RecursosSistema** que se encarga de la parte grafica del programa, este pinta los tenedores que estan siendo utilizado y coloca el nombre del filosfo que lo usa. Si el tenedor esta libre lo pinta de verde, si esta siendo utilizado lo pinta de azul.
```
    public static ArrayList<Tenedor> Tenedores=new ArrayList<Tenedor>();
    public static int velocidadHilos=250;
    //El filosofo 1 usa el tenedor 1 y 2(der izq)
    Thread filosofo1= new Filosofo(0,"Socrates",200,new Tenedor(false,1),new Tenedor(false,2));
    //El filosofo 2 usa el tenedor 2 y 3
    Thread filosofo2= new Filosofo(0,"Aristoteles",200,new Tenedor(false,2),new Tenedor(false,3));
    //El filosofo 3 usa el tenedor 3 y 4
    Thread filosofo3= new Filosofo(0,"Platon",200,new Tenedor(false,3),new Tenedor(false,4));
    //El filosofo 4 usa el tenendor 4 y 5
    Thread filosofo4= new Filosofo(0,"Pitagoras",200,new Tenedor(false,4),new Tenedor(false,5));
    //El filosofo 5 usa el tenedor 5 y 1
    Thread filosofo5= new Filosofo(0,"Goku",200,new Tenedor(false,5),new Tenedor(false,1));
    Thread RecursosSistema = new Recursos(150);
```
El metodo que inicia todos los procesos se llama **aComer** este inicia la ejecucion de todos los filosofos y el monitor de recursos de forma simultanea.
```
public void aComer(int pausa){
        
        RecursosSistema.start();
        filosofo1.start();
        filosofo2.start();
        filosofo3.start();
        filosofo4.start();
        filosofo5.start();
        
        
    }
```

### Comunicacion entre procesos y sincronizacion
Para la comunicacion entre procesos fue fundamental el arreglo de tenedores inicial, este simulaba los recursos disponible en el sistema, cada filosofo consultaba el mismo arreglo de recursos y se le asignaba un recurso en caso de estar disponible. Tambien al momento de liberar el recursos, la informacion quedaba almacenada en el objeto dentro del arreglo de recursos, permietndole volver a ser usado por otro filosofo.

### Situacion de deadlock y condiciones de carrera
Ocurria un deadlock cuando todos los filosofos utilizaban el tenedor derecho, eso hacian que se quedaran en una espera eterna a que alguien liberara un tenedor para poder comer, la solucion fue tener un contador para filosofo en la que si pasaban mas de 6 turnos y el aun no habia comido, el filosofo libera el tenedor que tiene a su disposicion para que alguien mas pueda comer.

## Variable o datos que eran necesario compartir entre procesos
Los datos necesarios a compartir son el arreglo de tenedores al que todos tenian acceso, en el estaban almacenados todos los tenedores de la mesa y se maracaban con usados o disponibles.


