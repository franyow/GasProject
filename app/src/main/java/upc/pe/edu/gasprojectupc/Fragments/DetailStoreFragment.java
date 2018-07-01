package upc.pe.edu.gasprojectupc.Fragments;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import upc.pe.edu.gasprojectupc.Adapters.CarritoAdapter;
import upc.pe.edu.gasprojectupc.Entities.Distri;
import upc.pe.edu.gasprojectupc.Entities.Distribuidor;
import upc.pe.edu.gasprojectupc.Entities.Order;
import upc.pe.edu.gasprojectupc.Entities.Product;
import upc.pe.edu.gasprojectupc.Entities.Store;
import upc.pe.edu.gasprojectupc.R;

import static android.widget.Toast.LENGTH_LONG;


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

    RecyclerView recyclerProducts;
    ArrayList<Order> listProducts;




    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView textNombre;
    ImageView imageDetalle;
    Button buttonAddP;

    FloatingActionButton buttonCarrito;

    //referencia

    FirebaseAuth mAuth;






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


        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();



        textNombre=view.findViewById(R.id.textNombreD);

        listProducts = new ArrayList<>();

        imageDetalle=view.findViewById(R.id.imageDetail);
        buttonCarrito = view.findViewById(R.id.addFloat);
        recyclerProducts = view.findViewById(R.id.recyclerProducts);
        buttonAddP = view.findViewById(R.id.buttonAddPro);

        recyclerProducts.setLayoutManager(new GridLayoutManager(getContext(),2));





        //recibe bundle de objeto
        Bundle storeObject = getArguments();
        Distri distribuidor = null;

        CarritoAdapter adapter = new CarritoAdapter(listProducts);
        recyclerProducts.setAdapter(adapter);


        if(storeObject != null) {
            distribuidor = (Distri) storeObject.getSerializable("object");
            mDatabase= FirebaseDatabase.getInstance().getReference();
            //mDatabase.keepSynced(true);}

            //textDescription.setText(distribuidor.getDescription());
            Picasso.with(getContext()).load(distribuidor.getImage()).into(imageDetalle);
            textNombre.setText(distribuidor.getName());
            Log.e("FUENTES","A" + distribuidor.getProducts().get(0));

            /*for(int i=0;i<distribuidor.getProducts().size();i++){
                Product p = new Product();
                p= distribuidor.getProducts().get(i);
                listProducts.add(p);
            }*/






           /* mDatabase.child("products").child().child(distribuidor.getIdSupplier()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Product product = dataSnapshot.getValue(Product.class);
                    //Obtenemos los valores que queres
                    String idProduct = product.getIdSupplier();


                    Log.e("id de producto: " , "" + idProduct );




                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
*/
        }



        buttonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CarritoFragment();
                getFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();


            }
        });



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
