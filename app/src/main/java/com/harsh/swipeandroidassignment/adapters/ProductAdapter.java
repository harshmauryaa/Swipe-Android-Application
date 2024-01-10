package com.harsh.swipeandroidassignment.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.harsh.swipeandroidassignment.R;
import com.harsh.swipeandroidassignment.data.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProgrammingViewHolder> {
    Context context;
    ArrayList<ProductModel> productModels;
    List<ProductModel> originalProductList;


    public ProductAdapter(Context context)
    {
        this.context=context;
        this.productModels = new ArrayList<>();
        this.originalProductList = new ArrayList<>();
    }

    public ProductAdapter(ArrayList<ProductModel> productModels, Context context)
    {
        this.productModels = new ArrayList<>(productModels);
        this.originalProductList = new ArrayList<>(productModels);
        this.context=context;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.content_product_list,parent,false);
        return new ProgrammingViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {

        ProductModel productModel = this.productModels.get(position);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_loading_image)
                .error(R.drawable.ic_broken_image);

        Glide.with(context).load(productModel.getImage())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.viewProductImage);

        holder.viewProductPrice.setText(
                String.format(
                        holder.itemView.getContext().getString(R.string.price_label),
                        productModel.getPrice() != 0 ? String.valueOf(productModel.getPrice()) : "0"
                )
        );

        holder.viewProductName.setText(
                String.format(
                        holder.itemView.getContext().getString(R.string.product_name_label),
                        productModel.getProduct_name() != null ? String.valueOf(productModel.getProduct_name()) : "Unable to the fetch name"
                )
        );

        holder.viewProductType.setText(
                String.format(
                        holder.itemView.getContext().getString(R.string.product_type_label),
                        productModel.getProduct_type() != null ? String.valueOf(productModel.getProduct_type()) : "Unable to the fetch type"
                )
        );

        holder.viewProductTax.setText(
                String.format(
                        holder.itemView.getContext().getString(R.string.tax_label),
                        productModel.getTax() != 0 ? String.valueOf(productModel.getTax()) : "0"
                )
        );

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }
    public void submitList(List<ProductModel> updatedList) {
        productModels.clear();
        productModels.addAll(updatedList);

        originalProductList.clear();
        originalProductList.addAll(updatedList);

        notifyDataSetChanged();
    }

    public void filter(String searchText) {
        searchText = searchText.toLowerCase().trim();
        productModels.clear();

        if (searchText.isEmpty()) {
            productModels.addAll(originalProductList);
        } else {
            for (ProductModel model : originalProductList) {
                if (model.getProduct_name().toLowerCase().contains(searchText)) {
                    productModels.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        private final ImageView viewProductImage;
        private final TextView viewProductPrice;
        private final TextView viewProductName;
        private final TextView viewProductType;
        private final TextView viewProductTax;

        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);

            viewProductImage = itemView.findViewById(R.id.prd_image);
            viewProductPrice = itemView.findViewById(R.id.prd_price);
            viewProductName = itemView.findViewById(R.id.prd_name);
            viewProductType = itemView.findViewById(R.id.prd_type);
            viewProductTax = itemView.findViewById(R.id.prd_tax);
        }


    }
}

