package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.List;

import models.FavoriteProfes;

/**
 * Created by leo on 1/26/15.
 */
public class FavoriteProfesFragment extends Fragment {


    public FavoriteProfesFragment(){}

    public static FavoriteProfesFragment newInstance(){
        FavoriteProfesFragment favoriteProfesFragmentFragment = new FavoriteProfesFragment();
        return favoriteProfesFragmentFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<FavoriteProfes> favoriteProfes = FavoriteProfes.listAll(FavoriteProfes.class);
        Log.d("TAG_FAVORITE",favoriteProfes.toString());
    }
}
