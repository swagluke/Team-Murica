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

Our project design involves using ASM to read java class code. Then multiple vistor patterns will collect and organize useful class information into the right format for Dot. There are total five vistor pattern such as ClassDeclarationVisitor that covers class declaration, ClassFieldVisitor covers field, ClassMethodVisitor covers method. And we use classrecord and methodrecord to store useful information. Dot will use the data to generate the UML diagram.

# Usage Instructions


# Work Distribution

Milestone 1:

README: Lujia Zhang

Implementation: Primary Lucas Miller, Lujia Zhang and Philip Ross helped out.

UML Diagram: 

Design: 

UML comparison testing: 

Automated tests: Philip Ross
