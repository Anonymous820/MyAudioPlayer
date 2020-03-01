package com.example.myaudioplayer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerSongsAdapter extends RecyclerView.Adapter<RecyclerSongsAdapter.ViewHolder> {

    private static final String TAGG = "YYYY";
    ArrayList<SuitcaseSong> suitcaseSongArrayList;
    OnItemCliceKedL mcallback;
    Context context;
    int lastItemPosition=-1;

    public RecyclerSongsAdapter(ArrayList<SuitcaseSong> suitcaseSongArrayList, Context context,OnItemCliceKedL mcallback) {
        this.suitcaseSongArrayList = suitcaseSongArrayList;
        this.context = context;
        this.mcallback=mcallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_recyclewr,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        lastItemPosition = position;
        SuitcaseSong song=suitcaseSongArrayList.get(position);

        holder.titletv.setText(song.getTitle());
        holder.songiv.setImageResource(song.getImageid());


    }

    @Override
    public int getItemCount() {
        return suitcaseSongArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView songiv;
        TextView titletv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            songiv=itemView.findViewById(R.id.reciv);
            titletv=itemView.findViewById(R.id.rectitletv);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    SuitcaseSong song=suitcaseSongArrayList.get(position);
                    mcallback.OnClickedImet(song);
                }
            });

        }
    }
}
