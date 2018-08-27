package officeexport;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

public class OfficeExport {

   
    public static void main(String[] args) throws ParseException {
        BufferedReader lector= new BufferedReader(new InputStreamReader(System.in));
        File f = new File("/Users/yian/Desktop/Prueba_Excel.xlsx");
        LectorExcel lec = new LectorExcel(f);
        ArrayList<Despacho> prueba = lec.getTodos();
        MyStack test = new MyStack();
        int minimo = test.lowValue(prueba).getMontoTotal();
        int maximo = test.topValue(prueba).getMontoTotal();
        int rango = maximo - minimo;
        Secuenciacion sec = new Secuenciacion(prueba);
        
        for(int i = 0; i < prueba.size(); i++){
            System.out.println(sec.ordenarDespacho().size());
            System.out.println(sec.ordenarDespacho().get(i).getNombreArticulo());
            System.out.println("cuartil 1: " + (minimo + 0.25* rango));
            System.out.println("cuartil 2: " + (minimo + 0.5* rango));
            System.out.println("cuartil 3: " + (minimo + 0.725* rango));
            System.out.println(maximo - minimo);
            System.out.println(sec.ordenarDespacho().get(i).getMontoTotal());
            System.out.println();
        }
    }
    
}
