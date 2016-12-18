package com.nmp90.pictureminer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nmp90.pictureminer.api.models.Picture;
import com.nmp90.pictureminer.di.pictures.PicturesModule;
import com.nmp90.pictureminer.mvp.main.MainContract;
import com.nmp90.pictureminer.mvp.main.MainPresenter;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlickrApplication.getApplicationComponent().plus(new PicturesModule(this)).inject(this);
        presenter.getPictures(null);
    }

    @Override
    public void displayPictures(List<Picture> pictures) {

    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }
}
