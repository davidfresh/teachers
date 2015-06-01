package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.melnykov.fab.FloatingActionButton;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import adapters.ParseAdapter;
import util.NavigationController;
import util.SharedPreferencesHelper;
import vision69.teachers.DetalleActivity;
import vision69.teachers.NewProfesorActivity;
import vision69.teachers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class busqueda_fragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener {

    private GridView busqueda;
    private SwipeRefreshLayout swipeRefreshLayout;
    public static busqueda_fragment newInstance() {
        busqueda_fragment busquedaFragment = new busqueda_fragment();
        return busquedaFragment;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_busqueda, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(busqueda_fragment.this);


        busqueda = (GridView)view.findViewById(R.id.busqueda);
        busqueda.setOnItemClickListener(busqueda_fragment.this);
        getprofesores(busqueda);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout_container);
        swipeRefreshLayout.setOnRefreshListener(busqueda_fragment.this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                NavigationController.navigateTo((ActionBarActivity) getActivity(), NewProfesorActivity.class, false);
                break;
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ParseObject parseObject = (ParseObject) parent.getAdapter().getItem(position);
        Intent detailActivity = new Intent(getActivity(), DetalleActivity.class);
        Bundle  detailBundle = new Bundle();
        detailBundle.putString("username", SharedPreferencesHelper.getUsername(getActivity()));
        detailBundle.putString("facebookId", SharedPreferencesHelper.getUserId(getActivity()));
        detailBundle.putString("profe_nombre", parseObject.getString("nombre"));
        detailBundle.putString("profe_materia", parseObject.getString("materia"));
        detailBundle.putString("profe_escuela", parseObject.getString("escuela"));
        detailActivity.putExtras(detailBundle);

        startActivity(detailActivity);

    }

    @Override
    public void onRefresh() {


        getprofesores(busqueda);
        swipeRefreshLayout.setRefreshing(false);

    }

    private void getprofesores(GridView busqueda) {
        ParseAdapter parseadapter = new ParseAdapter(getActivity(),new ParseQueryAdapter.QueryFactory<ParseObject>() {
            @Override
            public ParseQuery<ParseObject> create() {

                ParseQuery<ParseObject> query = new ParseQuery<>("profesor");
                query.include("usuario");
                query.orderByDescending("createdAt");
                return query;
            }
        });

        busqueda.setAdapter(parseadapter);
        parseadapter.loadObjects();
    }






}
