

import static mk.FileSelector.allOf;
import static mk.FileSelector.file;
import static mk.Folder.folder;
import mk.gen.Javac;
import mk.*;

/**
 * A example make file. All make file are named <code>mk</code>. 
 */
public interface mk extends mk_build {

	FileSelector sources = allOf(_java);

	Module 
		main   = sources.in(folder("src/main")),
		test   = sources.in(folder("src/test")).requires(main);
	
	Production 
		javac  = _java.to(_class).by(new Javac().source(8).target(6)),
		wget   = _dep.to(_jar);
	
	Component
		engine = javac.at("my.domain.engine"),
		web    = javac.at("my.domain.web").requires(engine),
		db     = javac.at("my.domain.db").requires(engine).requires(file("libs/driver", _jar));	
	
	Goal
		compile      = main.mirrored().as(_class).in(target),
		compile_test = test.mirrored().as(_class).in(target),
		compile_all  = compile.and(compile_test),
		jar          = main.flattened().as(_jar).in(target),
		_default_    = compile;
}
