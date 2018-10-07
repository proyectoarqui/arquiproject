package officeexport;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OfficeExport {

   
    public static void main(String[] args) throws ParseException, IOException {
        
        BufferedReader lector= new BufferedReader(new InputStreamReader(System.in));
        //File f = new File("/Users/yian/Desktop/Prueba_Excel.xlsx");
        
        File f = new File("/Users/yian/Desktop/SergioStore_datos_legacy.xlsx");
        
        Prototype proto = new PrototypeDesp(new LectorExcel(f).getTodos());
        
        Listado sec = new Listado(proto.clone());
        System.out.println("ingresa fecha de inicio: dd-MM-yyyy");
        SimpleDateFormat dato = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaInicio = dato.parse(lector.readLine());
        System.out.println("ingresa fecha de despacho: dd-MM-yyyy");
        Date fechaDespacho = dato.parse(lector.readLine());
        ArrayList<Articulo> jiren = sec.listadoArticulos(fechaInicio, fechaDespacho);
                      
        int minimo = sec.minimoValor(fechaInicio, fechaDespacho);
        int maximo = sec.maximoValor(fechaInicio, fechaDespacho);
        
        MyStack test = new MyStack();
        test.clear();
        
        MyList quiz = new MyList();
        quiz.clear();
        
        for(int i = jiren.size() - 1; i >= 0; i--){
            test.push(jiren.get(i));
            quiz.insert(jiren.get(i));
        
        }
        for(int i = 0; i < jiren.size(); i++){
            }
        Exportar excel = new Exportar();
        Pantalla sop = new Pantalla();
        
        
        
        System.out.println("De que forma desea ver el archivo(segun el numero): ");
        System.out.println("1.- por pantalla");
        System.out.println("2.- por archivo excel");
        System.out.println("3.- por archivo de texto");
        System.out.println("4.- de las tres formas");
        int roll = Integer.parseInt(lector.readLine());
        switch(roll){
            case 1:
                if((sop.mostrarPantalla(jiren, minimo, maximo))){
                    System.out.println("se ve por pantalla");
                }else{
                    System.out.println("no se ve por pantalla");
                }
            break;
            
            case 2:
                if(excel.exportarExcel(jiren, minimo, maximo, test, quiz)){
                    System.out.println("se creo el archivo excel");
                }
                else{
                    System.out.println("no se creo el archivo excel");
                }
            break;
            
            case 3:
                
            break;
            
            case 4:
                if(excel.exportarExcel(jiren, minimo, maximo, test, quiz)){
                    System.out.println("se creo el archivo excel");
                }
                else{
                    System.out.println("no se creo el archivo excel");
                }
                if((sop.mostrarPantalla(jiren, minimo, maximo))){
                    System.out.println("se ve por pantalla");
                }else{
                    System.out.println("no se ve por pantalla");
                }
                
            break;    
        }
        
    }
    
}
