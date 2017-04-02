factorial(0,1).
factorial(A,B):-
	A > 0,
	C is A-1,
	factorial(C,D),
	B is A*D.

conflict(Coloring):-
	adjacent(X,Y),
	color(X,Color,Coloring),
	color(Y,Color,Coloring).

adjacent(1,2).         adjacent(2,1).
adjacent(1,3).         adjacent(3,1).
adjacent(1,4).         adjacent(4,1).
adjacent(1,5).         adjacent(5,1).
adjacent(2,3).         adjacent(3,2).
adjacent(2,4).         adjacent(4,2).
adjacent(3,4).         adjacent(4,3).
adjacent(4,5).         adjacent(5,4).


color(1,red,a).    color(1,red,b).
color(2,blue,a).   color(2,blue,b).
color(3,green,a).  color(3,green,b).
color(4,yellow,a). color(4,blue,b).
color(5,blue,a).   color(5,green,b).

greater(Var):-
	Var > 0,
	true.
	
nonnegative(Var):- Var >= 0.

nonzero(Var) :- Var \= 0.

ordered([X]).    
ordered([Head|[Head1|Tail]]) :-
	Head =< Head1,
    ordered([Head1|Tail]).
