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
 * Created by Mahdiyar on 12/2/2016.
 */

public class MenuItem extends AbstractItem<MenuItem, MenuItem.MenuViewHolder> {
    public String name;
    public int imageRes;

    @Override
    public void bindView(MenuViewHolder holder, List payloads) {
        super.bindView(holder, payloads);

        holder.titleTextView.setText(name);
        Glide.with(holder.menuImageView.getContext()).load(imageRes).into(holder.menuImageView);
    }

    @Override
    public int getType() {
        return R.id.menu;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_menu;
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public ImageView menuImageView;

        public MenuViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.title_tv);
            menuImageView = (ImageView) itemView.findViewById(R.id.menu_iv);
        }
    }
}
