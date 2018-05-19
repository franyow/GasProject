package upc.pe.edu.gasprojectupc.Fragments;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import upc.pe.edu.gasprojectupc.Entities.Distribuidor;
import upc.pe.edu.gasprojectupc.Entities.Store;
import upc.pe.edu.gasprojectupc.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailStoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailStoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailStoreFragment extends Fragment {

    DatabaseReference mDatabase;






    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView textDescription,textNombre;
    ImageView imageDetalle;
    FloatingActionButton buttonCarrito;


    public DetailStoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailStoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailStoreFragment newInstance(String param1, String param2) {
        DetailStoreFragment fragment = new DetailStoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        textDescription=view.findViewById(R.id.textDescription);
        textNombre=view.findViewById(R.id.textNombreD);

        imageDetalle=view.findViewById(R.id.imageDetail);
        buttonCarrito = view.findViewById(R.id.addFloat);


        //recibe bundle de objeto
        Bundle storeObject = getArguments();
        Distribuidor distribuidor = null;

        //String key = null;

        if(storeObject != null) {
            distribuidor = (Distribuidor) storeObject.getSerializable("object");
            //mDatabase= FirebaseDatabase.getInstance().getReference().child("suppliers");
            //mDatabase.keepSynced(true);}

            textDescription.setText(distribuidor.getDescription());
            Picasso.with(getContext()).load(distribuidor.getImage()).into(imageDetalle);
            textNombre.setText(distribuidor.getName());
        }



            //Query query = mDatabase.equalTo(key);

            /*mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    textDescription.setText(value);

                    dataSnapshot.getChildren();

                    Distribuidor distri = dataSnapshot.getValue(Distribuidor.class);
                    //Picasso.with(getContext()).load(distri.getImage()).into(imageDetalle);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getContext(), "on Cancelled", Toast.LENGTH_SHORT).show();

                }
            });

        buttonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CarritoFragment();
                getFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();


            }
        });*/



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
