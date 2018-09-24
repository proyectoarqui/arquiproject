package officeexport;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

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
        
        Arbol nuevo = new Arbol();
        nuevo.insertar(jiren.get(0));
        nuevo.insertar(jiren.get(1));
        nuevo.insertar(jiren.get(2));
        nuevo.insertar(jiren.get(3));
        System.out.println("impresion entreorden: " );
        nuevo.imprimirEntre();
        
        System.out.println("impresion entreorden: " );
        krilin.imprimirEntre();
        HSSFWorkbook workbook = new HSSFWorkbook();
        FileOutputStream fileOut = null;
        File file = null;
        String[] encabezado = new String[] {"Cuartil", "Codigo articulo", "Nombre articulo", "Cantidad Unidades", "Valor Unitario", "Monto total",
                                             "Estado"};
                
        try{
            HSSFSheet sheet = workbook.createSheet("Listado de Despachos");
            String rutaArchivo = System.getProperty("user.home") + "/ListadoDespachos.xls";
            File archivoXLS = new File(rutaArchivo);
            /*Si el archivo existe se elimina*/
            if(archivoXLS.exists()) archivoXLS.delete();
            /*Se crea el archivo*/
            archivoXLS.createNewFile();
            /*Se crea el libro de excel usando el objeto de tipo Workbook*/
            Workbook libro = new HSSFWorkbook();
            /*Se inicializa el flujo de datos con el archivo xls*/
            FileOutputStream archivo = new FileOutputStream(archivoXLS);       
            /*Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente*/
            Sheet hoja = libro.createSheet("Listados despachos");   
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            headerStyle.setFont(font);
            file = new File(rutaArchivo);
            if (file.exists()) {
                file.delete();
            }
            HSSFRow row = sheet.createRow(0);
            file.createNewFile();
            fileOut = new FileOutputStream(file);
            for (int i = 0; i < encabezado.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(encabezado[i]);
                cell.setCellStyle(headerStyle);
                sheet.autoSizeColumn(i);
            }
            for(int i = 0; i < jiren.size(); i++){
                krilin.insertar(jiren.get(i));
                HSSFRow datoRow = sheet.createRow(i+1);
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
                for(int j = 0; j <= 6; j++){
                    if(j == 0){
                        if(jiren.get(i).getMontoTotal() >= minimo &&  jiren.get(i).getMontoTotal()<= cuartil1){
                            datoRow.createCell(j).setCellValue(1);            
                        }
                        if(jiren.get(i).getMontoTotal() >= cuartil1 && jiren.get(i).getMontoTotal() <= cuartil2){
                            datoRow.createCell(j).setCellValue(2);
                        }
                        if(jiren.get(i).getMontoTotal() >= cuartil2 &&  jiren.get(i).getMontoTotal()<= cuartil3){
                            datoRow.createCell(j).setCellValue(3);
                        }
                        if(jiren.get(i).getMontoTotal() >= cuartil3 && jiren.get(i).getMontoTotal() <= maximo){
                            datoRow.createCell(j).setCellValue(4);
                        }
                    }
                    if(j == 1){
                        datoRow.createCell(j).setCellValue(jiren.get(i).getCodArticulo());
                    }
                    if(j == 2){
                        datoRow.createCell(j).setCellValue(jiren.get(i).getNombreArticulo());
                    }
                    if(j == 3){
                        datoRow.createCell(j).setCellValue(jiren.get(i).getCantidadUnidades());
                    }
                    if(j == 4){
                        datoRow.createCell(j).setCellValue(jiren.get(i).getValorUni());
                    }
                    if(j == 5){
                        datoRow.createCell(j).setCellValue(jiren.get(i).getMontoTotal());
                    }
                    if(j == 6){
                        datoRow.createCell(j).setCellValue(jiren.get(i).getEstado());
                    }
                
            }    
            }     
        }catch(Exception e){
            System.out.println("no se pudo crear el excel: " + e);
        } finally{
            if(fileOut != null){
                fileOut.close();
            }
        }
        Desktop.getDesktop().open(file);
    }
    
}
