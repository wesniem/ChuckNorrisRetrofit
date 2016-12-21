package nyc.c4q.wesniemarcelin.chucknorrisretrofit.network;

import nyc.c4q.wesniemarcelin.chucknorrisretrofit.model.ChuckNorrisJoke;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by wesniemarcelin on 12/21/16.
 */

public interface ChuckNorrisService {
    @GET("jokes/random")
    Call<ChuckNorrisJoke> getRandomJoke();
}
