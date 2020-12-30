package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.delowar.firebasedatastore.R;

import java.util.ArrayList;

import model.Student;

public class CustomAdapter extends ArrayAdapter<Student> {
    private ArrayList<Student> studentArrayList;
    private Activity context;
    private LayoutInflater layoutInflater;
    public CustomAdapter(Activity context, ArrayList<Student> studentArrayList) {
        super(context, R.layout.row_layout,studentArrayList);
        this.studentArrayList=studentArrayList;
        this.context=context;
        this.layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public class MyViewHolder{
        private TextView nameTV;
        private TextView emailTV;
        private TextView phoneTV;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final MyViewHolder holder;
        if (convertView == null){
            holder=new MyViewHolder();
            convertView=layoutInflater.inflate(R.layout.row_layout,parent,false);
            holder.nameTV=convertView.findViewById(R.id.nameTextView);
            holder.emailTV=convertView.findViewById(R.id.emailTextView);
            holder.phoneTV=convertView.findViewById(R.id.phoneTextView);

            convertView.setTag(holder);
        }else {
            holder= (MyViewHolder) convertView.getTag();
        }
        holder.nameTV.setText(studentArrayList.get(position).getName());
        holder.emailTV.setText(studentArrayList.get(position).getEmail());
        holder.phoneTV.setText(studentArrayList.get(position).getPhone());
        return convertView;
    }
}
