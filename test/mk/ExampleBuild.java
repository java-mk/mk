package mk;

import static mk.Folder.folder;
import static mk.Goal.unmake;

public interface ExampleBuild extends Build {

	Module 
		core = allFiles.within(folder("src/core")),
		test = allFiles.within(folder("src/test"));
		;
	
	Goal
		compile 		= core.mirrored().as($class).in(target),
		compile_test 	= test.mirrored().as($class).in(target),
		compile_all 	= compile.and(compile_test),
		jar 			= core.flattened().as($jar).in(target),
		clean 			= unmake(allFiles).in(target);
						// undos <goal>, <goal>, ...
		;
		
}
