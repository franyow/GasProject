package upc.pe.edu.gasprojectupc.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import upc.pe.edu.gasprojectupc.Entities.Order;
import upc.pe.edu.gasprojectupc.Entities.Product;
import upc.pe.edu.gasprojectupc.Fragments.CarritoFragment;
import upc.pe.edu.gasprojectupc.MainActivity;
import upc.pe.edu.gasprojectupc.R;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> implements View.OnClickListener{

    ArrayList<Order> listaDatos;
    private View.OnClickListener listener;
    Context context;




    public CarritoAdapter(ArrayList<Order> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,null,false);
        view.setOnClickListener(this);
        context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.product.setText(listaDatos.get(position).getIdProduct());
        holder.price.setText(listaDatos.get(position).getPrice());
        holder.quantity.setText(listaDatos.get(position).getQuantity());
        holder.date.setText(listaDatos.get(position).getDate());






    }



    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView product;
        TextView quantity;
        TextView price;
        TextView date;



        public ViewHolder(View itemView) {
            super(itemView);
            product  =(TextView) itemView.findViewById(R.id.textNombrep);
            quantity = itemView.findViewById(R.id.nameDistri);
            price = itemView.findViewById(R.id.textPrice);
            date = itemView.findViewById(R.id.textDate);


        }


    }
}