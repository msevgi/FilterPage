
package tr.com.dteknoloji.filterpage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MustafaS on 27.1.2015.
 */
public class SpinnerAdapter extends ArrayAdapter<String> {
   private ViewHolder viewHolder;

   public SpinnerAdapter(Context context, int resource, List<String> objects) {
      super(context, resource, objects);
   }

   public View getCustomView(int position, View convertView, ViewGroup parent) {
      if (convertView == null) {
         convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.spinner_row, parent, false);
         viewHolder = new ViewHolder();
         viewHolder.textView = (TextView) convertView.findViewById(R.id.textview);
         convertView.setTag(viewHolder);
      }
      else {
         viewHolder = (ViewHolder) convertView.getTag();
      }
      viewHolder.textView.setText(getItem(position));
      return convertView;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {

      return getCustomView(position, convertView, parent);
   }

   class ViewHolder {
      private TextView textView;
   }

   @Override
   public View getDropDownView(int position, View convertView, ViewGroup parent) {
      return getCustomView(position, convertView, parent);
   }
}
