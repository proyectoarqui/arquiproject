package officeexport;

import java.util.ArrayList;
import java.util.Date;

public class Secuenciacion {
    
    private ArrayList<Despacho> todos = new ArrayList();
    public Secuenciacion(ArrayList todos){
        this.todos = todos;
        
    }
    
    public ArrayList<Despacho> despachado(Date fechaInicio, Date fechaFin){
        ArrayList<Despacho> des = new ArrayList();
        for(int i = 0; i < todos.size(); i++){
            if(todos.get(i).getEstado().equals("DESP")){
                if(todos.get(i).getFechaSol().compareTo(fechaInicio) >= 0 && 
                   todos.get(i).getFechaDesp().compareTo(fechaFin) <= 0){
                    des.add(todos.get(i));
                }
            }
        }
        return des;
    }
    
    public ArrayList<Articulo> mayorDespacho(Date fechaInicio, Date fechaFin){
        ArrayList<Despacho> des = despachado(fechaInicio, fechaFin);
        ArrayList<Articulo> art = new ArrayList();
        int num = todos.size() - 1;
        Articulo tic = new Articulo(des.get(0).getCodArticulo(), des.get(0).getNombreArticulo(), 
                                    des.get(0).getValorUni(), des.get(0).getCantidadUnidades(),
                                    des.get(0).getMontoTotal(), des.get(0).getEstado(), des.get(0).getFechaDesp());
        art.add(tic);
        
        try{
        for(int i = 1; i < todos.size(); i++){
            for(int j = 0; j < art.size();){
                if(art.get(j).getCodArticulo() != todos.get(i).getCodArticulo() ){
                    tic = new Articulo(todos.get(i).getCodArticulo(), todos.get(i).getNombreArticulo(), 
                                        todos.get(i).getValorUni(), todos.get(i).getCantidadUnidades(),
                                        todos.get(i).getMontoTotal(), todos.get(i).getEstado(), todos.get(i).getFechaDesp());
                    art.add(tic);
                }    
            }
        }
        } catch(Exception e){
            System.out.println("ocurrio un error" + e);
        }
        return art;
    }
}
