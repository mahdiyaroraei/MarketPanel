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
 * Created by Mahdiyar on 11/17/2016.
 */

public class CategoryItem extends AbstractItem<CategoryItem, CategoryItem.CategoryViewHolder> {
    public int id , layoutRes = R.layout.row_category;
    public String name, imageUrl;

    @Override
    public int getType() {
        return R.id.category;
    }

    @Override
    public int getLayoutRes() {
        return layoutRes;
    }

    @Override
    public void bindView(CategoryViewHolder holder, List payloads) {
        super.bindView(holder, payloads);
        holder.categoryTextView.setText(name.trim());
        Glide.with(holder.categoryImageView.getContext()).load(imageUrl).into(holder.categoryImageView);
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryTextView;
        public ImageView categoryImageView;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            categoryTextView = (TextView) itemView.findViewById(R.id.category_tv);
            categoryImageView = (ImageView) itemView.findViewById(R.id.category_iv);
        }
    }
}
