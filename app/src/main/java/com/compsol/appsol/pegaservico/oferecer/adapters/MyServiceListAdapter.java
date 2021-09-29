package com.compsol.appsol.pegaservico.oferecer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.compsol.appsol.pegaservico.R;
import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.lib.base.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyServiceListAdapter extends RecyclerView.Adapter<MyServiceListAdapter.ViewHolder>{

    private List<ServiceItem> servicesList;
    private ImageLoader imageLoader;

    public MyServiceListAdapter(List<ServiceItem> servicesList, ImageLoader imageLoader) {
        this.servicesList = servicesList;
        this.imageLoader = imageLoader;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceItem service = servicesList.get(position);

        String urlPhotoUser = service.getUrlPhotoUser();
        if ((!urlPhotoUser.equals("default"))&&!(urlPhotoUser.equals("url_sh_pr"))&&!(urlPhotoUser.isEmpty()))
            //imageLoader.load(holder.imgAvatar, urlPhotoUser);

        holder.txtNomeUser.setText(service.getNome());
        holder.txtStatus.setText(service.getStatus()+"");
        holder.txtData.setText(service.getData());
        holder.txtTime.setText(service.getEntrada());
        holder.txtPeriod.setText(service.getPeriodo()+"");
        holder.txtValue.setText(service.getValor()+"");

    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public void add(ServiceItem serviceItem) {
        if (!alreadyInAdapter(serviceItem)) {
            this.servicesList.add(serviceItem);

            //this.notifyDataSetChanged();// usado antes, agora vou tentar usar no observer
            // do oferecerViewModel.getServiceMutableLiveData() em OferecerFragment
        }
    }

    public void setServicesList(List<ServiceItem> servicesList){
        this.servicesList = servicesList;
    }

    private boolean alreadyInAdapter(ServiceItem newService) {
        boolean alreadyInAdapter = false;
        for (ServiceItem service : this.servicesList) {
            if (service.getEmail().equals(newService.getEmail())) {
                alreadyInAdapter = true;
                break;
            }
        }

        return alreadyInAdapter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgAvatar)
        CircleImageView imgAvatar;
        @BindView(R.id.txtNomeUser)
        TextView txtNomeUser;
        @BindView(R.id.txtStatus)
        TextView txtStatus;
        @BindView(R.id.txtData)
        TextView txtData;
        @BindView(R.id.txtTime)
        TextView txtTime;
        @BindView(R.id.txtPeriod)
        TextView txtPeriod;
        @BindView(R.id.txtValue)
        TextView txtValue;

        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
