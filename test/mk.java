

import static mk.FileSelector.allOf;
import static mk.FileSelector.file;
import static mk.Folder.folder;
import static mk.Production.is;
import static mk.Component.at;
import mk.gen.Javac;
import mk.*;

/**
 * A example make file. All make file are named <code>mk</code>. 
 */
public interface mk extends mk_build {

	FileSelector sources = allOf(_java);

	Module 
		main   = sources.in(folder("src/main")),
		test   = sources.in(folder("src/test")).includes(main);
	
	Component
		engine = at("my.domain.engine"),
		web    = at("my.domain.web").includes(engine),
		db     = at("my.domain.db").includes(engine).includes(file("libs/driver", _jar));	
	
	Production 
		javac = is(_java).to(_class).by(new Javac().source(8).target(6)),
		wget  = is(_dep).to(_jar);
	
	Goal
		compile 		= main.mirrored().as(_class).in(target),
		compile_test 	= test.mirrored().as(_class).in(target),
		compile_all 	= compile.and(compile_test),
		jar 			= main.flattened().as(_jar).in(target),
		
		_default_       = compile;
	
}
