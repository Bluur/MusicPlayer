package funciones;

import Clases.Cancion;
import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class Archivos {
    /**
     * Función para leer playlist de tipo M3U
     * @param rutaPlaylist -> String con la ruta del archivo
     * @return ArrayList con las canciones presentes en el archivo
     */
    public static ArrayList<Cancion> leerPlaylistM3U(String rutaPlaylist){
        BufferedReader flujoEntrada = null;
        ArrayList<Cancion> canciones = new ArrayList<>();

        try{
            flujoEntrada = new BufferedReader(new FileReader(rutaPlaylist));
            while(flujoEntrada.ready()){
                if(flujoEntrada.readLine().equals("#EXTM3U")){
                    String linea = flujoEntrada.readLine();
                    String numeros = linea.substring(8, linea.indexOf(","));
                    if(!tieneLetras(numeros)){
                        int duracion = Integer.parseInt(numeros);
                        String nombre = linea.substring(linea.indexOf(",")+1);
                        String ruta = flujoEntrada.readLine();
                        canciones.add(new Cancion(nombre, ruta, duracion));
                    }
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally{
            if(flujoEntrada != null){
                try{
                    flujoEntrada.close();
                }catch(IOException e2){
                    System.out.println(e2.getMessage());
                }
            }
        }
        return canciones;
    }
    
    /**
     * Función para leer playlist de tipo PLS
     * @param rutaPlaylist -> String con la ruta del archivo
     * @return ArrayList con las canciones presentes en el archivo
     */
    public static ArrayList<Cancion> leerPlaylistPLS(String rutaPlaylist){
        BufferedReader flujoEntrada = null;
        ArrayList<Cancion> canciones = new ArrayList<>();
        String nombre = "";
        String ruta = "";
        int duracion = -1;

        try{
            flujoEntrada = new BufferedReader(new FileReader(rutaPlaylist));
            flujoEntrada.readLine();
            while(flujoEntrada.ready()){
                boolean continuar = true;
                for(int i=0; i < 3 && continuar; i++){
                    String linea = flujoEntrada.readLine();
                    
                    if(!linea.startsWith("Number")){
                        switch(i){
                            case 0 -> ruta = linea.substring(linea.indexOf("=")+1);
                            case 1 -> nombre = linea.substring(linea.indexOf("=")+1);
                            case 2 -> duracion = Integer.parseInt(linea.substring(linea.indexOf("=")+1));
                        }
                    }else{
                        continuar = false;
                        flujoEntrada.readLine();
                        flujoEntrada.readLine();
                    }
                }
                if(continuar){
                    Cancion nueva = new Cancion(nombre, ruta, duracion);
                    canciones.add(nueva);
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally{
            if(flujoEntrada != null){
                try{
                    flujoEntrada.close();
                }catch(IOException e2){
                    System.out.println(e2.getMessage());
                }
            }
        }
        return canciones;
    }
    /**
     * Función para leer playlists de tipo XPFS (XML)
     * @param rutaPlaylist -> String con la ruta de la playlist
     * @return ArrayList con todas las canciones que están presentes en
     * el archivo XPFS
     */
    public static ArrayList<Cancion> leerPlaylistXSPF(String rutaPlaylist){
        ArrayList<Cancion> canciones = new ArrayList<>();

        try{
            File aCambiar = new File(rutaPlaylist);
            //Document builders y documento
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(aCambiar);
            
            NodeList nodes = documento.getElementsByTagName("track");

            //Recorremos cada Track que tiene el archivo XPFS
            for(int i=0; i < nodes.getLength(); i++){
                //Sacamos el Track correspondiente con el índice del bucle
                //Creamos También las variables 
                Node actual = nodes.item(i);
                String title = "";
                String location = "";
                //Switch con el tipo de nodo para evitar errores
                switch(actual.getNodeType()){
                    //En caso de que sea un elemento nodo, obtenemos sus hijos 
                    case Node.ELEMENT_NODE -> {
                        NodeList interna = actual.getChildNodes();
                        //Recorremos todos los nodos internos
                        for(int j=0; j < interna.getLength(); j++){
                            Node actualInterno = interna.item(j);
                            switch(actualInterno.getNodeType()){
                                //Elementos Title y Location
                                case Node.ELEMENT_NODE -> {
                                    if(actualInterno.getNodeName().equals("title")){
                                        title = actualInterno.getTextContent();
                                    }else{
                                        location = actualInterno.getTextContent();
                                        location = location.substring(8);
                                    }
                                }
                            }
                        }
                    }
                }
                canciones.add(new Cancion(title, location, 5));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return canciones;
    }
    
    /**
     * Función para guardar una playlist de tipo M3U
     * @param lista -> ArrayList con las canciones a guardar
     * @param ruta -> Ruta donde crear el archivo
     * @param nombre -> Nombre del archivo
     * @return -> Boolean (True => Se ha guardado, False => No se ha guardado)
     */
    public static boolean guardarPlaylistM3U(ArrayList<Cancion> lista, String ruta, String nombre){
        BufferedWriter flujoSalida = null;
        boolean guardado = false;
        if(!nombre.endsWith(".m3u")){
            nombre += ".m3u";
        }
        File aGuardar = new File(ruta, nombre);
        try{
            flujoSalida = new BufferedWriter(new FileWriter(aGuardar));
            for(int i=0; i < lista.size(); i++){
                flujoSalida.write("#EXTM3U");
                flujoSalida.newLine();
                flujoSalida.write("#EXTINF:"+lista.get(i).getDuracion()+", "+lista.get(i).getNombre());
                flujoSalida.newLine();
                flujoSalida.write(lista.get(i).getRuta());
                flujoSalida.newLine();
            }
            guardado = true;
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally{
            if(flujoSalida != null){
                try{
                    flujoSalida.close();
                }catch(IOException e2){
                    System.out.println(e2.getMessage());
                }
            }
        }

        return guardado;
    }
    
    /**
     * Función para guardar una playlist de tipo PLS
     * @param lista -> ArrayList con las canciones a guardar
     * @param ruta -> Ruta donde crear el archivo
     * @param nombre -> Nombre del archivo
     * @return -> Boolean (True => Se ha guardado, False => No se ha guardado)
     */
    public static boolean guardarPlaylistPLS(ArrayList<Cancion> lista, String ruta, String nombre){
        BufferedWriter flujoSalida = null;
        boolean guardado = false;
        if(!nombre.endsWith(".pls")){
            nombre += ".pls";
        }
        File aGuardar = new File(ruta, nombre);
        try{
            flujoSalida = new BufferedWriter(new FileWriter(aGuardar));
            flujoSalida.write("[playlist]");
            for(int i=0; i < lista.size(); i++){
                int indice = i + 1;
                flujoSalida.newLine();
                flujoSalida.write("File"+indice+"="+lista.get(i).getRuta());
                flujoSalida.newLine();
                flujoSalida.write("Title"+indice+"="+lista.get(i).getNombre());
                flujoSalida.newLine();
                flujoSalida.write("Length"+indice+"="+lista.get(i).getDuracion());
            }
            flujoSalida.newLine();
            flujoSalida.write("NumberOfEntries="+lista.size());
            flujoSalida.newLine();
            flujoSalida.write("Version=2");
            guardado = true;
        }catch(IOException e){
            System.out.println(e);
        }finally{
            if(flujoSalida != null){
                try{
                    flujoSalida.close();
                }catch(IOException e2){
                    System.out.println(e2.getMessage());
                }
            }
        }

        return guardado;
    }
    
    /**
     * Función para guardar una playlist de tipo XPFS
     * @param lista -> ArrayList con las canciones a guardar
     * @param ruta -> Ruta donde crear el archivo
     * @param nombre -> Nombre del archivo
     * @return -> Boolean (True => Se ha guardado, False => No se ha guardado)
     */
    public static boolean guardarPlaylistXSPF(ArrayList<Cancion> lista, String ruta, String nombre){
        boolean guardado = false;
        if(!nombre.endsWith(".xspf")){
            nombre += ".xspf";
        }
        
        try{
            //Document builders y documento
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.newDocument();
            
            //Archivo en el que transformaremos el documento xml
            File aGuardar = new File(ruta, nombre);
            
            //Transformador que transformara el DOM al fichero
            Transformer transformador = TransformerFactory.newInstance().newTransformer();
            //Se cambian las propiedades del transformador
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            
            //Creamos un stream 
            StreamResult resultado = new StreamResult(aGuardar);
            
            //Obtenemos la raiz del documento
            DOMSource source = new DOMSource(documento);
            
            //Creamos el elemento raíz PLAYLIST y atributos
            Element raiz = documento.createElement("playlist");
            raiz.setAttribute("xmlns", "http://xspf.org/ns/0/");
            raiz.setAttribute("Version", "1");
            //Creamos el primer hijo de playlist TRACKS
            Element tracks = documento.createElement("trackList");
            //Creamos el primer hijo de tracks LOCATION, insertamos
            //la ruta de la playlist y lo añadimos al elemento tracks
            Element location1 = documento.createElement("location");
            location1.setTextContent("File:///"+ruta);
            tracks.appendChild(location1);
            /*
            Recorremos todo el array de canciones extrayendo la información
            y añadiendolo todo a sus respectivos nodos
            */
            for(int i = 0; i < lista.size(); i++){
                Element track = documento.createElement("track");
                Element title = documento.createElement("title");
                Element location2 = documento.createElement("location");
                title.setTextContent(lista.get(i).getNombre());
                location2.setTextContent("File:///"+lista.get(i).getRuta());
                track.appendChild(title);
                track.appendChild(location2);
                tracks.appendChild(track);
            }
            raiz.appendChild(tracks);
            documento.appendChild(raiz);
            
            transformador.transform(source, resultado);
            guardado = true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return guardado;
    }
    
    /**
     * Función que comprueba si una String tiene letras dentro 
     * (Para validar la duración obtenida)
     * @param cadena -> String a comprobar
     * @return -> Boolean (True => Contiene letras, False => No contiene letras)
     */
    private static boolean tieneLetras(String cadena){
        char[] cadenaCaracteres = cadena.toCharArray();
        boolean tieneLetras = false;
        for(char c:cadenaCaracteres){
            if(!Character.isDigit(c)){
                tieneLetras = true;
            }
        }
        return tieneLetras;
    }

}
