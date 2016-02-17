package com.salesianos.jose.polizas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class Polizas extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    //Declaramos las variables
    private TextView mImporte = null;
    private Switch mEstado = null;
    private TextView mHijos = null;
    private RelativeLayout mCaja = null;
    private float discSons = 0;
    private float importe = 40f;
    private int incremento = 0;
    private RadioGroup rg = null;

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

        //Damos valores a la variables
        mImporte = (TextView) findViewById(R.id.cajatexto);
        mEstado = (Switch) findViewById(R.id.estado);
        mHijos = (TextView) findViewById(R.id.numero);
        mCaja = (RelativeLayout) findViewById(R.id.relativeBox);

        //Damos valores al spinner
        Spinner mEdad = (Spinner) findViewById(R.id.insertaedad);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.insertaedad, android.R.layout.simple_spinner_item);

        rg = (RadioGroup) findViewById(R.id.state);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.married:
                        mCaja.setVisibility(View.VISIBLE);
                        break;
                    case R.id.single:
                        mCaja.setVisibility(View.GONE);
                        discSons = 0;
                        break;
                }
            }
        });
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
        mEstado = (Switch) findViewById(R.id.estado);
        mEstado.setOnCheckedChangeListener(this);


    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        mImporte.setText("El importe de su póliza es " + ((importe - discSons) + incremento) + " € / mes ");
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

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
        if(TextUtils.isEmpty(edadCadena)) {
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

    public void hideText(View v) {
        mImporte.setText("");
        incremento = 0;
        discSons = 0;
        mCaja.setVisibility(View.GONE);
        mEstado.setChecked(false);
        rg.clearCheck();
        mHijos.setText("");
    }
}