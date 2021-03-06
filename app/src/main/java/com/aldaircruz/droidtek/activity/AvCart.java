package com.aldaircruz.droidtek.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.aldaircruz.droidtek.R;
import com.aldaircruz.droidtek.data.Constants;
import com.aldaircruz.droidtek.data.StaticData;
import com.aldaircruz.droidtek.model.Product;
import com.aldaircruz.droidtek.rv_adapter.RVAdapter;

import java.util.ArrayList;

public class AvCart extends AppCompatActivity {

    RVAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av_carrinho);

        Log.d(Constants.DEBUG_KEY, StaticData.client.get(0).getNome());

        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        StaticData.products= new ArrayList<>();


        final ArrayList<Product> products = new ArrayList<>();

            for(int counter = 0; counter< Constants.numberOfProducts; counter++){
                products.add(new Product());
            }


        mAdapter = new RVAdapter(products,this);
        rv.setAdapter(mAdapter);


        FloatingActionButton fab;
        fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAdapter.addItem(new Product(),mAdapter.getItemCount());
                mAdapter.notifyDataSetChanged();

            }
        });

    }
}
