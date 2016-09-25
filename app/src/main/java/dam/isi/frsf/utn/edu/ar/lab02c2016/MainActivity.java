package dam.isi.frsf.utn.edu.ar.lab02c2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;



public class MainActivity extends AppCompatActivity {

    private ToggleButton reservarMesa;
    private Spinner horarios;
    private Switch reservarAntes;
    private TextView resultados;
    private RadioButton rbPlato;
    private RadioButton rbPostre;
    private RadioButton rbBebida;
    private Button agregar;
    private Button confirmar;
    private Button reiniciar;
    private ListView listado;

    public ElementoMenu[] listaBebidas;
    public ElementoMenu[] listaPlatos;
    public ElementoMenu[] listaPostre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         reservarMesa = (ToggleButton) findViewById(R.id.reservar_mesa);
         horarios = (Spinner) findViewById(R.id.spinner_horarios);
         reservarAntes = (Switch) findViewById(R.id.reservar_antes);
         resultados = (TextView) findViewById(R.id.txt_resultados);
         rbPlato = (RadioButton) findViewById(R.id.rb_plato);
         rbPostre = (RadioButton) findViewById(R.id.rb_postre);
         rbBebida = (RadioButton) findViewById(R.id.rb_bebidas);
         agregar = (Button) findViewById(R.id.b_agregar);
         confirmar = (Button) findViewById(R.id.b_confirmar);
         reiniciar = (Button) findViewById(R.id.b_reiniciar);
         listado = (ListView) findViewById(R.id.lv_listado);




        iniciarListas();




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
        listaPlatos[12] = new ElementoMenu(14, "Calamares");

        // inicia lista de postres
        listaPostre = new ElementoMenu[15];
        listaPostre[0] = new ElementoMenu(1, "Helado");
        listaPostre[1] = new ElementoMenu(2, "Ensalada de Frutas");
        listaPostre[2] = new ElementoMenu(3, "Macedonia");
        listaPostre[3] = new ElementoMenu(4, "Brownie");
        listaPostre[4] = new ElementoMenu(5, "Cheescake");
        listaPostre[5] = new ElementoMenu(6, "Tiramisu");
        listaPostre[6] = new ElementoMenu(7, "Mousse");
        listaPostre[7] = new ElementoMenu(8, "Fondue");
        listaPostre[8] = new ElementoMenu(9, "Profiterol");
        listaPostre[9] = new ElementoMenu(10, "Selva Negra");
        listaPostre[10] = new ElementoMenu(11, "Lemon Pie");
        listaPostre[11] = new ElementoMenu(12, "KitKat");
        listaPostre[12] = new ElementoMenu(13, "IceCreamSandwich");
        listaPostre[13] = new ElementoMenu(14, "Frozen Yougurth");
        listaPostre[14] = new ElementoMenu(15, "Queso y Batata");
    }

}