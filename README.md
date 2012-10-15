BAPA Z3 integration
===================

A plugin for the [Z3 SMT solver](http://z3.codeplex.com). The plugin is written
in Scala, and relies on [ScalaZ3](https://github.com/psuter/ScalaZ3) to work.
It naturally also relies on Z3. Precompiled versions --for 32bit Linux
systems-- of both are included in this repository. Please note that Z3 comes
with its own license.

The underlying algorithm of the BAPA theory plugin is described in:

    Philippe Suter, Robin Steiger, and Viktor Kuncak. Sets with Cardinality
    Constraints in Satisfiability Modulo Theories. VMCAI 2011, pp. 403-418.

The paper is available from
[Springer](http://dx.doi.org/10.1007/978-3-642-18275-4_28) or from the first
author's [home page](http://lara.epfl.ch/~psuter/).
