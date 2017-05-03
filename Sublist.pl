sublist(S,M,N,[_A|B]):- 
	M>0, 
	M<N, 
	sublist(S,M-1,N-1,B).
	
sublist(S,M,N,[_A|B]):- 
	M>0, 
	M=N,
	sublist(S,0,0,[B]).
	
sublist(S,M,N,[A|B]):- 
	0 is M, 
	M<N, 
	N2 is N-1, 
	S=[A|D], 
	sublist(D,0,N2,B).
	
sublist([],0,0,_).

checkPivotValid(Pivotindex,List):-
	Pindex is Pivotindex,
	Pindexx is Pivotindex+1,
	sublist(S,0,Pindex,List),
	nth0(Pivotindex,List,Pivotelement),
	check_prelist_util(S,Pivotelement),
	countElements(List,Count),
	sublist(N,Pindexx,Count,List),
	check_postlist_util(N,Pivotelement).
	
countElements([],0).
	
countElements([_|Xs],Count):-
	countElements(Xs,Count1),
	Count is Count1+1.

check_prelist_util([H|T], N) :-
    H =< N,
    check_prelist_util(T, N).
	
check_prelist_util([H|[]], N) :-
    H =< N.
check_prelist_util([], N).
	
check_postlist_util([H|T], N) :-
    H >= N,
    check_postlist_util(T, N).
	
check_postlist_util([H|[]], N) :-
    H >= N.
check_postlist_util([], N).