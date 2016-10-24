package mk;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class TestJavac {

	public static void main(String[] args) throws IOException {
		JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fm = javac.getStandardFileManager(null, null, null);
		JavaFileManager myFm = new ForwardingJavaFileManager(fm) {
			@Override
			public JavaFileObject getJavaFileForOutput(Location location,
					String className, Kind kind, FileObject sibling)
					throws IOException {
				System.out.println("getJavaFileForOutput "+className);
				return super.getJavaFileForOutput(location, className, kind, sibling);
			}
			
			@Override
			public JavaFileObject getJavaFileForInput(Location location,
					String className, Kind kind) throws IOException {
				System.out.println("getJavaFileForInput "+className);
				return super.getJavaFileForInput(location, className, kind);
			}
			
			@Override
			public ClassLoader getClassLoader(Location location) {
				System.out.println("getClassLoader "+location);
				return super.getClassLoader(location);
			}
			
			@Override
			public FileObject getFileForOutput(Location location,
					String packageName, String relativeName, FileObject sibling)
					throws IOException {
				System.out.println("getFileForOutput "+packageName+" "+relativeName);
				return super.getFileForOutput(location, packageName, relativeName, sibling);
			}
			
			@Override
			public FileObject getFileForInput(Location location,
					String packageName, String relativeName) throws IOException {
				System.out.println("getFileForInput "+packageName+" "+relativeName+" "+location);
				return super.getFileForInput(location, packageName, relativeName);
			}
			
			@Override
			public Iterable<JavaFileObject> list(Location location, String packageName,
					Set kinds, boolean recurse) throws IOException {
				if (packageName.equals("root.module_b"))
					System.err.println("Unwanted dependency to other module: "+packageName);
				System.out.println("list "+packageName+" "+kinds+" "+location);
				return super.list(location, packageName, kinds, recurse);
			}
		};
		Iterable<? extends JavaFileObject> sources = fm.getJavaFileObjects(
				new File("example/root/module_a/").listFiles((dir, name) -> { return name.endsWith(".java"); }));
		CompilationTask task = javac.getTask(null, myFm, null, Arrays.asList("-cp", "example", "-d", "target"), null, sources);
		task.call();
		
		fm.close();
	}
}
