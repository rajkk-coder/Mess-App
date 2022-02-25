package com.ooslab.messfee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class BillAdapter extends FirebaseRecyclerAdapter<Order,BillAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BillAdapter(@NonNull FirebaseRecyclerOptions<Order> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Order model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getTotalPrice());
        holder.quantity.setText(model.getQuantity());

    }
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order,parent,false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,quantity;
        ImageButton delete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
           name=itemView.findViewById(R.id.ordName);
            price= itemView.findViewById(R.id.ordPrice);
            quantity=itemView.findViewById(R.id.ordQuantity);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
