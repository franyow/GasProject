package upc.pe.edu.gasprojectupc.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import upc.pe.edu.gasprojectupc.R;

public class OrderViewHolder extends RecyclerView.ViewHolder{

    View mView;
    View.OnClickListener listener;
    public OrderViewHolder(View itemView) {
        super(itemView);
        mView = itemView;



        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });
    }

    public void setName(String name) {
        TextView post_name = mView.findViewById(R.id.nameDistri);
        post_name.setText(name);
    }

    public void setQuantity(String prod) {
        TextView post_descrip = mView.findViewById(R.id.textNombrep);
        post_descrip.setText(prod);
    }

    public void setPrice(String price) {
        TextView post_descrip = mView.findViewById(R.id.textPrice);
        post_descrip.setText(price);
    }

    public void setDate(String date) {
        TextView post_date = mView.findViewById(R.id.textDate);
        post_date.setText(date);
    }

    /*public void setImg(Context ctx, String image) {
        ImageView post_img = (ImageView) mView.findViewById(R.id.imageView3);
        Picasso.with(ctx).load(image).into(post_img);
    }*/

    private OrderViewHolder.ClickListener mClickListener;

    //Interface to send callbacks...
    public interface ClickListener{
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(OrderViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }


}
