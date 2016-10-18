package com.aldaircruz.droidtek.rv_adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aldaircruz.droidtek.R;
import com.aldaircruz.droidtek.model.Product;
import com.aldaircruz.droidtek.util.Util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Cruz on 16-10-2016.
 */


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ProductViewHolder> {

    private ArrayList<Product> products;
    Activity activity;
    TextView txv_total;
    ArrayList<Double> preco = new ArrayList<>();
    private static int lastEditedPosition=0;

    public RVAdapter(ArrayList<Product> products, Activity activity) {
        this.products = products;
        this.activity = activity;
        txv_total= (TextView) activity.findViewById(R.id.txv_total);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_products, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(v);
        return productViewHolder;

    }

    public void addItem(Product dataObj, int index) {
        products.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        products.remove(index);
        notifyItemRemoved(index);
    }

    public Product getItemById(int id){
        return products.get(id);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {

        holder.preco.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {

                if(holder.preco.getText().toString().trim().length()>0){
                    int value=Integer.parseInt(holder.preco.getText().toString().trim());
                    if(preco.size()> 0 && preco.get(position)>0)
                        preco.set(position,0d);

                    preco.add(position,Double.valueOf(value));
                }
                else if(preco.size()>0){
                    preco.set(position,0d);
                }

                txv_total.setText(Util.sumOfProducts(preco)+"");


            }
        });
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nome;
        TextView preco;

        ProductViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            nome = (TextView) itemView.findViewById(R.id.cv_product_name);
            preco = (TextView) itemView.findViewById(R.id.cv_product_price);

        }
    }
}
