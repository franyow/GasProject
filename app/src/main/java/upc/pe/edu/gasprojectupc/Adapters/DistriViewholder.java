package upc.pe.edu.gasprojectupc.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import upc.pe.edu.gasprojectupc.R;

public class DistriViewholder extends RecyclerView.ViewHolder {
    View mView;
    View.OnClickListener listener;

    TextView post_name;
    TextView post_descrip;
    ImageView post_img;


    public DistriViewholder(View itemView) {
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
        TextView post_name = mView.findViewById(R.id.textTitulo);
        post_name.setText(name);
    }

    public void setDescrip(String description) {
        TextView post_descrip = mView.findViewById(R.id.textShortDes);
        post_descrip.setText(description);
    }

    public void setImg(Context ctx, String image) {
        ImageView post_img = (ImageView) mView.findViewById(R.id.imageView3);
        Picasso.with(ctx).load(image).into(post_img);
    }



    private DistriViewholder.ClickListener mClickListener;

    //Interface to send callbacks...
    public interface ClickListener{
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(DistriViewholder.ClickListener clickListener){
        mClickListener = clickListener;
    }


}