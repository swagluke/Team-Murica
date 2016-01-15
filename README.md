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
5. Related Sequence Graph and UML Diagram will show up inside the project folder.


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

UML Diagram: Lujia Zhang

Design: All three

UML comparison testing: Lujia Zhang

Automated tests: Philip Ross