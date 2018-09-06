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
        
        int minimo = test.lowValue(jiren).getMontoTotal();
        int maximo = test.topValue(jiren).getMontoTotal();
        int rango = maximo - minimo;
        double cuartil1 = minimo + 0.25* rango;
        double cuartil2 = minimo + 0.5* rango;
        double cuartil3 = minimo + 0.75* rango;
        System.out.println(cuartil1);
        System.out.println(cuartil2);
        System.out.println(cuartil3);
        for(int j = 0; j < des.size(); j++){
            System.out.println(des.get(j).getIdTran() + ", " + des.get(j).getNombreArticulo()
                                + ", " + des.get(j).getMontoTotal());
        }
        for(int i = 0; i < jiren.size(); i++){
            if(jiren.get(i).getMontoTotal() >= minimo &&  jiren.get(i).getMontoTotal()<= cuartil1){
                System.out.println("1 ( " + minimo + "-" + cuartil1 +"), "+ jiren.get(i).getCodArticulo() + ", " +jiren.get(i).getNombreArticulo() + ", " + 
                                    jiren.get(i).getCantidadUnidades() + ", " + jiren.get(i).getValorUni() + ", " +
                                    jiren.get(i).getMontoTotal() + ", " + jiren.get(i).getEstado());
            }
            if(jiren.get(i).getMontoTotal() >= cuartil1 && jiren.get(i).getMontoTotal() <= cuartil2){
                System.out.println("2(" + cuartil1 + "-" + cuartil2 +"), "+ jiren.get(i).getCodArticulo() + ", " +jiren.get(i).getNombreArticulo() + ", " + 
                                    jiren.get(i).getCantidadUnidades() + ", " + jiren.get(i).getValorUni() + ", " +
                                    jiren.get(i).getMontoTotal() + ", " + jiren.get(i).getEstado());
            }
            if(jiren.get(i).getMontoTotal() >= cuartil2 &&  jiren.get(i).getMontoTotal()<= cuartil3){
                System.out.println("3(" + cuartil2 + "-" + cuartil3 +"), "+ jiren.get(i).getCodArticulo() + ", " +jiren.get(i).getNombreArticulo() + ", " + 
                                    jiren.get(i).getCantidadUnidades() + ", " + jiren.get(i).getValorUni() + ", " +
                                    jiren.get(i).getMontoTotal() + ", " + jiren.get(i).getEstado());
            }
            if(jiren.get(i).getMontoTotal() >= cuartil3 && jiren.get(i).getMontoTotal() <= maximo){
                System.out.println("4(" + cuartil3 + "-" + maximo +"), "+ jiren.get(i).getCodArticulo() + ", " +jiren.get(i).getNombreArticulo() + ", " + 
                                    jiren.get(i).getCantidadUnidades() + ", " + jiren.get(i).getValorUni() + ", " +
                                    jiren.get(i).getMontoTotal() + ", " + jiren.get(i).getEstado());
            }
            
            System.out.println(jiren.get(i).getNombreArticulo() + ", " + jiren.get(i).getMontoTotal());
        }
        
    }
    
}
