package com.ericmedeiros.eric_medeiros_assignment3;


import android.os.Parcel;
import android.os.Parcelable;

public class Pizza implements Parcelable {
	//A basic class to store all of the json file contents. Includes an array of
	//toppings.
	private int smallPrice, mediumPrice, largePrice;
	private double smallFactor, mediumFactor, largeFactor;
	private Topping[] toppings;
	private String orderSize;

	public Pizza(int smallPrice, int mediumPrice, int largePrice,
			double smallFactor, double mediumFactor, double largeFactor,
			Topping[] toppings) {
		this.smallPrice = smallPrice;
		this.mediumPrice = mediumPrice;
		this.largePrice = largePrice;
		this.smallFactor = smallFactor;
		this.mediumFactor = mediumFactor;
		this.largeFactor = largeFactor;
		this.orderSize = "";
		this.toppings = toppings;
	}

	public Pizza(Parcel source) {
		this.smallPrice = source.readInt();
		this.mediumPrice = source.readInt();
		this.largePrice = source.readInt();
		this.smallFactor = source.readDouble();
		this.mediumFactor = source.readDouble();
		this.largeFactor = source.readDouble();
		this.orderSize = source.readString();
		source.readTypedArray(this.toppings, Topping.CREATOR);
	}

	public int getSmallPrice() {
		return smallPrice;
	}

	public void setSmallPrice(int smallPrice) {
		this.smallPrice = smallPrice;
	}

	public int getMediumPrice() {
		return mediumPrice;
	}

	public void setMediumPrice(int mediumPrice) {
		this.mediumPrice = mediumPrice;
	}

	public int getLargePrice() {
		return largePrice;
	}

	public void setLargePrice(int largePrice) {
		this.largePrice = largePrice;
	}

	public double getSmallFactor() {
		return smallFactor;
	}

	public void setSmallFactor(double smallFactor) {
		this.smallFactor = smallFactor;
	}

	public double getMediumFactor() {
		return mediumFactor;
	}

	public void setMediumFactor(double mediumFactor) {
		this.mediumFactor = mediumFactor;
	}

	public double getLargeFactor() {
		return largeFactor;
	}

	public void setLargeFactor(double largeFactor) {
		this.largeFactor = largeFactor;
	}

	public Topping[] getToppings() {
		return toppings;
	}

	public void setToppings(Topping[] toppings) {
		this.toppings = toppings;
	}

	@Override
	public int describeContents() {
		return 0;
	}
	
	public String getOrderSize() {
		return orderSize;
	}

	public void setOrderSize(String orderSize) {
		this.orderSize = orderSize;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(smallPrice);
		dest.writeInt(mediumPrice);
		dest.writeInt(largePrice);
		dest.writeDouble(smallFactor);
		dest.writeDouble(mediumFactor);
		dest.writeDouble(largeFactor);
		dest.writeString(orderSize);
		dest.writeTypedArray(toppings,0);

	}

	public static final Parcelable.Creator<Pizza> CREATOR = new Parcelable.Creator<Pizza>() {

		@Override
		public Pizza createFromParcel(Parcel source) {
			return new Pizza(source);
		}

		@Override
		public Pizza[] newArray(int size) {
			return new Pizza[size];
		}

	};
}
