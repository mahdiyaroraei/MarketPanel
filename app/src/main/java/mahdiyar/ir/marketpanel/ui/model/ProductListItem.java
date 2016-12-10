package mahdiyar.ir.marketpanel.ui.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.ArrayList;
import java.util.List;

import mahdiyar.ir.marketpanel.R;
import mahdiyar.ir.marketpanel.core.api.model.Image;

/**
 * Created by Mahdiyar on 11/7/2016.
 */

public class ProductListItem extends AbstractItem<ProductListItem, ProductListItem.ProductViewHolder> {
    public ArrayList<Image> image_urls = new ArrayList<>();
    public String title;
    public String desc;
    public int price;
    public String date;
    public int id;
    public int catId;
    public int offprice;

    @Override
    public int getType() {
        return R.id.list_mode;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.row_product_list;
    }

    @Override
    public void bindView(ProductViewHolder holder, List payloads) {
        super.bindView(holder, payloads);
        if (image_urls.size() > 0) {
            Glide.with(holder.productImageView.getContext()).load(image_urls.get(0).getAddress()).into(holder.productImageView);
        }
        holder.titleTextView.setText(title);
        holder.descTextView.setText(desc);
        holder.priceTextView.setText(price + " تومان ");
        holder.dateTextView.setText(date);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView priceTextView;
        public TextView dateTextView;
        public TextView titleTextView;
        public TextView descTextView;
        public ImageView productImageView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            priceTextView = (TextView) itemView.findViewById(R.id.price_tv);
            dateTextView = (TextView) itemView.findViewById(R.id.date_tv);
            titleTextView = (TextView) itemView.findViewById(R.id.title_tv);
            descTextView = (TextView) itemView.findViewById(R.id.desc_tv);
            productImageView = (ImageView) itemView.findViewById(R.id.product_iv);
        }
    }
}
