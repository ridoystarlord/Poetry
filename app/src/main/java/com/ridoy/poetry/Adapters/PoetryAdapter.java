package com.ridoy.poetry.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.ridoy.poetry.API.ApiClient;
import com.ridoy.poetry.API.ApiInterface;
import com.ridoy.poetry.Models.Poetry;
import com.ridoy.poetry.R;
import com.ridoy.poetry.Responses.DeletePoetry;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoetryAdapter extends RecyclerView.Adapter<PoetryAdapter.PoetryViewHolder> {

    Context context;
    List<Poetry> list;

    public PoetryAdapter(Context context, List<Poetry> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PoetryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.poetry_list_layout,parent,false);
        return new PoetryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PoetryViewHolder holder, int position) {
        holder.poetName.setText(list.get(position).getPoet_name());
        holder.dataTime.setText(list.get(position).getDate_time());
        holder.poetry.setText(list.get(position).getPoetry_data());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClient.getClient().deletePoetry(String.valueOf(list.get(position).getId())).enqueue(new Callback<DeletePoetry>() {
                    @Override
                    public void onResponse(Call<DeletePoetry> call, Response<DeletePoetry> response) {
                        if (response!=null){
                            if (response.body().getStatus().equals("1")){
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                list.remove(position);
                                notifyDataSetChanged();
                            }else {
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DeletePoetry> call, Throwable t) {
                        Toast.makeText(context, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PoetryViewHolder extends RecyclerView.ViewHolder {
        TextView poetName,dataTime,poetry;
        AppCompatButton update,delete;
        public PoetryViewHolder(@NonNull View itemView) {
            super(itemView);
            poetName=itemView.findViewById(R.id.poetName);
            dataTime=itemView.findViewById(R.id.dateTime);
            poetry=itemView.findViewById(R.id.poetryData);
            update=itemView.findViewById(R.id.updateButton);
            delete=itemView.findViewById(R.id.deleteButton);
        }
    }
}
