package mahdiyar.ir.marketpanel.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.util.TimeZone;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mahdiyar.ir.marketpanel.R;
import mahdiyar.ir.marketpanel.core.ApplicationLoader;
import mahdiyar.ir.marketpanel.core.Utility;
import mahdiyar.ir.marketpanel.core.api.model.Category;
import mahdiyar.ir.marketpanel.core.api.model.Product;
import mahdiyar.ir.marketpanel.ui.model.CategoryItem;
import mahdiyar.ir.marketpanel.ui.model.ProductGridItem;
import mahdiyar.ir.marketpanel.ui.model.ProductListItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductsActivity extends AppCompatActivity {

    private FastItemAdapter categoryAdapter;
    private int catID = -1;

    private enum RecyclerMode {LIST, GRID}

    ;

    private RecyclerView productRecyclerView;
    private FastItemAdapter adapter;
    private RecyclerMode mode = RecyclerMode.LIST;
    private int order = 1;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        ButterKnife.bind(this);

        initViews();
        setViews();
        setupRecyclerView();
        setupCategory();
        setupSort();
        getCategory();
        getProducts();
    }

    @OnClick(R.id.add_product_tv)
    public void addProduct() {
        startActivity(new Intent(getApplicationContext(), EditProductActivity.class));
    }

    private void setupSort() {
        AppCompatSpinner spinner = (AppCompatSpinner) findViewById(R.id.sort_s);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner, getResources().getStringArray(R.array.sort));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                order = i + 1;
                adapter.clear();
                getProducts();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner.setAdapter(spinnerAdapter);
    }

    private void getCategory() {
        for (Category category :
                ApplicationLoader.userInfo.getCategories()) {
            CategoryItem categoryItem = new CategoryItem();
            categoryItem.name = category.getName();
            categoryItem.imageUrl = category.getImageUrl();
            categoryItem.id = category.getId();

            categoryAdapter.add(categoryItem);
        }
    }

    private void setupCategory() {
        RecyclerView categoryRecyclerView = (RecyclerView) findViewById(R.id.category_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryAdapter = new FastItemAdapter();

        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.withOnClickListener(new FastAdapter.OnClickListener() {
            @Override
            public boolean onClick(View v, IAdapter adapter, IItem item, int position) {
                CategoryItem categoryItem = (CategoryItem) item;
                catID = categoryItem.id;
                getProducts();
                return true;
            }
        });
    }

    private void setViews() {
        findViewById(R.id.list_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                productRecyclerView.setLayoutManager(layoutManager);
                mode = RecyclerMode.LIST;
                getProducts();
            }
        });

        findViewById(R.id.grid_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                int mNoOfColumns = Utility.calculateNoOfColumns(getApplicationContext());
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                productRecyclerView.setLayoutManager(layoutManager);
                mode = RecyclerMode.GRID;
                getProducts();
            }
        });
    }

    private void getProducts() {
        Call<List<Product>> productCall = ApplicationLoader.api.getProduct(7, catID, order);
        productCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.code() == 200) {
                    adapter.clear();
                    for (Product product :
                            response.body()) {
                        if (mode == RecyclerMode.LIST) {
                            ProductListItem listItem = new ProductListItem();
                            PersianCalendar calendar = new PersianCalendar(TimeZone.getDefault());
                            calendar.setTimeInMillis(Long.valueOf(product.getPost().getCreatedAt().replaceAll("\\D+", "")));
                            listItem.id = product.getPost().getId();
                            listItem.catId = product.getPost().getCatId();
                            listItem.date = calendar.get(PersianCalendar.YEAR) + "/" + calendar.get(PersianCalendar.MONTH) + "/" + calendar.get(PersianCalendar.DAY_OF_MONTH);
                            listItem.title = product.getPost().getTitle();
                            listItem.desc = product.getPost().getDesc();
                            listItem.price = product.getPost().getCost();
                            listItem.offprice = product.getPost().getOffcost();
                            if (product.getImages().size() > 0) {
                                listItem.image_urls.addAll(product.getImages());
                            }

                            adapter.add(listItem);
                        } else {
                            ProductGridItem gridItem = new ProductGridItem();
                            gridItem.title = product.getPost().getTitle();
                            if (product.getImages().size() > 0) {
                                gridItem.image_url = product.getImages().get(0).getAddress();
                            }

                            adapter.add(gridItem);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new FastItemAdapter();

        productRecyclerView.setLayoutManager(layoutManager);
        productRecyclerView.setAdapter(adapter);
        adapter.withOnClickListener(new FastAdapter.OnClickListener() {
            @Override
            public boolean onClick(View v, IAdapter adapter, IItem item, int position) {
                ProductListItem productItem = (ProductListItem) item;
                Bundle bundle = new Bundle();
                bundle.putInt("post_id", productItem.id);
                bundle.putInt("cat_id", productItem.catId);
                bundle.putString("title", productItem.title);
                bundle.putString("desc", productItem.desc);
                bundle.putInt("price", productItem.price);
                bundle.putString("date", productItem.date);
                bundle.putParcelableArrayList("images", productItem.image_urls);
                bundle.putInt("offprice", productItem.offprice);
                Intent intent = new Intent(getApplicationContext(), EditProductActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        });
    }

    private void initViews() {
        productRecyclerView = (RecyclerView) findViewById(R.id.product_rv);
    }
}
