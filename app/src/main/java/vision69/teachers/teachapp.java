package vision69.teachers;

import com.orm.SugarApp;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;

/**
 * Created by Davidfresh on 20/05/15.
 */
public class teachapp extends SugarApp {

    // Inicializamos la aplicacion con Parse.
    // Application ID
    //Client Key
    //Inicializamos Sdk de Facebook con el Id de la aplicacion.

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "", "");
        ParseFacebookUtils.initialize("");
    }
}
