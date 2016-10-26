package mk;

import static mk.FileSelector.allOf;
import static mk.FileSelector.file;
import static mk.Filetype.filetype;
import static mk.Folder.folder;
import static mk.Production.is;
import static mk.Unit.unit;
import mk.gen.Javac;

/**
 * A example make file. All make file are named <code>mk</code>. 
 */
public interface mk extends mk_constants {

	
	Unit
		my_domain        = unit("my","domain"),
		my_domain_engine = my_domain.part("engine"),
		my_domain_web    = my_domain.part("web"),
		my_domain_db     = my_domain.part("db");
	
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
		jar 			= core.flattened().as(_jar).in(target),
		
		_default_       = compile;
	
		
	// how to wire the case where e.g. a jar should be downloaded by a production that uses a list of URLs in a file as input?
}
