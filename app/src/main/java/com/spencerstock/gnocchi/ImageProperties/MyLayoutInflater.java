package com.spencerstock.gnocchi.ImageProperties;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spencerstock.gnocchi.Activities.DetailActivity;
import com.spencerstock.gnocchi.FileIO.BitmapFileDao;
import com.spencerstock.gnocchi.R;

import java.util.ArrayList;

public class MyLayoutInflater extends ArrayAdapter{

    public static final String                     TITLE = "title";
    private             LayoutInflater             mInflater;
    private             ArrayList<GnocchiOverview> objects;
    private String subTitleText;

    public MyLayoutInflater(Context context, ArrayList<GnocchiOverview> objects) {
        super(context, R.layout.single_element_gnocchi_list, objects);
        /* We get the inflator in the constructor */
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        /* We inflate the xml which gives us a view */
        view = mInflater.inflate(R.layout.single_element_gnocchi_list, parent, false);

        /* Get the item in the adapter */
        final GnocchiOverview myObject = objects.get(position);

        /* Get the widget with id name which is defined in the xml of the row */
        TextView title = view.findViewById(R.id.single_element_textView_top);
        TextView subTitle = view.findViewById(R.id.single_element_textView_bottom);
        ImageView preview = view.findViewById(R.id.single_element_imageView);
        ImageView add_photo = view.findViewById(R.id.single_element_add_photo_imageView);
        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                BitmapFileDao.dispatchTakePictureIntent(context, myObject.getSize()+1, myObject.getTitle());
                myObject.setSize(myObject.getSize()+1);
            }
        });

        /* Populate the row's xml with info from the item */
        title.setText(myObject.getTitle());
        subTitleText = myObject.getSize() + " image files in this Gnocchi";
        subTitle.setText(subTitleText);
        preview.setImageBitmap(myObject.getFirstFrame());

        /* Return the generated view */

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext().getApplicationContext(), DetailActivity.class);
                i.putExtra(TITLE,myObject.getTitle());
                v.getContext().startActivity(i);
            }
        });
        return view;
    }
}
