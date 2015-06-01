package fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.internal.ImageRequest;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.net.URISyntaxException;
import adapters.ParseAdapterdetalles;
import de.hdodenhof.circleimageview.CircleImageView;
import vision69.teachers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class detalles_profes extends Fragment {

    private GridView comtarios;
    private CircleImageView userImage,coment_img;
    private TextView username,nombreprofe,materiaprofe,escuelaprofe,Calificacion;
    private RatingBar ratingBar;
    //private Button calif;
    private EditText com;

    public static detalles_profes newInstance(Bundle args){
        detalles_profes detallesProfes = new detalles_profes();
        detallesProfes.setArguments(args);
        return detallesProfes;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalles_profes, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        this.userImage = (CircleImageView)view.findViewById(R.id.detalle_user_image);
        this.username = (TextView)view.findViewById(R.id.detalle_username);
        this.nombreprofe = (TextView)view.findViewById(R.id.detalle_profe_nombre);
        this.materiaprofe = (TextView)view.findViewById(R.id.detalle_profe_materia);
        this.escuelaprofe = (TextView)view.findViewById(R.id.detalle_profe_escuela);
        this.ratingBar = (RatingBar)view.findViewById(R.id.califica_profe);
        Calificacion = (TextView)view.findViewById(R.id.calificacion);
        com = (EditText) view.findViewById(R.id.comenta);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                float resul = rating + rating / rating;

                Calificacion.setText(String.valueOf(resul));

            }
        });

        comtarios = (GridView)view.findViewById(R.id.comentarios);
         //comtarios.setOnItemClickListener(busqueda_fragment.this);
        getcalif(comtarios);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search_bar:
                savecalf(com.getText().toString());
                return true;

        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }



    public void savecalf(String comentario) {
        if ( !TextUtils.isEmpty(comentario )) {
            uploadcalif(comentario);
        }


    }





    private void uploadcalif(final  String comentario ) {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());

        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Comentando...");
        progressDialog.show();

        //ParseObject profesores =  ParseObject("profesor");

            ParseObject comentar = new ParseObject("profesor");
            comentar.put("comentario", comentario);
            comentar.put("usuarios", ParseUser.getCurrentUser());
            //comentar.put("profesores", profesores);

        comentar.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null)
                    e.printStackTrace();
                progressDialog.dismiss();
                    getActivity().finish();
                }
            });
    }




    @Override
    public void onResume() {
        super.onResume();

        Log.d("TAG_OBJ", "" + getArguments().getString("username"));
        setProfileImage(this.userImage, getArguments().getString("facebookId"));
        //setProfileImage(this.coment_img, getArguments().getString("facebookId"));
        this.username.setText(getArguments().getString("username"));
        this.nombreprofe.setText(getArguments().getString("profe_nombre"));
        this.materiaprofe.setText(getArguments().getString("profe_materia"));
        this.escuelaprofe.setText(getArguments().getString("profe_escuela"));

    }




    private void setProfileImage(final CircleImageView profileImage,String imageUrl){
        try {
            Picasso.with(getActivity())
                    .load(ImageRequest.getProfilePictureUrl(imageUrl, 180, 180).toString())
                    .noFade()
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            profileImage.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }



    private void getcalif(final GridView comtarios) {
        ParseAdapterdetalles parseadapter = new ParseAdapterdetalles(getActivity(),new ParseQueryAdapter.QueryFactory<ParseObject>() {
            @Override
            public ParseQuery<ParseObject> create() {

                ParseQuery<ParseObject> query = new ParseQuery<>("comentarios");
                //query.include("usuarios");
                //query.whereEqualTo("usuarios", ParseUser.getCurrentUser());
                query.orderByDescending("createdAt");
                return query;
            }

        });

        comtarios.setAdapter(parseadapter);
        parseadapter.loadObjects();
    }

    /*private void saveFavorite(String nombre, String materia, String escuela){
        FavoriteProfes favoriteProfes = new FavoriteProfes(nombre,materia,escuela);
        favoriteProfes.save();
    }*/


}
