package pl.nygamichal.yoapp;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Admin on 07.08.2017.
 */

public class AdapterHej extends RecyclerView.Adapter<AdapterHej.ViewHolder>{
    List<Hej> wishes = new ArrayList<>();
        public AdapterHej(List<Hej> wishes ) {
        this.wishes = this.wishes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)//s04e03 1:30
    {
       View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wish, parent, false);
        AdapterHej.ViewHolder vh = new ViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)//ukazujemy wybrana notatke
    {
        final Hej wish = wishes.get(position);

    }

    @Override
    public int getItemCount() {//ile leementow ma ta lista
        return wishes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewContent;
        public TextView textViewOwner;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewContent = (TextView) itemView.findViewById(R.id.content);
            textViewOwner = (TextView) itemView.findViewById(R.id.owner);
        }
    }
}
