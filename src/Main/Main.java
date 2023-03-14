package Main;

import Main.JNIHelper;

public class Main {
	
	private String JNIString = "";
	
    public static void main(String[] args) {
        System.out.println("Hello world from java bytecode");
		// Instantiate jni access helper class
		JNIHelper JNIObject = new JNIHelper();
		// Invoke the native method
		System.out.println(JNIObject.JNITransaction("Hello world from native machine code").JNIString);
    }
}