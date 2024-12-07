# Facade design pattern

The facade design pattern simplifies the interface to a complex subsystem, because it is
usually composed of all the classes which make up the subsystems of the complex
system

A facade shields the user from the complex details of the system and provides them with
the simplified view of it which is easy to use. It also decouples the code uses the system
from the details of the subsystem, making it easier to modify the system later.

## Participants
The classes and objects participating in this pattern include:

### a) Facade (HomeTheatreFacade)
    1. It knows which subsystem classes are responsible for the request,
       as it holds the reference to classes of the underlying subsystem-
       AudioSystem, DVDPlayer, PlayStation.
    2. It delegates client requests to appropriate subsystem objects by using makeActions
       method.

### b) Subsystems classes (AudioSystem, DVDPlayer, PlayStation)
    1. Implement subsystem functionality using operate method.
    2. Handle work assigned by the Facade object.
    3. They have no knowledge of the facade and keep no reference to it.
            