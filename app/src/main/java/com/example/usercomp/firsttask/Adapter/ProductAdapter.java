package com.example.usercomp.firsttask.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usercomp.firsttask.ClassesForRequests.Category;
import com.example.usercomp.firsttask.ChangedViews.ImageViewHelper;
import com.example.usercomp.firsttask.R;

import java.util.List;

/**
 * Created by UserComp on 30.04.2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Category> listData;
    private LayoutInflater inflater;



    public ProductAdapter (List<Category> listData, Context c){
        inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Category item = listData.get(position);
        MyHolder myHolder = (MyHolder) holder;

        myHolder.nameOfProduct.setText(item.getName());
        Bitmap bitmap = ImageViewHelper.getBitmapFromURL(item.getIconUrl());
        myHolder.productImage.setImageBitmap(bitmap);

    }



    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyHolder  extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView nameOfProduct;

        public MyHolder(View itemView) {
            super(itemView);

            productImage  = (ImageView)itemView.findViewById(R.id.item_product_ImageView);
            nameOfProduct = (TextView)itemView.findViewById(R.id.item_product_NameOfProducts);
        }
    }

}

