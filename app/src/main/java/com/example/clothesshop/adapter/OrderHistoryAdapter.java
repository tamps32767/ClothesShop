package com.example.clothesshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.R;
import com.example.clothesshop.model.Order;
import com.example.clothesshop.model.Product;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder> {
    private List<Order> orders;

    public OrderHistoryAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtNewAdress;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtNewAdress = itemView.findViewById(R.id.txtNewAdress);
        }

        public void bind(Order order) {
            StringBuilder details = new StringBuilder();
            details.append("Shipping Info: ").append(order.getShippingInfo()).append("\nProducts:\n");
            for (Product product : order.getProducts()) {
                details.append("- ").append(product.getName()).append(": $").append(product.getPrice()).append("\n");
            }
            txtTitle.setText(details.toString());
            txtNewAdress.setText(details.toString());
        }
    }
}
