package models;

import com.orm.SugarRecord;

/**
 * Created by Davidfresh on 23/05/15.
 */
public class profesor extends SugarRecord<profesor> {


    String nombre;
    String materia;
    String escuela;

    public profesor(){

    }

    public  profesor(String nombre,String materia,String escuela){

        this.nombre = nombre;
        this.materia = materia;
        this.escuela = escuela;

    }

}
