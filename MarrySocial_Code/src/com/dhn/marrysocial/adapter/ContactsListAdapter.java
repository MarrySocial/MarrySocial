package com.dhn.marrysocial.adapter;

import java.util.ArrayList;

import com.dhn.marrysocial.R;
import com.dhn.marrysocial.fragment.ContactsListFragment.ContactEntry;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactsListAdapter extends BaseAdapter {

    private static final String TAG = "ContactsListAdapter";

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<ContactEntry> mData;

    public ContactsListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setDataSource(ArrayList<ContactEntry> source) {
        mData = source;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.contacts_list_item_layout,
                    parent, false);
            holder = new ViewHolder();
            holder.person_pic = (ImageView) convertView
                    .findViewById(R.id.contacts_person_pic);
            holder.person_name = (TextView) convertView
                    .findViewById(R.id.contacts_person_name);
            holder.person_description = (TextView) convertView
                    .findViewById(R.id.contacts_person_description);
            holder.person_description_more = (ImageView) convertView
                    .findViewById(R.id.contacts_person_description_more);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.person_name.setText(mData.get(position).contact_name);
//        holder.person_pic.setImageResource(R.drawable.person_default_small_pic);
        return convertView;
    }

    class ViewHolder {
        ImageView person_pic;
        TextView person_name;
        TextView person_description;
        ImageView person_description_more;
    }

//    public Bitmap getRoundedCornerBitmap(float ratio) {
//
//        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
//                R.drawable.person_default_pic);
//
//        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
//                bitmap.getHeight(), Config.ARGB_8888);
//        Canvas canvas = new Canvas(output);
//
//        final Paint paint = new Paint();
//        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//        final RectF rectF = new RectF(rect);
//
//        paint.setAntiAlias(true);
//        canvas.drawARGB(0, 0, 0, 0);
//        canvas.drawRoundRect(rectF, bitmap.getWidth() / ratio,
//                bitmap.getHeight() / ratio, paint);
//
//        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//        canvas.drawBitmap(bitmap, rect, rect, paint);
//        return output;
//    }
}
