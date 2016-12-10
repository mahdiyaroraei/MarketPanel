package mahdiyar.ir.marketpanel.ui.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import mahdiyar.ir.marketpanel.R;


/**
 * Created by Mahdiyar on 11/7/2016.
 */

public class ProductGridItem extends AbstractItem<ProductGridItem, ProductGridItem.ProductViewHolder> {
    public String image_url;
    public String title;

    @Override
    public int getType() {
        return R.id.grid_mode;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.row_product_grid;
    }

    @Override
    public void bindView(ProductViewHolder holder, List payloads) {
        super.bindView(holder, payloads);
        Glide.with(holder.productImageView.getContext()).load(image_url).into(holder.productImageView);
        holder.titleTextView.setText(title);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public ImageView productImageView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.title_tv);
            productImageView = (ImageView) itemView.findViewById(R.id.product_iv);
        }
    }
}
