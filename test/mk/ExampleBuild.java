package mk;

import static mk.FileSelector.file;
import static mk.Folder.folder;
import static mk.Goal.unmake;
import static mk.Production.makes;
import mk.gen.Javac;

public interface ExampleBuild extends Build {

	Module 
		core = files.inSource(folder("src/core")),
		test = files.inSource(folder("src/test")),
		// logical modules within the application use inPackage
		engine = files.inPackage("my.domain.engine"),
		web    = files.inPackage("my.domain.web")
						.dependsOn(engine),
		db     = files.inPackage("my.domain.db")
						.dependsOn(engine)
						.dependsOn(file("libs/driver.jar"))
		;
	
	Production 
		javac = makes($class).from($java).using(new Javac().source(8).target(6)); 
	
	Goal
		compile 		= core.mirrored().as($class).in(target),
		compile_test 	= test.mirrored().as($class).in(target),
		compile_all 	= compile.and(compile_test),
		jar 			= core.flattened().as($jar).in(target),
		clean 			= unmake(files).in(target);
						// undos <goal>, <goal>, ...
		;
		
}
