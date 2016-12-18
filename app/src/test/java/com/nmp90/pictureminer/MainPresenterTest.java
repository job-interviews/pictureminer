package com.nmp90.pictureminer;

import android.content.Context;

import com.nmp90.pictureminer.api.FakeApi;
import com.nmp90.pictureminer.api.models.PictureOrder;
import com.nmp90.pictureminer.mvp.main.MainContract;
import com.nmp90.pictureminer.mvp.main.MainPresenter;
import com.nmp90.pictureminer.utils.FakeTransformer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;


/**
 * Created by joro on 18.12.16.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    MainPresenter presenter;
    MainContract.View view;
    private FakeApi api;

    @Before
    public void setUp() {
        view = Mockito.mock(MainContract.View.class);
        Mockito.stub(view.isActive()).toReturn(true);
        api = new FakeApi();
        presenter = new MainPresenter(view, api, Mockito.mock(Context.class), new FakeTransformer());
    }

    @Test
    public void testDoSomething() {
        presenter.getPictures(null, PictureOrder.DATE_TAKEN);
        verify(view).displayPictures(api.picturesResponse.getItems());
    }


}
