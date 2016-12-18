package com.nmp90.pictureminer;

import android.Manifest;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.nmp90.pictureminer.api.models.Picture;
import com.nmp90.pictureminer.api.models.PictureOrder;
import com.nmp90.pictureminer.databinding.ActivityMainBinding;
import com.nmp90.pictureminer.di.pictures.PicturesModule;
import com.nmp90.pictureminer.mvp.main.MainContract;
import com.nmp90.pictureminer.mvp.main.MainPresenter;
import com.nmp90.pictureminer.ui.adapters.PicturesAdapter;
import com.nmp90.pictureminer.utils.FileUtils;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainPresenter presenter;

    private ActivityMainBinding binding;
    private CompositeDisposable subscription = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rvPictures.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else {
            binding.rvPictures.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        }

        FlickrApplication.getApplicationComponent().plus(new PicturesModule(this)).inject(this);
        presenter.start();
        presenter.getPictures(null, PictureOrder.DATE_TAKEN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
        subscription.dispose();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void displayPictures(List<Picture> pictures) {
        PicturesAdapter adapter = new PicturesAdapter(pictures);
        subscription.add(
                adapter.getSaveListener()
                        .subscribe(picture -> {
                            MainActivityPermissionsDispatcher.savePictureWithCheck(this, picture);
                        }));

        subscription.add(
                adapter.getShareListener()
                        .subscribe(picture -> {
                            MainActivityPermissionsDispatcher.sharePictureWithCheck(this, picture);
                        }));
        binding.rvPictures.setAdapter(adapter);
    }

    @Override
    public void openSystemGallery(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(FileUtils.getImageContentUri(this, file), "image/*");
        startActivity(intent);
    }

    @Override
    public void savePictureFailed() {
        Snackbar.make(binding.activityMain, R.string.picture_save_failed, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void sharePicture(File file) {
        Uri imageUri = FileUtils.getImageContentUri(this, file);
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("application/image");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        emailIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(emailIntent, getString(R.string.send_email)));
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void savePicture(Picture picture) {
        presenter.savePicture(picture);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void sharePicture(Picture picture) {
        presenter.sharePicture(picture);
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showRationaleForStorage(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.permission_external_storage)
                .setPositiveButton(R.string.button_allow, (dialog, button) -> request.proceed())
                .setNegativeButton(R.string.button_deny, (dialog, button) -> request.cancel())
                .show();
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showDeniedForStorage() {
        Snackbar.make(binding.activityMain, R.string.permission_external_storage, Snackbar.LENGTH_SHORT ).show();
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showNeverAskForStorage() {
        Snackbar.make(binding.activityMain, R.string.permission_storage_neverask, Toast.LENGTH_SHORT).show();
    }
}
