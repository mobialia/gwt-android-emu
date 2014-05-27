package android.os;

public interface Parcelable {

	abstract int describeContents();

	abstract void writeToParcel(Parcel dest, int flags);

}
