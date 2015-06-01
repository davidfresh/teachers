package adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import vision69.teachers.R;

/**
 * Created by Davidfresh on 25/05/15.
 */
public class ParseAdapter  extends ParseQueryAdapter<ParseObject> {




    public ParseAdapter(Context context, QueryFactory<ParseObject> queryFactory) {
        super(context, queryFactory);
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {


        ViewHolder viewHolder;
        if(v == null){
            v = View.inflate(getContext(), R.layout.profesor_card_view_layout,null);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.getNombreprofe().setText(object.getString("nombre"));
        viewHolder.getMateriaprofe().setText(object.getString("materia"));
        viewHolder.getEscuelaprofe().setText(object.getString("escuela"));




        return v;


    }

    private static class ViewHolder{
        private TextView nombreprofe;
        private TextView materiaprofe;
        private TextView escuelaprofe;
       ;


        public  ViewHolder(View container){

            nombreprofe = (TextView)container.findViewById(R.id.nombre_profe);
            materiaprofe = (TextView)container.findViewById(R.id.materia_profe);
            escuelaprofe = (TextView)container.findViewById(R.id.escuela_profe);


        }


        public TextView getNombreprofe() {
            return nombreprofe;
        }

        public TextView getMateriaprofe() {
            return materiaprofe;
        }

        public TextView getEscuelaprofe() {
            return escuelaprofe;
        }



    }


}
