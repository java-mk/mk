
                _    
               | |   
      _ __ ___ | | __
     | '_ ` _ \| |/ /
     | | | | | |   < 
     |_| |_| |_|_|\_\
                 

_A (hopefully) less insane build tool._

* declared in pure java (no XML)
* states facts (we want to be true) not steps (to do)
* modular builds, no multi-project insanity
* is about building not downloading the internet

### Setup
`mk` is not _installed_. 
Download it and check it in as part of a project's sources.
Don't worry: it is a small, stand-alone program.

Create this structure: 

    <project-home>
    |- mk                      (downloaded run script)
    |- mk.java                 (your project's build file)
    L- .mk/classpath/          (root for running build)
      |- mk.jar                (downloaded jar)
      |- <extension>.jar       (put code for own processos here) 
      L- <extension>.class     (put code for own processos here)
        
The run script simply runs main class in `mk.jar` that reads `mk.java` in the script location to understand and achieve the specified goals.

### Usage
```bash
mk <options> <gaols>
```
