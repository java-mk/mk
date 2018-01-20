

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
		compilation = _java.to(_class).by(new Javac().source(8).target(6)),
		downloading = _dep.to(_jar).by(null),
		packaging   = _class.to(_jar).by(null);
	
	Component
		engine = compilation.at("my.domain.engine"),
		web    = compilation.at("my.domain.web").requires(engine),
		db     = compilation.at("my.domain.db").requires(engine).requires(file("libs/driver", _jar));	
	
	Goal
		compile      = main.mirrored().as(_class).in(target.X("classes")),
		compile_test = test.mirrored().as(_class).in(target.X("classes")),
		compile_all  = compile.and(compile_test),
		jar          = main.flattened().as(_jar).in(target),
		javadoc      = main.mirrored().as(_html).in(target.X("javadoc")),
		ready_to_run = compile.and(jar),
		_default_    = compile;
}
