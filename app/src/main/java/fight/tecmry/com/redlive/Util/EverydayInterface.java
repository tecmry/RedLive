package fight.tecmry.com.redlive.Util;

import fight.tecmry.com.redlive.Bean.EverydaySentenceData;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tecmry on 2017/8/24.
 */

public interface EverydayInterface
{
    @GET("/dsapi")
    Call<EverydaySentenceData> getSentence();
}
