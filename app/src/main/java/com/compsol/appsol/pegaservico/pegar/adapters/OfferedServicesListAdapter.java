package com.compsol.appsol.pegaservico.pegar.adapters;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.compsol.appsol.pegaservico.R;
import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.lib.base.ImageLoader;
import com.compsol.appsol.pegaservico.oferecer.adapters.MyServiceListAdapter;
import com.compsol.appsol.pegaservico.pegar.ui.PegarView;
import com.compsol.appsol.pegaservico.pegar.ui.PegarViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class OfferedServicesListAdapter extends RecyclerView.Adapter<OfferedServicesListAdapter.ViewHolder>{

    private List<ServiceItem> servicesList;
    private ImageLoader imageLoader;
    private PegarView pegarView;

    public OfferedServicesListAdapter(List<ServiceItem> servicesList, ImageLoader imageLoader, PegarView view){
        this.servicesList = servicesList;
        this.imageLoader = imageLoader;
        this.pegarView = view;
    }

    @NonNull
    @Override
    public OfferedServicesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new OfferedServicesListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferedServicesListAdapter.ViewHolder holder, int position) {
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

        holder.pegarButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pegarView.onServiceClickedToCatch(service);
                    }
                }
        );

        if (position + 1 == getItemCount()) {
            // It is the last item of the list

            // Set bottom margin to 72dp
            setBottomMargin(holder.itemView, (int) (72 * Resources.getSystem().getDisplayMetrics().density));
        } else {
            // Reset bottom margin back to zero
            setBottomMargin(holder.itemView, 0);
        }

        holder.itemView.setOnClickListener(v -> {
            // Get the current state of the item
            //boolean expanded = service.isExpanded();      //codigo para expandir item view ao tocar nele
            // Change the state
            //service.setExpanded(!expanded);
            // Notify the adapter that item has changed
            notifyItemChanged(position);
        });

    }

    private static void setBottomMargin(View view, int bottomMargin) {//https://gist.github.com/rodrigoborgesdeoliveira/a37b1534969fe4e9ada0bb440b8f4b4b
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, bottomMargin);
            view.requestLayout();
        }
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

        @BindView(R.id.item_service)
        ConstraintLayout itemService;
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
        @BindView(R.id.sub_item_pegar)
        Button pegarButton;

        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);

            /*pegarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });*/

        }

        private void bind(ServiceItem service) {
            // Get the state        //codigo para expandir item view ao tocar nele
            //boolean expanded = service.isExpanded();
            // Set the visibility based on state
            //service.setVisibility(expanded ? View.VISIBLE : View.GONE);

            //title.setText(movie.getTitle());
            //genre.setText("Genre: " + movie.getGenre());
            //year.setText("Year: " + movie.getYear());
        }

    }

}
