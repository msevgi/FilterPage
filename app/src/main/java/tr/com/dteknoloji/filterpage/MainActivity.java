
package tr.com.dteknoloji.filterpage;

import java.util.ArrayList;
import java.util.Arrays;

import tr.com.dteknoloji.filterpage.seekbar.ComboSeekBar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener,
         SeekBar.OnSeekBarChangeListener {
   private TextView          textViewDistance;
   private TextView          textViewAlphabet;
   private TextView          textViewPopular;
   private TextView          textViewAll;
   private TextView          textViewCheap;
   private TextView          textViewSuitable;
   private TextView          textViewExpensive;
   private Spinner           spinnerKitchen;
   private Spinner           spinnerConsept;
   private Spinner           spinnerPlace;
   private Spinner           spinnerClothes;
   private TextView          textViewLastSelectSorting;
   private TextView          textViewLastSelectPrice;
   private SpinnerAdapter    adapterKitchen;
   private SpinnerAdapter    adapterConsept;
   private SpinnerAdapter    adapterPlace;
   private SpinnerAdapter    adapterClothes;
   private Button            buttonApply;
   // private SeekBar seekBarDistance;
   private ComboSeekBar      comboSeekBarDistance;
   private ArrayList<String> listKitchen = new ArrayList<>();
   private ArrayList<String> listConsept = new ArrayList<>();
   private ArrayList<String> listPlace   = new ArrayList<>();
   private ArrayList<String> listClothes = new ArrayList<>();
   private FilterModel       filterModel;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      getKitchenList();
      getConseptList();
      getPlaceList();
      getClothesList();
      initItem();
      ArrayList<String> dots = new ArrayList<>();
      dots.add("0");
      dots.add("5");
      dots.add("10");
      dots.add("15");
      dots.add("20");
      comboSeekBarDistance.setAdapter(dots);
      adapterKitchen = new SpinnerAdapter(this, 0, listKitchen);
      spinnerKitchen.setAdapter(adapterKitchen);
      adapterKitchen.setNotifyOnChange(true);
      adapterConsept = new SpinnerAdapter(this, 0, listConsept);
      spinnerConsept.setAdapter(adapterConsept);
      adapterConsept.setNotifyOnChange(true);
      adapterClothes = new SpinnerAdapter(this, 0, listClothes);
      spinnerClothes.setAdapter(adapterClothes);
      adapterClothes.setNotifyOnChange(true);
      adapterPlace = new SpinnerAdapter(this, 0, listPlace);
      spinnerPlace.setAdapter(adapterPlace);
      adapterPlace.setNotifyOnChange(true);
      Bundle bundle = getIntent().getExtras();
       //spinnerlari dolduracak listeyi bundle alabiliriz.
      if (bundle == null) {
         filterModel = new FilterModel();
      }
      else {
         filterModel = (FilterModel) bundle.getSerializable("filter");
         initFirstValue();
      }
   }

   private void initFirstValue() {
      switch (filterModel.getSort()) {
         case FilterModel.SORT_DIST :
            selectSorting(textViewDistance);
            break;
         case FilterModel.SORT_ALP :
            selectSorting(textViewAlphabet);
            break;
         case FilterModel.SORT_POP :
            selectSorting(textViewPopular);
            break;
         default :
            break;
      }
      comboSeekBarDistance.setProgress(filterModel.getDistance());
      switch (filterModel.getPrice()) {
         case FilterModel.ALL :
            selectPrice(textViewAll);
            break;
         case FilterModel.CHEAP :
            selectPrice(textViewCheap);
            break;
         case FilterModel.SUITABLE :
            selectPrice(textViewSuitable);
            break;
         case FilterModel.EXPENSIVE :
            selectPrice(textViewExpensive);
            break;
         default :
            break;
      }
      String kitchen = filterModel.getKitchen();
      if (!TextUtils.isEmpty(kitchen)) {
         spinnerKitchen.setSelection(adapterKitchen.getPosition(kitchen));
      }
      String consept = filterModel.getConsept();
      if (!TextUtils.isEmpty(consept)) {
         spinnerConsept.setSelection(adapterConsept.getPosition(consept));
      }
      String place = filterModel.getPlace();
      if (!TextUtils.isEmpty(place)) {
         spinnerPlace.setSelection(adapterPlace.getPosition(place));
      }
      String clothes = filterModel.getClothes();
      if (!TextUtils.isEmpty(clothes)) {
         spinnerClothes.setSelection(adapterClothes.getPosition(clothes));
      }
   }

   private void getClothesList() {
      String[] list = getResources().getStringArray(R.array.clothes);
      listClothes.addAll(Arrays.asList(list));
   }

   private void getKitchenList() {

      String[] list = getResources().getStringArray(R.array.kitchen);
      listKitchen.addAll(Arrays.asList(list));
   }

   private void getConseptList() {
      String[] list = getResources().getStringArray(R.array.consept);
      listConsept.addAll(Arrays.asList(list));
   }

   private void getPlaceList() {
      String[] list = getResources().getStringArray(R.array.place);
      listPlace.addAll(Arrays.asList(list));
   }

   private void initItem() {
      textViewDistance = (TextView) findViewById(R.id.textview_distance);
      textViewAlphabet = (TextView) findViewById(R.id.textview_alphabet);
      textViewPopular = (TextView) findViewById(R.id.textview_popular);
      textViewAll = (TextView) findViewById(R.id.textview_all);
      textViewCheap = (TextView) findViewById(R.id.textview_cheap);
      textViewSuitable = (TextView) findViewById(R.id.textview_suitable);
      textViewExpensive = (TextView) findViewById(R.id.textview_expensive);
      spinnerKitchen = (Spinner) findViewById(R.id.spinner_kitchen);
      spinnerConsept = (Spinner) findViewById(R.id.spinner_consept);
      spinnerClothes = (Spinner) findViewById(R.id.spinner_clothes);
      spinnerPlace = (Spinner) findViewById(R.id.spinner_place);
      buttonApply = (Button) findViewById(R.id.button_apply);
      // seekBarDistance = (SeekBar) findViewById(R.id.seekbar_distance);
      comboSeekBarDistance = (ComboSeekBar) findViewById(R.id.combo_seekbar_distance);
      comboSeekBarDistance.setOnSeekBarChangeListener(this);
      comboSeekBarDistance.setOnSeekBarChangeListener(this);
      spinnerKitchen.setOnItemSelectedListener(this);
      spinnerPlace.setOnItemSelectedListener(this);
      spinnerClothes.setOnItemSelectedListener(this);
      spinnerConsept.setOnItemSelectedListener(this);
      buttonApply.setOnClickListener(this);
      textViewDistance.setOnClickListener(this);
      textViewAlphabet.setOnClickListener(this);
      textViewPopular.setOnClickListener(this);
      textViewAll.setOnClickListener(this);
      textViewCheap.setOnClickListener(this);
      textViewSuitable.setOnClickListener(this);
      textViewExpensive.setOnClickListener(this);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_main, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();

      // noinspection SimplifiableIfStatement
      if (id == R.id.action_settings) {
         return true;
      }

      return super.onOptionsItemSelected(item);
   }

   private void selectSorting(TextView textView) {
      if (textViewLastSelectSorting != null) {

         textViewLastSelectSorting.setSelected(false);
      }
      textView.setSelected(true);
      textViewLastSelectSorting = textView;
   }

   private void selectPrice(TextView textView) {
      if (textViewLastSelectPrice != null) {

         textViewLastSelectPrice.setSelected(false);
      }
      textView.setSelected(true);
      textViewLastSelectPrice = textView;
   }

   @Override
   public void onClick(View v) {
      if (v.equals(textViewDistance)) {
         filterModel.setSort(FilterModel.SORT_DIST);
         selectSorting(textViewDistance);
      }
      if (v.equals(textViewAlphabet)) {
         filterModel.setSort(FilterModel.SORT_ALP);
         selectSorting(textViewAlphabet);
      }
      if (v.equals(textViewPopular)) {
         filterModel.setSort(FilterModel.SORT_POP);
         selectSorting(textViewPopular);
      }
      if (v.equals(textViewAll)) {
         filterModel.setPrice(FilterModel.ALL);
         selectPrice(textViewAll);
      }
      if (v.equals(textViewCheap)) {
         filterModel.setPrice(FilterModel.CHEAP);
         selectPrice(textViewCheap);
      }
      if (v.equals(textViewSuitable)) {
         filterModel.setPrice(FilterModel.SUITABLE);
         selectPrice(textViewSuitable);
      }
      if (v.equals(textViewExpensive)) {
         filterModel.setPrice(FilterModel.EXPENSIVE);
         selectPrice(textViewExpensive);
      }
      if (v.equals(buttonApply)) {
         Intent intent = new Intent(this, MainActivity.class);
         intent.putExtra("filter", filterModel);
         startActivity(intent);
      }
   }

   @Override
   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      if (parent.equals(spinnerKitchen)) {
         filterModel.setKitchen(spinnerKitchen.getSelectedItem().toString());
      }
      if (parent.equals(spinnerConsept)) {
         filterModel.setConsept(spinnerConsept.getSelectedItem().toString());
      }
      if (parent.equals(spinnerPlace)) {
         filterModel.setPlace(spinnerPlace.getSelectedItem().toString());
      }
      if (parent.equals(spinnerClothes)) {
         filterModel.setClothes(spinnerClothes.getSelectedItem().toString());
      }
   }

   @Override
   public void onNothingSelected(AdapterView<?> parent) {

   }

   @Override
   public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
      filterModel.setDistance(progress);
      Log.i("", "asd " + progress);
   }

   @Override
   public void onStartTrackingTouch(SeekBar seekBar) {

   }

   @Override
   public void onStopTrackingTouch(SeekBar seekBar) {

   }
}
