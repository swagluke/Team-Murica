# Team-Murica
Git repository for CSSE374 project

Team: Team-Murica

Members:

        Lujia Zhang(Luke) (Github: swagluke)
        Lucas Miller      (Github: Jernik)
        Philip Ross       (Github: baseballlover723)

# Description/Design

ASM - ASM is an all purpose Java bytecode manipulation and analysis framework. It can be used to modify existing classes or dynamically generate classes, directly in binary form. 

Dot (Graphviz) - Graphviz is open source graph visualization software. Graph visualization is a way of representing structural information as diagrams of abstract graphs and networks. It has important applications in networking, bioinformatics,  software engineering, database and web design, machine learning, and in visual interfaces for other technical domains. 

SDE: Quick Sequence Diagram Editor is a tool for creating UML sequence diagrams from textual descriptions of objects and messages that follow a very easy syntax.

Our project design involves using ASM, to read java class code. Then multiple vistor patterns will collect and organize useful class information into the right format for Dot and SDE. There are total seven vistor pattern such as ClassDeclarationVisitor that covers class declaration, ClassFieldVisitor covers field, ClassMethodVisitor covers method. And we use classrecord and methodrecord to store useful information. Dot will use the data to generate the UML diagram. SDE creates sequence diagram.

# Usage Instructions
This tool is use for generating the most essential elements of the UML class diagram for arbitrary Java code: classes, methods, instance variables, and inheritance/implements arrows.

Please follow the instruction below.

1. Download the GraphViz.zip file from <http://www.graphviz.org/Download.php>, then extract into folder.
2. Add the path of this folder to your PATH system variable
3. Folk this repository and import the project into Eclipse.
4. Change the run configuration.
5. Related UML Diagram will show up inside the project folder.
6. Download SDEdit from http://iweb.dl.sourceforge.net/project/sdedit/sdedit/4.0/sdedit-4.01.jar
7. Place the downloaded .jar in the same folder as this README.md file
8. Change the run configuration, follow the format as classpath of the root class for your sequence diagram as the first argument, the method signature as the second argument, and the depth as the third (can be omitted - defaults to 5).


# Work Distribution

Milestone 1:

README: Lujia Zhang

Implementation: Primary Lucas Miller, Lujia Zhang helped out.

UML Diagram: Lujia Zhang

Design: All three

UML comparison testing: Lujia Zhang

Automated tests: Philip Ross

Milestone 2:

README: Lujia Zhang

Implementation: Primary Lucas Miller, Lujia Zhang and Philip Ross helped out.

UML Diagram: Lujia Zhang

Design: All three

UML comparison testing: Lujia Zhang

Automated tests: Philip Ross

Milestone 3:

README: Lujia Zhang

Implementation: Primary Lucas Miller, Lujia Zhang and Philip Ross helped out.

Design: All three

Sequence Diagram Manual: Lujia Zhang

Automated tests: Philip Ross

Milestone 4:

README: Lujia Zhang

Implementation: Primary Lucas Miller, Lujia Zhang and Philip Ross helped out.

Singletons Manual: Lujia Zhang

Design: All three

Automated tests: Philip Ross

Milestone 5:

README: Lujia Zhang

Implementation: Primary Lucas Miller, Lujia Zhang and Philip Ross helped out.

Adapter and Decorator patterns Manual: Lujia Zhang

Design: All three

Automated tests: Philip Ross

Milestone 6:

README: Philip Ross

Implementation: Primary Lucas Miller, Philip Ross helped out.

Composite patterns Manual: Philip Ross

Design: Lucas Miller and Philip Ross

Automated tests: Philip Ross

Milestone 7:

README: Luke Miller

Implementation: Primary Philip Ross, Luke Miller helped out.

Design: Lucas Miller and Philip Ross

Our design changed slightly because we had to add processing phases to the core logic, and add a few getter methods to it, but our core logic did not change much due to the addition of the GUI

The config file format is a series of key-value pairs comprising of Output-Directory, Phases, Input-Folder, Dot-Path, Input-Classes, Builder-Classes. An example of this is: 
Output-Directory=.
Phases=Load, PatternDetection, GenerateUML, Print
Input-Folder=src
Dot-Path=C\:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe
Input-Classes=
Builder-Classes=ExtensionBuilder, ImplementsBuilder, AssociationBuilder, DecoratorBuilder, AdapterBuilder, SingletonBuilder, UsesBuilder, CompositeBuilder

Users can add any phases that implement IPhase, and any builders that implement IBuilder. If they wish to create a pattern detector, they must extend the abstract class AbstractBuilderDetector