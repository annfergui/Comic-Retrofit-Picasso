package com.example.matah.comic_retrofit_picasso;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.matah.comic_retrofit_picasso.Model.ComicModel;
import com.example.matah.comic_retrofit_picasso.Presenter.ComicPresenter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.Random;

import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MainActivity extends AppCompatActivity implements ComicPresenter.ComicPresenterListener {

    //declaramos un botón y una imageView
    private Button btnCarga;
    private ImageView iv;

    //declaramos un objeto de la clase ComicPresenter para acceder al getComic()
    private ComicPresenter comicPresenter;

    //declaramos variables necesarias para el desarrollo

    private int comicMax = 0;
    private String urlString = "";
    public boolean descargaActiva = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //modficamos la orientación para que sea vertical
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnCarga = (Button) findViewById(R.id.btnCargar);
        iv = (ImageView) findViewById(R.id.imageView);

        //vamos a inicializar el comicPresenter para acceder al método getComic
        comicPresenter = new ComicPresenter(this, this);
        comicPresenter.getComic(comicMax, false);

    }

    public void comicReady(ComicModel comic, Boolean aCache) {
        //comprobamos si aCache es verdadero y que comic no es null
        if (aCache) {
            if (comic != null) {
                Picasso.with(this).load(comic.getImg()).fetch();
                urlString = comic.getImg();
                if (descargaActiva) {
                    //si se ha activado el carrusel , con Handler +Runnable llamamos al método
                    //que nos adaptará la pantalla a la imagen
                    Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            cargarImagen(urlString);//pasamos la URL que hemos obtenido
                        }

                    };
                    handler.postDelayed(runnable, 4000);//carrusel cada 4 segundos
                }

            }
        } else {//si aCache es false
            if (comic == null) {
                Picasso.with(this).load(R.drawable.error).into(iv);
                Toast.makeText(this, "Error de servidor", Toast.LENGTH_LONG).show();
            } else {
                if (comicMax == 0) {
                    comicMax = Integer.parseInt(comic.getNum());
                    cargarImagen(comic.getImg());
                }
            }
        }
    }

    //Método para adaptar la imagen del Comic a la pantalla utilizando CustomTransform de Picasso
    private void cargarImagen(String url) {
        final Transformation transformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                Matrix matrix = new Matrix();
                if (source.getHeight() < source.getWidth()) {
                    //si la imagen es horizontal la giramos para que ocupe toda la pantalla
                    matrix.postRotate(90);
                }
                Bitmap result = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
                if (result != source) {
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };
        Picasso.with(this)
                .load(url)
                .error(R.drawable.error)//imagen si no se puede cargar
                .placeholder(R.drawable.progreso_animacion)
                .transform(transformation)//ajuste con Rotacion
                .transform(new ColorFilterTransformation(Color.parseColor("#15BC5732")))//la aplico un filtro de color
                .transform(new RoundedCornersTransformation(20, 2))//redondeo los cantos pero poco
                .into(iv);
        //eliminamos imagen de cache
        Picasso.with(this).invalidate(url);
        urlString = "";
        mostrarImagen();//rellenamos la imagen
    }

    private void mostrarImagen() {
        if (urlString.equals("")) {//si no hay imagen elegiremos un aleatoria
            Random rnd = new Random();
            int rdnNumero = rnd.nextInt(comicMax) + 1;
            comicPresenter.getComic(rdnNumero, true);
        } else {
            cargarImagen(urlString);//si hay url se carga la imagen de la memoria
        }


    }
//implementamos los onClick carga y nuevoComic
    public void carga(View v){
        if(btnCarga.getText().equals("INICIAR CARGA IMAGEN")){
            btnCarga.setText("PARAR CARRUSEL");
            descargaActiva=true;
            mostrarImagen();
        }else{
            btnCarga.setText("INICIAR CARGA IMAGEN");
            descargaActiva=false;
        }
    }

    public void nuevoComic(View v){
        if(descargaActiva){
            carga(null);

        }
        mostrarImagen();
    }


}