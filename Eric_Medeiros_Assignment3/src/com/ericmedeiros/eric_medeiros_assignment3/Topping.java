package com.ericmedeiros.eric_medeiros_assignment3;

import android.os.Parcel;
import android.os.Parcelable;

public class Topping implements Parcelable {
	//A basic class to store the properties of the pizza toppings.
	//information on the toppings is received from the json file in RAW.
	private String name;
	private double price;

	public Topping(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Topping(Parcel source) {
		name = source.readString();
		price = source.readDouble();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeDouble(price);
	}

	public static final Parcelable.Creator<Topping> CREATOR = new Parcelable.Creator<Topping>() {

		@Override
		public Topping createFromParcel(Parcel source) {
			return new Topping(source);
		}

		@Override
		public Topping[] newArray(int size) {
			return new Topping[size];
		}

	};
}
