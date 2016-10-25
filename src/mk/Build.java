package mk;

import static mk.Filetype.filetype;
import static mk.Production.is;

public interface Build {

	Filetype
		__    = Filetype.VOID,
		_java = filetype("java"),
		_jar  = filetype("jar").build(),
		_class= filetype("class").build();
	
	Folder
		target = new Folder();
	
	Production
		javac = is(_class).to(_java),
		jar   = is(_jar).to(_class);
	
}
