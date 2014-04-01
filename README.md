GraphIsomorphism
================

Given two graphs, G=(V,E) and G'=(V',E'), one problem is to determine whether there exists an isomorphism (a bijective
function) f between V and V' such that if nodes v and u in G have an edge, then f(v) and f(u) have an edge in G'.

Code presented here explores a new approach to solving the above problem largely by creating spanning trees using
Breadth First Search from each of the nodes in G and G'.

This work is part of my M.S. thesis which could be found here: http://filer.case.edu/axi48/thesis/

The paper also explains the experimental running times on random pairs of graphs.
