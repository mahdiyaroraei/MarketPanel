package mahdiyar.ir.marketpanel.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import mahdiyar.ir.marketpanel.R;
import mahdiyar.ir.marketpanel.ui.model.MenuItem;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MenuActivity extends AppCompatActivity {

    private String[] names = new String[]{"محصولات", "دسته بندی ها"};
    private int[] icons = new int[]{R.drawable.ic_market, R.drawable.ic_pie_chart};
    private Class[] classes = new Class[]{ProductsActivity.class, CategoryActivity.class};

    @BindView(R.id.menu_rv)
    RecyclerView menuRecyclerView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);
        initMenu();
    }

    private void initMenu() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        FastItemAdapter adapter = new FastItemAdapter();

        menuRecyclerView.setLayoutManager(layoutManager);
        menuRecyclerView.setAdapter(adapter);

        for (int i = 0; i < names.length; i++) {
            MenuItem menuItem = new MenuItem();
            menuItem.name = names[i];
            menuItem.imageRes = icons[i];

            final int finalI = i;
            menuItem.withOnItemClickListener(new FastAdapter.OnClickListener<MenuItem>() {
                @Override
                public boolean onClick(View v, IAdapter<MenuItem> adapter, MenuItem item, int position) {
                    startActivity(new Intent(getApplicationContext(), classes[finalI]));
                    return true;
                }
            });
            adapter.add(menuItem);
        }
    }
}
