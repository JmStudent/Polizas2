package com.salesianos.jose.polizas;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Polizas extends AppCompatActivity /*implements CompoundButton.OnCheckedChangeListener/*, AdapterView.OnItemSelectedListener */{

    //Declaramos las variables
    private Spinner mEdad = null;
    private RadioGroup rg = null;
    private RelativeLayout mCaja = null;
    private TextView mHijos = null;
    private ListView mNumHijos = null;
    private Button mRemove = null;
    private TextView mImporte = null;

    //Variables que no se extraen del modo gráfico para trabajar con ellas.
    private float discSons = 0;
    private float importe = 40f;
    private int incremento = 0;

    /*
    * Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
spinner.setAdapter(adapter);
    *
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polizas);

        //Damos valores al spinner
        mEdad = (Spinner) findViewById(R.id.insertaedad);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.insertaedad, android.R.layout.simple_spinner_item);
        mEdad.setAdapter(adapter);

        //Damos valores al ListView
        mNumHijos = (ListView) findViewById(R.id.selectSon);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.numSons, android.R.layout.simple_spinner_item);
        mNumHijos.setAdapter(adapter2);

        //Damos valores a la variables
        mImporte = (TextView) findViewById(R.id.cajatexto);
        mHijos = (TextView) findViewById(R.id.numero);
        mCaja = (RelativeLayout) findViewById(R.id.relativeBox);
        mRemove = (Button) findViewById(R.id.borrar);

        //Aquí contenemos al radiogroup y controlamos sus acciones
        rg = (RadioGroup) findViewById(R.id.state);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.married:
                        mCaja.setVisibility(View.VISIBLE);
                        break;
                    case R.id.single:
                        mCaja.setVisibility(View.GONE);
                        discSons = 0;
                        mostrarImporte();
                        break;
                }
            }
        });
/*
        mNumHijos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            @Override
            public void onItemSelected (AdapterView < ? > parent, View view,
                                        int pos, long id){

                if (parent.getId() == mNumHijos.getId()) {
                    switch (mNumHijos.getSelectedItemPosition()) {

                    }
                } else if (parent.getId() == mEdad.getId()) {

                }

                if (pos == 4) {
                    incremento++;
                } else if (pos > 4) {
                    for (int i = 5; i < pos; i++)
                        incremento += 5;
                }

                switch (pos) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            })
            mNumHijos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

            {
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
            });*/

            mEdad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (mEdad.getSelectedItemPosition() > 0) {
                        int edad = Integer.parseInt(mEdad.getSelectedItem().toString());
                        if (edad > 34) {
                            for (int i = 34; i < edad; i++)
                                incremento++;
                        } else {
                            incremento = 0;
                        }
                        mostrarImporte();
                    } else {
                        mImporte.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    mImporte.setText("");
                }
            });

        mNumHijos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for(int a = 0; a < parent.getChildCount(); a++)
                {
                    parent.getChildAt(a).setBackgroundColor(Color.WHITE);
                }
                view.setBackgroundColor(Color.GREEN);

                switch (position) {
                    case 0:
                        mHijos.setText("0");
                        discSons = 0;
                        break;
                    case 1:
                        mHijos.setText("1");
                        discSons = importe * 0.05f;
                        break;
                    case 2:
                        mHijos.setText("2");
                        discSons = importe * 0.10f;
                        break;
                    case 3:
                        mHijos.setText("3 o +");
                        discSons = importe * 0.15f;
                        break;
                }
                mostrarImporte();
            }
        });
    }
        /*
        //Esto sirve para recoger los datos del radiogroup
        rg = (RadioGroup) findViewById(R.id.optionHijos);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.zeroSons:
                        mHijos.setText("0");
                        discSons = 0;
                        break;
                    case R.id.oneSon:
                        mHijos.setText("1");
                        discSons = importe * 0.05f;
                        break;
                    case R.id.twoSons:
                        mHijos.setText("2");
                        discSons = importe * 0.10f;
                        break;
                    case R.id.moreSons:
                        mHijos.setText("+2");
                        discSons = importe * 0.15f;
                        break;
                }
            }
        });
        */
        //esto junto con el método onCheckedChanged y el implements Compound.... sirve para recoger
        //los datos del switch (es decir, si se acciona o no)
        //mEstado = (Switch) findViewById(R.id.estado);
        //mEstado.setOnCheckedChangeListener(this);




/*
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mCaja.setVisibility(View.VISIBLE);
        } else {
            mCaja.setVisibility(View.GONE);
            discSons = 0;
        }
    }

    public void showText(View v) {
        String edadCadena = mEdad.getText().toString();
        if (TextUtils.isEmpty(edadCadena)) {
            mEdad.setError("Este campo no puede quedar vacío");
            return;
        }
        int edad = Integer.parseInt(edadCadena);
        if (edad > 34) {
            for (int i = 34; i < edad; i++)
                incremento++;
        }
        mImporte.setText("El importe de su póliza es " + ((importe - discSons) + incremento) + " € / mes ");
    }
*/
    public void hideText(View v) {
        rg.clearCheck();
        mCaja.setVisibility(View.GONE);
        mImporte.setText("");
        mEdad.setSelection(0);
        incremento = 0;
        discSons = 0;
        mHijos.setText("");
    }

    public void mostrarImporte() {
        mImporte.setText("El importe de su póliza es " + ((importe - discSons) + incremento) + " € / mes ");
    }
}