package mahdiyar.ir.marketpanel.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nguyenhoanglam.imagepicker.activity.ImagePicker;
import com.nguyenhoanglam.imagepicker.activity.ImagePickerActivity;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mahdiyar.ir.marketpanel.R;
import mahdiyar.ir.marketpanel.core.ApplicationLoader;
import mahdiyar.ir.marketpanel.core.api.model.ImageUpload;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EditCategoryActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PICKER = 1;

    @BindView(R.id.category_iv)
    ImageView catImageView;

    @BindView(R.id.category_edt)
    EditText catEditText;

    private String url;
    private String name;
    private int id;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        ButterKnife.bind(this);
        getArgs();
    }

    private void getArgs() {
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        url = getIntent().getStringExtra("image_url");

        if (id != 0) {
            fillFields();
        }
    }

    private void fillFields() {
        catEditText.setText(name);
        Glide.with(EditCategoryActivity.this).load(url).into(catImageView);
    }

    @OnClick(R.id.add_category_tv)
    public void addCategory() {
        Call<String> call;
        if (id == 0) {
            call = ApplicationLoader.api.addCategory(url, catEditText.getText().toString(), 7);
        } else {
            call = ApplicationLoader.api.editCategory(url, catEditText.getText().toString(), 7, id);
        }
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ApplicationLoader.loadUserInfo(new ApplicationLoader.OnChangeUserInfo() {
                    @Override
                    public void onChange() {
                        setResult(RESULT_OK);
                        finish();
                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICKER && resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(ImagePickerActivity.INTENT_EXTRA_SELECTED_IMAGES);
            // do your logic ....
            CropImage.activity(Uri.fromFile(new File(images.get(0).getPath())))
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(EditCategoryActivity.this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                File file = new File(resultUri.getPath());
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("img", file.getName(), requestBody);
                Call<String> call = ApplicationLoader.api.uploadImage(body);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.code() == 200) {
                            url = response.body();
                            Glide.with(EditCategoryActivity.this).load(url).into(catImageView);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }


    @OnClick(R.id.add_image)
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
