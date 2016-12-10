package mahdiyar.ir.marketpanel.ui.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mahdiyar.ir.marketpanel.R;

/**
 * Created by Mahdiyar on 12/4/2016.
 */

public class ProductImageItem extends AbstractItem<ProductImageItem, ProductImageItem.ProductViewHolder> {
    public String imageUrl;

    @Override
    public void bindView(ProductViewHolder holder, List payloads) {
        super.bindView(holder, payloads);

        Glide
                .with(holder.productImageView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.image_placeholder)
                .into(holder.productImageView);
    }

    @Override
    public int getType() {
        return R.id.product_image;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_product;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_iv)
        ImageView productImageView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
