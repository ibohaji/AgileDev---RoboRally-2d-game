package Main;

// Helper class to access the native bridge
public class JNIHelper {
	
	// Non-static way to load the native library
	public JNIHelper() {
		System.loadLibrary("libnative");
	}
	
	// Native method to be invoked, it returns object Main
	public native Main JNITransaction(String in_string);
}
