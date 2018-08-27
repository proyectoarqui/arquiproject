package officeexport;

import java.util.ArrayList;

public interface Stack {
    public void clear();
    public void push(Despacho d);
    public Despacho pop();
    public Despacho topValue(ArrayList<Despacho> d);
    public Despacho lowValue(ArrayList<Despacho> d);
    public boolean isEmpty();
}
