package mahdiyar.ir.marketpanel.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.nguyenhoanglam.imagepicker.activity.ImagePicker;
import com.nguyenhoanglam.imagepicker.activity.ImagePickerActivity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mahdiyar.ir.marketpanel.R;
import mahdiyar.ir.marketpanel.core.ApplicationLoader;
import mahdiyar.ir.marketpanel.core.api.model.Category;
import mahdiyar.ir.marketpanel.core.api.model.Image;
import mahdiyar.ir.marketpanel.core.api.model.ImageUpload;
import mahdiyar.ir.marketpanel.ui.model.ProductImageItem;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EditProductActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PICKER = 1;

    @BindView(R.id.pics_rv)
    RecyclerView picsRecyclerView;

    @BindView(R.id.cat_s)
    AppCompatSpinner catSpinner;

    @BindView(R.id.name_edt)
    EditText nameEditText;

    @BindView(R.id.price_edt)
    EditText priceEditText;

    @BindView(R.id.off_edt)
    EditText offEditText;

    @BindView(R.id.desc_edt)
    EditText descEditText;

    @BindView(R.id.add_product_tv)
    TextView addProductTextView;

    private FastItemAdapter imageAdapter;
    private ArrayList<ImageUpload> urls = new ArrayList<>();
    private ArrayAdapter<Category> catAdapter;
    private String title;
    private int postId;
    private int catId;
    private String desc;
    private int price;
    private ArrayList<Image> images;
    private int offprice;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        ButterKnife.bind(this);

        productImagesSetup();
        productCategoriesSetup();
        isEditingMode();
        getProductImage();
    }

    private void isEditingMode() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        postId = bundle.getInt("post_id", 0);
        catId = bundle.getInt("cat_id", 0);
        title = bundle.getString("title", null);
        desc = bundle.getString("desc", null);
        price = bundle.getInt("price", 0);
        images = bundle.getParcelableArrayList("images");
        offprice = bundle.getInt("offprice", 0);

        if (postId != 0) {
            addProductTextView.setText("بروزرسانی");
            fillFields();
        }
    }

    private void fillFields() {
        int i = 0;
        for (; i < ApplicationLoader.userInfo.getCategories().size(); i++) {
            Category category = ApplicationLoader.userInfo.getCategories().get(i);
            if (category.getId() == catId) {
                catSpinner.setSelection(i);
                break;
            }
        }
        nameEditText.setText(title);
        descEditText.setText(desc);
        priceEditText.setText(price + "");
        offEditText.setText(offprice + "");
        for (Image image :
                images) {
            urls.add(new ImageUpload(image.getAddress()));
        }
        getProductImage();
    }

    @OnClick(R.id.add_product_tv)
    public void addProduct() {
        if (validate()) {
            Call<String> call;
            if (postId == 0) {
                call = ApplicationLoader.api.addPost(
                        nameEditText.getText().toString(),
                        descEditText.getText().toString(),
                        Integer.parseInt(priceEditText.getText().toString()),
                        Integer.parseInt(offEditText.getText().toString()),
                        true,
                        ApplicationLoader.userInfo.getUser().getId(),
                        new Gson().toJson(urls),
                        ApplicationLoader.userInfo.getCategories().get(catSpinner.getSelectedItemPosition()).getId());
            } else {
                call = ApplicationLoader.api.updatePost(
                        nameEditText.getText().toString(),
                        descEditText.getText().toString(),
                        Integer.parseInt(priceEditText.getText().toString()),
                        Integer.parseInt(offEditText.getText().toString()),
                        true,
                        ApplicationLoader.userInfo.getUser().getId(),
                        new Gson().toJson(urls),
                        ApplicationLoader.userInfo.getCategories().get(catSpinner.getSelectedItemPosition()).getId(),
                        postId);
            }
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    startActivity(new Intent(getApplicationContext(), ProductsActivity.class));
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
    }

    private boolean validate() {
        if (nameEditText.getText().toString().equals("")
                || priceEditText.getText().toString().equals("")
                || offEditText.getText().toString().equals("")
                || descEditText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "لطفا تمام فیلد ها را پر کنید.", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void productCategoriesSetup() {
        catAdapter = new ArrayAdapter<Category>(getApplicationContext(), R.layout.item_cat_spinner, ApplicationLoader.userInfo.getCategories());
        catSpinner.setAdapter(catAdapter);
        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView selectedText = (TextView) adapterView.getChildAt(0);
                if (selectedText != null) {
                    selectedText.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getProductImage() {
        for (int i = 0; i < 5; i++) {
            ProductImageItem imageItem = new ProductImageItem();

            imageAdapter.add(imageItem);
        }
        if (urls != null) {
            for (int i = 0; i < urls.size(); i++) {
                String url = urls.get(i).getAddress();
                try {
                    ((ProductImageItem) imageAdapter.getItem(i)).imageUrl = url;
                } catch (IndexOutOfBoundsException e) {
                    ProductImageItem imageItem = new ProductImageItem();
                    imageItem.imageUrl = url;

                    imageAdapter.add(imageItem);
                }
            }
            imageAdapter.notifyAdapterDataSetChanged();
        }
    }

    private void productImagesSetup() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        imageAdapter = new FastItemAdapter();

        picsRecyclerView.setLayoutManager(layoutManager);
        picsRecyclerView.setAdapter(imageAdapter);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICKER && resultCode == RESULT_OK && data != null) {
            ArrayList<com.nguyenhoanglam.imagepicker.model.Image> images = data.getParcelableArrayListExtra(ImagePickerActivity.INTENT_EXTRA_SELECTED_IMAGES);
            // do your logic ....
            CropImage.activity(Uri.fromFile(new File(images.get(0).getPath())))
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(146, 186)
                    .start(EditProductActivity.this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Intent intent = new Intent(getApplicationContext(), ChromaKeyActivity.class);
                intent.setData(resultUri);
                startActivityForResult(intent, ChromaKeyActivity.CHROMA_IMAGE_ACTIVITY_RESULT_OK_CODE);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
        if (requestCode == ChromaKeyActivity.CHROMA_IMAGE_ACTIVITY_RESULT_OK_CODE) {
            File file = new File(data.getData().getPath());
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("img", file.getName(), requestBody);
            Call<String> call = ApplicationLoader.api.uploadImage(body);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 200) {
                        String url = response.body();
                        urls.add(new ImageUpload(url));
                        getProductImage();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
    }

    @OnClick(R.id.add_product_image_tv)
    public void addProductImage() {
        ImagePicker.ImagePickerWithActivity pickerWithFragment = new ImagePicker.ImagePickerWithActivity(this);
        pickerWithFragment
                .folderMode(true) // folder mode (false by default)
                .folderTitle("Folder") // folder selection title
                .imageTitle("Tap to select") // image selection title
                .single() // single mode
                .showCamera(true) // show camera or not (true by default)
                .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                .start(REQUEST_CODE_PICKER);
    }
}
