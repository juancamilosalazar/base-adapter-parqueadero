package co.com.k4soft.parqueaderouco.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.parqueaderouco.R;
import co.com.k4soft.parqueaderouco.entidades.Tarifa;
import co.com.k4soft.parqueaderouco.persistencia.room.DataBaseHelper;
import co.com.k4soft.parqueaderouco.utilities.ActionBarUtil;

public class EdicionTarifaActivity extends AppCompatActivity {

    @BindView(R.id.nombreEditar)
    public EditText nombreEditar;

    @BindView(R.id.precioEditar)
    public EditText precioEditar;

    private ActionBarUtil actionBarUtil;

    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_tarifa);
        ButterKnife.bind(this);
        initComponents();


    }

    private void initComponents() {
        db  = DataBaseHelper.getDBMainThread(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.configurar_tarifa));
        Tarifa tarifa = getTarifa();
        nombreEditar.setText(tarifa.getNombre());
        precioEditar.setText(Double.toString(tarifa.getPrecio()));
    }


    public void actualizar(View view) {
        String nombreTarifa = nombreEditar.getText().toString();
        Double valorTarifa = toDouble(precioEditar.getText().toString());
        if (validarInformacion(nombreTarifa, valorTarifa)) {
            Tarifa tarifa = getTarifa();
            tarifa.setNombre(nombreTarifa);
            tarifa.setPrecio(valorTarifa);
            db.getTarifaDAO().update(tarifa);
            finish();
        }
    }

    private Double toDouble(String valor) {
        return "".equals(valor) ? 0 : Double.parseDouble(valor);
    }

    private boolean validarInformacion(String nombreTarifa, Double valorTarifa) {
        boolean esValido = true;
        if ("".equals(nombreTarifa)) {
            nombreEditar.setError(getString(R.string.requerido));
            esValido = false;
        }

        if (valorTarifa == 0) {
            precioEditar.setError(getString(R.string.requerido));
            esValido = false;
        }
        return esValido;
    }
    private Tarifa getTarifa() {
        String idTarifa = (String) getIntent().getExtras().getSerializable("id");
        System.out.println(db.getTarifaDAO().getById(Integer.parseInt(idTarifa)));
        return db.getTarifaDAO().getById(Integer.parseInt(idTarifa));

    }
}
