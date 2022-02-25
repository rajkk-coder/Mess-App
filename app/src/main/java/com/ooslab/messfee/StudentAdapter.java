package com.ooslab.messfee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    Context context;
    List<Detail>list;
    public StudentAdapter(Context context,List<Detail>list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.student_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        if(!list.isEmpty() && list.size()>0){
            Detail detail=list.get(position);
            holder.roll.setText(detail.getId());
            holder.name.setText(detail.getName());
        }else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView roll,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roll=itemView.findViewById(R.id.txtRoll);
            name=itemView.findViewById(R.id.txtName);
        }
    }
}

