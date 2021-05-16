package av.borisov;

import java.io.*;

/**
 * A class representing a vector. It implements Externalizable interface, allowing it to use
 * serialization and deserialization mechanisms.
 * 
 * The class contains methods for performing basic vector operations such as adding,
 * multiplying by scalar and evaluating the dot product.
 * @author Alexander Borisov
 */
public class Vector implements Externalizable {
	
	/**
	 * An array storing vector elements.
	 */
	private double[] elements;
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(getDim());
		for (double e : elements) {
			out.writeObject(e);
		}
	} 
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		elements = new double[(int)in.readObject()];
		for (int i=0; i<elements.length; i++) {
			elements[i] = (double)in.readObject();
		}
	}
	
	/**
	 * A static method that performs serialization of
	 * Vector object into the specified byte stream.
	 * @param v 			a Vector to be serialized
	 * @param out 			the byte stream to serialize into
	 * @throws IOException 	Includes any I/O exceptions that may occur
	 */
	public static void outputVector(Vector v, OutputStream out) throws IOException {
		ObjectOutputStream s = new ObjectOutputStream(out);
		v.writeExternal(s);
	}
	
	/**
	 * A static method that performs deserialization of
	 * Vector object from the specified byte stream.
	 * @param v 						a Vector to be deserialized
	 * @param in 						the byte stream to deserialize from
	 * @throws IOException				Includes any I/O exceptions that may occur
	 * @throws ClassNotFoundException 	If the class for an object being restored cannot be found.
	 */
	public static void inputVector(Vector v, InputStream in) throws IOException, ClassNotFoundException {
		ObjectInputStream s = new ObjectInputStream(in);
		v.readExternal(s);
	}
	
	/**
	 * A static method that performs serialization of
	 * Vector object into the specified character stream.
	 * @param v 				a Vector to be serialized
	 * @param out 				the character stream to serialize into
	 * @throws IOException		Includes any I/O exceptions that may occur
	 */
	public static void writeVector(Vector v, Writer out) throws IOException {
		String target = v.getDim() + " ";
		for (double e : v.elements) {
			target += e + " ";
		}
		target = target.substring(0, target.length() - 1);
		out.write(target);
		out.flush();
	}
	
	/**
	 * A static method that performs deserialization of
	 * Vector object from the specified character stream.
	 * @param v 				a Vector to be deserialized
	 * @param in 				the character stream to deserialize from
	 * @throws IOException		Includes any I/O exceptions that may occur
	 */
	public static void readVector(Vector v, Reader in) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(in);
		tokenizer.whitespaceChars(' ', ' ');
		tokenizer.nextToken();
		v.elements = new double[(int)tokenizer.nval];
		for (int i=0; i<v.elements.length; i++) {
			tokenizer.nextToken();
			v.elements[i] = (double)tokenizer.nval;
		}
	}
	
	/**
	 * Returns a string reprsentation of the object in the format of
	 * <i>"(value1, value2, ...)"</i>
	 */
	@Override
	public String toString() {
		String s = "(";
		for (double e : elements) {
			s+=e+", ";
		}
		return s.substring(0, s.length() - 2)+")";
	}
	
	/**
	 * A constructor that initializes elements array with given parameters.
	 * @param elements a double array to initialize elements class field with
	 */
	public Vector(double... elements) {
		this.elements = elements;
	}
	
	/**
	 * An elements array getter.
	 * @return A {@code double[]} array of vector elements.
	 */
	public double[] getElements() {
		return elements;
	}
	
	/**
	 * A getter method that returns the dimension of a vector.
	 * It returns the same int value as getElements().length.
	 * @return A length of vector elements array.
	 */
	public int getDim() {
		return elements.length;
	}
	
	/**
	 * A method that multiplies a vector by a given scalar.
	 * @param a 	a vector to be multiplied
	 * @param c		a scalar to multiply by
	 * @return		A vector that is the result of multiplication.
	 */
	public static Vector multiply(Vector a, double c) {
		double[] newElements = new double[a.getDim()];
		for (int i=0; i<a.getElements().length; i++) {
			newElements[i] = a.getElements()[i]*c;
		}
		return new Vector(newElements);
	}
	
	/**
	 * A method that evaluates the dot product of two given vectors.
	 * @param a		first vector
	 * @param b		second vector
	 * @return		A {@code double} value of the dot product.
	 */
	public static double dot(Vector a, Vector b) {
		if (a.getDim() != b.getDim()) {
			throw new IllegalArgumentException("Vector dimensions do not match");
		}
		double product = 0;
		for (int i=0; i<a.getElements().length; i++) {
			product += a.getElements()[i]*b.getElements()[i];
		}
		return product;
	}
	
	/**
	 * A method that evaluates the sum of two given vectors.
	 * @param a		first vector
	 * @param b		second vector
	 * @return		A vector that is the sum of two given vectors.
	 */
	public static Vector sum(Vector a, Vector b) {
		if (a.getDim() != b.getDim()) {
			throw new IllegalArgumentException("Vector dimensions do not match");
		}
		double[] newElements = new double[a.getDim()];
		for (int i=0; i<a.getElements().length; i++) {
			newElements[i] = a.getElements()[i]+b.getElements()[i];
		}
		return new Vector(newElements);
	}
}
