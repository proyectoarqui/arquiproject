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
        int minimo = test.lowValue(prueba).getMontoTotal();
        int maximo = test.topValue(prueba).getMontoTotal();
        int rango = maximo - minimo;
        Secuenciacion sec = new Secuenciacion(prueba);
        ArrayList<Despacho> des = sec.despachado(fechaInicio, fechaDespacho);
        ArrayList<Articulo> art = sec.mayorDespacho(fechaInicio, fechaDespacho);
        for(int j = 0; j < des.size(); j++){
            System.out.println(des.get(j).getNombreArticulo());
        }
        for(int i = 0; i < art.size(); i++){
            System.out.println(art.get(i).getNombreArticulo());
        }
        for(int i = 0; i < prueba.size(); i++){
            
            System.out.println("cuartil 1: " + (minimo + 0.25* rango));
            System.out.println("cuartil 2: " + (minimo + 0.5* rango));
            System.out.println("cuartil 3: " + (minimo + 0.725* rango));
            System.out.println(maximo - minimo);
            System.out.println(prueba.get(i).getMontoTotal());
            }
    }
    
}
