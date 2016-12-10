package mahdiyar.ir.marketpanel.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mahdiyar.ir.marketpanel.R;
import mahdiyar.ir.marketpanel.ui.component.ChromaKey;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ChromaKeyActivity extends AppCompatActivity {
    public static int CHROMA_IMAGE_ACTIVITY_RESULT_OK_CODE = 1565;

    @BindView(R.id.sample_iv)
    ImageView sampleImageView;

    @BindView(R.id.smooth_sb)
    SeekBar smoothSeekBar;

    @BindView(R.id.red_sb)
    SeekBar redSeekBar;

    @BindView(R.id.green_sb)
    SeekBar greenSeekBar;

    @BindView(R.id.blue_sb)
    SeekBar blueSeekBar;

    @BindViews({R.id.dist_val_tv, R.id.red_val_tv, R.id.green_val_tv, R.id.blue_val_tv})
    List<TextView> valTextViews;

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            valTextViews.get(0).setText(smoothSeekBar.getProgress() + "");
            valTextViews.get(1).setText(redSeekBar.getProgress() + "");
            valTextViews.get(2).setText(greenSeekBar.getProgress() + "");
            valTextViews.get(3).setText(blueSeekBar.getProgress() + "");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            applyChanges();
        }
    };
    private Bitmap finalBitmap;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chroma_key);

        ButterKnife.bind(this);

        initSeekBars();
        applyChanges();
    }

    @OnClick(R.id.select_tv)
    public void selectImage() {

        Intent intent = new Intent();
        intent.setData(saveBitmap());
        setResult(RESULT_OK, intent);

        finish();
    }

    private Uri saveBitmap() {
        File file = new File(Environment.getExternalStorageDirectory().toString(), System.currentTimeMillis() + ".jpg");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Uri.fromFile(file);
    }

    private void applyChanges() {
        Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getData().getPath());
        ChromaKey chromaKey = new ChromaKey();
        finalBitmap = chromaKey.replaceIntervalColor(bitmap, redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress(), smoothSeekBar.getProgress());
        sampleImageView.setImageBitmap(finalBitmap);
    }

    private void initSeekBars() {
        smoothSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        smoothSeekBar.setMax(200);
        smoothSeekBar.setProgress(170);

        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        redSeekBar.setMax(255);
        blueSeekBar.setMax(255);
        greenSeekBar.setMax(255);
    }
}
