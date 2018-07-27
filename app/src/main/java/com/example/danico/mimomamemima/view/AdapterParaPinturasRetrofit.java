package com.example.danico.mimomamemima.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danico.mimomamemima.R;
import com.example.danico.mimomamemima.model.pojo.paraRetrofit.ObraRetrofit;

import java.util.List;

import static com.example.danico.mimomamemima.R.id.imagenRetrofit;

/**
 * Created by danico on 25/07/2018.
 */

public class AdapterParaPinturasRetrofit extends RecyclerView.Adapter {

    private List<ObraRetrofit> internetList;
    private Context context;

    public AdapterParaPinturasRetrofit(List<ObraRetrofit> internetList, Context context) {
        this.context = context;
        this.internetList = internetList;

    }

    @Override
    public RetrofitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //1 - 2- aca voy a obtener el contexto del ViewGroup y el inflador
        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        //3- debo inflar la celda de la obra
        View pinturaRetrofit = layoutInflater.inflate(R.layout.pintura_retrofit, parent, false);
        //4- esto es para guardar la celda inflada en un vieewHolder
        RetrofitViewHolder retrofitViewHolder = new RetrofitViewHolder(pinturaRetrofit);
        return retrofitViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


    }


    @Override
    public int getItemCount() {
        return 0;
    }


    /***  VIEWHOLDER  ***/
    public class RetrofitViewHolder extends RecyclerView.ViewHolder {

        /***atributos de la celda***/
        private TextView textViewname;
        private ImageView pinturaImage;

        /***constructor***/
        public RetrofitViewHolder(View itemView) {
            super(itemView);


            /***conecto los atributos con los layouts***/
            textViewname = itemView.findViewById(R.id.nombreRetrofit);
            pinturaImage = itemView.findViewById(imagenRetrofit);
        }
    }
}
