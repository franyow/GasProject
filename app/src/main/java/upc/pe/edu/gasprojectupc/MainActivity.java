package upc.pe.edu.gasprojectupc;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import upc.pe.edu.gasprojectupc.Entities.Distribuidor;
import upc.pe.edu.gasprojectupc.Entities.Store;
import upc.pe.edu.gasprojectupc.Fragments.CardsFragment;
import upc.pe.edu.gasprojectupc.Fragments.CarritoFragment;
import upc.pe.edu.gasprojectupc.Fragments.DetailStoreFragment;
import upc.pe.edu.gasprojectupc.Fragments.FavoritesFragment;
import upc.pe.edu.gasprojectupc.Fragments.MapFragment;
import upc.pe.edu.gasprojectupc.Fragments.OrderDetailFragment;
import upc.pe.edu.gasprojectupc.Fragments.OrderFragment;
import upc.pe.edu.gasprojectupc.Fragments.StoreFragment;
import upc.pe.edu.gasprojectupc.Interfaces.IComunicateFragments;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CardsFragment.OnFragmentInteractionListener, DetailStoreFragment.OnFragmentInteractionListener, FavoritesFragment.OnFragmentInteractionListener, MapFragment.OnFragmentInteractionListener, OrderDetailFragment.OnFragmentInteractionListener,OrderFragment.OnFragmentInteractionListener,StoreFragment.OnFragmentInteractionListener, IComunicateFragments, CarritoFragment.OnFragmentInteractionListener {

    DetailStoreFragment detailStoreFragment;

    ANImageView userPhotoImageView;
    TextView userNameTextView;
    TextView userEmailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View header=navigationView.getHeaderView(0);

        userPhotoImageView = (ANImageView)  header.findViewById(R.id.userPhotoANImageView);
        userNameTextView = (TextView) header.findViewById(R.id.userNameTextView);
        userEmailTextView = (TextView) header.findViewById(R.id.userEmailTextView);

        Bundle bundle = this.getIntent().getExtras();

        ArrayList<String> profile = new ArrayList<>();

        profile = bundle.getStringArrayList("Profile");

        userNameTextView.setText(profile.get(0));
        userEmailTextView.setText(profile.get(1));
        userPhotoImageView.setImageUrl(profile.get(2));



        //inicia fragment principal

        Fragment fragment = new StoreFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment myFragment = null;
        boolean fragmentSelected = false;


        if (id == R.id.nav_camera) {
            myFragment = new StoreFragment();
            fragmentSelected=true;
        } else if (id == R.id.nav_gallery) {
            myFragment = new OrderDetailFragment();
            fragmentSelected=true;

        } else if (id == R.id.nav_slideshow) {
            myFragment = new FavoritesFragment();
            fragmentSelected = true;

        } else if (id == R.id.nav_manage) {
            myFragment = new CardsFragment();
            fragmentSelected = true;

        } else if (id == R.id.nav_share) {
            myFragment = new MapFragment();
            fragmentSelected = true;

        }

        if (fragmentSelected==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,myFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }




    @Override
    public void enviarDistri(Distribuidor distribuidor) {
        detailStoreFragment = new DetailStoreFragment();
        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("object",distribuidor);
        detailStoreFragment.setArguments(bundleEnvio);

        //carga fragment en activity
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,detailStoreFragment).addToBackStack(null).commit();
    }
}
