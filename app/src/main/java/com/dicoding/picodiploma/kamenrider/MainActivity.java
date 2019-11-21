package com.dicoding.picodiploma.kamenrider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategoryKR;
    private ArrayList<KamenRider> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Kamen Rider Wiki");

        rvCategoryKR = findViewById(R.id.rv_categoryKR);
        rvCategoryKR.setHasFixedSize(true);

        list.addAll(KamenRiderData.getListData());
        showKamenRiderGrid();

    }

    private void showKamenRiderList() {
        rvCategoryKR.setLayoutManager(new LinearLayoutManager(this));
        ListKamenRiderAdapter listKamenRiderAdapter = new ListKamenRiderAdapter(this);
        listKamenRiderAdapter.setListKamenRider(list);
        rvCategoryKR.setAdapter(listKamenRiderAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
        //super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_list:
                showKamenRiderList();
                break;
            case R.id.action_grid:
                showKamenRiderGrid();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showKamenRiderGrid() {
        rvCategoryKR.setLayoutManager(new GridLayoutManager(this, 2));
        GridKamenRiderAdapter gridKamenRiderAdapter = new GridKamenRiderAdapter(this);
        gridKamenRiderAdapter.setListKamenRider(list);
        rvCategoryKR.setAdapter(gridKamenRiderAdapter);

        ItemClickSupport.addTo(rvCategoryKR).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedKamenRider(list.get(position));
            }
        });
    }

    private void showSelectedKamenRider(KamenRider kamenRider){
        Toast.makeText(this, kamenRider.getName(), Toast.LENGTH_SHORT).show();
        Intent detail = new Intent(MainActivity.this, DetailActivity.class);
        detail.putExtra(DetailActivity.EXTRA_NAME,kamenRider.getName());
        detail.putExtra(DetailActivity.EXTRA_HEI,kamenRider.getHeisei());
        detail.putExtra(DetailActivity.EXTRA_RLS,kamenRider.getRealese());
        detail.putExtra(DetailActivity.EXTRA_EPS,kamenRider.getTotaleps());
        detail.putExtra(DetailActivity.EXTRA_PRO,kamenRider.getProducer());
        detail.putExtra(DetailActivity.EXTRA_SIN,kamenRider.getDesc());
        detail.putExtra(DetailActivity.EXTRA_LOGO,kamenRider.getPhoto());

        startActivity(detail);

    }
}
