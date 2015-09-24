package pt.ei2015.ementasipleiria;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements opcaoDrawerAdapter.ClickListener  {


    public static final String USER_LEARN_DRAWER = "UserLearnDrawer";

    private View drawerFragment;
    private RecyclerView recyclerView;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearnDrawer=false;
    private boolean mFromSaveInstanceState=false;
    private opcaoDrawerAdapter adapter;

    MainActivity mMainActivity;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mUserLearnDrawer = Boolean.parseBoolean(mMainActivity.readfromPreferences(getActivity(), USER_LEARN_DRAWER, "false"));
        if(savedInstanceState!= null){
            mFromSaveInstanceState=true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        List<String> t = new LinkedList<>();
        t.add("Campus 1 - ESSECS");
        t.add("Campus 2 - ESTG & ESSLEI");
        t.add("Campus 3 - ESAD.CR");
        t.add("Campus 4 - ESTM");
        t.add("Serviços Centrais");


        adapter = new opcaoDrawerAdapter(getActivity(),t);
        adapter.setClickListener(this);
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
       // recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null, false, true));
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }


    public void setUp(MainActivity mainActivity, int fragmentId, Toolbar toolbar, DrawerLayout drawerLayout) {
        this.mMainActivity=mainActivity;
        mDrawerLayout=drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnDrawer) {
                    mUserLearnDrawer = true;
                    mMainActivity.saveToPreferences(getActivity(), USER_LEARN_DRAWER, String.valueOf(mUserLearnDrawer));
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        drawerFragment = getActivity().findViewById(fragmentId);
        if(!mUserLearnDrawer && !mFromSaveInstanceState){
            mDrawerLayout.openDrawer(drawerFragment);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }



    @Override
    public void itemClicked(View view, int position) {
        mMainActivity.updateSpinner(position);

        if(mDrawerLayout!=null)
            mDrawerLayout.closeDrawers();
    }
}
