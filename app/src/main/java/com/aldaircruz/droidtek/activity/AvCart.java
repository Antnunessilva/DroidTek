package com.aldaircruz.droidtek.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.aldaircruz.droidtek.R;
import com.aldaircruz.droidtek.data.Constants;
import com.aldaircruz.droidtek.data.StaticData;
import com.aldaircruz.droidtek.model.Product;
import com.aldaircruz.droidtek.rv_adapter.RVAdapter;

import java.util.ArrayList;

public class AvCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av_carrinho);

        Log.d("debug", StaticData.client.get(0).getNome());

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);


        ArrayList<Product> products = new ArrayList<>();


        for(int counter = 0; counter< Constants.numberOfProducts; counter++){
            products.add(new Product());
        }

        RVAdapter adapter = new RVAdapter(products,this);
        rv.setAdapter(adapter);

    }
}
