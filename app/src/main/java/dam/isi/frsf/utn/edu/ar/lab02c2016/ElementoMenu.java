package dam.isi.frsf.utn.edu.ar.lab02c2016;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Luciano on 25/09/2016.
 */

public class ElementoMenu {

    private DecimalFormat f = new DecimalFormat("##.00");
    private Integer id;
    private String nombre;
    private Double precio;

    public ElementoMenu() {

    }
    public ElementoMenu(Integer i, String n, Double p) {
        this.setId(i);
        this.setNombre(n);
        this.setPrecio(p);
    }

    public ElementoMenu(Integer i, String n) {
        this(i,n,0.0);
        Random r = new Random();
        this.precio= (r.nextInt(3)+1)*((r.nextDouble()*100));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override public String toString() {
        return this.nombre + " $ (" + f.format(this.precio) + ")";
    }
}
