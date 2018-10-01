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
        System.out.println("ingresa fecha de inicio: dd-MM-yyyy");
        SimpleDateFormat dato = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaInicio = dato.parse(lector.readLine());
        System.out.println("ingresa fecha de despacho: dd-MM-yyyy");
        Date fechaDespacho = dato.parse(lector.readLine());
        
        File f = new File("/Users/yian/Desktop/Prueba_Excel.xlsx");
        LectorExcel lec = new LectorExcel(f);
        ArrayList<Despacho> prueba = lec.getTodos();
        MyStack test = new MyStack();
        Secuenciacion sec = new Secuenciacion(prueba);
        ArrayList<Despacho> des = sec.despachado(fechaInicio, fechaDespacho);
        ArrayList<Articulo> jiren = sec.listadoArticulos(fechaInicio, fechaDespacho);
        Arbol krilin = new Arbol();
        
        int minimo = test.lowValue(jiren).getMontoTotal();
        int maximo = test.topValue(jiren).getMontoTotal();
        
        Exportar excel = new Exportar();
        if(excel.exportarExcel(jiren, minimo, maximo)){
            System.out.println("se creo el archivo excel");
        }
        else{
            System.out.println("no se creo el archivo excel");
        }
    }
    
}
