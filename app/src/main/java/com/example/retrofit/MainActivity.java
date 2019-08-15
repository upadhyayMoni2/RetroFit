package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    Recyadapter recyadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetdataService service = RetrofitInstance.getRetrofitInstance().create(GetdataService.class);

        //Starting from

       /* Call<List<Pokemon>> call = service.getPokemons();

        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {

                System.out.println(response.body());
            genrateData(response.body());

            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });*/
       //Starting from json object

        Call<PokemonPojo> call = service.getPokemonsobj();
        call.enqueue(new Callback<PokemonPojo>() {
            @Override
            public void onResponse(Call<PokemonPojo> call, Response<PokemonPojo> response) {
                ArrayList<Pokemon> pokarry = new ArrayList<>();
                PokemonPojo pokemonPojo =  response.body();

                try{

                    pokarry = new ArrayList<>(pokemonPojo.getPokemon());
                    genrateData(pokarry);

                }catch (NullPointerException e){

                    System.out.println(e.getMessage());

                }

            }

            @Override
            public void onFailure(Call<PokemonPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void genrateData(ArrayList<Pokemon> pokes/*List<Pokemon> pokemonList*/){

        @SuppressLint("WrongConstant") LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
       // ArrayList<Pokemon> pokes = (ArrayList<Pokemon>) pokemonList;
        recyadapter = new Recyadapter(pokes,getApplicationContext());
        RecyclerView recyclerView = findViewById(R.id.recycle_poke);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recyadapter);



    }
}
