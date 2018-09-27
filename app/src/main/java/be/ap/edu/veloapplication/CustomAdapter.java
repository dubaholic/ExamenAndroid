package be.ap.edu.veloapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> naamStation;
    ArrayList<String> longitude;
    ArrayList<String> latitude;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> naamStation, ArrayList<String> longitude, ArrayList<String> latitude) {
        this.context = context;
        this.naamStation= naamStation;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.naam.setText(naamStation.get(position));
        holder.lng.setText(longitude.get(position));
        holder.lat.setText(latitude.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, naamStation.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return naamStation.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView naam, lng, lat;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            naam = (TextView) itemView.findViewById(R.id.naam);
            lng = (TextView) itemView.findViewById(R.id.lng);
            lat = (TextView) itemView.findViewById(R.id.lat);

        }
    }
}
