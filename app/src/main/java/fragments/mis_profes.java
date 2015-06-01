package fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import adapters.ParseAdapter;
import vision69.teachers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class mis_profes extends Fragment {


    public mis_profes() {
        // Required empty public constructor
    }

    public static mis_profes newInstace(){
        mis_profes misprofes = new mis_profes();
        return misprofes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mis_profes, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);


        GridView myBooksContainer = (GridView)view.findViewById(R.id.mis_profes_container);
        getMyBooks(myBooksContainer);
    }


    private void getMyBooks(GridView myprofesContainer){

        ParseAdapter ParseAdapter = new ParseAdapter(getActivity(),new ParseQueryAdapter.QueryFactory<ParseObject>() {
            @Override
            public ParseQuery<ParseObject> create() {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("profesor");
                query.whereEqualTo("usuario", ParseUser.getCurrentUser());
                query.orderByDescending("createdAt");
                return query;
            }
        });

        myprofesContainer.setAdapter(ParseAdapter);
        ParseAdapter.loadObjects();

    }



}
