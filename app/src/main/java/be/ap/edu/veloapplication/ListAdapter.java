package be.ap.edu.veloapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter
{
    private Context mContext = null;
    private String json = null;
    private ArrayList< VeloStation > veloStations = null;
    public ListAdapter(Context context, String json)
    {
        this.mContext = context;
        this.json = json;
        Response response = new Gson().fromJson(json, Response.class);
        veloStations = response.getVeloStations();
    }
    @Override
    public View getView(int arg0, View view, ViewGroup arg2)
    {
        ViewHolder holder = null;
        if (view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.naamText = (TextView) view.findViewById(R.id.txtNaam);
            holder.latText = (TextView) view.findViewById(R.id.txtLat);
            holder.lngText = (TextView) view.findViewById(R.id.txtLng);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        holder.naamText.setText(getItem(arg0).getNaam());
        holder.latText.setText(getItem(arg0).getPoint_lat());
        holder.lngText.setText(getItem(arg0).getPoint_lng());
        return view;
    }
    class ViewHolder
    {
        private TextView naamText = null;
        private TextView latText = null;
        private TextView lngText = null;

    }
}
