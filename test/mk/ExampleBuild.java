package mk;

import static mk.FileSelector.allOf;
import static mk.FileSelector.file;
import static mk.Filetype.filetype;
import static mk.Folder.folder;
import static mk.Production.is;
import static mk.Unit.unit;
import mk.gen.Javac;

public interface ExampleBuild extends Build {

	
	Unit
		my_domain_engine = unit("my.domain.engine"),
		my_domain_web    = unit("my.domain.web"),
		my_domain_db     = unit("my.domain.db");
	
	FileSelector sources = allOf(_java);
	Module 
		core   = sources.in(folder("src/core")),
		test   = sources.in(folder("src/test")),
		engine = sources.in(my_domain_engine),
		web    = sources.in(my_domain_web)
						.uses(engine),
		db     = sources.in(my_domain_db)
						.uses(engine)
						.uses(file("libs/driver", _jar));
	
	Production 
		javac = is(_java).to(_class).by(new Javac().source(8).target(6)),
		wget  = is(filetype(".dep")).to(_jar);
	
	Goal
		compile 		= core.mirrored().as(_class).in(target),
		compile_test 	= test.mirrored().as(_class).in(target),
		compile_all 	= compile.and(compile_test),
		jar 			= core.flattened().as(_jar).in(target);
		
	// how to wire the case where e.g. a jar should be downloaded by a production that uses a list of URLs in a file as input?
}
