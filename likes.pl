%% Demo coming from http://clwww.essex.ac.uk/course/LG519/2-facts/index_18.html
%%
%% Please load this file into SWI-Prolog
%%
%% Sam's likes and dislikes in food
%%
%% Considering the following will give some practice
%% in thinking about backtracking.
%%
%% You can also run this demo online at
%% http://swish.swi-prolog.org/?code=https://github.com/SWI-Prolog/swipl-devel/raw/master/demo/likes.pl&q=likes(sam,Food).

/** <examples>
?- likes(sam,dahl).
?- likes(sam,chop_suey).
?- likes(sam,pizza).
?- likes(sam,chips).
?- likes(sam,curry).
*/

nonnegative(Var):- Var > 0.

nonzero(Var) :- Var \= 0.

likes(sam,Food) :-
        indian(Food),
        mild(Food).
likes(sam,Food) :-
        chinese(Food).
likes(sam,Food) :-
        italian(Food).
likes(sam,Food) :-
	spanish(Food).
likes(sam,chips).

spanish(chicken_chillie).

indian(curry).
indian(dahl).
indian(tandoori).
indian(kurma).

mild(dahl).
mild(tandoori).
mild(kurma).

chinese(chow_mein).
chinese(chop_suey).
chinese(sweet_and_sour).

italian(pizza).
italian(spaghetti).
