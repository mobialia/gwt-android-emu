package android.os;


public class Binder implements IBinder {

	@Override
	public String getInterfaceDescriptor() throws RemoteException {
		return null;
	}

	@Override
	public boolean pingBinder() {
		return false;
	}

	@Override
	public boolean isBinderAlive() {
		return false;
	}

	@Override
	public IInterface queryLocalInterface(String descriptor) {
		return null;
	}

	@Override
	public boolean transact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
		return false;
	}

	@Override
	public void linkToDeath(DeathRecipient recipient, int flags) throws RemoteException {

	}

	@Override
	public boolean unlinkToDeath(DeathRecipient recipient, int flags) {
		return false;
	}
}
