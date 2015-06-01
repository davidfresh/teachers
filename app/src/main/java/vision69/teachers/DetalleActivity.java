package vision69.teachers;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import fragments.detalles_profes;

/**
 * Created by Davidfresh on 31/05/15.
 */
public class DetalleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_activity);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        getSupportFragmentManager().beginTransaction().replace(R.id.detalle_fragment_container, detalles_profes.newInstance(getIntent().getExtras())).commit();

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id) {
            case android.R.id.home:
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
