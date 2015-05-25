package fragments;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import vision69.teachers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class new_profesor_fragment extends Fragment {

    private EditText nombreedit, materiaedit, escuedit;
    //private FloatingActionButton fbAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.new_profesor_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nombreedit = (EditText) view.findViewById(R.id.nombre_edit);
        materiaedit = (EditText) view.findViewById(R.id.materia_edit);
        escuedit = (EditText) view.findViewById(R.id.escue_edit);
        // fbAdd = (FloatingActionButton) view.findViewById(R.id.add_profe);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_profesor, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_publish:
                //Toast.makeText(getActivity(), "SI funciona!", Toast.LENGTH_SHORT).show();
                saveprofesor(nombreedit.getText().toString(), materiaedit.getText().toString(), escuedit.getText().toString());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

/*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_profe:
                saveprofesor(nombreedit.getText().toString(), materiaedit.getText().toString(), escuedit.getText().toString());
                break;
        }
    }*/

    private void saveprofesor(String profeNombre, String profeMateria, String profeEscuela) {
        if (!TextUtils.isEmpty(profeNombre) && !TextUtils.isEmpty(profeMateria) && !TextUtils.isEmpty(profeEscuela)) {
            uploadprofesor(profeNombre, profeMateria, profeEscuela);
        }


    }

    private void uploadprofesor(final String profeNombre, final String profeMateria, final String profeEscuela) {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());

        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Guardando Profesor...");
        progressDialog.show();


        final ParseObject parseObject = new ParseObject("profesor");
        parseObject.put("nombre", profeNombre);
        parseObject.put("materia", profeMateria);
        parseObject.put("escuela", profeEscuela);
        parseObject.put("usuario", ParseUser.getCurrentUser());
        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null)
                    e.printStackTrace();
                progressDialog.dismiss();
                getActivity().finish();
            }
        });



    }
}
