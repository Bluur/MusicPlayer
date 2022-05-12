package Clases;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 * Clase encargada del Modelo Abstracto necesario para el JTable de la APP
 */
public class TablaCanciones extends AbstractTableModel {

    private ArrayList<Cancion> listaCanciones;
    private String[] nombres = {"Nombre", "Autor", "Duración"};

    public TablaCanciones() {
        listaCanciones = new ArrayList<>();
    }

    public TablaCanciones(ArrayList<Cancion> array) {
        listaCanciones = new ArrayList<>(array);
    }

    @Override
    public int getRowCount() {
        return listaCanciones.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        boolean entero = false;
        Integer duracion = -1;
        String aDevolver = "";
        switch (columnIndex) {
            case 0 -> {
                aDevolver = listaCanciones.get(rowIndex).getNombre();
            }
            case 1 -> {
                aDevolver = listaCanciones.get(rowIndex).getRuta();
            }
            case 2 -> {
                entero = true;
                duracion = (Integer) listaCanciones.get(rowIndex).getDuracion();
            }
        }

        return entero ? duracion : aDevolver;
    }

    /**
     * El resto de funciones son bastante Auto-Explicativas, pero esta es más
     * importante, es la encargada junto con isCellEditable de que se puedan
     * editar los campos de las dintintas canciones directamente desde el JTable.
     * @param value -> Objeto a cambiar (String)
     * @param rowIndex -> Fila 
     * @param columnIndex -> Columna
     */
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (isCellEditable(rowIndex, columnIndex)) {
            switch (columnIndex) {
                case 0 -> {
                    listaCanciones.get(rowIndex).setNombre((String) value);
                }
                case 1 -> {
                    listaCanciones.get(rowIndex).setRuta((String) value);
                }
                case 2 -> {
                    try {
                        listaCanciones.get(rowIndex).setDuracion(Integer.parseInt((String) value));
                    } catch (NumberFormatException e) {
                        System.out.println("Te he pillado :D");
                    }
                }
            }
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        return this.nombres[column];
    }

    public void añadirCancion(Cancion c) {
        listaCanciones.add(c);
    }

    public void eliminarCancion(int index) {
        listaCanciones.remove(index);
    }

    public ArrayList<Cancion> getArray() {
        return this.listaCanciones;
    }
}
