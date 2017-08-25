package fight.tecmry.com.redlive.Bean;

import com.google.gson.Gson;

/**
 * Created by Tecmry on 2017/8/24.
 */

public class WithMeData
{

    /**
     * _lctype : -1
     * _lctext : dfhdjs
     */

    private int _lctype;
    private String _lctext;

    public static WithMeData objectFromData(String str) {

        return new Gson().fromJson(str, WithMeData.class);
    }

    public int get_lctype() {
        return _lctype;
    }

    public void set_lctype(int _lctype) {
        this._lctype = _lctype;
    }

    public String get_lctext() {
        return _lctext;
    }

    public void set_lctext(String _lctext) {
        this._lctext = _lctext;
    }
}
