package mahdiyar.ir.marketpanel.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import mahdiyar.ir.marketpanel.R;
import mahdiyar.ir.marketpanel.core.ApplicationLoader;
import mahdiyar.ir.marketpanel.core.api.model.Category;
import mahdiyar.ir.marketpanel.ui.model.CategoryItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CategoryActivity extends AppCompatActivity {

    private FastItemAdapter categoryAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getCategory();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ButterKnife.bind(this);

        setupCategory();
    }

    @OnClick(R.id.add_category_tv)
    public void addCategory() {
        startActivityForResult(new Intent(getApplicationContext(), EditCategoryActivity.class), 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCategory();
    }

    public void getCategory() {
        categoryAdapter.clear();
        for (Category category :
                ApplicationLoader.userInfo.getCategories()) {
            CategoryItem categoryItem = new CategoryItem();
            categoryItem.layoutRes = R.layout.item_category_list;
            categoryItem.name = category.getName();
            categoryItem.imageUrl = category.getImageUrl();
            categoryItem.id = category.getId();

            categoryAdapter.add(categoryItem);
        }
    }

    private void setupCategory() {
        RecyclerView categoryRecyclerView = (RecyclerView) findViewById(R.id.category_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        categoryAdapter = new FastItemAdapter();

        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.withOnClickListener(new FastAdapter.OnClickListener() {
            @Override
            public boolean onClick(View v, IAdapter adapter, IItem item, int position) {
                CategoryItem categoryItem = (CategoryItem) item;
                Bundle bundle = new Bundle();
                bundle.putInt("id", categoryItem.id);
                bundle.putString("name", categoryItem.name);
                bundle.putString("image_url", categoryItem.imageUrl);
                Intent intent = new Intent(getApplicationContext(), EditCategoryActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

                return true;
            }
        });
    }

    public FastItemAdapter getCategoryAdapter() {
        return categoryAdapter;
    }
}
