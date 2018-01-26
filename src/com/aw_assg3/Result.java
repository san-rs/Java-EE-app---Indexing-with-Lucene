package com.aw_assg3;

import java.io.*;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.RAMDirectory;

import com.sun.jersey.api.view.Viewable;

@Path("/")
public class Result {
   
   @Context ServletContext servletContext;
   
   @POST
   @Path("search")
   @Produces(MediaType.TEXT_HTML)
   public Viewable search(@Context HttpServletRequest request, @FormParam("query") String querystr) {
	   //String querystr = @FormParam("query");
	   System.out.println(querystr);
	   ArrayList<String> result = null;

	   String inputPath = servletContext.getRealPath("/WEB-INF/input");
	   try {
			@SuppressWarnings("unused")
			RAMDirectory inDirectory = Lucene.getIndexDir(inputPath);
			result = Lucene.search(querystr);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   request.setAttribute("result", result);
       System.out.println("Search called.");
       return new Viewable("/search.jsp", null);
   }
   
   @GET
   @Path("input")
   @Produces(MediaType.TEXT_HTML)
   public Viewable search(@Context HttpServletRequest request) {
	   ArrayList<String> querystr = new ArrayList<String>();
	   querystr.add("I was presented with this question in an end of module open book exam today and found myself lost. I was reading Head first Java and both definitions seemed to be exactly the same. I was just wondering what the MAIN difference was for my own piece of mind. I know there are a number of similar questions to this but, none I have seen which provide a definitive answer. Thanks, Darren");
	   querystr.add("Inheritance is when a 'class' derives from an existing 'class'. So if you have a Person class, then you have a Student class that extends Person, Student inherits all the things that Person has. There are some details around the access modifiers you put on the fields/methods in Person, but that's the basic idea. For example, if you have a private field on Person, Student won't see it because its private, and private fields are not visible to subclasses. Polymorphism deals with how the program decides which methods it should use, depending on what type of thing it has. If you have a Person, which has a read method, and you have a Student which extends Person, which has its own implementation of read, which method gets called is determined for you by the runtime, depending if you have a Person or a Student. It gets a bit tricky, but if you do something likePerson p = new Student();p.read();the read method on Student gets called. Thats the polymorphism in action.You can do that assignment because a Student is a Person, but the runtime is smart enough to know that the actual type of p is Student. Note that details differ among languages. You can do inheritance in javascript for example, but its completely different than the way it works in Java.");
	   querystr.add("Polymorphism: The ability to treat objects of different types in a similar manner.Example: Giraffe and Crocodile are both Animals, and animals can Move.If you have an instance of an Animal then you can call Move without knowing or caring what type of animal it is.Inheritance: This is one way of achieving both Polymorphism and code reuse at the same time.Other forms of polymorphism:There are other way of achieving polymorphism, such as interfaces, which provide only polymorphism but no code reuse (sometimes the code is quite different, such as Move for a Snake would be quite different from Move for a Dog, in which case an Interface would be the better polymorphic choice in this case.In other dynamic languages polymorphism can be achieved with Duck Typing, which is the classes don't even need to share the same base class or interface, they just need a method with the same name.Or even more dynamic like Javascript, you don't even need classes at all, just an object with the same method name can be used polymorphically.");
	   querystr.add("I found out that the above piece of code is perfectly legal in Java. I have the following questions. Thanks. Added one more question regarding Abstract method classes. public class TestClass { public static void main(String[] args) { TestClass t = new TestClass();} private static void testMethod(){ abstract class TestMethod{ int a;int b;int c;abstract void implementMe();} class DummyClass extends TestMethod{ void implementMe(){}} DummyClass dummy = new DummyClass();}}");
	   querystr.add("In java it's a bit difficult to implement a deep object copy function. What steps you take to ensure the original object and the cloned one share no reference? ");
	   querystr.add("You can make a deep copy serialization without creating some files. Copy: Restore: ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos); oos.writeObject(object); oos.flush(); oos.close(); bos.close(); byte[] byteData = bos.toByteArray(); ByteArrayInputStream bais = new ByteArrayInputStream(byteData); (Object) object = (Object) new ObjectInputStream(bais).readObject();");
	   querystr.add("Java has the ability to create classes at runtime. These classes are known as Synthetic Classes or Dynamic Proxies. See for more information. Other open-source libraries, such as and also allow you to generate synthetic classes, and are more powerful than the libraries provided with the JRE. Synthetic classes are used by AOP (Aspect Oriented Programming) libraries such as Spring AOP and AspectJ, as well as ORM libraries such as Hibernate. ");
	   querystr.add("A safe way is to serialize the object, then deserialize. This ensures everything is a brand new reference about how to do this efficiently. Caveats, It's possible for classes to override serialization such that new instances are created, e.g for singletons. Also this of course doesn't work if your classes aren't Serializable.");
	   querystr.add("comment this code /*if (savedinstancestate == null) { getsupportfragmentmanager().begintransaction().add(r.id.container new placeholderfragment()).commit(); }*/");
	   querystr.add("In a class i can have as many constructors as i want with different argument types. I made all the constructors as private it didn't give any error because my implicit default constructor was public but when I declared my implicit default constructor as private then its showing an error while extending the class. Why? This works fine. This can not be inherited. public class demo4{ private string name; private int age; private double sal; private demo4(string name int age) { this.name=name;this.age=age;} demo4(string name){ this.name=name;} demo4(){ this(\\\"unknown\\\"20); this.sal=2000;} void show(){ system.out.println(\\\"name\\\"+name); system.out.println(\\\"age: \\\"+age);}} public class demo4{ private string name; private int age; private double sal; private demo4(string name int age){ this.name=name; this.age=age;} demo4(string name){ this.name=name;} private demo4(){ this(\\\"unknown\\\"20); this.sal=2000;} void show(){ system.out.println(\\\"name\\\"+name); system.out.println(\\\"age:\\\"+age);}}");
	   System.out.println(querystr);
	   ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();;

	   String inputPath = servletContext.getRealPath("/WEB-INF/input");
	   try {
			@SuppressWarnings("unused")
			RAMDirectory inDirectory = Lucene.getIndexDir(inputPath);
			for(int i=0;i<querystr.size();i++) {
				result.add(Lucene.search(querystr.get(i)));
			}
	   } catch (IOException e) {
		   e.printStackTrace();
	   } catch (ParseException e) {
		   e.printStackTrace();
	   }
	   
	   request.setAttribute("querystr", querystr);
	   request.setAttribute("result", result);
       System.out.println("Given Input called.");
       return new Viewable("/input.jsp", null);
   }
}