package maquinas;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class maquinasDinamicaPro {

     static int gastadoA=0,gastadoB=0,auxA=0,auxB=0,antiguedadIngresada=0;
    
    //Se manejarán dos arrays, uno para los costos del mantenimiento y otro para los valores de la venta
    static ArrayList  <Integer> manteni = new ArrayList ();
    static ArrayList  <Integer> venta = new ArrayList ();
    //int manteni[] = {0,180,210,240,270,900}; 
    //int venta[] =   {0,300,500,600,675,900};
   
    //Se tienen 3 años al ser el tiempo que la empresa quiere planificar
    static int años=3,reinicio=4;
    //Costo de comprar una maquina nueva
    static int maquinaNueva=900;
    static int costoMaquina=0,totalD=0;
    // maquinasAn manejará cada una de las antiguedades
    static ArrayList <Integer> maquinasAn = new ArrayList ();
    //Se mostrará en un array el menor costo de cada una de las máquinas
    static ArrayList <Integer> solucionD = new ArrayList ();
    
    //Funcion basica para retornar el minimo entre dos numeros
    public int mini(int a,int b){
        if(a<b){
            return a;
        }else{
            return b;
        }
    }
    

    // Se tendrá una variable ant para manejar la antiguedad de la maquina y t para el tiempo que va transcurriendo desde el analisis
    public int analisisD(int ant,int t){

        //Se verifica si la máquina ya cumplio su ciclo de vida para cambiarla por una nueva
        if(ant>reinicio){
            //Se resetea la antigüedad de la máquina
            gastadoA=manteni.get(ant-1)+analisisD(1,t+1);
            gastadoB=venta.get(ant-1)+analisisD(1,t+1);
            return mini(gastadoA,gastadoB);
        }

        //Se tiene el cuenta el tiempo a planificar, partiendo desde t=1 hasta llegar al año que se quiere planificar antes del que se quiere planificar
        if(t<=años-1){

            //Se va buscando por una "rama" hasta llegar a los años a planificar e ir retornando el menor costo y saber cual es la mejor opcion en ese año
            gastadoA=manteni.get(ant-1)+ analisisD(ant+1,t+1);

            //Se guarda el costo únicamente cuando calcule todos los años y llegue primer caso recursivo
            //auxA contendrá el costo menor calculado si se le hace mantenimiento en el año 1 y auxB contendrá el costo menor al hacer venta en el año 1

            if(ant==this.antiguedadIngresada && t==1){
                auxA=gastadoA;   
            }
            //Se va buscando por la otra "rama", reseteando la antiguedad hasta llegar a los años a planificar e ir retornando el menor costo y saber cual es la mejor opcion en ese año
            //Se resetea la antigüedad al vender la máquina
            gastadoB=venta.get(ant-1)+ analisisD(1,t+1);
            if(ant==this.antiguedadIngresada && t==1){
                auxB=gastadoB;   
            }
            return mini(gastadoA,gastadoB);
        }
        //Apartir del último año se empieza a retornar el menor costo entre hacer mantenimiento o vender
        return mini(manteni.get(ant-1),venta.get(ant-1));
    }
    

    //Se va analizando cada una de las maquinas con sus respectivas antigüedades
    public ArrayList dinamica(ArrayList m){
        for(int i=0;i<m.size();i++){
            //Se tiene guarda en una variable la antigüedad de la máquina para poder consultarla en futuras recursiones
            antiguedadIngresada=(Integer)m.get(i);
            analisisD(antiguedadIngresada,1);
            totalD+=(mini(auxA,auxB));
            solucionD.add(mini(auxA,auxB));
        }
        return solucionD;
    } 
    
    public static void main(String[] args) {
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de maquinas"));
        for(int i = 1; i<=cantidad;i++){
            int antiguedad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la antiguedad de la maquina "+i));
            maquinasAn.add(antiguedad);
        }

        //La solución extendida es cambiar los parametros base, cada uno de los valores de mantenimiento, venta y de una maquina nueva, así como los años a planificar
        String ext = JOptionPane.showInputDialog("Desea la solución extendida? Escriba si o no");
        if(ext.equals("no")){
            maquinasDinamicaPro m1 = new maquinasDinamicaPro();

            manteni.add(180);
            manteni.add(210);
            manteni.add(240);
            manteni.add(270);
            manteni.add(900);

            venta.add(300);
            venta.add(500);
            venta.add(600);
            venta.add(675);
            venta.add(900);
            
        System.out.println("\n\tSolución dinamica");
        System.out.println("\nEl gasto minimo para cada maquina es " + m1.dinamica(maquinasAn));
        System.out.println("El costo minimo a gastar por todas las maquinas es: "+totalD);
        }else{
            años = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de años que desea para la solución"));
            reinicio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de años a tolerar en antiguedad maxima"));
            maquinaNueva = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor al comprar una nueva maquina"));

            for(int i = 1;i<=reinicio;i++){
                int costo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el costo del mantenimiento para el año "+i));
                manteni.add(costo);
                int ven = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el el valor de venta para la maquina de antiguedad "+i));
                venta.add(maquinaNueva-ven);
            }
            manteni.add(maquinaNueva);
            venta.add(maquinaNueva);
            maquinasDinamicaPro m1 = new maquinasDinamicaPro();
            System.out.println("\n\tSolución dinamica");
            System.out.println("\nEl gasto minimo para cada maquina es " + m1.dinamica(maquinasAn));
            System.out.println("El costo minimo a gastar por todas las maquinas es: "+totalD);
        }
    }
    
}
