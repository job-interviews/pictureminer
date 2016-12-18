package com.nmp90.pictureminer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.nmp90.pictureminer.api.models.Picture;
import com.nmp90.pictureminer.databinding.ActivityMainBinding;
import com.nmp90.pictureminer.di.pictures.PicturesModule;
import com.nmp90.pictureminer.mvp.main.MainContract;
import com.nmp90.pictureminer.mvp.main.MainPresenter;
import com.nmp90.pictureminer.ui.adapters.PicturesAdapter;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainPresenter presenter;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.rvPictures.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        FlickrApplication.getApplicationComponent().plus(new PicturesModule(this)).inject(this);
        presenter.start();
        presenter.getPictures(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }

    @Override
    public void displayPictures(List<Picture> pictures) {
        binding.rvPictures.setAdapter(new PicturesAdapter(pictures));
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }
}
