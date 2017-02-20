package com.example.simeon.firstapplication.activities.httpRequestsImagesWithDataLayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.data.services.ImagesHttpData;
import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.models.Book;
import com.example.simeon.firstapplication.models.Student;

public class ImageTextArrayAdapter extends ArrayAdapter<Book> {
    private final ImagesHttpData imageHttpData;

    public ImageTextArrayAdapter(Context context) {
        super(context, -1);
        this.imageHttpData = new ImagesHttpData();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.item_book_with_images, parent, false);
        }

        Book currentBook = this.getItem(position);

        TextView tvBookTitle = (TextView) view.findViewById(R.id.tv_book_with_image_title);
        tvBookTitle.setText(currentBook.getTitle());

        ImageView ivBookImage = (ImageView) view.findViewById(R.id.iv_book_image);

        imageHttpData.getImage(currentBook.getImageUrl())
            .subscribe(bitmap -> {
                ivBookImage.setImageBitmap(bitmap);
            });

        return view;
    }
}
