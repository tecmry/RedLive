package fight.tecmry.com.redlive.Util;

import fight.tecmry.com.redlive.Bean.ImageData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tecmry on 2017/8/24.
 */

public interface ImageInterface{
@GET("/HPImageArchive.aspx")
    Call<ImageData> getUrl(@Query("format")String format,@Query("idx")String idx,@Query("n")String n);
}