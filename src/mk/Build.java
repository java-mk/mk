package mk;

import static mk.Filetype.filetype;
import static mk.Filetype.Origin.MAKE;
import static mk.Filetype.Origin.SOURCE;
import static mk.Production.makes;

public interface Build {

	Filetype
		$     = Filetype.$,
		$java = filetype("java", SOURCE),
		$jar  = filetype("jar", MAKE),
		$class= filetype("class", MAKE);
	
	FileSelector
		files = FileSelector.ALL;
	
	Folder
		target = new Folder();
	
	Production
		javac = makes($class).from($java),
		jar   = makes($jar).from($class);
	
}
