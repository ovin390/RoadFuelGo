package com.example.roadfuelgo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {
    private List<bookb> bookingList;

    public BookingAdapter(List<bookb> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        bookb booking = bookingList.get(position);
        holder.textViewName.setText("Customer Name: " + booking.getCustname());
        holder.textViewId.setText("Booking ID: " + booking.getId());
        holder.textViewFuelType.setText("Fuel Type: " + booking.getFueltype());
        holder.textViewQuantity.setText("Quantity: " + booking.getQuantity());
        holder.textViewLocation.setText("Location: " + booking.getLocation());
        holder.textViewBookingDate.setText("Booking Date: " + booking.getBdate());
        holder.textViewPhone.setText("Phone: " + booking.getPhone());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewId;
        public TextView textViewFuelType;
        public TextView textViewQuantity;
        public TextView textViewLocation;
        public TextView textViewBookingDate;
        public TextView textViewPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewFuelType = itemView.findViewById(R.id.textViewFuelType);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            textViewBookingDate = itemView.findViewById(R.id.textViewBookingDate);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
        }
    }
}
