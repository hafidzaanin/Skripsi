package com.example.trashme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

    //private LayoutInflater layoutInflater;
    private  Context context;
    private List<TipsViewModel> data;


    TipsAdapter(Context context, List<TipsViewModel> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_tips, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        // bind the textview with data receiver
        final TipsViewModel tipsItem = data.get(i);

        viewHolder.imageTips.setImageResource(tipsItem.getImageTips());

        final String title2 = data.get(i).getTitle();
        viewHolder.textTitle2.setText(title2);

        final String desc2 = data.get(i).getDesc();
        viewHolder.textDescription2.setText(desc2);

       // viewHolder.listTips.setOnClickListener(new View.OnClickListener() {
        //     @Override
        // public void onClick(View v) {
                // start new intent
        //      Intent i = new Intent(v.getContext(),DetailTipsActivity.class);
        //      i.putExtra("imageTips", tipsItem.getImageTips());
        //      i.putExtra("titleTips", title2);
        //      i.putExtra("descTips", desc2);
        //      v.getContext().startActivity(i);
        //  }
        //});

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout listTips;
        ImageView imageTips;
        TextView textTitle2, textDescription2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageTips = itemView.findViewById(R.id.imageTips);
            textTitle2 = itemView.findViewById(R.id.textTitle2);
            textDescription2 = itemView.findViewById(R.id.textDescription2);
            listTips = itemView.findViewById(R.id.listTips);


        }
    }
}
