package maquinas;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class maquinasVoraz {
    
    //Se manejarán dos arrays, uno para los costos del mantenimiento y otro para los valores de la venta
    static ArrayList  <Integer> manteni = new ArrayList ();
    static ArrayList  <Integer> venta = new ArrayList ();

    //Se tienen 3 años al ser el tiempo que la empresa quiere planificar
    static int años=3,reinicio=4;
    //Costo de comprar una maquina nueva
    static int maquinaNueva=900;
    //Se manejará una bandera para saber cuando se compro una maquina nueva
    int conV=1,flag=0;
    static int costoMaquina=0,totalV=0;
    // maquinasAn manejará cada una de las antiguedades
    static ArrayList <Integer> maquinasAn = new ArrayList ();
    //Se mostrará en un array el menor costo de cada una de las máquinas
    static ArrayList <Integer> solucionV = new ArrayList ();


    public void analisisV(int ant){
        costoMaquina=0;
        System.out.println("\nMaquina "+conV);
        for(int i=0;i<años;i++){
            //Si la antigüedad ya es mayor a 4 años se debe reemplazar la maquina por una nueva
            if(ant>reinicio){
                costoMaquina+=maquinaNueva;
                ant=0;
                System.out.println("Se compra una nueva maquina en el año "+ (i+1));
            }
            //Se verifica si el costo del mantenimiento es menor al costo de vender y comprar una máquina nueva
            else if(manteni.get(ant-1)<=(maquinaNueva-venta.get(ant-1))){
                System.out.println("Se hace mantenimiento en el año "+(i+1));
                costoMaquina+=manteni.get(ant-1);
            }
            //Se verifica si sale más costoso hacerle mantenimiento a la maquina con respecto a venderla y comprar una nueva
            else if(manteni.get(ant-1)>(maquinaNueva-venta.get(ant-1))) {
                costoMaquina+=(maquinaNueva-venta.get(ant-1));
                System.out.println("Se vende en el año "+ (i+1));
            }
            ant++;
        }
        totalV+=costoMaquina;
        solucionV.add(costoMaquina);
        conV++;
    }

    //Se va analizando cada una de las maquinas con sus respectivas antigüedades
    public ArrayList voraz(ArrayList<Integer> m){
        for(int i=0;i<m.size();i++){
            this.analisisV(m.get(i));
        }
        return solucionV;    
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
            maquinasVoraz m1 = new maquinasVoraz();

            manteni.add(180);
            manteni.add(210);
            manteni.add(240);
            manteni.add(270);

            venta.add(600);
            venta.add(400);
            venta.add(300);
            venta.add(225);
            
            System.out.println("\n\tSolución voraz");
            System.out.println("\nEl gasto minimo para cada maquina es " + m1.voraz(maquinasAn));
            System.out.println("El costo minimo a gastar por todas las maquinas es: "+totalV);
        }else{
            años = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de años que desea planificar para la solución"));
            reinicio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de años a tolerar en antiguedad maxima"));
            maquinaNueva = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor de las maquinas nuevas"));
            for(int i = 1;i<=reinicio;i++){
                int costo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el costo del mantenimiento para el año "+i));
                manteni.add(costo);
                int ven = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el el valor de venta para la maquina de antiguedad "+i));
                venta.add(ven);
            }
            maquinasVoraz m1 = new maquinasVoraz();
            System.out.println("\n\tSolución voraz");
            System.out.println("\nEl gasto minimo para cada maquina es " + m1.voraz(maquinasAn));
            System.out.println("El costo minimo a gastar por todas las maquinas es: "+totalV);
        }
    }
} 