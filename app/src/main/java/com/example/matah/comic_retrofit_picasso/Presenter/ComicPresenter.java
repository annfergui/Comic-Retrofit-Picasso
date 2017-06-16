package com.example.matah.comic_retrofit_picasso.Presenter;

import android.content.Context;

import com.example.matah.comic_retrofit_picasso.MainActivity;
import com.example.matah.comic_retrofit_picasso.Model.ComicModel;
import com.example.matah.comic_retrofit_picasso.Service.ComicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by matah on 11/06/2017.
 */

public class ComicPresenter {
    private final Context context;
    private final ComicPresenterListener mListener;
    private final ComicService comicService;

    public interface ComicPresenterListener{
        void comicReady(ComicModel comic,Boolean aCache);
    }

    public ComicPresenter(ComicPresenterListener mlistener, Context context) {
        this.context = context;
        this.mListener =  mlistener;
        this.comicService=new ComicService();
    }

    public void getComic(int num, final Boolean aCache){
        String numMas="";
        if(num!=0){
            numMas=""+num+"/";
        }

        comicService
                .getAPI()
                .loadComic(numMas)
                .enqueue(new Callback<ComicModel>() {
                    @Override
                    public void onResponse(Call<ComicModel> call, Response<ComicModel> response) {
                        ComicModel resultado=response.body();
                        if(resultado!=null) mListener.comicReady(resultado,aCache);
                    }

                    @Override
                    public void onFailure(Call<ComicModel> call, Throwable t) {
                        mListener.comicReady(null,aCache);

                    }
                });
    }
}
