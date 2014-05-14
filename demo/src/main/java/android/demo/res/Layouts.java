package android.demo.res;

import android.demo.res.layout.Main;
import android.demo.res.layout.Other;

public class Layouts {

    /**
     * We simluate the Andorid layouts with the UiBinder, each screens needs a
     * class and a XML
     *
     * @return
     */
    public Main main() {
        return new Main();
    }

    public Other other() {
        return new Other();
    }

}
