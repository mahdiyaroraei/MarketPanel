package mahdiyar.ir.marketpanel.ui.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mahdiyar.ir.marketpanel.R;
import mahdiyar.ir.marketpanel.core.ApplicationLoader;
import mahdiyar.ir.marketpanel.ui.CategoryActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahdiyar on 11/17/2016.
 */

public class CategoryItem extends AbstractItem<CategoryItem, CategoryItem.CategoryViewHolder> {
    public int id, layoutRes = R.layout.row_category;
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
    public void bindView(final CategoryViewHolder holder, List payloads) {
        super.bindView(holder, payloads);
        holder.categoryTextView.setText(name.trim());
        Glide.with(holder.categoryImageView.getContext()).load(imageUrl).into(holder.categoryImageView);
        if (holder.trashImageView != null) {
            holder.trashImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new SweetAlertDialog(holder.categoryImageView.getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Are you sure?")
                            .setContentText("Won't be able to recover this file!")
                            .setConfirmText("Yes,delete it!")
                            .setCustomImage(R.drawable.ic_garbage)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(final SweetAlertDialog sDialog) {
                                    Call<String> call = ApplicationLoader.api.removeCategory(id, 7);
                                    call.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            ApplicationLoader.loadUserInfo(new ApplicationLoader.OnChangeUserInfo() {
                                                @Override
                                                public void onChange() {
                                                    sDialog
                                                            .setTitleText("Deleted!")
                                                            .setContentText("Your imaginary file has been deleted!")
                                                            .setConfirmText("OK")
                                                            .setConfirmClickListener(null)
                                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                    ((CategoryActivity) holder.trashImageView.getContext()).getCategory();
                                                }
                                            });
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {

                                        }
                                    });
                                }
                            })
                            .show();

                }
            });
        }
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryTextView;
        public ImageView categoryImageView, trashImageView;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            categoryTextView = (TextView) itemView.findViewById(R.id.category_tv);
            categoryImageView = (ImageView) itemView.findViewById(R.id.category_iv);
            trashImageView = (ImageView) itemView.findViewById(R.id.trash_iv);
        }
    }
}
