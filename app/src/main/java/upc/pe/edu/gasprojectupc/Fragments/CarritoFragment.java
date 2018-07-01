package upc.pe.edu.gasprojectupc.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import upc.pe.edu.gasprojectupc.Adapters.OrderViewHolder;
import upc.pe.edu.gasprojectupc.Entities.Distri;
import upc.pe.edu.gasprojectupc.Entities.Order;
import upc.pe.edu.gasprojectupc.Entities.Product;
import upc.pe.edu.gasprojectupc.Entities.Store;
import upc.pe.edu.gasprojectupc.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarritoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarritoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarritoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    RecyclerView recyclerStores;
    ArrayList<Order> listaStores;

    FirebaseAuth mAuth;

    DatabaseReference mDatabase;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CarritoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarritoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarritoFragment newInstance(String param1, String param2) {
        CarritoFragment fragment = new CarritoFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carrito, container, false);

        mDatabase=FirebaseDatabase.getInstance().getReference().child("orders");

        recyclerStores=view.findViewById(R.id.recyclerCarrito);
        listaStores = new ArrayList<>();
        recyclerStores.setLayoutManager(new LinearLayoutManager(getContext()));

        //obtengo id del usuario
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();



        return view;

    }


    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Order,OrderViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Order, OrderViewHolder>
                (Order.class,R.layout.item_order,OrderViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Order model, int position) {

                viewHolder.setName(model.getIdProduct());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setQuantity(model.getQuantity());
                viewHolder.setDate(model.getDate());

            }

            @Override
            public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                OrderViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new OrderViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(final View view, int position) {
                        String mGroupId = mDatabase.push().getKey();
                        mDatabase.child(mGroupId).setValue(new Order());
                        final ArrayList<Order> distriList = new ArrayList<>();



                        mDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {


                                ArrayList<Order> pro = new ArrayList<>();


                                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {



                                    Order distribuidor = messageSnapshot.getValue(Order.class);

                                    //Distri distri = messageSnapshot.getValue(Distri.class);
                                    Log.d("TESTING ","NOMBRE: "+distribuidor.getIdProduct());
                                    distriList.add(distribuidor);
                                    //distriList1.add(distri);



                                }


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });



                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(getContext(), "Item long clicked at " + position, Toast.LENGTH_SHORT).show();
                    }
                });
                return viewHolder;
            }
        };

        recyclerStores.setAdapter(firebaseRecyclerAdapter);
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
