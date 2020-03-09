package com.hafidza.trashme.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hafidza.trashme.R;
import com.hafidza.trashme.activities.DetailsKreasiActivity;
import com.hafidza.trashme.models.KreasiViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class KreasiAdapter extends RecyclerView.Adapter<KreasiAdapter.ViewHolder> {

    //private LayoutInflater layoutInflater;
    private  Context context;
    private List<KreasiViewModel> data;


    public KreasiAdapter(Context context, List<KreasiViewModel> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_kreasi, viewGroup, false);
        //View view = LayoutInflater.inflate(R.layout.custom_view_kreasi,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        // bind the textview with data receiver
        final KreasiViewModel kreasiItem = data.get(i);

        viewHolder.imageKreasi.setImageResource(kreasiItem.getImage());

        final String title = data.get(i).getTitle();
        viewHolder.textTitle.setText(title);

        final String desc = data.get(i).getDesc();
        viewHolder.textDescription.setText(desc);

        viewHolder.listKreasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start new intent

                Intent i = new Intent(v.getContext(), DetailsKreasiActivity.class);
                i.putExtra("imageKreasi", kreasiItem.getImage());
                i.putExtra("title", title);
                i.putExtra("desc", desc);
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout listKreasi;
        ImageView imageKreasi;
        TextView textTitle, textDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageKreasi = itemView.findViewById(R.id.imageKreasi);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            listKreasi = itemView.findViewById(R.id.listKreasi);


        }
    }
}
