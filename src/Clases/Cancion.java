
package Clases;


/**
 * Clase que tiene los atributos de nuestras canciones que vamos a utilizar.
 * @author Migue
 */
public class Cancion {
    
    private String nombre;
    private String ruta;
    private int duracion;

    /**
     * 
     * @param nombre -> String con el nombre
     * @param ruta -> String con la ruta del archivo
     * @param duracion  -> Int con la duración de la canción
     */
    public Cancion(String nombre, String ruta, int duracion){
        if(nombre.isBlank()){
            throw new IllegalArgumentException("El nombre de la canción no puede estar vacío");
        }
        
        if(ruta.isBlank()){
            throw new IllegalArgumentException("El ruta no puede estar vacío");
        }
        
        if(duracion <= 0){
            throw new IllegalArgumentException("La duración debe ser > 0");
        }
        
        this.nombre = nombre;
        this.ruta = ruta;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre.isBlank()){
            throw new IllegalArgumentException("El nombre de la canción no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        if(ruta.isBlank()){
            throw new IllegalArgumentException("El ruta no puede estar vacío");
        }
        
        this.ruta = ruta;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        if(duracion <= 0){
            throw new IllegalArgumentException("La duración debe ser > 0");
        }
        
        this.duracion = duracion;
    }
}
