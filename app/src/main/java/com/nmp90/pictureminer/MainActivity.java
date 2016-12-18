package com.nmp90.pictureminer;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.nmp90.pictureminer.api.models.Picture;
import com.nmp90.pictureminer.databinding.ActivityMainBinding;
import com.nmp90.pictureminer.di.pictures.PicturesModule;
import com.nmp90.pictureminer.mvp.main.MainContract;
import com.nmp90.pictureminer.mvp.main.MainPresenter;
import com.nmp90.pictureminer.ui.adapters.PicturesAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainPresenter presenter;

    private ActivityMainBinding binding;
    private Disposable subscription;

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
        presenter.getPictures(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
        subscription.dispose();
    }

    @Override
    public void displayPictures(List<Picture> pictures) {
        PicturesAdapter adapter = new PicturesAdapter(pictures);
        subscription = adapter.getClickObservable()
                .subscribe(picture -> {
                    Toast.makeText(this, "" + picture.getTitle(), Toast.LENGTH_SHORT).show();
                });
        binding.rvPictures.setAdapter(adapter);
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }
}
