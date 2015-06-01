package models;

import com.orm.SugarRecord;

/**
 * Created by leo on 1/26/15.
 */
public class FavoriteProfes extends SugarRecord<FavoriteProfes> {

    String nombre;
    String materia;
    String escuela;




    public  FavoriteProfes(String nombre,String materia,String escuela){

        this.nombre = nombre;
        this.materia = materia;
        this.escuela = escuela;

    }

    @Override
    public String toString() {
        return "FavoriteProfes{" +
                "nombre'" + nombre + '\'' +
                ", materia='" + materia + '\'' +
                ", escuela='" + escuela + '\'' +
                '}';
    }
}
