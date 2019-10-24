package br.udesc.comandaappadm.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.udesc.comandaappadm.R;
import br.udesc.comandaappadm.model.ConfigProduto;

public class ConfiguracaoProdutoAdapter extends BaseAdapter {

    private Context context;
    private final List<ConfigProduto> configuracoes;

    public ConfiguracaoProdutoAdapter(Context context, List<ConfigProduto> configuracoes) {
        this.context = context;
        this.configuracoes = configuracoes;
    }

    @Override
    public int getCount() {
        return configuracoes.size();
    }

    @Override
    public Object getItem(int position) {
        return configuracoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_configuracao, parent, false);
        ConfigProduto conf = configuracoes.get(position);


        TextView txtConfigProduto = view.findViewById(R.id.txtAdapterConf);
        TextView txtConfigValue = view.findViewById(R.id.txtAdapterConfValue);
        TextView txtValueAcresc = view.findViewById(R.id.txtAdapterValueAcresc);
        LinearLayout adapterConfig = view.findViewById(R.id.layoutAdapterConfig);

//        if (position % 2 == 0) {
//            adapterConfig.setBackgroundColor(Color.parseColor("#cccccc"));
//        }
        txtConfigProduto.setText(conf.getNomeConfigProduto());
        txtConfigValue.setText(conf.getValues().toString());
        txtValueAcresc.setText(conf.getValues().toString());

        return view;
    }
}
