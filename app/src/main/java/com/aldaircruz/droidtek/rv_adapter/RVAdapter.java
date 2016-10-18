package com.aldaircruz.droidtek.rv_adapter;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aldaircruz.droidtek.R;
import com.aldaircruz.droidtek.data.Constants;
import com.aldaircruz.droidtek.data.StaticData;
import com.aldaircruz.droidtek.model.Product;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Cruz on 16-10-2016.
 */


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ProductViewHolder> {

    private ArrayList<Product> _products;
    Product editing = new Product();
    int lastEditedIndex=0;
    Double lastInsertedPrice=0d;
    Activity activity;
    TextView txv_total;

    public RVAdapter(ArrayList<Product> products, Activity activity) {
        this._products = products;
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
        _products.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        _products.remove(index);
        notifyItemRemoved(index);
    }

    public Product getItemById(int id){
        return _products.get(id);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {

        String _name = " ";
        String _price = " ";

        if (_products.get(position) == null) {
            _name = "";
            _price = "";
        } else {
            Double price;

            if (_products.get(position).getName() != null)
                _name = _products.get(position).getName();

            if (_products.get(position).getPrice() != null) {
                price = _products.get(position).getPrice();
                _price = String.valueOf(_price);
            }
        }
        holder.nome.setText(_name);
        holder.preco.setText(String.valueOf(_price));




        holder.preco.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {

                String preco;


                try {
                    if(holder.preco.getText().toString().trim().equals("")){

                        Double total = 0d;
                        if(position==lastEditedIndex){
                            Log.d("DEBUG", "size before:"+StaticData.products.size());

                            if(StaticData.products.size()>0)
                            Log.d("DEBUG", "total:" + StaticData.products.get(0).getTotalPrice());

                            Log.d("DEBUG", "removing...");


                            total= StaticData.products.get(0).getTotalPrice();
                            total-=lastInsertedPrice;
                            StaticData.products.get(lastEditedIndex).setTotalPrice(total);

                            StaticData.products.remove(lastEditedIndex);

                            Log.d("DEBUG", "size after:"+StaticData.products.size());

                        }

                        Log.d("DEBUG","position:"+position);
                        Log.d("DEBUG","last edited position:"+lastEditedIndex);

                        Double totalProducts=0d;
                        if(StaticData.products.size()>0)
                        {
                            totalProducts = StaticData.products.get(0).getTotalPrice();
                        }
                        txv_total.setText(String.valueOf(totalProducts));
                    }else{

                        lastEditedIndex = position;
                        preco = holder.preco.getText().toString();
                        Log.d("DEBUG", "preço:" + preco);

                        String nome;
                        String price="0";

                        if(holder.nome.getText().toString().length()>0)
                            nome = holder.nome.getText().toString();
                        else nome ="";


                        if(!holder.preco.getText().toString().trim().equals(""))
                            price = holder.preco.getText().toString();
                        else price="0";

                        lastInsertedPrice = Double.valueOf(price);

                        Product product = new Product();

                        try {
                            product.setPrice(lastInsertedPrice);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        try {
                            product.setName(nome);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        StaticData.products.add(editing);
                        Log.d("DEBUG", "total:" + StaticData.products.get(0).getTotalPrice());

                        txv_total.setText(String.valueOf(StaticData.products.get(0).getTotalPrice()));
                    }
                } catch (Exception e) {
                    Log.d("DEBUG","erro");
                }

            }
        });
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return _products.size();
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
