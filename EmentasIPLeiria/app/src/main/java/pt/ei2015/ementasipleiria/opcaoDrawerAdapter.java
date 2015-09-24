package pt.ei2015.ementasipleiria;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Collections;
import java.util.List;

/**
 * Created by alves_000 on 19/03/2015.
 */
public class opcaoDrawerAdapter extends RecyclerView.Adapter <opcaoDrawerAdapter.MyInformationHolder> {

    private LayoutInflater inflater;
    private  ClickListener clickListener;
    private List<String> data = Collections.emptyList();

    public opcaoDrawerAdapter(Context context, List<String> d){
        inflater = LayoutInflater.from(context);
        this.data = d;
    }

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener=clickListener;
    }

    @Override
    public MyInformationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row_drawer, parent,false);
        MyInformationHolder holder = new MyInformationHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyInformationHolder holder, int position) {
        String current = data.get(position);
        holder.title.setText(current);

    }

    @Override
    public int getItemCount() {
        return  data.size();
    }

    class MyInformationHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView icon;
        public MyInformationHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title= (TextView) itemView.findViewById(R.id.ListText);
        }

        @Override
        public void onClick(View v) {

            if (clickListener!= null){
                clickListener.itemClicked(v,getAdapterPosition() );
            }

        }
    }
    public interface ClickListener{
        public void itemClicked(View view, int position);

    }
}
