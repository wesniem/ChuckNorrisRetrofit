package nyc.c4q.wesniemarcelin.chucknorrisretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import nyc.c4q.wesniemarcelin.chucknorrisretrofit.model.ChuckNorrisJoke;
import nyc.c4q.wesniemarcelin.chucknorrisretrofit.network.ChuckNorrisService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL ="https://api.chucknorris.io/";
    private static final String TAG ="YOO" ;
    TextView chuckTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chuckTV = (TextView) findViewById(R.id.my_tv);

        //Retrofit call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Service call
        ChuckNorrisService service = retrofit.create(ChuckNorrisService.class);

        //Get Response
        Call<ChuckNorrisJoke> call = service.getRandomJoke();
        call.enqueue(new Callback<ChuckNorrisJoke>() {
            @Override
            public void onResponse(Call<ChuckNorrisJoke> call, Response<ChuckNorrisJoke> response) {
                try {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "Success" + response.body().toString());
                        chuckTV.setText(response.body().getValue());
                    } else {
                        Log.d(TAG, "Error" + response.errorBody().string());
                    }
                }catch(IOException e){
                        Log.e(TAG,e.getMessage());
                    }
            }

            @Override
            public void onFailure(Call<ChuckNorrisJoke> call, Throwable t) {
                Log.d("Failure", t.getMessage());
            }
        });


    }
}
