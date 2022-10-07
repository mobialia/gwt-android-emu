package android.os;

public interface Parcelable {

	int describeContents();

	void writeToParcel(Parcel dest, int flags);

}
