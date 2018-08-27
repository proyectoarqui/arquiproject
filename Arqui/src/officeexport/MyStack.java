
package officeexport;

import java.util.ArrayList;

public class MyStack implements Stack{
    @Override
    public void clear(){
        
    }
    @Override
    public void push(Despacho d){
        
    }
    @Override
    public Despacho pop(){
        return null;
    }
    @Override
    public Despacho topValue(ArrayList<Despacho> d){
        int mayor = 0;
        Despacho max = null;
        for(int i = 1; i <= d.size(); i++){
            if(d.get(i-1).getMontoTotal() >= mayor){
                mayor = d.get(i-1).getMontoTotal();
                max = d.get(i-1);
            }    
        }
        return max;
    }
    @Override
    public Despacho lowValue(ArrayList<Despacho> d){
        int menor = d.get(0).getMontoTotal();
        Despacho min = null;
        for(int i = 1; i <= d.size(); i++){
            if(d.get(i-1).getMontoTotal() <= menor){
                menor = d.get(i-1).getMontoTotal();
                min = d.get(i-1);
            }    
        }
        return min;
    }
    @Override
    public boolean isEmpty(){
     return false;   
    }
}
