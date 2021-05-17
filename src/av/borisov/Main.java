package av.borisov;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		System.out.println("Создаём векторы v1 = (1,2,3) и v2 = (3,4,5)\n");
		Vector v1 = new Vector(1,2,3);
		Vector v2 = new Vector(3,4,5);
		
		System.out.print("Вектор 2*v1: ");
		System.out.println(Vector.multiply(v1, 2)+"\n");
		
		System.out.print("Вектор v1+v2: ");
		System.out.println(Vector.sum(v1,v2)+"\n");
		
		System.out.print("Скалярное произведение v1 и v2: ");
		System.out.println(Vector.dot(v1,v2)+"\n");
		
		System.out.println("Сериализуем вектор v1 побайтово в файл \"vector\"...\n");
		FileOutputStream out = new FileOutputStream("vector");
		Vector.outputVector(v1, out);
		out.close();
		
		System.out.println("Десериалиуем вектор побайтово из файла \"vector\" в вектор v...\n");
		FileInputStream in = new FileInputStream("vector");
		Vector v = new Vector();
		Vector.inputVector(v, in);
		in.close();
		
		System.out.print("Восстановленный вектор: ");
		System.out.println(v);
		
		
		System.out.print("\nСериализуем посимвольно вектор v2, испольуя поток System.out: ");
		Vector.writeVector(v2, new PrintWriter(System.out));
		System.out.println();
		
		System.out.print("\nДесериализуем вектор посимвольно, используя поток System.in (ожидание ввода): ");
		Vector.readVector(v2, new InputStreamReader(System.in));
		System.out.println();
		
		System.out.print("Восстановленный вектор: ");
		System.out.println(v2);
	}

}
