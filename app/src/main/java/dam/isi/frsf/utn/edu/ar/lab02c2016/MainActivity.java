package dam.isi.frsf.utn.edu.ar.lab02c2016;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private ToggleButton reservarMesa;
    private Spinner horarios;
    private Switch reservarAntes;
    private TextView resultados;
    private TextView cuenta;
    private RadioButton rbPlato;
    private RadioButton rbPostre;
    private RadioButton rbBebida;
    private Button agregar;
    private Button confirmar;
    private Button reiniciar;
    private ListView listado;

    public ArrayList<ElementoMenu> elementos;
    public ElementoMenu itemSeleccionado;
    public ElementoMenu cuentaTotal;
    public int opcion;
    public Integer posicion;
    public ElementoMenu[] listaBebidas;
    public ElementoMenu[] listaPlatos;
    public ElementoMenu[] listaPostres;
    public ArrayList<String> listaStringBebidas;
    public ArrayList<String> listaStringPlatos;
    public ArrayList<String> listaStringPostres;
    public String[] listaHorarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         reservarMesa = (ToggleButton) findViewById(R.id.reservar_mesa);
         horarios = (Spinner) findViewById(R.id.spinner_horarios);
         reservarAntes = (Switch) findViewById(R.id.reservar_antes);
         resultados = (TextView) findViewById(R.id.txt_resultados);
         cuenta = (TextView) findViewById(R.id.txt_cuenta_total);
         rbPlato = (RadioButton) findViewById(R.id.rb_plato);
         rbPostre = (RadioButton) findViewById(R.id.rb_postre);
         rbBebida = (RadioButton) findViewById(R.id.rb_bebidas);
         agregar = (Button) findViewById(R.id.btn_agregar);
         confirmar = (Button) findViewById(R.id.btn_confirmar);
         reiniciar = (Button) findViewById(R.id.btn_reiniciar);
         listado = (ListView) findViewById(R.id.lv_listado);

        iniciarListas();

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaHorarios);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        horarios.setAdapter(spinnerArrayAdapter);

        elementos = new ArrayList<ElementoMenu>();
        itemSeleccionado = new ElementoMenu();
        cuentaTotal = new ElementoMenu();
        cuentaTotal.setNombre("Total");
        cuentaTotal.setPrecio((double) 0);
        opcion = 0;
        posicion = -1;


        listado.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3){
                posicion = position;
            }
        });


        agregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String text = "";

                itemSeleccionado = null;

                switch (opcion){
                    case 0:
                        text = "Seleccione una opción: Platos, Postres o Bebidas.";
                        break;
                    case 1:
                        if(posicion==-1){
                            text = "Seleccione un plato.";
                            break;
                        }
                        itemSeleccionado = listaPlatos[posicion];
                        text = itemSeleccionado.getNombre() + " agregado/a.";
                        break;
                    case 2:
                        if(posicion==-1){
                            text = "Seleccione un postre.";
                            break;
                        }
                        itemSeleccionado = listaPostres[posicion];
                        text = itemSeleccionado.getNombre() + " agregado/a.";
                        break;
                    case 3:
                        if(posicion==-1){
                            text = "Seleccione una bebida.";
                            break;
                        }
                        itemSeleccionado = listaBebidas[posicion];
                        text = itemSeleccionado.getNombre() + " agregado/a.";
                        break;
                    default:
                        break;
                }

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                if(itemSeleccionado!=null){
                    elementos.add(itemSeleccionado);
                    Double aux = cuentaTotal.getPrecio();
                    aux = aux + itemSeleccionado.getPrecio();
                    cuentaTotal.setPrecio(aux);
                    cuenta.setText(cuentaTotal.toString());

                    String resultadosAux = "";

                    for(ElementoMenu e: elementos){
                        resultadosAux = resultadosAux + e.toString() + "\r\n";
                    }

                    resultados.setText(resultadosAux);
                }

                itemSeleccionado = new ElementoMenu();
                posicion = -1;
                listado.clearChoices();
            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });

        reiniciar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                elementos.clear();
                cuentaTotal = new ElementoMenu();
                cuentaTotal.setNombre("Total");
                cuentaTotal.setPrecio((double) 0);
                opcion = 0;
                posicion = -1;
                listado.clearChoices();
                listado.setAdapter(null);

                rbPlato.setChecked(false);
                rbPostre.setChecked(false);
                rbBebida.setChecked(false);

                resultados.setText("");
                cuenta.setText("");
            }
        });
    }





    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        ArrayAdapter<String> adaptadorBebidas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, listaStringBebidas);
        ArrayAdapter<String> adaptadorPlatos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, listaStringPlatos);
        ArrayAdapter<String> adaptadorPostres = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, listaStringPostres);

        listado.clearChoices();
        posicion = -1;

        switch(view.getId()) {
            case R.id.rb_plato:
                if (checked)
                    opcion = 1;
                    listado.setAdapter(adaptadorPlatos);
                    break;
            case R.id.rb_postre:
                if (checked)
                    opcion = 2;
                    listado.setAdapter(adaptadorPostres);
                    break;
            case R.id.rb_bebidas:
                if (checked)
                    opcion = 3;
                    listado.setAdapter(adaptadorBebidas);
                    break;
        }
    }


    private void iniciarListas() { // inicia lista de bebidas
        listaBebidas = new ElementoMenu[7];
        listaBebidas[0] = new ElementoMenu(1, "Coca");
        listaBebidas[1] = new ElementoMenu(4, "Jugo");
        listaBebidas[2] = new ElementoMenu(6, "Agua");
        listaBebidas[3] = new ElementoMenu(8, "Soda");
        listaBebidas[4] = new ElementoMenu(9, "Fernet");
        listaBebidas[5] = new ElementoMenu(10, "Vino");
        listaBebidas[6] = new ElementoMenu(11, "Cerveza");

        // inicia lista de platos
        listaPlatos = new ElementoMenu[14];
        listaPlatos[0] = new ElementoMenu(1, "Ravioles");
        listaPlatos[1] = new ElementoMenu(2, "Gnocchi");
        listaPlatos[2] = new ElementoMenu(3, "Tallarines");
        listaPlatos[3] = new ElementoMenu(4, "Lomo");
        listaPlatos[4] = new ElementoMenu(5, "Entrecot");
        listaPlatos[5] = new ElementoMenu(6, "Pollo");
        listaPlatos[6] = new ElementoMenu(7, "Pechuga");
        listaPlatos[7] = new ElementoMenu(8, "Pizza");
        listaPlatos[8] = new ElementoMenu(9, "Empanadas");
        listaPlatos[9] = new ElementoMenu(10, "Milanesas");
        listaPlatos[10] = new ElementoMenu(11, "Picada 1");
        listaPlatos[11] = new ElementoMenu(12, "Picada 2");
        listaPlatos[12] = new ElementoMenu(13, "Hamburguesa");
        listaPlatos[13] = new ElementoMenu(14, "Calamares");

        // inicia lista de postres
        listaPostres = new ElementoMenu[15];
        listaPostres[0] = new ElementoMenu(1, "Helado");
        listaPostres[1] = new ElementoMenu(2, "Ensalada de Frutas");
        listaPostres[2] = new ElementoMenu(3, "Macedonia");
        listaPostres[3] = new ElementoMenu(4, "Brownie");
        listaPostres[4] = new ElementoMenu(5, "Cheescake");
        listaPostres[5] = new ElementoMenu(6, "Tiramisu");
        listaPostres[6] = new ElementoMenu(7, "Mousse");
        listaPostres[7] = new ElementoMenu(8, "Fondue");
        listaPostres[8] = new ElementoMenu(9, "Profiterol");
        listaPostres[9] = new ElementoMenu(10, "Selva Negra");
        listaPostres[10] = new ElementoMenu(11, "Lemon Pie");
        listaPostres[11] = new ElementoMenu(12, "KitKat");
        listaPostres[12] = new ElementoMenu(13, "IceCreamSandwich");
        listaPostres[13] = new ElementoMenu(14, "Frozen Yougurth");
        listaPostres[14] = new ElementoMenu(15, "Queso y Batata");

        listaHorarios = new String[6];
        listaHorarios[0] = new String("20:00");
        listaHorarios[1] = new String("20:30");
        listaHorarios[2] = new String("21:00");
        listaHorarios[3] = new String("21:30");
        listaHorarios[4] = new String("22:00");
        listaHorarios[5] = new String("22:30");


        listaStringBebidas = new ArrayList<String>();
        for(int i=0; i<listaBebidas.length; i++){
            listaStringBebidas.add(listaBebidas[i].toString());
        }

        listaStringPlatos = new ArrayList<String>();
        for(int i=0; i<listaPlatos.length; i++){
            listaStringPlatos.add(listaPlatos[i].toString());
        }

        listaStringPostres = new ArrayList<String>();
        for(int i=0; i<listaPostres.length; i++){
            listaStringPostres.add(listaPostres[i].toString());
        }
    }
}