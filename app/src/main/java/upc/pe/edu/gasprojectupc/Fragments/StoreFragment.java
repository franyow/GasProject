package upc.pe.edu.gasprojectupc.Fragments;

import android.app.Activity;
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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import upc.pe.edu.gasprojectupc.Adapters.DistriViewholder;
import upc.pe.edu.gasprojectupc.Entities.Distri;
import upc.pe.edu.gasprojectupc.Entities.Distribuidor;
import upc.pe.edu.gasprojectupc.Entities.Product;
import upc.pe.edu.gasprojectupc.Entities.Store;
import upc.pe.edu.gasprojectupc.Interfaces.IComunicateFragments;
import upc.pe.edu.gasprojectupc.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerStores;
    ArrayList<Distribuidor> listaStores;

    DatabaseReference mDatabase;
    DatabaseReference detailSupplier;

    Activity activity;
    IComunicateFragments interfaceComunicateFragments;

    public StoreFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
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
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        //firebase data
        mDatabase=FirebaseDatabase.getInstance().getReference().child("suppliers");
        mDatabase.keepSynced(true);




        //detail
        detailSupplier=FirebaseDatabase.getInstance().getReference().child("suppliers");


        recyclerStores=view.findViewById(R.id.recycler_store);
        listaStores = new ArrayList<>();
        recyclerStores.setLayoutManager(new LinearLayoutManager(getContext()));


        //StoreAdapter adapter = new StoreAdapter(listaStores);
        //recyclerStores.setAdapter(adapter);

        /*adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceComunicateFragments.enviarTienda(listaStores.get(recyclerStores.getChildAdapterPosition(view)));

            }
        });*/

        //llenarLista();

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

        //necesario para comunica lista y detalle
        if(context instanceof Activity){
            this.activity = (Activity) context;
            interfaceComunicateFragments= (IComunicateFragments) this.activity;
        }


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



    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Distribuidor,DistriViewholder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Distribuidor, DistriViewholder>
                (Distribuidor.class,R.layout.item_list_store,DistriViewholder.class,mDatabase) {
            @Override
            protected void populateViewHolder(DistriViewholder viewHolder, Distribuidor model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setDescrip(model.getDescription());
                viewHolder.setImg(getContext(),model.getImage());

            }

            @Override
            public DistriViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
                DistriViewholder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new DistriViewholder.ClickListener() {
                    @Override
                    public void onItemClick(final View view, int position) {
                        String mGroupId = mDatabase.push().getKey();
                        mDatabase.child(mGroupId).setValue(new Distribuidor());
                        final ArrayList<Distribuidor> distriList = new ArrayList<>();
                        final ArrayList<Distri> distriList1 = new ArrayList<>();


                        //Toast.makeText(getContext(), "Item clicked at " + position+" "+distribuidor.getName() , Toast.LENGTH_SHORT).show();

                        mDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                /*GenericTypeIndicator<Map<String, Distri>> t = new GenericTypeIndicator<Map<String, Distri>>() {};
                                Map<String, Distri> map = dataSnapshot.getValue(t);

                                List<Distri> targetList = new ArrayList<>(map.values());*/
                                ArrayList<Product> pro = new ArrayList<>();


                                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {

                                    Map<String, Object> map = (Map<String, Object>) messageSnapshot.getValue();
                                    String nameProduct = (String) map.get("nameProduct");
                                    String price = (String) map.get("unitPrice");
                                    String imgProduct = (String) map.get("imageProduct");



                                    String date = (String) map.get("date");
                                    String description = (String) map.get("description");
                                    String idSupplier = (String) map.get("idSupplier");
                                    String image = (String) map.get("image");
                                    String latitude = (String) map.get("latitude");
                                    String longitud = (String) map.get("longitud");
                                    String name = (String) map.get("name");
                                    String phone = (String) map.get("phone");


                                    pro.add(new Product(nameProduct,price,imgProduct));

                                    distriList1.add(new Distri(date,description,idSupplier,image,latitude,longitud,name,phone, pro));
                                    Log.e("product list", ""+pro.size());




                                    Distribuidor distribuidor = messageSnapshot.getValue(Distribuidor.class);

                                    //Distri distri = messageSnapshot.getValue(Distri.class);
                                    Log.d("TESTING ","NOMBRE: "+distribuidor.getName());
                                    distriList.add(distribuidor);
                                    //distriList1.add(distri);



                                }


                                interfaceComunicateFragments.enviarDistri(distriList1.get(recyclerStores.getChildAdapterPosition(view)));

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

}
