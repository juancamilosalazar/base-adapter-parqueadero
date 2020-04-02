package co.com.k4soft.parqueaderouco.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.parqueaderouco.R;
import co.com.k4soft.parqueaderouco.entidades.Tarifa;
import co.com.k4soft.parqueaderouco.view.EdicionTarifaActivity;
import co.com.k4soft.parqueaderouco.view.TarifaActivity;

public class TarifaAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private List<Tarifa> tarifasOut;
    private List<Tarifa> tarifasIn;

    public TarifaAdapter(Context context, List<Tarifa> tarifas){
        tarifasOut = tarifas;
        tarifasIn  = tarifas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tarifasOut.size();
    }

    @Override
    public Tarifa getItem(int position) {
        return tarifasOut.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.tarifa_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.txtNombreTarifa.setText(tarifasOut.get(position).getNombre());
        holder.txtPrecioTarifa.setText(Double.toString(tarifasOut.get(position).getPrecio()));
        holder.txtCodigoTarifa.setText(Integer.toString(tarifasOut.get(position).getIdTarifa()));
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.nombre)
        TextView txtNombreTarifa;
        @BindView(R.id.valor)
        TextView txtPrecioTarifa;
        @BindView(R.id.codigo)
        TextView txtCodigoTarifa;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
