package fight.tecmry.com.redlive.Util;

import fight.tecmry.com.redlive.Bean.CountData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tecmry on 2017/8/25.
 */

public interface CountInterface
{
    @GET("/index")
    Call<CountData> getCount(@Query("key")String key,@Query("kws")String kws);
}
