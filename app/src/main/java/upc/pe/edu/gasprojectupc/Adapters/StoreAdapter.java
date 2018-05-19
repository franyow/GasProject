package upc.pe.edu.gasprojectupc.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import upc.pe.edu.gasprojectupc.Entities.Store;
import upc.pe.edu.gasprojectupc.R;

/**
 * Created by wes_o on 19/04/2018.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> implements View.OnClickListener{

    ArrayList<Store> listaDatos;
    private View.OnClickListener listener;

    public StoreAdapter(ArrayList<Store> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_store,null,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titulo.setText(listaDatos.get(position).getNombre());
        holder.imagen.setImageResource(listaDatos.get(position).getFoto());
        holder.shortdescript.setText(listaDatos.get(position).getShortDescription());


    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;

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
        ImageView imagen, detalleImg;
        TextView titulo, shortdescript, description;
        CardView cv;
        //private AdapterView.OnItemClickListener listener;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen=(ImageView) itemView.findViewById(R.id.imageView3);
            titulo=(TextView) itemView.findViewById(R.id.textTitulo);
            shortdescript = (TextView) itemView.findViewById(R.id.textShortDes);
            //detalleImg = itemView.findViewById(R.id.imageDetail);
            //description = itemView.findViewById(R.id.textDescription);
            //cv=(CardView) itemView.findViewById(R.id.cardviewid);




        }


    }
}
