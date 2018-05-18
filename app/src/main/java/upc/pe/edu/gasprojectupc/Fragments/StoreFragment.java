package upc.pe.edu.gasprojectupc.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import upc.pe.edu.gasprojectupc.Adapters.DistriViewholder;
import upc.pe.edu.gasprojectupc.Entities.Distribuidor;
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
    ArrayList<Store> listaStores;
    DatabaseReference mDatabase;
    DatabaseReference detailSupplier;



    //
    // read the index key



    Activity activity;
    IComunicateFragments interfaceComunicateFragments;

    public StoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreFragment.
     */
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

    private void llenarLista() {
        listaStores.add(new Store("Illidan",R.drawable.illidan,"Illidan Tempestira fue el autoproclamado Señor de Terrallende , que gobernó desde el Templo Oscuro. Illidan nació como un un elfo de la noche, y, en palabras de Maiev Shadowsong \"no es un demonio ni un elfo de la noche, sino algo más\". Es el hermano gemelo de Malfurion, y, al igual que él, siempre estuvo enamorado de Tyrande Whisperwind .",R.drawable.illidan_detail,"Señor de terrallente"));
        listaStores.add(new Store("Valeera",R.drawable.valeera,"Valeera Sanguinar es un pícaro elfo de sangre ofrecida en World of Warcraft de la CC ilimitada.Valeera aparece en World of Warcraft: The Comic . Descrito como \"joven, caliente y amargo\", se ha dado cuenta de que el mundo se ha convertido en un lugar muy diferente, ya que la destrucción de Quel'Thalas . Se dijo desde el principio que ella se siente atraída físicamente a la protagonista principal de la serie humana. Esta idea no ha sido visitada en el cómic y Varian incluso se refiere a ella como un niño varias veces. ",R.drawable.valeera_detail,"Valeera Sanguinar"));

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

        //comunica lista y detalle
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
                    public void onItemClick(View view, int position) {
                        String mGroupId = mDatabase.push().getKey();
                        mDatabase.child(mGroupId).setValue(new Distribuidor());

                        Toast.makeText(getContext(), "Item clicked at " + position+" "+mGroupId , Toast.LENGTH_SHORT).show();



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
