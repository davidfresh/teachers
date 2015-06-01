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
public class ParseAdapterdetalles extends ParseQueryAdapter<ParseObject> {




    public ParseAdapterdetalles(Context context, QueryFactory<ParseObject> queryFactory) {
        super(context, queryFactory);
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {


        ViewHolder viewHolder;
        if(v == null){
            v = View.inflate(getContext(), R.layout.detalles_card_view_layout,null);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.getComentario().setText(object.getString("comentario"));
        viewHolder.getCalifica().setText(object.getString("calificacion"));



        return v;


    }

    private static class ViewHolder{
        private TextView comentariosprofe;
        private  TextView calificacionprofe;

        public  ViewHolder(View container){

            comentariosprofe = (TextView)container.findViewById(R.id.comenta_profe);
            calificacionprofe = (TextView)container.findViewById(R.id.califica_profe);


        }


        public TextView getComentario() {
            return comentariosprofe;
        }

        public TextView getCalifica() {
            return calificacionprofe;
        }



    }


}
