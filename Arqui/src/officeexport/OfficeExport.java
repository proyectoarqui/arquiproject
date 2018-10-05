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
        //File f = new File("/Users/yian/Desktop/Prueba_Excel.xlsx");
        
        File f = new File("/Users/yian/Desktop/SergioStore_datos_legacy.xlsx");
        LectorExcel lec = new LectorExcel(f);
        ArrayList<Despacho> prueba = lec.getTodos();
        MyStack test = new MyStack();
        Secuenciacion sec = new Secuenciacion(prueba);
        ArrayList<Articulo> jiren = sec.listadoArticulos(fechaInicio, fechaDespacho);
        int minimo = sec.minimoValor(fechaInicio, fechaDespacho);
        int maximo = sec.maximoValor(fechaInicio, fechaDespacho);
        Prototype proto = new PrototypeDesp(lec.getTodos());
        System.out.print(proto.clone().get(0).getNombreArticulo());
        System.out.print(proto.clone().get(1).getNombreArticulo());
        System.out.print(proto.clone().get(2).getNombreArticulo());
        System.out.print(proto.clone().get(3).getNombreArticulo());
        System.out.print(proto.clone().get(4).getNombreArticulo());
        
        Exportar excel = new Exportar();
        if(excel.exportarExcel(jiren, minimo, maximo)){
            System.out.println("se creo el archivo excel");
        }
        else{
            System.out.println("no se creo el archivo excel");
        }
    }
    
}
