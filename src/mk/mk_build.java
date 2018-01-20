package mk;

import static mk.Filetype.filetype;
import static mk.Folder.folder;

public interface mk_build {

	Filetype
		__    = Filetype.VOID,
		_java = filetype("java"),
		_dep  = filetype("dep"),
		_jar  = filetype("jar").build(),
		_class= filetype("class").build(),
		_html = filetype("html").build();
	
	Folder
		target = folder("target");
	
	Production
		javac = _class.to(_java),
		jar   = _jar.to(_class);
	
	
	// GOALS (where is what; or how does it look when the goal is reached)
	// {creation} <name>: <module> [flattened | mirrored] as <filetype> [in <target-folder>]
	// {deletion} <name>: ???
	// clean: no files in output folders
	// compile: core mirrored as class files in target folder
	// compile-test: test mirrored as class files in target folder
	// jar: core flattened as app.jar in dest folder
	// compile-all: does compile, compile-test
	
	// source is a module, target a folder or a module
	
	// PRODUCTIONS (how to make X from Y)
	// a specific production states all the specifics of the generation like source and target versions etc. 
	// javac: makes class 1.6 from java 1.6 on source change (timestamp is newer than target)
	// jar: makes jar from class
	
	// MODULES state dependencies or structures within the source
	// core: files below core folder 
	//       and depends on library X
	// test: files below test folder 
	//       and depends on core
	// engine: files below engine package
	// web: files below web package
	//      and depends on engine
	// db: files below db package
	//     and depends on engine and library Y
	
	// TASKS (are work steps towards a goal)
	// this is just a intermediate description what should be done in order to reach the goal(s); 
	// a task sequence is a plan to get there
	
	// abstract concepts
	// 1. output folders: builds know what folder are pure output folder (no non-generated files)
	// 2. existence state: each state of a goal either state something that should exist or something that should not exist
	// 3. file selectors: what files should exist or not is given declaratively, a virtual file set that depends on the actual sources it refers to
	// 4. a goal is also just a state to reach (can be a state of another goal)
	// 5. goal states are a set (not ordered in the sequence to do; sequence is derived from module and production dependencies)
	// 6. a file selector has a type: source root, target root, package
	// 7. the 3 core concepts goals, productions and modules state facts about the environment (not what to do; actions) they have no order but interdependencies
	// 8. productions have source-target relation: 1:1 or many to one (used when checking need to redo via file timestamps) 
	
	// BUILD SEQUENCE
	// 1. goal identifies list of states to reach
	// 2. 
}
