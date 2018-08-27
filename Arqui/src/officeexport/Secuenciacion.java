package officeexport;

import java.util.ArrayList;

public class Secuenciacion {
    
    private ArrayList<Despacho> todos = new ArrayList();
    
    public Secuenciacion(ArrayList todos){
        this.todos = todos;
    }
    
    public ArrayList<Despacho> ordenarDespacho(){
        ArrayList<Despacho> des = new ArrayList();
        int cont = 0;
        des.add(todos.get(0));
        int num = des.size() - 1;
        for(int i = 0; i < todos.size(); i++){
            
                if(todos.get(i).getCodArticulo() != des.get(num).getCodArticulo()){
                    des.add(todos.get(cont));
                }
                else{
                    des.get(num).setCantidadUnidades(des.get(num).getCantidadUnidades() + todos.get(i).getCantidadUnidades());
                    des.get(num).setMontoTotal(des.get(num).getMontoTotal() + todos.get(i).getMontoTotal());
                }
            
            cont++;
        }
        return des;
    }
}
