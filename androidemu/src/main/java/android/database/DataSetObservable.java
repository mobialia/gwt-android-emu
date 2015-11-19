package android.database;

public class DataSetObservable extends Observable<DataSetObserver> {
	public void notifyChanged() {
		synchronized (mObservers) {
			for (DataSetObserver observer : mObservers) {
				observer.onChanged();
			}
		}
	}

	public void notifyInvalidated() {
		synchronized (mObservers) {
			for (DataSetObserver observer : mObservers) {
				observer.onInvalidated();
			}
		}
	}
}
