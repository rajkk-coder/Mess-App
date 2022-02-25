package com.ooslab.messfee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;

public class MenuAdapter extends FirebaseRecyclerAdapter<Item, MenuAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private String username;
    public MenuAdapter(@NonNull FirebaseRecyclerOptions<Item> options, String str) {
        super(options);
        this.username=str;
    }



    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Item model) {
        holder.name.setText("Name :"+model.getName());
        holder.price.setText("Price :"+model.getPrice());
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus= DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.add_popup))
                        .setExpanded(true,1380)
                        .create();
                //dialogPlus.show();
                View view1=dialogPlus.getHolderView();
                EditText name=view1.findViewById(R.id.idName);
                EditText price=view1.findViewById(R.id.idPrice);
                EditText quantity=view1.findViewById(R.id.idQuantity);
                Button btn=view1.findViewById(R.id.addButton);
                name.setText(model.getName());
                price.setText(model.getPrice());
                dialogPlus.show();

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Order order=new Order(name.getText().toString(),price.getText().toString()
                        ,quantity.getText().toString());
                        HashMap<String,Object>map=new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("price",price.getText().toString());
                        map.put("quantity",quantity.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child(username).child("Bill")
                        .push().setValue(order)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.name.getContext(), "Added", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.name.getContext(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu,parent,false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageButton btnAdd;
        TextView name,price;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            btnAdd=itemView.findViewById(R.id.menuAdd);
            name= itemView.findViewById(R.id.menuName);
            price= itemView.findViewById(R.id.menuPrice);

        }
    }
}

