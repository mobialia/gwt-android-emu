package android.content;

import android.text.TextUtils;

public final class ComponentName implements Cloneable, Comparable<ComponentName> {
	private final String mPackage;
	private final String mClass;

	public static ComponentName createRelative(String pkg, String cls) {
		if (TextUtils.isEmpty(cls)) {
			throw new IllegalArgumentException("class name cannot be empty");
		}

		final String fullName;
		if (cls.charAt(0) == '.') {
			// Relative to the package. Prepend the package name.
			fullName = pkg + cls;
		} else {
			// Fully qualified package name.
			fullName = cls;
		}
		return new ComponentName(pkg, fullName);
	}

	public static ComponentName createRelative(Context pkg, String cls) {
		return createRelative(pkg.getPackageName(), cls);
	}

	public ComponentName(String pkg, String cls) {
		if (pkg == null) throw new NullPointerException("package name is null");
		if (cls == null) throw new NullPointerException("class name is null");
		mPackage = pkg;
		mClass = cls;
	}

	public ComponentName(Context pkg, String cls) {
		if (cls == null) throw new NullPointerException("class name is null");
		mPackage = pkg.getPackageName();
		mClass = cls;
	}

	public ComponentName(Context pkg, Class<?> cls) {
		mPackage = pkg.getPackageName();
		mClass = cls.getName();
	}

	public ComponentName clone() {
		return new ComponentName(mPackage, mClass);
	}

	public String getPackageName() {
		return mPackage;
	}

	public String getClassName() {
		return mClass;
	}

	public String getShortClassName() {
		if (mClass.startsWith(mPackage)) {
			int PN = mPackage.length();
			int CN = mClass.length();
			if (CN > PN && mClass.charAt(PN) == '.') {
				return mClass.substring(PN, CN);
			}
		}
		return mClass;
	}

	private static void appendShortClassName(StringBuilder sb, String packageName,
											 String className) {
		if (className.startsWith(packageName)) {
			int PN = packageName.length();
			int CN = className.length();
			if (CN > PN && className.charAt(PN) == '.') {
				sb.append(className, PN, CN);
				return;
			}
		}
		sb.append(className);
	}


	public String flattenToString() {
		return mPackage + "/" + mClass;
	}

	public String flattenToShortString() {
		StringBuilder sb = new StringBuilder(mPackage.length() + mClass.length());
		appendShortString(sb, mPackage, mClass);
		return sb.toString();
	}

	public void appendShortString(StringBuilder sb) {
		appendShortString(sb, mPackage, mClass);
	}

	public static void appendShortString(StringBuilder sb, String packageName, String className) {
		sb.append(packageName).append('/');
		appendShortClassName(sb, packageName, className);
	}

	public static ComponentName unflattenFromString(String str) {
		int sep = str.indexOf('/');
		if (sep < 0 || (sep + 1) >= str.length()) {
			return null;
		}
		String pkg = str.substring(0, sep);
		String cls = str.substring(sep + 1);
		if (cls.length() > 0 && cls.charAt(0) == '.') {
			cls = pkg + cls;
		}
		return new ComponentName(pkg, cls);
	}

	public String toShortString() {
		return "{" + mPackage + "/" + mClass + "}";
	}

	@Override
	public String toString() {
		return "ComponentInfo{" + mPackage + "/" + mClass + "}";
	}

	@Override
	public boolean equals(Object obj) {
		try {
			if (obj != null) {
				ComponentName other = (ComponentName) obj;
				// Note: no null checks, because mPackage and mClass can
				// never be null.
				return mPackage.equals(other.mPackage)
						&& mClass.equals(other.mClass);
			}
		} catch (ClassCastException e) {
		}
		return false;
	}

	@Override
	public int hashCode() {
		return mPackage.hashCode() + mClass.hashCode();
	}

	public int compareTo(ComponentName that) {
		int v;
		v = this.mPackage.compareTo(that.mPackage);
		if (v != 0) {
			return v;
		}
		return this.mClass.compareTo(that.mClass);
	}
}
